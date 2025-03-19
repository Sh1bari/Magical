package ru.noxly.guildservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;
import ru.noxly.guildservice.model.model.dto.HeroDto;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.guildservice.model.model.request.CreateHeroRequest;
import ru.noxly.guildservice.service.HeroService;

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
}
