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
import ru.noxly.guildservice.model.model.dto.TaskDto;
import ru.noxly.guildservice.model.model.request.CreateTaskRequest;
import ru.noxly.guildservice.service.TaskService;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/task")
@Tag(name = "Task API", description = "")
public class TaskController {

    private final TaskService taskService;

    @Qualifier("conversionService")
    private final ConversionService converter;

    @Operation(summary = "Create task")
    @ApiResponses()
    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        val task = taskService.create(request);
        val response = converter.convert(task, TaskDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get task")
    @ApiResponses()
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable String id) {
        val task = taskService.findById(id);
        val response = converter.convert(task, TaskDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
