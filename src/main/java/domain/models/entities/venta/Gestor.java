package domain.models.entities.venta;

import domain.models.Persona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gestores")
@Setter
@Getter
public class Gestor extends Persona {

}
