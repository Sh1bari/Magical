package ru.noxly.guildservice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.enums.ExpeditionStatus;


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

    public static Specification<Expedition> hasStatus(ExpeditionStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("expeditionStatus"), status);
        };
    }
}
