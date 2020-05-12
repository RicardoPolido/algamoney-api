package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person update(Long id, Person person) {

        Person personSaved = getPersonById(id);
        BeanUtils.copyProperties(person, personSaved, "id");
        return personRepository.save(personSaved);
    }

    public void updatePropertyActive(Long id, Boolean active) {
        Person personSaved = getPersonById(id);
        personSaved.setActive(active);
        personRepository.save(personSaved);
    }

    private Person getPersonById(Long id){
        Person personSaved = personRepository.findById(id).orElse(null);

        if (personSaved == null) {
            throw new EmptyResultDataAccessException(1);
        }

        return personSaved;
    }
}
