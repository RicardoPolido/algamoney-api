package com.algaworks.algamoney.api.controller;

import com.algaworks.algamoney.api.event.CreatedResourceEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.repository.AccountPostingRepository;
import com.algaworks.algamoney.api.repository.filter.AccountPostingFilter;
import com.algaworks.algamoney.api.service.AccountPostingService;
import com.algaworks.algamoney.api.service.exception.InexistentOrInactivePersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/accountposting")
public class AccountPostingController {

    @Autowired
    private AccountPostingRepository accountPostingRepository;

    @Autowired
    private AccountPostingService accountPostingService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public Page<AccountPosting> list(AccountPostingFilter accountPostingFilter, Pageable pageable) {
        return accountPostingRepository.filter(accountPostingFilter, pageable);
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

        AccountPosting accountPostingCreated = accountPostingService.save(accountPosting);

        publisher.publishEvent(new CreatedResourceEvent(this, response, accountPostingCreated.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(accountPostingCreated);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountPostingRepository.deleteById(id);
    }

    @ExceptionHandler({InexistentOrInactivePersonException.class})
    public ResponseEntity<Object> handleInexistentOrInactivePersonException(InexistentOrInactivePersonException ex, WebRequest request) {
        String messageUser = messageSource.getMessage("person.inexist-or-inactive", null, LocaleContextHolder.getLocale());
        String messageDeveloper = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        List<Erro> errors = Arrays.asList(new Erro(messageUser, messageDeveloper));
        return ResponseEntity.badRequest().body(errors);
    }

}
