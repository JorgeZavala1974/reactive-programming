package com.david.reactiveprogramming.service;

import com.david.reactiveprogramming.domain.Persona;
import com.david.reactiveprogramming.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonaService {

    private final PersonaRepository personaRepository;

    public Mono<Persona> save(Persona persona){
        return personaRepository.save(persona);
    }

    public Mono<Persona> update(Persona persona,Long id){
        Mono<Persona> oldPersona = personaRepository.findById(id);

        return oldPersona.flatMap(x -> {
            x.setId_persona(persona.getId_persona());
            x.setCx_correo(persona.getCx_correo());
            x.setLock_flag(persona.getLock_flag());
            x.setCx_nombre(persona.getCx_nombre());
            x.setCx_primer_apellido(persona.getCx_primer_apellido());
            x.setCx_segundo_apellido(persona.getCx_segundo_apellido());
            x.setCx_nombre_completo(persona.getCx_nombre_completo());
            x.setDf_fecha_nacimiento(persona.getDf_fecha_nacimiento());

            return personaRepository.save(x);
        });
    }

    public Mono<Persona> findById(Long id){

        return personaRepository.findById(id);
    }

    public Flux<Persona> findAll(){

        return personaRepository.findAll();
    }

    public Mono<Void> delete(Long id){
        return personaRepository.deleteById(id);
    }

}
