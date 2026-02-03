package com.andesfin.andesfinms.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andesfin.andesfinms.dto.SimulacionRequestDTO;
import com.andesfin.andesfinms.entity.Simulacion;
import com.andesfin.andesfinms.entity.SimulacionDetalle;
import com.andesfin.andesfinms.repository.SimulacionDetalleRepository;
import com.andesfin.andesfinms.repository.SimulacionRepository;
import com.andesfin.andesfinms.repository.UsuarioRepository;

@Service
public class SimulacionService {

    private final UsuarioRepository usuarioRepo;
    private final SimulacionRepository simulacionRepo;
    private final SimulacionDetalleRepository detalleRepo;

    public SimulacionService(UsuarioRepository usuarioRepo,
                             SimulacionRepository simulacionRepo,
                             SimulacionDetalleRepository detalleRepo) {
        this.usuarioRepo = usuarioRepo;
        this.simulacionRepo = simulacionRepo;
        this.detalleRepo = detalleRepo;
    }

    @Transactional
    public Map<String, Object> simular(SimulacionRequestDTO dto) {

        // validar usuario
        usuarioRepo.findById(dto.getUsuario_id())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        BigDecimal capital = dto.getCapital_disponible();

        // solo productos viables individualmente
        List<SimulacionRequestDTO.ProductoCandidatoDTO> viables = dto.getProductos()
                .stream()
                .filter(p -> p.getPrecio().compareTo(capital) <= 0)
                .toList();

        if (viables.isEmpty()) {
            BigDecimal min = dto.getProductos().stream()
                    .map(SimulacionRequestDTO.ProductoCandidatoDTO::getPrecio)
                    .min(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);

            Map<String, Object> err = new LinkedHashMap<>();
            err.put("error", "Fondos insuficientes");
            err.put("detalle", "El capital disponible es insuficiente para adquirir cualquier producto de la lista.");
            err.put("capital_disponible", capital);
            err.put("producto_mas_barato", min);
            err.put("diferencia_necesaria", min.subtract(capital).max(BigDecimal.ZERO));
            err.put("recomendacion", "Aumente su capital o consulte productos con menor inversión mínima.");
            throw new IllegalArgumentException(err.toString());
        }

        int n = viables.size();

        BigDecimal mejorGanancia = new BigDecimal("-1");
        BigDecimal mejorCosto = BigDecimal.ZERO;
        BigDecimal mejorRiesgo = new BigDecimal("999999");
        List<SimulacionRequestDTO.ProductoCandidatoDTO> mejorSeleccion = new ArrayList<>();

        // evaluar combinaciones
        for (int mask = 1; mask < (1 << n); mask++) {
            BigDecimal costo = BigDecimal.ZERO;
            BigDecimal ganancia = BigDecimal.ZERO;
            BigDecimal riesgo = BigDecimal.ZERO;
            List<SimulacionRequestDTO.ProductoCandidatoDTO> seleccion = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    var p = viables.get(i);
                    costo = costo.add(p.getPrecio());
                    if (costo.compareTo(capital) > 0) break;

                    BigDecimal g = p.getPrecio()
                            .multiply(p.getPorcentaje_ganancia())
                            .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

                    ganancia = ganancia.add(g);
                    riesgo = riesgo.add(p.getRiesgo()); // simple y explicable
                    seleccion.add(p);
                }
            }

            if (costo.compareTo(capital) > 0) continue;

            boolean esMejor =
                    ganancia.compareTo(mejorGanancia) > 0 ||
                    (ganancia.compareTo(mejorGanancia) == 0 && riesgo.compareTo(mejorRiesgo) < 0) ||
                    (ganancia.compareTo(mejorGanancia) == 0 && riesgo.compareTo(mejorRiesgo) == 0 && costo.compareTo(mejorCosto) > 0);

