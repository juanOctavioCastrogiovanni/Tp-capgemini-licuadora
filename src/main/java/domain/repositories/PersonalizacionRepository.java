package domain.repositories;

import domain.models.entities.producto.Personalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizacionRepository extends JpaRepository<Personalizacion,Integer> {
}
