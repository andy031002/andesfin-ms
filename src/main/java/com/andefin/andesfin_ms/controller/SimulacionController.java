package com.andesfin.andesfinms.controller;

import java.util.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.andesfin.andesfinms.dto.SimulacionRequestDTO;
import com.andesfin.andesfinms.service.SimulacionService;

@RestController
@RequestMapping("/simulaciones")
public class SimulacionController {

    private final SimulacionService service;

    public SimulacionController(SimulacionService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, Object> simular(@Valid @RequestBody SimulacionRequestDTO dto) {
        return service.simular(dto);
    }

    @GetMapping("/{usuarioId}")
    public List<Map<String, Object>> listar(@PathVariable UUID usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}
