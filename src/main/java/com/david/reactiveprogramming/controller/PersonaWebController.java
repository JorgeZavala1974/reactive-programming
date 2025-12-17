package com.david.reactiveprogramming.controller;

import com.david.reactiveprogramming.domain.Persona;
import com.david.reactiveprogramming.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web/personas")
public class PersonaWebController {

    private final PersonaRepository personaRepository;

    @GetMapping()
    public String findAll(Model model){
        Flux<Persona> all = personaRepository.findAll();
        Flux<Persona> personaFlux = Flux.merge(all.skipLast(3), all.skip(4)).delayElements(Duration.ofSeconds(1));
        IReactiveDataDriverContextVariable personas = new ReactiveDataDriverContextVariable(personaFlux,1);
        model.addAttribute("personas",personas);
        return "personas";
    }

}
