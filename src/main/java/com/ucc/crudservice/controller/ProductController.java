// Importaciones necesarias
package com.ucc.crudservice.controller;
import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

// Anotación para indicar que esta clase es un controlador de Spring MVC
@RestController
// Mapeo de la URL base para todas las solicitudes a este controlador
@RequestMapping("api/products")
// Lombok genera automáticamente un constructor con los argumentos requeridos
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService; // Inyección de dependencia del servicio de productos

    // Método para obtener todos los productos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts() {
        return this.productService.getProducts(); // Llama al método del servicio para obtener todos los productos
    }

    // Método para crear un nuevo producto
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    // El objeto Product se valida usando las anotaciones de validación definidas en la clase Product
    public ResponseEntity<Object> newProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // Verifica si hay errores de validación
            // Obtiene los mensajes de error y los convierte en una lista de cadenas
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            // Devuelve una respuesta de error con la lista de errores
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        // Llama al método del servicio para crear un nuevo producto
        return this.productService.addProduct(product);
    }

    // Método para borrar un producto existente
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    // El ID del producto a borrar se obtiene de la URL
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        // Llama al método del servicio para eliminar el producto con el ID especificado
        return this.productService.deleteProduct(id);
    }

    // Método para modificar un producto existente
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    // El ID del producto a modificar se obtiene de la URL
    public ResponseEntity<Object> modifyProduct(@Valid @PathVariable("id") Long id, @RequestBody Product product, BindingResult bindingResult) {
        // Llama al método del servicio para modificar el producto con el ID especificado
        return this.productService.modifyProduct(id, product);
    }
}