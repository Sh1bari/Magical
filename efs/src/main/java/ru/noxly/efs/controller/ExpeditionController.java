package ru.noxly.efs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
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
import ru.noxly.efs.webClient.main.model.dto.ExpeditionDto;
import ru.noxly.efs.webClient.main.model.request.CreateExpeditionRequest;
import ru.noxly.efs.webClient.main.model.request.GetExpeditionReq;
import ru.noxly.efs.webClient.main.model.response.ExpeditionPageRes;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("expedition")
@Tag(name = "Expedition API", description = "")
public class ExpeditionController {

    private final GuildClient guildClient;

    @Operation(summary = "Post expedition")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<ExpeditionDto> createExpedition(@RequestBody CreateExpeditionRequest request) {
        val response = guildClient.postExpedition(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get expedition")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<ExpeditionDto> getExpeditionById(@PathVariable String id) {
        val response = guildClient.getExpeditionById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get expedition")
    @ApiResponses()
    @PostMapping("/filters")
    public ResponseEntity<ExpeditionPageRes> getExpeditionWithFilters(@RequestBody GetExpeditionReq req) {
        val response = guildClient.getExpeditionWithFilters(req);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
