package fr.jfc.ptut.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Entreprise {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String nom;

    @Column(unique=true)
    @NonNull
    private String numTel;

    @Column(unique=true)
    @NonNull
    private String email;

    @NonNull
    private String secteurActivite;

    @OneToMany(mappedBy="entreprise")
    @ToString.Exclude
    @JsonIgnoreProperties({ "entreprise" })
    private List<Stage> stages = new ArrayList<>();
}
