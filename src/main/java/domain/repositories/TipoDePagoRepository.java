package domain.repositories;

import domain.models.entities.venta.TipoDePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDePagoRepository extends JpaRepository<TipoDePago, Integer> {


}
