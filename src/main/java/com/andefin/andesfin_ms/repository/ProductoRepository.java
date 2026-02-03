package com.andesfin.andesfinms.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.andesfin.andesfinms.entity.ProductoFinanciero;

public interface ProductoRepository extends JpaRepository<ProductoFinanciero, UUID> {
    List<ProductoFinanciero> findByActivoTrue();
}
