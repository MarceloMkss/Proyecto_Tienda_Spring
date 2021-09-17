package com.twt.curso.spring.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.twt.curso.spring.entity.Producto;
import com.twt.curso.spring.service.IUploadFileService;
import com.twt.curso.spring.service.ProductoService;

@CrossOrigin(origins = { "http://localhost:4200" }) // servidor angular con uso de CORS
@RestController
@RequestMapping("rest")
public class ProductoRESTController {

	@Autowired
	private ProductoService servicio;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("/productos")
	public List<Producto> all() {
		return servicio.getTodosProductos();
	}

	/**
	 * ResponseEntity<?> lo dejo con iterrogacion puede ser cualquier tipode objeto,
	 * ej: String o una coleccion
	 * 
	 * @param id
	 * @return retorna un responseEntity el contenido en el cuerpo de la respuesta
	 */
	@GetMapping("/productos/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {

		// puede que el producto venga null, por eso hago una instancia de Producto
		// igual a Nulo y gestiono los errores
		Producto producto = null;

		// creo una coleccion de errores map
		//

		/**
		 * @Map<String, Object> = este mapa de Java va a estar asociado a pares de
		 *              nombres con sus valores del tipo tipos object, un hashMap();
		 * 
		 */
		Map<String, Object> response = new HashMap<>();

		/**
		 * DataAccessException: podria ocurrir un error no solamente en el (id) si no
		 * tambien por el lado del servidor en base de datos.
		 * 
		 * Entonces utilizaré un Try Catch con DataAccessException.
		 */
		try {
			producto = servicio.getProductosPorId(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al realizar la consulta en la base de datos");

			// getMostSpecificCause causa mas especifica del error
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// compruebo si es Nulo
		if (producto == null) {
			response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos!")));

			// retormo el tipo Map<String, Object>, en el constructor pasó el response que
			// almacena el mensaje y el HttpStatus.NOT_FOUND que es un Error 404.

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}

	/**
	 * Crea Producto
	 * 
	 * @param producto
	 * @param result
	 * @return
	 */
	@PostMapping("/productos")
	public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {

		// creo un nuevo producto
		Producto productoNew = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			productoNew = servicio.saveProducto(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido creado con éxito!");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("productos/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Producto producto, @PathVariable Integer id) {

		Producto productoActual = servicio.getProductosPorId(id);
		Producto productoModificado = null;

		// MAP para los mensajes de errores
		Map<String, Object> response = new HashMap<>();

		// compruebo si es Nulo
		if (productoActual == null) {
			response.put("mensaje", "Error: no se pudo Editar, el producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));

			// retormo el tipo Map<String, Object>, en el constructor pasó el response que
			// almacena el mensaje y el HttpStatus.NOT_FOUND que es un Error 404.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		} // if

		try {

			productoModificado = servicio.saveProducto(productoActual);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al Actualizar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		} // catch

		response.put("mensaje", "El producto ha sido actualizado con éxito!");
		response.put("producto", productoModificado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}// Update

	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		Map<String, Object> response = new HashMap<>();

		Producto productoActual = servicio.getProductosPorId(id);
		
		if (productoActual == null) {
			response.put("mensaje", "Error: no se pudo Elimiar, el producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));

			// retormo el tipo Map<String, Object>, en el constructor pasó el response que
			// almacena el mensaje y el HttpStatus.NOT_FOUND que es un Error 404.
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			 servicio.borrar(id);		
			
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto ha sido eliminado con éxito!");
		

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/productos/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();

		Producto producto = servicio.getProductosPorId(id);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del producto");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

//			String nombreFotoAnterior = producto.getFoto();

//			uploadService.eliminar(nombreFotoAnterior);

//			producto.setFoto(nombreArchivo);

			servicio.saveProducto(producto);

			response.put("producto", producto);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
