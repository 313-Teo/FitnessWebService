package com.example.webservice;

import com.example.webservice.cards.Card;
import com.example.webservice.cards.CardRepository;
import com.example.webservice.jwt.JwtConfig;
import com.example.webservice.users.User;
import com.example.webservice.users.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@SpringBootApplication
@RestController
public class WebServiceApplication {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@RequestBody User request) {
        User existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser != null) {
            HashMap<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "L'email è già in uso");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMap);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(hashedPassword);

        userRepository.save(request);

        Card card = new Card();
        card.setCodice(request.getCodice());
        card.setNome(request.getNome());
        card.setCognome(request.getCognome());

        card.setWorkout1("workout1");
        card.setWorkout2("workout2");
        card.setWorkout3("workout3");
        cardRepository.save(card);

        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Registrazione completata");
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                String token = jwtConfig.generateToken(user.getEmail());
                String account = existingUser.getAccount();

                HashMap<String, String> responseMap = new HashMap<>();
                responseMap.put("token", token);
                responseMap.put("account", account);
                return ResponseEntity.ok(responseMap);
            } else {
                HashMap<String, String> responseMap = new HashMap<>();
                responseMap.put("error", "Password errata");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
            }
        } else {
            HashMap<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Credenziali non valide");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<HashMap<String, Object>> profile(@RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            responseMap.put("codice", user.getCodice());
            responseMap.put("nome", user.getNome());
            responseMap.put("cognome", user.getCognome());
            responseMap.put("peso", user.getPeso());
            responseMap.put("altezza", user.getAltezza());
            responseMap.put("account", user.getAccount());
            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Utente non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @GetMapping("/card")
    public ResponseEntity<HashMap<String, Object>> card(@RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        Card card = cardRepository.findByCodice(user.getCodice());

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            responseMap.put("nome", card.getNome());
            responseMap.put("cognome", card.getCognome());
            responseMap.put("workout1", card.getWorkout1());
            responseMap.put("workout2", card.getWorkout2());
            responseMap.put("workout3", card.getWorkout3());
            responseMap.put("account", user.getAccount());
            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Scheda non trovata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }





}
