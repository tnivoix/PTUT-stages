package fr.jfc.ptut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

import fr.jfc.ptut.dao.*;
import fr.jfc.ptut.entity.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /rest (after Application path)
@Slf4j
public class RestController {

	@Autowired
	private EntrepriseRepository entrepriseDao;
	@Autowired
	private EtatStageRepository etatStageDao;
	@Autowired
	private RoleRepository roleDao;
	@Autowired
	private StageRepository stageDao;
	@Autowired
	private UtilisateurRepository utilisateurDao;

	/**
	 * Renvoie tous les stages
	 * @return tous les stages
	 */
	@GetMapping(path = "allInterships") 
	public @ResponseBody List<Stage> allInterships() {
		log.info("Renvoie la liste des stages");
		return stageDao.findAll();
	}

	/**
	 * Renvoie tous les stages validés et disponibles
	 * @return tous les stages dispos
	 */
	@GetMapping(path = "freeInterships") 
	public @ResponseBody List<Stage> freeInterships() {
		log.info("Renvoie la liste des stages dispos");
		return stageDao.findByIntershipState("Proposition validée");
	}

	/**
	 * Renvoie tous les stages terminés
	 * @return tous les stages terminés
	 */
	@GetMapping(path = "achievedInterships") 
	public @ResponseBody List<Stage> achievedInterships() {
		log.info("Renvoie la liste des stages terminés");
		return stageDao.findByIntershipState("Stage terminé");
	}

	/**
	 * Renvoie tous les stages en attente de validation
	 * @return tous les stages en attente
	 */
	@GetMapping(path = "pendingInterships") 
	public @ResponseBody List<Stage> pendingInterships() {
		log.info("Renvoie la liste des stages en attente de validation");
		return stageDao.findByIntershipState("Proposition en attente de validation");
	}

	/**
	 * Renvoie tous les stages en cours de processus
	 * @return tous les stages en cours
	 */
	@GetMapping(path = "inProgressInterships") 
	public @ResponseBody List<Stage> inProgressInterships() {
		log.info("Renvoie la liste des stages en cours de processus");
		return stageDao.inProgressInterships();
	}

	/**
	 * Renvoie tous les stages d'un étudiant
	 * @param idStudent identifiant de l'étudiant
	 * @return tous les stages de l'étudiant
	 */
	@GetMapping(path = "intershipsByStudent/{idStudent}")
	public @ResponseBody List<Stage> intershipsByStudent(@PathVariable int idStudent) {
		log.info("Renvoie la liste des stages de l'étudiant idStudent");
		return utilisateurDao.intershipsByStudent(idStudent);
	}

	/**
	 * Renvoie tous les stages d'un tuteur
	 * @param idTutor identifiant du tuteur
	 * @return tous les stages du tuteur
	 */
	@GetMapping(path = "intershipsByTutor/{idTutor}")
	public @ResponseBody List<Stage> intershipsByTutor(@PathVariable int idTutor) {
		log.info("Renvoie la liste des stages du tuteur idTutor");
		return utilisateurDao.intershipsByTutor(idTutor);
	}

	/**
	 * Renvoie tous les stages d'une entreprise
	 * @param idCompany identifiant de l'entreprise
	 * @return tous les stages de l'entreprise
	 */
	@GetMapping(path = "intershipsByCompany/{idCompany}")
	public @ResponseBody List<Stage> intershipsByCompany(@PathVariable int idCompany) {
		log.info("Renvoie la liste des stages de l'entreprise idCompany");
		return entrepriseDao.intershipsByCompany(idCompany);
	}

	/**
	 * Renvoie tous les étudiants
	 * @return tous les étudiants
	 */
	@GetMapping(path = "allStudents") 
	public @ResponseBody List<Utilisateur> allStudents() {
		log.info("Renvoie la liste des étudiants");
		return utilisateurDao.findAllByRole("ROLE_ETUDIANT");
	}

	/**
	 * Renvoie tous les tuteurs
	 * @return tous les tuteurs
	 */
	@GetMapping(path = "allTutors") 
	public @ResponseBody List<Utilisateur> allTutors() {
		log.info("Renvoie la liste des tuteurs");
		return utilisateurDao.findAllByRole("ROLE_TUTEUR");
	}

	/**
	 * Renvoie toutes les entreprises
	 * @return toutes les entreprises
	 */
	@GetMapping(path = "allCompanies") 
	public @ResponseBody List<Entreprise> allCompanies() {
		log.info("Renvoie la liste des entreprises");
		return entrepriseDao.findAll();
	}
}
