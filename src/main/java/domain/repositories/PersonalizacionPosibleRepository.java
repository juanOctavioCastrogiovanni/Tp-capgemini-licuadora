package domain.repositories;

import domain.models.entities.producto.PosiblePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacionPosibleRepository extends JpaRepository<PosiblePersonalizacion, Integer> {
}
