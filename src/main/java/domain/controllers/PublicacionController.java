package domain.controllers;

import domain.models.entities.venta.Publicacion;
import domain.repositories.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionRepository repoPublicaciones;

    @PersistenceContext
    private EntityManager em;


    @GetMapping({"/", ""})
    public Page<Publicacion> getPublicaciones(@RequestParam(name = "category", required = false) String categoria,
                                              @RequestParam(name = "seller", required = false) String vendedor,
                                              @RequestParam(name = "search", required = false) String busqueda,
                                              @PageableDefault(size=6) Pageable pagina) {

           if (categoria != null && vendedor != null) {
                return repoPublicaciones.findByCategoriaAndVendedor(categoria, vendedor, pagina);
           } else if (categoria != null) {
                return repoPublicaciones.findByCategoria(categoria, pagina);
           } else if (vendedor != null) {
                return repoPublicaciones.findByVendedor(vendedor, pagina);
           } else if (busqueda != null) {
                return repoPublicaciones.findByNombreContaining(busqueda, pagina);
           } else {
                return repoPublicaciones.findAll(pagina);
           }
    }
}
