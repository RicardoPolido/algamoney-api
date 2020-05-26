package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.repository.AccountPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accountposting")
public class AccountPostingController {

    @Autowired
    AccountPostingRepository accountPostingRepository;

    @Autowired
    ApplicationEventPublisher publisher;

    @GetMapping
    public List<AccountPosting> list() {
        return accountPostingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {

        AccountPosting accountPosting = accountPostingRepository.findById(id).orElse(null);

        if (accountPosting != null) {
            return ResponseEntity.ok().body(accountPosting);
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<AccountPosting> save(@Valid @RequestBody AccountPosting accountPosting, HttpServletResponse response) {

        AccountPosting accountPostingCreated = accountPostingRepository.save(accountPosting);

        publisher.publishEvent(new CreatedResourceEvent(this, response, accountPostingCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountPostingCreated);

    }

}
