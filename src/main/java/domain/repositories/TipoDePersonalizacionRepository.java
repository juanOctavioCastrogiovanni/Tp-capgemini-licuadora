package domain.repositories;

import domain.models.entities.producto.TipoDePersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDePersonalizacionRepository extends JpaRepository<TipoDePersonalizacion, Long> {

}
