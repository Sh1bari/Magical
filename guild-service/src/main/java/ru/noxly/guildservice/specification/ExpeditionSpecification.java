package ru.noxly.guildservice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.noxly.guildservice.model.entity.Expedition;


public class ExpeditionSpecification {

    public static Specification<Expedition> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%");
        };
    }
}
