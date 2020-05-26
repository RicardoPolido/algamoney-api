package com.algaworks.algamoney.api.repository;

import com.algaworks.algamoney.api.model.AccountPosting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountPostingRepository extends JpaRepository<AccountPosting, Long> {

}