package ru.noxly.guildservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
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
import ru.noxly.guildservice.model.model.dto.MissionDto;
import ru.noxly.guildservice.model.model.request.CreateMissionRequest;
import ru.noxly.guildservice.service.MissionService;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/mission")
@Tag(name = "Mission API", description = "")
public class MissionController {

    private final MissionService missionService;

    @Qualifier("conversionService")
    private final ConversionService converter;

    @Operation(summary = "Create mission")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<MissionDto> createMission(@RequestBody CreateMissionRequest request) {
        val mission = missionService.create(request);
        val response = converter.convert(mission, MissionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get mission")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<MissionDto> getMissionById(@PathVariable String id) {
        val mission = missionService.findById(id);
        val response = converter.convert(mission, MissionDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
