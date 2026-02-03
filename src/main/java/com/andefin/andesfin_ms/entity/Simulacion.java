package com.andesfin.andesfinms.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "simulaciones")
public class Simulacion {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "usuario_id", columnDefinition = "uuid")
    private UUID usuarioId;

    @Column(name = "fecha_simulacion")
    private LocalDateTime fechaSimulacion;

    @Column(name = "capital_disponible", precision = 10, scale = 2)
    private BigDecimal capitalDisponible;

    @Column(name = "costo_total", precision = 10, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "capital_restante", precision = 10, scale = 2)
    private BigDecimal capitalRestante;

    @Column(name = "ganancia_total", precision = 10, scale = 2)
    private BigDecimal gananciaTotal;

    @Column(name = "retorno_total_porcentaje", precision = 10, scale = 2)
    private BigDecimal retornoTotalPorcentaje;

    // Get y Set de Simulaciones
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }

    public LocalDateTime getFechaSimulacion() { return fechaSimulacion; }
    public void setFechaSimulacion(LocalDateTime fechaSimulacion) { this.fechaSimulacion = fechaSimulacion; }

    public BigDecimal getCapitalDisponible() { return capitalDisponible; }
    public void setCapitalDisponible(BigDecimal capitalDisponible) { this.capitalDisponible = capitalDisponible; }

    public BigDecimal getCostoTotal() { return costoTotal; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }

    public BigDecimal getCapitalRestante() { return capitalRestante; }
    public void setCapitalRestante(BigDecimal capitalRestante) { this.capitalRestante = capitalRestante; }

    public BigDecimal getGananciaTotal() { return gananciaTotal; }
    public void setGananciaTotal(BigDecimal gananciaTotal) { this.gananciaTotal = gananciaTotal; }

    public BigDecimal getRetornoTotalPorcentaje() { return retornoTotalPorcentaje; }
    public void setRetornoTotalPorcentaje(BigDecimal retornoTotalPorcentaje) { this.retornoTotalPorcentaje = retornoTotalPorcentaje; }
}
