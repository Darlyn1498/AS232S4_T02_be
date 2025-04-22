package pe.edu.vallegrande.RestLosPinos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.vallegrande.RestLosPinos.model.Category;
import pe.edu.vallegrande.RestLosPinos.model.Product;
import pe.edu.vallegrande.RestLosPinos.repository.ProductRepository;
import pe.edu.vallegrande.RestLosPinos.service.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Esta es la anotación que habilita el soporte de Mockito en JUnit 5
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Postres Fitness", "A");
        product = new Product("Torta Proteica", "Deliciosa torta alta en proteínas", 25.50, "A", "image_url");
        product.setCategory(category);
    }

    @Test
    public void testSaveProduct() {
        // Arrange
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = productService.saveProduct(product, category);

        // Assert
        assertNotNull(savedProduct);
        verify(productRepository, times(1)).save(any(Product.class));  // Verifica que se haya llamado a save una vez
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product foundProduct = productService.getProductById(productId);

        // Assert
        assertNotNull(foundProduct);
        verify(productRepository, times(1)).findById(productId);  // Verifica que se haya llamado a findById una vez
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Product updatedProduct = new Product("Torta Proteica V2", "Torta mejorada", 30.50, "A", "image_url_v2");
        updatedProduct.setCategory(category);

        // Act
        Product result = productService.updateProduct(productId, updatedProduct, category);

        // Assert
        assertNotNull(result);
        verify(productRepository, times(1)).save(updatedProduct);  // Verifica que se haya llamado a save con el producto actualizado
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository, times(1)).save(product);  // Verifica que se haya llamado a save al actualizar el estado del producto
    }
}
