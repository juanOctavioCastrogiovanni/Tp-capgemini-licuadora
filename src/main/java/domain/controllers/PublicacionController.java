package domain.controllers;

import domain.models.entities.venta.Publicacion;
import domain.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionRepository repoPublicaciones;

    @PersistenceContext
    private EntityManager em;

    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping({"/", ""})
    public ResponseEntity<Page<Publicacion>> getPublicaciones(@RequestParam(name = "category", required = false) String categoria,
                                           @RequestParam(name = "seller", required = false) String vendedor,
                                           @RequestParam(name = "search", required = false) String busqueda,
                                           @PageableDefault(size=8) Pageable pagina) {


           if (categoria != null && vendedor != null) {
                return ResponseEntity.ok(repoPublicaciones.findByCategoriaAndVendedor(categoria, vendedor, pagina));
           } else if (categoria != null) {
                return ResponseEntity.ok(repoPublicaciones.findByCategoria(categoria, pagina));
           } else if (vendedor != null) {
                return ResponseEntity.ok(repoPublicaciones.findByVendedor(vendedor, pagina));
           } else if (busqueda != null) {
                return ResponseEntity.ok(repoPublicaciones.findByNombreContaining(busqueda, pagina));
           } else {
                   try{
                         return ResponseEntity.ok(repoPublicaciones.findAll(pagina));
                   } catch (Exception e) {
                         return ResponseEntity.badRequest().build();
                   }
           }


       /* String categoriaFiltro = "";
        String vendedorFiltro = "";
        String busquedaFiltro = "";
        String paginaFiltro = "";

        if (categoria!=null) {
            categoriaFiltro = "AND p.categoria = '" + categoria + "' ";
        }

        if (vendedor!=null) {
            vendedorFiltro = "AND p.vendedor = '" + vendedor + "' ";
        }

        if (busqueda!=null) {
            busquedaFiltro = "AND p.nombre LIKE '%" + busqueda + "%' ";
        }

        System.out.println("SELECT p FROM Publicacion p " + categoriaFiltro + vendedorFiltro + busquedaFiltro + paginaFiltro);

        List<Publicacion> consulta = em.createQuery("SELECT p FROM Publicacion p " + categoriaFiltro + vendedorFiltro + busquedaFiltro, Publicacion.class)
                .setFirstResult((int) pagina.getOffset())
                .setMaxResults(pagina.getPageSize())
                .getResultList();


        Page<Publicacion> page = new PageImpl<>(consulta);

        return page;

        */
    }

    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicacion(@PathVariable(name = "id") Integer id) {
        try{
            return ResponseEntity.ok(repoPublicaciones.findById(id).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se encontró la publicación");
        }
    }
}
