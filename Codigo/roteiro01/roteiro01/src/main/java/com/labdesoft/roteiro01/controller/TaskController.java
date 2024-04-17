package com.labdesoft.roteiro01.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Operation(summary = "Lista todas as tarefas da lista")
    @GetMapping("/task")
    public String listAll() {
        return "Listar todas as tasks";
    }
}
