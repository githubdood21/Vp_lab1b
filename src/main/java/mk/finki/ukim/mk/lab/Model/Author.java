package mk.finki.ukim.mk.lab.Model;

import lombok.Data;

@Data
public class Author {
    public Author(Long id, String name, String surname, String biography)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
    private Long id;
    private String name;
    private String surname;
    private String biography;
}
