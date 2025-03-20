package ru.noxly.guildservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.noxly.guildservice.model.model.dto.HeroDto;
import ru.noxly.guildservice.model.model.request.CreateHeroRequest;
import ru.noxly.guildservice.model.model.request.GetHeroReq;
import ru.noxly.guildservice.model.model.response.ExpeditionPageRes;
import ru.noxly.guildservice.model.model.response.HeroPageRes;
import ru.noxly.guildservice.service.HeroService;
import ru.noxly.guildservice.specification.HeroSpecification;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/hero")
@Tag(name = "Hero API", description = "")
public class HeroController {

    private final HeroService heroService;

    @Qualifier("conversionService")
    private final ConversionService converter;

    @Operation(summary = "Create hero")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<HeroDto> createHero(@RequestBody CreateHeroRequest request) {
        val hero = heroService.create(request);
        val response = converter.convert(hero, HeroDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get hero")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getHeroById(@PathVariable String id) {
        val hero = heroService.findById(id);
        val response = converter.convert(hero, HeroDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get heroes")
    @ApiResponses()
    @PostMapping("/filters")
    public ResponseEntity<HeroPageRes> getAllHeroes(@RequestBody GetHeroReq req) {
        val spec = Specification.where(HeroSpecification.hasLevel(req.getHeroFilter().getLevel()))
                .and(HeroSpecification.hasHeroStatus(req.getHeroFilter().getStatus()))
                .and(HeroSpecification.hasHeroType(req.getHeroFilter().getType()))
                .and(HeroSpecification.hasFight(req.getHeroFilter().getFight()))
                .and(HeroSpecification.hasStrategy(req.getHeroFilter().getStrategy()))
                .and(HeroSpecification.hasMagic(req.getHeroFilter().getMagic()))
                .and(HeroSpecification.hasName(req.getHeroFilter().getName()));
        val heroes = heroService.findByPatternAndPageable(spec, req.getPaginationRequest().getPageable());
        val response = HeroPageRes.fromPage(
                heroes.map(hero -> converter.convert(hero, HeroDto.class))
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
