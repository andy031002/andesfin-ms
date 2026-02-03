package com.andesfin.andesfinms.entity;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "simulacion_detalle")
public class SimulacionDetalle {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "simulacion_id", columnDefinition = "uuid")
    private UUID simulacionId;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(precision = 5, scale = 2)
    private BigDecimal riesgo;

    @Column(name = "porcentaje_ganancia", precision = 5, scale = 2)
    private BigDecimal porcentajeGanancia;

    @Column(name = "ganancia_esperada", precision = 10, scale = 2)
    private BigDecimal gananciaEsperada;

    @Column(name = "retorno_porcentaje", precision = 10, scale = 2)
    private BigDecimal retornoPorcentaje;

    // Get y Set de Simulacion Detalle
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getSimulacionId() { return simulacionId; }
    public void setSimulacionId(UUID simulacionId) { this.simulacionId = simulacionId; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public BigDecimal getRiesgo() { return riesgo; }
    public void setRiesgo(BigDecimal riesgo) { this.riesgo = riesgo; }

    public BigDecimal getPorcentajeGanancia() { return porcentajeGanancia; }
    public void setPorcentajeGanancia(BigDecimal porcentajeGanancia) { this.porcentajeGanancia = porcentajeGanancia; }

    public BigDecimal getGananciaEsperada() { return gananciaEsperada; }
    public void setGananciaEsperada(BigDecimal gananciaEsperada) { this.gananciaEsperada = gananciaEsperada; }

    public BigDecimal getRetornoPorcentaje() { return retornoPorcentaje; }
    public void setRetornoPorcentaje(BigDecimal retornoPorcentaje) { this.retornoPorcentaje = retornoPorcentaje; }
}
