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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.guildservice.model.model.request.GetExpeditionReq;
import ru.noxly.guildservice.model.model.request.StartExpeditionReq;
import ru.noxly.guildservice.model.model.response.ExpeditionPageRes;
import ru.noxly.guildservice.service.ExpeditionService;
import ru.noxly.guildservice.specification.ExpeditionSpecification;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/expedition")
@Tag(name = "Expedition API", description = "")
public class ExpeditionController {

    private final ExpeditionService expeditionService;

    @Qualifier("conversionService")
    private final ConversionService converter;

    @Operation(summary = "Create expedition")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<ExpeditionDto> createExpedition(@RequestBody CreateExpeditionRequest request) {
        val expedition = expeditionService.create(request);
        val response = converter.convert(expedition, ExpeditionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get expedition")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<ExpeditionDto> getExpeditionById(@PathVariable String id) {
        val expedition = expeditionService.findById(id);
        val response = converter.convert(expedition, ExpeditionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Calculate team")
    @ApiResponses()
    @GetMapping("/{id}/calculate")
    public ResponseEntity<String> calculateExpedition(@PathVariable Long id) {
        expeditionService.calculateExpedition(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("команда формируется");
    }

    @Operation(summary = "Get expeditions")
    @ApiResponses()
    @PostMapping("/filters")
    public ResponseEntity<ExpeditionPageRes> getAllExpeditions(@RequestBody GetExpeditionReq req) {
        val spec = Specification.where(ExpeditionSpecification.hasStatus(req.getExpeditionFilter().getStatus()))
                .and(ExpeditionSpecification.hasName(req.getExpeditionFilter().getName()));
        val expeditions = expeditionService.findByPatternAndPageable(spec, req.getPaginationRequest().getPageable());
        val response = ExpeditionPageRes.fromPage(
                expeditions.map(expedition -> converter.convert(expedition, ExpeditionDto.class))
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Start expedition")
    @ApiResponses()
    @PutMapping("/{id}/start")
    public ResponseEntity<ExpeditionDto> startExpedition(@PathVariable Long id, @RequestBody StartExpeditionReq req) {
        val expedition = expeditionService.startExpedition(id, req.getTeam());
        val response = converter.convert(expedition, ExpeditionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
