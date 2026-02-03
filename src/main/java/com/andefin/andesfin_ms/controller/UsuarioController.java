package com.andesfin.andesfinms.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.andesfin.andesfinms.entity.Usuario;
import com.andesfin.andesfinms.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }
}
