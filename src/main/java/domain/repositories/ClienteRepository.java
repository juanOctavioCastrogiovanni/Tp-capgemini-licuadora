package domain.repositories;

import domain.models.entities.venta.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    boolean existsByEmail(String email);

    Cliente findByEmail(String email);
}
