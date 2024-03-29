package domain.repositories;

import domain.models.entities.producto.AreaDePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<AreaDePersonalizacion, Integer> {

}
