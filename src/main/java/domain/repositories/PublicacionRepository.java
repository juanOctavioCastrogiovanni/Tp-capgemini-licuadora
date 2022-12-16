package domain.repositories;

import domain.models.entities.venta.Publicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;


@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {


    Page<Publicacion> findByCategoria(String categoria, Pageable pagina);

    Page<Publicacion> findByVendedor(String vendedor, Pageable pagina);

    Page<Publicacion> findByCategoriaAndVendedor(String categoria, String vendedor, Pageable pagina);

    Page<Publicacion> findByNombreContaining(String busqueda, Pageable pagina);

}
