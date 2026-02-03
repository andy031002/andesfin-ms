package com.andesfin.andesfinms.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.andesfin.andesfinms.entity.ProductoFinanciero;
import com.andesfin.andesfinms.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductoFinanciero> listarActivos() {
        return service.listarActivos();
    }
}
