// Importaciones necesarias
package com.ucc.crudservice.service;
import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Anotación para indicar que esta clase es un servicio de Spring
@Service
// Lombok genera un constructor con los argumentos requeridos automáticamente
@RequiredArgsConstructor
public class ProductService {
    // Inyección de dependencia del repositorio de productos
    private final ProductRepository productRepository;

    // Método para obtener todos los productos
    public List<Product> getProducts() {
        return productRepository.findAll(); // Utiliza el método findAll() proporcionado por el repositorio para obtener todos los productos
    }

    // Método para crear un nuevo producto
    public ResponseEntity<Object> addProduct(Product product) {
        productRepository.save(product); // Guarda el nuevo producto utilizando el método save() proporcionado por el repositorio
        return new ResponseEntity<>("producto creado", HttpStatus.OK); // Devuelve una respuesta indicando que el producto ha sido creado
    }

    // Método para borrar un producto existente
    public ResponseEntity<Object> deleteProduct(Long id) {
        productRepository.deleteById(id); // Elimina el producto con el ID especificado utilizando el método deleteById() proporcionado por el repositorio
        return new ResponseEntity<>("producto borrado", HttpStatus.OK); // Devuelve una respuesta indicando que el producto ha sido borrado
    }

    // Método para modificar un producto existente
    public ResponseEntity<Object> modifyProduct(Long id, Product product) {
        // Busca el producto en la base de datos por su ID
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isPresent()){ // Si el producto existe
            Product newproduct = productOptional.get(); // Obtiene el producto de la base de datos
            // Actualiza los campos del producto con los valores proporcionados
            newproduct.setSku(product.getSku());
            newproduct.setName(product.getName());
            newproduct.setDescription(product.getDescription());
            newproduct.setPrice(product.getPrice());
            newproduct.setStatus(product.getStatus());
            productRepository.save(newproduct); // Guarda el producto modificado
            return new ResponseEntity<>("producto modificado", HttpStatus.OK); // Devuelve una respuesta indicando que el producto ha sido modificado
        } else {
            return new ResponseEntity<>("producto no encontrado mediante el id ", HttpStatus.NOT_FOUND); // Si el producto no existe, devuelve una respuesta indicando que el producto no fue encontrado
        }
    }
}