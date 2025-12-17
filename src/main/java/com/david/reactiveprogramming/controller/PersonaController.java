package com.david.reactiveprogramming.controller;

import com.david.reactiveprogramming.domain.Persona;
import com.david.reactiveprogramming.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @RequiredArgsConstructor de Lombok sirve para generar automáticamente un constructor que recibe como parámetros todos los campos final y aquellos marcados con @NonNull en una clase, reduciendo código repetitivo (boilerplate) y asegurando que estos campos críticos sean inicializados al crear un objeto, lo cual es ideal para crear objetos inmutables y facilitar la inyección de dependencias en Spring Boot.
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/personas")
public class PersonaController {

    // Servicio de los movimientos de la persona
    public final PersonaService personaService;

    @PostMapping
    public ResponseEntity<Mono<Persona>> create(@RequestBody Persona persona){
        Mono<Persona> saved = personaService.save(persona);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Mono<Persona>> update(Persona persona,Long id){
        Mono<Persona> updated = personaService.update(persona, id);

        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Persona>> findById(@PathVariable Long id){

        Mono<Persona> persona = personaService.findById(id);

        return new ResponseEntity<>(persona,HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Persona>> findAll(){

        Flux<Persona> personas = personaService.findAll().delayElements(Duration.ofMillis(500));

        return new ResponseEntity<>(personas,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Mono<Void>> delete(Long id){
        Mono<Void> deleted = personaService.delete(id);

        return new ResponseEntity<>(deleted,HttpStatus.NO_CONTENT);
    }

}
