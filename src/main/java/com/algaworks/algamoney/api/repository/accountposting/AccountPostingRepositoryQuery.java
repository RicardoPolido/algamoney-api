package com.algaworks.algamoney.api.repository.accountposting;

import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.repository.filter.AccountPostingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountPostingRepositoryQuery {

    public Page<AccountPosting> filter(AccountPostingFilter accountPostingFilter, Pageable pageable);
}
