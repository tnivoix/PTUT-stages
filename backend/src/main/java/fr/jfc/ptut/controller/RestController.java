package fr.jfc.ptut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

import fr.jfc.ptut.dao.*;
import fr.jfc.ptut.dto.*;
import fr.jfc.ptut.entity.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /rest (after Application path)
@Slf4j
public class RestController {

	@Autowired
	private CityRepository cityDao;
	@Autowired
	private CountryRepository countryDao;
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
	 * Enregistre une ville dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCity	
	 * @param laVille la ville à enregistrer, initialisée par Spring à partir des
	 *                valeurs transmises dans la requête POST
	 *                Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 *                la ville dans la base
	 * @return la ville enregistrée (avec sa clé) en format JSON
	 */
	@PostMapping(path = "saveCity") 
	public @ResponseBody City enregistreUneVille(@RequestBody CityForm formData) {
		log.info("Reçu: {}", formData);
		Country c = countryDao.findById(formData.getCountry())
		   .orElseThrow(() -> new IllegalArgumentException("Pays inconnu"));
		City laVille = new City(formData.getName(), c);
		laVille.setPopulation(formData.getPopulation());
		cityDao.save(laVille);
		log.info("Enregistré: {}", laVille);
		return laVille;
	}

	/**
	 * Enregistre un pays dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCountry	
	 * @param lePays  le pays à enregistrer, initialisée par Spring à partir des
	 *                valeurs  transmises dans la requête POST
	 *                Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 *                le pays dans la base
	 * @return le pays enregistré (avec sa clé) en format JSON
	 */
	@PostMapping(path = "saveCountry") 
	public @ResponseBody Country enregistreUnPays(@RequestBody Country lePays) {
		log.info("Reçu: {}", lePays);
		countryDao.save(lePays);
		log.info("Enregistré: {}", lePays);
		return lePays;
	}

	@GetMapping(path = "population") 
	public @ResponseBody List<PopulationResult> populationParPays() {
		log.info("Population pour chaque les pays");
		return countryDao.populationParPaysJPQL();
	}

	@GetMapping(path = "allCities") 
	public @ResponseBody List<City> allCities() {
		log.info("Renvoie la liste des villes");
		return cityDao.findAll();
	}

	@GetMapping(path = "allCountries") 
	public @ResponseBody List<Country> allCountries() {
		log.info("Renvoie la liste des pays");
		return countryDao.findAll();
	}

	@GetMapping(path = "allInterships") 
	public @ResponseBody List<Stage> allInterships() {
		log.info("Renvoie la liste des stages");
		return stageDao.findAll();
	}

	@GetMapping(path = "freeInterships") 
	public @ResponseBody List<Stage> freeInterships() {
		log.info("Renvoie la liste des stages dispos");
		return stageDao.findByIntershipState("Proposition validée");
	}

	@GetMapping(path = "achievedInterships") 
	public @ResponseBody List<Stage> achievedInterships() {
		log.info("Renvoie la liste des stages terminés");
		return stageDao.findByIntershipState("Stage terminé");
	}

	@GetMapping(path = "pendingInterships") 
	public @ResponseBody List<Stage> pendingInterships() {
		log.info("Renvoie la liste des stages en attente de validation");
		return stageDao.findByIntershipState("Proposition en attente de validation");
	}

	@GetMapping(path = "inProgressInterships") 
	public @ResponseBody List<Stage> inProgressInterships() {
		log.info("Renvoie la liste des stages en cours de processus");
		return stageDao.inProgressInterships();
	}

	@GetMapping(path = "intershipsByStudent/{idStudent}")
	public @ResponseBody List<Stage> intershipsByStudent(@PathVariable int idStudent) {
		log.info("Renvoie la liste des stages de l'étudiant idStudent");
		return utilisateurDao.intershipsByStudent(idStudent);
	}

	@GetMapping(path = "intershipsByTutor/{idTutor}")
	public @ResponseBody List<Stage> intershipsByTutor(@PathVariable int idTutor) {
		log.info("Renvoie la liste des stages du tuteur idTutor");
		return utilisateurDao.intershipsByTutor(idTutor);
	}

	@GetMapping(path = "intershipsByCompany/{idCompany}")
	public @ResponseBody List<Stage> intershipsByCompany(@PathVariable int idCompany) {
		log.info("Renvoie la liste des stages de l'entreprise idCompany");
		return entrepriseDao.intershipsByCompany(idCompany);
	}

	@GetMapping(path = "allStudents") 
	public @ResponseBody List<Utilisateur> allStudents() {
		log.info("Renvoie la liste des étudiants");
		return utilisateurDao.findAllByRole("Étudiant");
	}

	@GetMapping(path = "allTutors") 
	public @ResponseBody List<Utilisateur> allTutors() {
		log.info("Renvoie la liste des tuteurs");
		return utilisateurDao.findAllByRole("Tuteur");
	}

	@GetMapping(path = "allCompanies") 
	public @ResponseBody List<Entreprise> allCompanies() {
		log.info("Renvoie la liste des entreprises");
		return entrepriseDao.findAll();
	}
}
