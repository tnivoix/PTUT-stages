package fr.jfc.ptut.entity;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity // Une entité JPA
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private Integer anneeEtude;

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
    private String details;

    @NonNull
    private String activites;

    @NonNull
    private String competences;

    @NonNull
    private Date dateDebut;

    @NonNull
    private Integer duree;

    @NonNull
    private Float remuneration;

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

    @ManyToMany
    @JoinTable(name = "stage_utilisateur", joinColumns = @JoinColumn(name = "stage_id"), inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    @ToString.Exclude
    @JsonIgnoreProperties({ "stages" })
    private List<Utilisateur> utilisateurs = new ArrayList<>();

    @NonNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "stages" })
    private EtatStage etatStage;

    @NonNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "stages" })
    private Entreprise entreprise;
}
