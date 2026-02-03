package com.andefin.andesfinms.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos_financieros")
public class ProductoFinanciero {

    @Id
    private UUID id;

    private String nombre;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "porcentaje_retorno", precision = 5, scale = 2)
    private BigDecimal porcentajeRetorno;

    private Boolean activo;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }

    public BigDecimal getPorcentajeRetorno() { return porcentajeRetorno; }
    public void setPorcentajeRetorno(BigDecimal porcentajeRetorno) { this.porcentajeRetorno = porcentajeRetorno; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}

