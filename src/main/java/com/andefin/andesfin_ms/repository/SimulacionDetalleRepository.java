package com.andesfin.andesfinms.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andesfin.andesfinms.entity.SimulacionDetalle;

public interface SimulacionDetalleRepository extends JpaRepository<SimulacionDetalle, UUID> {
    List<SimulacionDetalle> findBySimulacionId(UUID simulacionId);
    long countBySimulacionId(UUID simulacionId);
}
