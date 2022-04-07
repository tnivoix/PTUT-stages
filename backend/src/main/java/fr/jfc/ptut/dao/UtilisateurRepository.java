package fr.jfc.ptut.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.jfc.ptut.entity.Stage;
import fr.jfc.ptut.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

    default List<Utilisateur> findAllByRole(String role) {
        List<Utilisateur> users = findAll();
        List<Utilisateur> usersByRole = new ArrayList<>();
        for (Utilisateur u : users) {
            if(u.getRole().getNom().equals(role)){
                usersByRole.add(u);
            }
        }
        return usersByRole;
    }

    default List<Stage> intershipsByUser(int idUser) {
        return findById(idUser).get().getStages();
    }

    default List<Stage> intershipsByStudent(int idStudent) {
        if (findById(idStudent).get().getRole().getNom().equals("Étudiant")) {
            return intershipsByUser(idStudent);
        } else {
            throw new IllegalArgumentException("Cet utilisateur n'est pas un étudiant");
        }
    }

    default List<Stage> intershipsByTutor(int idTutor) {
        if (findById(idTutor).get().getRole().getNom().equals("Tuteur")) {
            return intershipsByUser(idTutor);
        } else {
            throw new IllegalArgumentException("Cet utilisateur n'est pas un tuteur");
        }
    }
}
