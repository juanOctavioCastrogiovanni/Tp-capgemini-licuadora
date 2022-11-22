package domain.models;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@MappedSuperclass
public abstract class PersistenceId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Integer id;


}
