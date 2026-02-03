package com.andesfin.andesfinms.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andesfin.andesfinms.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {}
