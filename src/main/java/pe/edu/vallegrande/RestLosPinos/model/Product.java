package pe.edu.vallegrande.RestLosPinos.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del producto

    @Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres")
    private String name;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    private String description;

    private Double price;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    private String estado;  // El estado del producto ('A' = Activo, 'I' = Inactivo)

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;  // Relación con la categoría

    private String image;  // Ruta de la imagen del producto

    // Constructor adicional que asigna manualmente la fecha de creación
    public Product(String name, String description, Double price, String estado, Category category, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.estado = estado;
        this.category = category;
        this.image = image;
        this.createdAt = LocalDateTime.now();  // Asignar la fecha de creación manualmente
    }

    // Método para obtener el ID de la categoría
    public Long getCategoryId() {
        return this.category != null ? this.category.getId() : null;
    }
}
