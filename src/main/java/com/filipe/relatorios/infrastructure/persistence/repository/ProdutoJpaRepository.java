package com.filipe.relatorios.infrastructure.persistence.repository;

import com.filipe.relatorios.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long>
{
}
