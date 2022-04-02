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
public class Utilisateur {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String identifiant;

    @NonNull
    private String motDePasse;

    @NonNull
    private String prenom;
    
    @NonNull
    private String nom;

    @Column(unique=true)
    @NonNull
    private String numTel;

    @Column(unique=true)
    @NonNull
    private String email;

    @NonNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "utilisateurs" })
    private Role role;

    @ManyToMany(mappedBy = "utilisateurs")
    @JsonIgnoreProperties({ "utilisateurs" })
    private List<Stage> stages = new ArrayList<>();
}