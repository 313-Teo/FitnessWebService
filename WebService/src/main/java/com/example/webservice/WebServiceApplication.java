package com.example.webservice;

import com.example.webservice.athletes.MyAthletes;
import com.example.webservice.athletes.MyAthletesRepository;
import com.example.webservice.athletes.MyAthletesRequest;
import com.example.webservice.cards.CardRepository;
import com.example.webservice.exercises.ExerciseRepository;
import com.example.webservice.jwt.JwtConfig;
import com.example.webservice.trainings.Training;
import com.example.webservice.trainings.TrainingRepository;
import com.example.webservice.users.User;
import com.example.webservice.users.UserRepository;

import com.example.webservice.workouts.Workout;
import com.example.webservice.exercises.Exercise;
import com.example.webservice.workouts.WorkoutNameRequest;
import com.example.webservice.workouts.WorkoutRequest;
import com.example.webservice.workouts.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

import java.util.HashMap;
import java.util.stream.Collectors;


@SpringBootApplication
@RestController
public class WebServiceApplication {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private MyAthletesRepository myAthletesRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@RequestBody User request) {

        User existingUser = userRepository.findByEmail(request.getEmail());

        HashMap<String, String> responseMap = new HashMap<>();

        if (existingUser != null) {
            responseMap.put("error", "L'email è già in uso");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMap);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(hashedPassword);

        userRepository.save(request);

        responseMap.put("message", "Registrazione completata");
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        HashMap<String, String> responseMap = new HashMap<>();

        if (existingUser != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                String token = jwtConfig.generateToken(user.getEmail());
                String account = existingUser.getAccount();

                responseMap.put("token", token);
                responseMap.put("account", account);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", "Password errata");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
            }
        } else {
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

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            responseMap.put("nome", user.getNome());
            responseMap.put("cognome", user.getCognome());

            List<String> workoutList = cardRepository.findByCodice(user.getCodice())
                    .stream()
                    .map(card -> card.getWorkout().getNome())
                    .collect(Collectors.toList());

            responseMap.put("workout", workoutList);

            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Scheda non trovata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @GetMapping("/myathletes")
    public ResponseEntity<HashMap<String, Object>> myathletes(@RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            List<MyAthletes> myAthletesList = myAthletesRepository.findByCodice(user.getCodice());

            List<String> athleteList = myAthletesList.stream()
                    .map(myAthletes -> myAthletes.getAthlete().getNome() + " " + myAthletes.getAthlete().getCognome())
                    .collect(Collectors.toList());

            List<String> emailAtletiList = myAthletesList.stream()
                    .map(myAthletes -> myAthletes.getAthlete().getEmail())
                    .collect(Collectors.toList());

            responseMap.put("atleti", athleteList);
            responseMap.put("emailAtleti", emailAtletiList);

            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Atleti non trovati");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @PostMapping("/myathletes/add")
    public ResponseEntity<HashMap<String, String>> add(@RequestHeader("Authorization") String token, @RequestBody MyAthletesRequest request) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        User athlete = userRepository.findByCodice(request.getCodice());

        HashMap<String, String> responseMap = new HashMap<>();

        if (user != null) {
            if (athlete != null) {
                boolean athleteAlreadyAdded = myAthletesRepository.existsByTrainerAndAthlete(user, athlete);

                if (athleteAlreadyAdded) {
                    responseMap.put("error", "Atleta già presente");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMap);
                }

                MyAthletes myAthletes = new MyAthletes();
                myAthletes.setTrainer(user);
                myAthletes.setAthlete(athlete);

                myAthletesRepository.save(myAthletes);

                responseMap.put("message", "Atleta aggiunto con successo");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", "Atleta non trovato");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
            }
        } else {
            responseMap.put("error", "Autorizzazione Negata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @PostMapping("/myathletes/cards")
    public ResponseEntity<HashMap<String, Object>> cards(@RequestHeader("Authorization") String token, @RequestBody User request) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        User requestUser = userRepository.findByEmail(request.getEmail());

        HashMap<String, Object> responseMap = new HashMap<>();

        if (requestUser != null) {
            responseMap.put("nome", requestUser.getNome());
            responseMap.put("cognome", requestUser.getCognome());

            List<String> workoutList = cardRepository.findByCodice(requestUser.getCodice())
                    .stream()
                    .map(card -> card.getWorkout().getNome())
                    .collect(Collectors.toList());

            responseMap.put("workout", workoutList);

            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Utente non trovato");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @GetMapping("/workout")
    public ResponseEntity<HashMap<String, Object>> workout(@RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            List<String> workoutList = workoutRepository.findAllNames();
            responseMap.put("workout", workoutList);

            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Autorizzazione Negata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @PostMapping("/workout/exercises")
    public ResponseEntity<HashMap<String, Object>> exercises(@RequestHeader("Authorization") String token, @RequestBody WorkoutNameRequest request) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        Workout existingWorkout = workoutRepository.findByNome(request.getWorkout());

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            if (existingWorkout != null) {
                responseMap.put("workout", existingWorkout.getNome());

                List<Training> trainingList = trainingRepository.findByCodice(existingWorkout.getCodice());
                LocalTime totalExerciseTime = LocalTime.MIN;
                for (Training training : trainingList) {
                    LocalTime exerciseTime = LocalTime.parse(training.getExercise().getTempo());
                    totalExerciseTime = totalExerciseTime.plusHours(exerciseTime.getHour())
                            .plusMinutes(exerciseTime.getMinute())
                            .plusSeconds(exerciseTime.getSecond());
                }

                responseMap.put("tempo", totalExerciseTime.toString());

                List<String> exerciseList = trainingRepository.findByCodice(existingWorkout.getCodice())
                        .stream()
                        .map(training -> training.getExercise().getNome())
                        .collect(Collectors.toList());

                responseMap.put("esercizi", exerciseList);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", "Esercizi non trovati");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
            }
        } else {
            responseMap.put("error", "Autorizzazione Negata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @PostMapping("/workout/create")
    public ResponseEntity<HashMap<String, String>> create(@RequestHeader("Authorization") String token, @RequestBody WorkoutRequest request) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        Workout existingWorkout = workoutRepository.findByNome(request.getNome());

        HashMap<String, String> responseMap = new HashMap<>();

        if (user != null) {
            if (existingWorkout != null) {
                responseMap.put("error", "Workout già esistente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMap);
            }

            Workout workout = new Workout();
            workout.setCodice(request.getCodice());
            workout.setNome(request.getNome());

            workoutRepository.save(workout);

            for (String exerciseName : request.getEsercizi()) {
                Exercise exercise = exerciseRepository.findByNome(exerciseName);
                if (exercise == null) {
                    responseMap.put("error", "Esercizio non trovato");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
                }

                Training training = new Training();
                training.setWorkout(workout);
                training.setExercise(exercise);

                trainingRepository.save(training);
            }

            responseMap.put("message", "Workout creato con successo");
            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Autorizzazione Negata");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseMap);
        }
    }

    @GetMapping("/workout/create/exercises")
    public ResponseEntity<HashMap<String, Object>> exercises(@RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtConfig.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);

        HashMap<String, Object> responseMap = new HashMap<>();

        if (user != null) {
            List<String> exerciseList = exerciseRepository.findAllNames();
            responseMap.put("esercizi", exerciseList);

            return ResponseEntity.ok(responseMap);
        } else {
            responseMap.put("error", "Autorizzazione Negata");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }


}

