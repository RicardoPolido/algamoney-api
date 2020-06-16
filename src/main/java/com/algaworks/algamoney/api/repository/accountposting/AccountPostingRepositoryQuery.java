package com.algaworks.algamoney.api.repository.accountposting;

import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.repository.filter.AccountPostingFilter;

import java.util.List;

public interface AccountPostingRepositoryQuery {

    public List<AccountPosting> filter(AccountPostingFilter accountPostingFilter);
}
