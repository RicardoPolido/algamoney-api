package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Person> list() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {

        Person person = personRepository.findById(id).orElse(null);

        if (person != null) {
            return ResponseEntity.ok().body(person);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Person> save(@Valid @RequestBody Person person, HttpServletResponse response) {

        Person personCreated = personRepository.save(person);

        publisher.publishEvent(new CreatedResourceEvent(this, response, personCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(personCreated);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person) {
        Person personSaved = personRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(person, personSaved, "id");
        personRepository.save(personSaved);
        return ResponseEntity.ok(personSaved);
    }

}
