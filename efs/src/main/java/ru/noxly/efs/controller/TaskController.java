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
import ru.noxly.efs.webClient.main.model.dto.TaskDto;
import ru.noxly.efs.webClient.main.model.request.CreateMissionRequest;
import ru.noxly.efs.webClient.main.model.request.CreateTaskRequest;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/task")
@Tag(name = "Task API", description = "")
public class TaskController {

    private final GuildClient guildClient;

    @Operation(summary = "Post task")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        val response = guildClient.postTask(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get task")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable String id) {
        val response = guildClient.getTaskById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
