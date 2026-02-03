package com.andesfin.andesfinms.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.*;

public class SimulacionRequestDTO {

    @NotNull
    private UUID usuario_id;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal capital_disponible;

    @NotEmpty
    private List<ProductoCandidatoDTO> productos;

    public static class ProductoCandidatoDTO {
        @NotBlank
        private String nombre;

        @NotNull
        @DecimalMin("0.00")
        private BigDecimal precio;

        @NotNull
        private BigDecimal porcentaje_ganancia;

        @NotNull
        private BigDecimal riesgo;

        // G Y S
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public BigDecimal getPrecio() { return precio; }
        public void setPrecio(BigDecimal precio) { this.precio = precio; }

        public BigDecimal getPorcentaje_ganancia() { return porcentaje_ganancia; }
        public void setPorcentaje_ganancia(BigDecimal porcentaje_ganancia) { this.porcentaje_ganancia = porcentaje_ganancia; }

        public BigDecimal getRiesgo() { return riesgo; }
        public void setRiesgo(BigDecimal riesgo) { this.riesgo = riesgo; }
    }

    // GET Y SET DE SIMULACIONES
    public UUID getUsuario_id() { return usuario_id; }
    public void setUsuario_id(UUID usuario_id) { this.usuario_id = usuario_id; }

    public BigDecimal getCapital_disponible() { return capital_disponible; }
    public void setCapital_disponible(BigDecimal capital_disponible) { this.capital_disponible = capital_disponible; }

    public List<ProductoCandidatoDTO> getProductos() { return productos; }
    public void setProductos(List<ProductoCandidatoDTO> productos) { this.productos = productos; }
}
