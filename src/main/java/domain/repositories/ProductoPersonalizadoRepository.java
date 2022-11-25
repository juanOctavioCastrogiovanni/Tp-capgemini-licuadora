package domain.repositories;

import domain.models.entities.producto.ProductoPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoPersonalizadoRepository extends JpaRepository <ProductoPersonalizado, Integer> {

}
