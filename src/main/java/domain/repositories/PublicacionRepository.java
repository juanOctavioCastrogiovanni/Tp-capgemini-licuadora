package domain.repositories;

import domain.models.entities.venta.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {


}
