package domain.controllers;

import domain.models.entities.producto.TipoDePersonalizacion;
import domain.repositories.TipoDePersonalizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    @Autowired
    private TipoDePersonalizacionRepository tipoRepository;

    @GetMapping({"", "/"})
    public List<TipoDePersonalizacion> tipos(){
        return tipoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TipoDePersonalizacion> tipo(@PathVariable(name = "id") Integer id){
        return tipoRepository.findById(id);
    }

    @PostMapping({"", "/"})
    public TipoDePersonalizacion agregarTipo(@RequestBody TipoDePersonalizacion tipo){
        tipo.setFechaCreacion(LocalDateTime.now());
        tipoRepository.save(tipo);
        return tipo;
    }

    @PutMapping("/{id}")
    public TipoDePersonalizacion modificarTipo(@PathVariable(name = "id") Integer id, @RequestBody TipoDePersonalizacion tipo){
        tipo.setId(id);
        tipoRepository.save(tipo);
        return tipo;
    }

}
