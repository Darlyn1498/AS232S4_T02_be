package pe.edu.vallegrande.RestLosPinos.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único de la categoría

    private String name;  // Nombre de la categoría

    private String state;  // Estado de la categoría ('A' = Activa, 'I' = Inactiva)

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;  // Lista de productos asociados con esta categoría

    // Constructor con parámetros para crear una categoría fácilmente
    public Category(String name, String state) {
        this.name = name;
        this.state = state;
    }

    // Método para comprobar que el estado sea válido (opcional)
    public boolean isActive() {
        return "A".equals(this.state);  // Devuelve true si la categoría está activa
    }
}
