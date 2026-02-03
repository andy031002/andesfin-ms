package com.andesfin.andesfinms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.andesfin.andesfinms.entity.Usuario;
import com.andesfin.andesfinms.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }
}
