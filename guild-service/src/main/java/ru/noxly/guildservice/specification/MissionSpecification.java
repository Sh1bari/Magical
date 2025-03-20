package ru.noxly.guildservice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.noxly.guildservice.model.entity.Mission;

public class MissionSpecification {

    public static Specification<Mission> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%");
        };
    }
}
