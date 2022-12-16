package domain.repositories;

import domain.models.entities.venta.ItemCarrito;
import domain.models.entities.venta.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemCarrito, Integer> {
}
