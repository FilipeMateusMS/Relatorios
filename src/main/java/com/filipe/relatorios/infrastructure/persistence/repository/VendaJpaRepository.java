package com.filipe.relatorios.infrastructure.persistence.repository;

import com.filipe.relatorios.infrastructure.persistence.entity.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaJpaRepository extends JpaRepository<VendaEntity, Long>
{
    List<VendaEntity> findByDtVendaBetween( LocalDateTime inicio, LocalDateTime fim );
}
