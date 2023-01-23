package domain.controllers;

import domain.repositories.CarritoRepository;
import domain.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemRepository repoItem;

    @Autowired
    private CarritoRepository repoCarrito;

    @CrossOrigin(origins = "https://capgemini-tp-licuadora.web.app")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarItem(@PathVariable(name = "id") Integer id){
        if (!repoItem.existsById(id)){
            return new ResponseEntity<>("No existe el item", HttpStatus.NOT_FOUND);
        }
        repoItem.deleteById(id);
        return ResponseEntity.ok("Item eliminado");
    }

}
