package ru.noxly.guildservice.converters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.noxly.guildservice.model.entity.Team;
import ru.noxly.guildservice.model.entity.TeamHero;
import ru.noxly.guildservice.model.model.dto.TeamDto;

import static ru.noxly.guildservice.utils.CommonUtils.nullOrApply;

@Component
@RequiredArgsConstructor
public class TeamDtoConverter implements Converter<Team, TeamDto> {

    private final HeroDtoConverter heroDtoConverter;

    @Override
    public TeamDto convert(@NonNull final Team source) {
        return TeamDto.init()
                .setId(source.getId())
                .setStatus(source.getStatus())
                .setCreateTime(source.getCreateTime())
                .setResultTime(source.getResultTime())
                .setSentTime(source.getSentTime())
                .setHeroes(
                        nullOrApply(
                                source.getTeamHeroes(), teamHeroes -> teamHeroes.stream()
                                        .map(TeamHero::getHero)
                                        .map(heroDtoConverter::convert)
                                        .toList()
                        )
                )
                .build();
    }
}
