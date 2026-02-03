package com.andesfin.andesfinms.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.andesfin.andesfinms.entity.ProductoFinanciero;
import com.andesfin.andesfinms.repository.ProductoRepository;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<ProductoFinanciero> listarActivos() {
        return repo.findByActivoTrue();
    }
}
