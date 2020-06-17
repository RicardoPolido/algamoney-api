package com.algaworks.algamoney.api.repository.accountposting;

import com.algaworks.algamoney.api.model.AccountPosting;
import com.algaworks.algamoney.api.repository.filter.AccountPostingFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AccountPostingRepositoryImpl implements AccountPostingRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<AccountPosting> filter(AccountPostingFilter accountPostingFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<AccountPosting> criteria = builder.createQuery(AccountPosting.class);
        Root<AccountPosting> root = criteria.from(AccountPosting.class);

        Predicate[] predicates = createRestriction(accountPostingFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<AccountPosting> query = manager.createQuery(criteria);
        addPageableParameters(query, pageable);
        
        return new PageImpl<>(query.getResultList(), pageable, total(accountPostingFilter));
    }

    private Predicate[] createRestriction(AccountPostingFilter accountPostingFilter, CriteriaBuilder builder, Root<AccountPosting> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(accountPostingFilter.getDescription())) {
            predicates.add(builder.like(
                    builder.lower(root.get("description")), "%" + accountPostingFilter.getDescription().toLowerCase() + "%"));
        }

        if (accountPostingFilter.getDueDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dueDate"), accountPostingFilter.getDueDateFrom()));
        }

        if (accountPostingFilter.getDueDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dueDate"), accountPostingFilter.getDueDateTo()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private void addPageableParameters(TypedQuery<AccountPosting> query, Pageable pageable) {
        int pageCurrent = pageable.getPageNumber();
        int pageNumber = pageable.getPageSize();
        int firstPage = pageCurrent * pageNumber;

        query.setFirstResult(firstPage);
        query.setMaxResults(pageNumber);
    }

    private Long total(AccountPostingFilter accountPostingFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<AccountPosting> root = criteria.from(AccountPosting.class);

        Predicate[] predicates = createRestriction(accountPostingFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
