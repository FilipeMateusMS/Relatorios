package com.filipe.relatorios.infrastructure.persistence.repository;

import com.filipe.relatorios.infrastructure.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long>
{
}
