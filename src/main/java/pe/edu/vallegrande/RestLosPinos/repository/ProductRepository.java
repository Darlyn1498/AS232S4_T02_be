package pe.edu.vallegrande.RestLosPinos.repository;

import pe.edu.vallegrande.RestLosPinos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
//import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    // Metodo para encontrar todos los productos no eliminados (activos)
    //List<Product> findByDeletedFalse();

    // Metodo para encontrar todos los productos eliminados lógicamente
    //List<Product> findByDeletedTrue();

    // Metodo para encontrar productos según su estado de eliminación lógica (activo o eliminado)
    //List<Product> findByDeleted(Boolean deleted);

    List<Product> findByCategoryId(Long categoryId);
}
