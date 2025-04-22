package pe.edu.vallegrande.RestLosPinos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.vallegrande.RestLosPinos.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
    // Ejemplo:
    // List<Category> findByState(String state);
}
