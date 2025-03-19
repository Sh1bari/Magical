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
import ru.noxly.guildservice.converters.ExpeditionDtoConverter;
import ru.noxly.guildservice.model.model.dto.ExpeditionDto;
import ru.noxly.guildservice.model.model.request.CreateExpeditionRequest;
import ru.noxly.guildservice.service.ExpeditionService;

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

    @Operation(summary = "Get expedition")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<ExpeditionDto> createExpedition(@RequestBody CreateExpeditionRequest request) {
        val expedition = expeditionService.createExpedition(request);
        val response = converter.convert(expedition, ExpeditionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);

    }
}
