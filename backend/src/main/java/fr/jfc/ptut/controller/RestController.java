package fr.jfc.ptut.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping(path = "allInternships") 
	public @ResponseBody List<Stage> allInternships() {
		log.info("Renvoie la liste des stages");
		return stageDao.findAll();
	}

	/**
	 * Renvoie un stage particulier
	 * @param idStudent identifiant du stage
	 * @return le stage
	 */
	@GetMapping(path = "internshipById/{idInternship}")
	public @ResponseBody Stage internshipById(@PathVariable int idInternship) {
		log.info("Renvoie le stage"+idInternship);
		return stageDao.findById(idInternship).get();
	}

	/**
	 * Renvoie tous les stages validés et disponibles
	 * @return tous les stages dispos
	 */
	@GetMapping(path = "freeInternships") 
	public @ResponseBody List<Stage> freeInternships() {
		log.info("Renvoie la liste des stages dispos");
		return stageDao.findByInternshipState("Proposition validée");
	}

	/**
	 * Renvoie tous les stages terminés
	 * @return tous les stages terminés
	 */
	@GetMapping(path = "achievedInternships") 
	public @ResponseBody List<Stage> achievedInternships() {
		log.info("Renvoie la liste des stages terminés");
		return stageDao.findByInternshipState("Stage terminé");
	}

	/**
	 * Renvoie tous les stages en attente de validation
	 * @return tous les stages en attente
	 */
	@GetMapping(path = "pendingInternships") 
	public @ResponseBody List<Stage> pendingInternships() {
		log.info("Renvoie la liste des stages en attente de validation");
		return stageDao.findByInternshipState("Proposition en attente de validation");
	}

	/**
	 * Renvoie tous les stages en cours de processus
	 * @return tous les stages en cours
	 */
	@GetMapping(path = "inProgressInternships") 
	public @ResponseBody List<Stage> inProgressInternships() {
		log.info("Renvoie la liste des stages en cours de processus");
		return stageDao.inProgressInternships();
	}

	/**
	 * Renvoie tous les stages d'un étudiant
	 * @param idStudent identifiant de l'étudiant
	 * @return tous les stages de l'étudiant
	 */
	@GetMapping(path = "internshipsByStudent/{idStudent}")
	public @ResponseBody List<Stage> internshipsByStudent(@PathVariable int idStudent) {
		log.info("Renvoie la liste des stages de l'étudiant"+idStudent);
		return utilisateurDao.internshipsByStudent(idStudent);
	}

	/**
	 * Renvoie tous les stages d'un tuteur
	 * @param idTutor identifiant du tuteur
	 * @return tous les stages du tuteur
	 */
	@GetMapping(path = "internshipsByTutor/{idTutor}")
	public @ResponseBody List<Stage> internshipsByTutor(@PathVariable int idTutor) {
		log.info("Renvoie la liste des stages du tuteur"+idTutor);
		return utilisateurDao.internshipsByTutor(idTutor);
	}

	/**
	 * Renvoie tous les stages d'une entreprise
	 * @param idCompany identifiant de l'entreprise
	 * @return tous les stages de l'entreprise
	 */
	@GetMapping(path = "internshipsByCompany/{idCompany}")
	public @ResponseBody List<Stage> internshipsByCompany(@PathVariable int idCompany) {
		log.info("Renvoie la liste des stages de l'entreprise "+idCompany);
		return entrepriseDao.internshipsByCompany(idCompany);
	}

	/**
	 * Renvoie tous les étudiants
	 * @return tous les étudiants
	 */
	@GetMapping(path = "allStudents") 
	public @ResponseBody List<Utilisateur> allStudents() {
		log.info("Renvoie la liste des étudiants");
		return utilisateurDao.findAllByRole("Étudiant");
	}

	/**
	 * Renvoie tous les tuteurs
	 * @return tous les tuteurs
	 */
	@GetMapping(path = "allTutors") 
	public @ResponseBody List<Utilisateur> allTutors() {
		log.info("Renvoie la liste des tuteurs");
		return utilisateurDao.findAllByRole("Tuteur");
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

	/**
	 * Renvoie un utilisateur suivant son identifiant
	 * @return un utilisateur
	 */
	@GetMapping(path = "utilisateurByIdentifiant/{identifiant}") 
	public @ResponseBody Utilisateur findUtilisateurByIdentifiant(@PathVariable String identifiant) {
		log.info("Renvoie un utilisateur");
		return utilisateurDao.findByIdentifiant(identifiant).get();
	}

	/**
	 * Renvoie un utilisateur suivant son id
	 * @return un utilisateur
	 */
	@GetMapping(path = "utilisateurById/{id}") 
	public @ResponseBody Utilisateur findUtilisateurById(@PathVariable Integer id) {
		log.info("Renvoie un utilisateur");
		return utilisateurDao.findById(id).get();
	}

	/**
	 * Renvoie une entrprise suivant son id
	 * @return une entreprise
	 */
	@GetMapping(path = "entrepriseById/{id}") 
	public @ResponseBody Entreprise findEntrepriseById(@PathVariable Integer id) {
		log.info("Renvoie une entreprise");
		return entrepriseDao.getById(id);
	}

	/**
	 * Renvoie un etatStage suivant son nom
	 * @return un etatStage
	 */
	@GetMapping(path = "etatStageByNom/{nom}") 
	public @ResponseBody EtatStage findEtatStageByNom(@PathVariable String nom) {
		log.info("Renvoie un etatStage");
		return etatStageDao.findByNom(nom).get();
	}

	/**
	 * Change l'état d'un stage
	 */
	@PatchMapping(path = "changeInternshipState/{idInternship}")
	public @ResponseBody void changeInternshipState(@PathVariable Integer idInternship, @RequestBody EtatStage state) {
		Stage s = stageDao.getById(idInternship);
		stageDao.changeInternshipState(s, state);
		log.info("Enregistré: {}", s);
	}

	/**
	 * Ajoute une soutenance et un jury au stage
	 */
	@PatchMapping(path = "changeSoutenanceAndJury/{idInternship}")
	public @ResponseBody void changeSoutenanceAndJury(@PathVariable Integer idInternship, @RequestBody String[] data) {
		Stage s = stageDao.getById(idInternship);
		Date date = Date.valueOf(data[0]); 
		stageDao.addSoutenanceAndJury(s, date, data[1]);
		log.info("Enregistré: {}", s);
	}

	/**
	 * Ajoute un utilisateur
	 */
	@PatchMapping(path = "addUser/{idInternship}")
	public @ResponseBody void addUser(@PathVariable Integer idInternship, @RequestBody Utilisateur utilisateur) {
		Stage s = stageDao.getById(idInternship);
		stageDao.addUser(s, utilisateur);
		log.info("Enregistré: {}", s);
	}
}
