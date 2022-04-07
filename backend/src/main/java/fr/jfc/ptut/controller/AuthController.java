package fr.jfc.ptut.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.jfc.ptut.dao.RoleRepository;
import fr.jfc.ptut.dao.UtilisateurRepository;
import fr.jfc.ptut.entity.Role;
import fr.jfc.ptut.entity.Utilisateur;
import fr.jfc.ptut.security.jwt.JwtUtils;
import fr.jfc.ptut.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		// Create new user's account
        Utilisateur user = new Utilisateur(
            signUpRequest.getUsername(),
            encoder.encode(signUpRequest.getPassword()),
            signUpRequest.getName(),
            signUpRequest.getFirstName(),
            signUpRequest.getNumTel(),
            signUpRequest.getEmail());
		String strRole = signUpRequest.getRole();
		Role role;
		if (strRole == "") {
			Role userRole = roleRepository.findByName("Étudiant")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			role = userRole;
		} else {
				switch (strRole) {
				case "Responsable des stages":
					Role respRole = roleRepository.findByName("Responsable des stages")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role = respRole;
					break;
				case "Tuteur":
					Role tuteurRole = roleRepository.findByName("Tuteur")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role = tuteurRole;
					break;
				default:
					Role etudRole = roleRepository.findByName("Étudiant")
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role = etudRole;
				}
			};
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}