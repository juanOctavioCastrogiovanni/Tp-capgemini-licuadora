package domain.repositories;

import domain.models.entities.productos.DTOProducto;
import domain.models.entities.productos.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Page<Producto> findAll(Pageable page);
    DTOProducto findByid(int id);
}
