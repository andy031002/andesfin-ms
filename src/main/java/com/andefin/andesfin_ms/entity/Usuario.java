package com.andesfin.andesfinms.entity;

import java.math.BigDecimal;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String nombre;

    @Column(unique = true)
    private String email;

    @Column(name = "capital_disponible", precision = 10, scale = 2)
    private BigDecimal capitalDisponible;

    // getters/setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getCapitalDisponible() { return capitalDisponible; }
    public void setCapitalDisponible(BigDecimal capitalDisponible) { this.capitalDisponible = capitalDisponible; }
}
