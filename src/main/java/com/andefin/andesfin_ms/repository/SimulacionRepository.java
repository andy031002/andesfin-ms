package com.andesfin.andesfinms.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andesfin.andesfinms.entity.Simulacion;

public interface SimulacionRepository extends JpaRepository<Simulacion, UUID> {
    List<Simulacion> findByUsuarioIdOrderByFechaSimulacionDesc(UUID usuarioId);
}
