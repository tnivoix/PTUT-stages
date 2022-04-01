package fr.jfc.ptut.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Stage {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NonNull
    private int anneeEtude;

    @NonNull
    private String adresse;

    @NonNull
    private String ville;

    @NonNull
    private String codePostal;

    @NonNull
    private String pays;

    @NonNull
    private String contexte;

    @NonNull
    private String sujet;

    @NonNull
    private String détails;

    @NonNull
    private String activites;

    @NonNull
    private String competences;

    @NonNull
    private Date dateDebut;

    @NonNull
    private int duree;

    @NonNull
    private float remuneration;

    @NonNull
    private String service;

    @NonNull
    private String horaires;

    private String commentaires;

    private Date soutenance;

    private String jury;

    @NonNull
    private String maitreDeStage;

    @NonNull
    private String fonction;

    @ManyToMany(optional = true)
    @JsonIgnoreProperties({ "stages" })
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "stages" })
    private EtatStage etatStage;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "stages" })
    private Entreprise entreprise;
}
