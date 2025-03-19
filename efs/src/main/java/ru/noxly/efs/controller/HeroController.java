package ru.noxly.efs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import ru.noxly.efs.webClient.main.GuildClient;
import ru.noxly.efs.webClient.main.model.dto.HeroDto;
import ru.noxly.efs.webClient.main.model.request.CreateHeroRequest;
import ru.noxly.efs.webClient.main.model.request.GetHeroReq;
import ru.noxly.efs.webClient.main.model.request.HeroFilter;
import ru.noxly.efs.webClient.main.model.response.HeroPageRes;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/hero")
@Tag(name = "Hero API", description = "")
public class HeroController {

    private final GuildClient guildClient;

    @Operation(summary = "Post hero")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<HeroDto> createHero(@RequestBody CreateHeroRequest request) {
        val response = guildClient.postHero(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get hero")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<HeroDto> getHeroById(@PathVariable String id) {
        val response = guildClient.getHeroById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get heroes with filters")
    @ApiResponses()
    @PostMapping("/filters")
    public ResponseEntity<HeroPageRes> getHeroesWithFilters(@RequestBody GetHeroReq req) {
        val response = guildClient.getHeroesWithFilters(req);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
