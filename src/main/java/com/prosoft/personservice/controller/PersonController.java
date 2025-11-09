package com.prosoft.personservice.controller;

import com.prosoft.personservice.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/persons")
@Tag(name = "Person API", description = "API для управления данными о людях")
@Slf4j
public class PersonController {

    private final Map<Long, Person> personStore = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @Operation(
            summary = "Получить список всех людей",
            description = "Возвращает полный список всех людей в системе"
    )
    @ApiResponse(responseCode = "200", description = "Список людей успешно получен")
    @GetMapping
    public List<Person> getAllPersons() {
        log.info("Getting all persons, total: {}", personStore.size());
        return personStore.values().stream().collect(Collectors.toList());
    }

    @Operation(
            summary = "Получить человека по ID",
            description = "Возвращает объект человека, если он найден в системе"
    )
    @ApiResponse(responseCode = "200", description = "Человек найден")
    @ApiResponse(responseCode = "404", description = "Человек не найден")
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(
            @Parameter(description = "ID человека", example = "1")
            @PathVariable Long id) {
        log.info("Getting person by ID: {}", id);
        Person person = personStore.get(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            log.warn("Person with ID {} not found", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Создать нового человека")
    @ApiResponse(responseCode = "201", description = "Человек успешно создан")
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Long newId = idCounter.getAndIncrement();
        person.setId(newId);
        personStore.put(newId, person);
        log.info("Created new person with ID: {}", newId);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @Operation(summary = "Обновить данные человека")
    @ApiResponse(responseCode = "200", description = "Данные успешно обновлены")
    @ApiResponse(responseCode = "404", description = "Человек не найден")
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(
            @Parameter(description = "ID человека") @PathVariable Long id,
            @RequestBody Person personDetails) {
        if (!personStore.containsKey(id)) {
            log.warn("Person with ID {} not found for update", id);
            return ResponseEntity.notFound().build();
        }
        personDetails.setId(id);
        personStore.put(id, personDetails);
        log.info("Updated person with ID: {}", id);
        return ResponseEntity.ok(personDetails);
    }

    @Operation(summary = "Удалить человека")
    @ApiResponse(responseCode = "204", description = "Человек успешно удален")
    @ApiResponse(responseCode = "404", description = "Человек не найден")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @Parameter(description = "ID человека") @PathVariable Long id) {
        if (personStore.containsKey(id)) {
            personStore.remove(id);
            log.info("Deleted person with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Person with ID {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}