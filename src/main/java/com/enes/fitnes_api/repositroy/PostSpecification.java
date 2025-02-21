package com.enes.fitnes_api.repositroy;

import com.enes.fitnes_api.dto.Criterion;
import com.enes.fitnes_api.model.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import javax.naming.OperationNotSupportedException;

public class PostSpecification implements Specification<Post> {
    private final Criterion criterion;

    private final static String EQUALS = "EQUALS";

    public PostSpecification(Criterion criterion) {
        this.criterion = criterion;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criterion.getOperation().equals(EQUALS)) {
            return criteriaBuilder.equal(
                    root.get(criterion.getFilterKey()), criterion.getValue());
        }
        try {
            throw new OperationNotSupportedException("Operation not supported yet");
        } catch (OperationNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
