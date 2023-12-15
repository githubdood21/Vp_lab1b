package mk.finki.ukim.mk.lab.Model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorFullname implements Serializable {
    String name;
    String surname;
}