            if (esMejor) {
                mejorGanancia = ganancia;
                mejorCosto = costo;
                mejorRiesgo = riesgo;
                mejorSeleccion = seleccion;
            }
        }

        BigDecimal capitalRestante = capital.subtract(mejorCosto);

        BigDecimal retornoTotalPorc = BigDecimal.ZERO;
        if (mejorCosto.compareTo(BigDecimal.ZERO) > 0) {
            retornoTotalPorc = mejorGanancia
                    .multiply(new BigDecimal("100"))
                    .divide(mejorCosto, 2, RoundingMode.HALF_UP);
        }

        // persistir simulación
        Simulacion sim = new Simulacion();
        sim.setUsuarioId(dto.getUsuario_id());
        sim.setFechaSimulacion(LocalDateTime.now());
        sim.setCapitalDisponible(capital.setScale(2, RoundingMode.HALF_UP));
        sim.setCostoTotal(mejorCosto.setScale(2, RoundingMode.HALF_UP));
        sim.setCapitalRestante(capitalRestante.setScale(2, RoundingMode.HALF_UP));
        sim.setGananciaTotal(mejorGanancia.setScale(2, RoundingMode.HALF_UP));
        sim.setRetornoTotalPorcentaje(retornoTotalPorc.setScale(2, RoundingMode.HALF_UP));

        Simulacion guardada = simulacionRepo.save(sim);

        // guardar detalle
        List<Map<String, Object>> detalleResp = new ArrayList<>();
        for (var p : mejorSeleccion) {
            BigDecimal g = p.getPrecio()
                    .multiply(p.getPorcentaje_ganancia())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

            SimulacionDetalle det = new SimulacionDetalle();
            det.setSimulacionId(guardada.getId());
            det.setNombreProducto(p.getNombre());
            det.setPrecio(p.getPrecio().setScale(2, RoundingMode.HALF_UP));
            det.setRiesgo(p.getRiesgo().setScale(2, RoundingMode.HALF_UP));
            det.setPorcentajeGanancia(p.getPorcentaje_ganancia().setScale(2, RoundingMode.HALF_UP));
            det.setGananciaEsperada(g);
            det.setRetornoPorcentaje(p.getPorcentaje_ganancia().setScale(2, RoundingMode.HALF_UP));

            detalleRepo.save(det);

            Map<String, Object> item = new LinkedHashMap<>();
            item.put("nombre", det.getNombreProducto());
            item.put("precio", det.getPrecio());
            item.put("riesgo", det.getRiesgo());
            item.put("porcentaje_ganancia", det.getPorcentajeGanancia());
            item.put("ganancia_esperada", det.getGananciaEsperada());
            detalleResp.add(item);
        }

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("id", guardada.getId());
        resp.put("usuario_id", guardada.getUsuarioId());
        resp.put("fecha_simulacion", guardada.getFechaSimulacion());
        resp.put("capital_disponible", guardada.getCapitalDisponible());
        resp.put("productos_seleccionados", detalleResp);
        resp.put("costo_total", guardada.getCostoTotal());
        resp.put("capital_restante", guardada.getCapitalRestante());
        resp.put("ganancia_total", guardada.getGananciaTotal());
        resp.put("retorno_total_porcentaje", guardada.getRetornoTotalPorcentaje());
        resp.put("mensaje", "Simulación registrada con selección óptima según el capital disponible.");

        return resp;
    }

    public List<Map<String, Object>> listarPorUsuario(UUID usuarioId) {
        var sims = simulacionRepo.findByUsuarioIdOrderByFechaSimulacionDesc(usuarioId);

        List<Map<String, Object>> out = new ArrayList<>();
        for (var s : sims) {
            long cantidad = detalleRepo.countBySimulacionId(s.getId());

            Map<String, Object> row = new LinkedHashMap<>();
            row.put("id", s.getId());
            row.put("usuario_id", s.getUsuarioId());
            row.put("fecha_simulacion", s.getFechaSimulacion());
            row.put("capital_disponible", s.getCapitalDisponible());
            row.put("ganancia_total", s.getGananciaTotal());
            row.put("cantidad_productos", cantidad);
            row.put("retorno_porcentaje", s.getRetornoTotalPorcentaje());
            out.add(row);
        }
        return out;
    }
}
