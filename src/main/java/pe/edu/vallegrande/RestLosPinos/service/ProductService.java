package pe.edu.vallegrande.RestLosPinos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.RestLosPinos.model.Product;
import pe.edu.vallegrande.RestLosPinos.model.Category;
import pe.edu.vallegrande.RestLosPinos.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product, Category category) {
        try {
            log.info("Guardando producto: {}", product.getName());
            product.setCategory(category);
            product.setCreatedAt(LocalDateTime.now());
            return productRepository.save(product);
        } catch (Exception e) {
            log.error("Error al guardar el producto: {}", e.getMessage());
            throw new RuntimeException("Error al guardar el producto");
        }
    }

    public List<Product> getAllProducts() {
        try {
            log.info("Obteniendo todos los productos");
            return productRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener productos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener los productos");
        }
    }

    public Product getProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (!product.isPresent()) {
                log.error("Producto no encontrado con ID: {}", id);
                throw new RuntimeException("Producto no encontrado");
            }
            log.info("Producto encontrado: {}", product.get().getName());
            return product.get();
        } catch (Exception e) {
            log.error("Error al obtener el producto con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al obtener el producto");
        }
    }

    public Product updateProduct(Long id, Product product, Category category) {
        try {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setEstado(product.getEstado());
            existingProduct.setImage(product.getImage());
            existingProduct.setCategory(category);

            log.info("Producto actualizado con ID: {}", id);
            return productRepository.save(existingProduct);
        } catch (Exception e) {
            log.error("Error al actualizar el producto con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al actualizar el producto");
        }
    }

    public void deleteProduct(Long id) {
        try {
            Product existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            existingProduct.setEstado("I");

            productRepository.save(existingProduct);
            log.info("Producto marcado como inactivo con ID: {}", id);
        } catch (Exception e) {
            log.error("Error al eliminar el producto con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al eliminar el producto");
        }
    }

    public void deletePhysically(Long id) {
        try {
            productRepository.deleteById(id);
            log.info("Producto eliminado físicamente con ID: {}", id);
        } catch (Exception e) {
            log.error("Error al eliminar físicamente el producto con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al eliminar el producto físicamente");
        }
    }
}
