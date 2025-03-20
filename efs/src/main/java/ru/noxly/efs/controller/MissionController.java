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
import ru.noxly.efs.webClient.main.model.dto.MissionDto;
import ru.noxly.efs.webClient.main.model.request.CreateMissionRequest;
import ru.noxly.efs.webClient.main.model.request.GetMissionReq;
import ru.noxly.efs.webClient.main.model.response.MissionPageRes;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/mission")
@Tag(name = "Mission API", description = "")
public class MissionController {

    private final GuildClient guildClient;

    @Operation(summary = "Post mission")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<MissionDto> createMission(@RequestBody CreateMissionRequest request) {
        val response = guildClient.postMission(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get mission")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<MissionDto> getMissionById(@PathVariable String id) {
        val response = guildClient.getMissionById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get missions")
    @ApiResponses()
    @PostMapping("/filters")
    public ResponseEntity<MissionPageRes> getMissionWithFilters(@RequestBody GetMissionReq req) {
        val response = guildClient.getMissionWithFilters(req);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
