package ru.noxly.guildservice.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.noxly.guildservice.model.entity.Expedition;
import ru.noxly.guildservice.model.entity.Hero;
import ru.noxly.guildservice.model.enums.HeroStatus;
import ru.noxly.guildservice.model.enums.HeroType;
import ru.noxly.guildservice.model.enums.LevelEnum;

public class HeroSpecification {

    public static Specification<Hero> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Hero> hasLevel(LevelEnum level) {
        return (root, query, criteriaBuilder) -> {
            if (level == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("level"), level);
        };
    }

    public static Specification<Hero> hasHeroStatus(HeroStatus heroStatus) {
        return (root, query, criteriaBuilder) -> {
            if (heroStatus == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), heroStatus);
        };
    }

    public static Specification<Hero> hasHeroType(HeroType heroType) {
        return (root, query, criteriaBuilder) -> {
            if (heroType == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("type"), heroType);
        };
    }

    public static Specification<Hero> hasFight(Integer fight) {
        return (root, query, criteriaBuilder) -> {
            if (fight == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("characteristic").get("fight"), fight);
        };
    }

    public static Specification<Hero> hasStrategy(Integer strategy) {
        return (root, query, criteriaBuilder) -> {
            if (strategy == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("characteristic").get("strategy"), strategy);
        };
    }

    public static Specification<Hero> hasMagic(Integer magic) {
        return (root, query, criteriaBuilder) -> {
            if (magic == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("characteristic").get("magic"), magic);
        };
    }
}
