package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.AccountPostingRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.exception.InexistentOrInactivePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountPostingService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountPostingRepository accountPostingRepository;

    public AccountPosting save(AccountPosting accountPosting) {
        Person person = personRepository.findById(accountPosting.getPerson().getId()).orElse(null);
        if (person == null || person.isInactive()) {
            throw new InexistentOrInactivePersonException();
        }
        return accountPostingRepository.save(accountPosting);
    }
}
