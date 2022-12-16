package domain.controllers;

import domain.models.entities.producto.AreaDePersonalizacion;
import domain.repositories.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping({"", "/"})
    public List<AreaDePersonalizacion> areas(){
        return areaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<AreaDePersonalizacion> area(@PathVariable(name = "id") Integer id){
        return areaRepository.findById(id);
    }

    @PostMapping({"", "/"})
    public AreaDePersonalizacion agregarArea(@RequestBody AreaDePersonalizacion area){
        area.setFechaCreacion(LocalDateTime.now());
        areaRepository.save(area);
        return area;
    }

    @PutMapping("/{id}")
    public AreaDePersonalizacion modificarArea(@PathVariable(name = "id") Integer id, @RequestBody AreaDePersonalizacion area){
        area.setId(id);
        area.setFechaModificacion(LocalDateTime.now());
        areaRepository.save(area);
        return area;
    }

    @DeleteMapping("/{id}")
    public void eliminarArea(@PathVariable(name = "id") Integer id){
        AreaDePersonalizacion areaAEliminar = areaRepository.findById(id).get();
        areaAEliminar.setFechaBaja(LocalDateTime.now());
        areaRepository.save(areaAEliminar);
    }


}
