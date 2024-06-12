package com.labdesoft.roteiro01.integration;

import com.labdesoft.roteiro01.Roteiro01Application;
import com.labdesoft.roteiro01.dto.TaskDto;
import com.labdesoft.roteiro01.entity.Task.TaskPriority;
import com.labdesoft.roteiro01.entity.Task.TaskType;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {Roteiro01Application.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class TaskControllerIntegrationTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.port = 8080;
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect(){
        get("api/tasks").then().statusCode(200);
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasOneTask_thenCorrect(){
        get("api/tasks1").then().statusCode(200).assertThat().body("description",equalTo("Primeira tarefa"));
    }

    @Test
    public void givenNewTask_whenCreated_thenCorrect() {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription("Nova tarefa");
        taskDto.setType(TaskType.LIVRE);
        taskDto.setPriority(TaskPriority.ALTA);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(taskDto)
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("description", equalTo("Nova tarefa"))
                .body("type", equalTo(TaskType.LIVRE.name()))
                .body("priority", equalTo(TaskPriority.ALTA.name()));
    }

    @Test
    public void givenTaskId_whenDeleted_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser deletada

        delete("/api/tasks/{id}", taskId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        get("/api/tasks/{id}", taskId)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void givenUpdatedTask_whenUpdated_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser atualizada
        TaskDto updatedTaskDto = new TaskDto();
        updatedTaskDto.setDescription("Tarefa Atualizada");
        updatedTaskDto.setType(TaskType.PRAZO);
        updatedTaskDto.setPriority(TaskPriority.MEDIA);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(updatedTaskDto)
                .when()
                .put("/api/tasks/{id}", taskId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("description", equalTo("Tarefa Atualizada"))
                .body("type", equalTo(TaskType.PRAZO.name()))
                .body("priority", equalTo(TaskPriority.MEDIA.name()));
    }

    @Test
    public void givenTaskId_whenMarkedAsCompleted_thenCorrect() {
        long taskId = 1; // ID da tarefa a ser marcada como concluída

        put("/api/tasks/{id}/complete", taskId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("completed", equalTo(true));
    }
}
