package br.com.softdesign.votacaoserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@NoRepositoryBean

public interface JpaAndRevisionRepository<T> extends JpaRepository<T, UUID> {

    @Override
    @Query("SELECT p FROM #{#entityName} p where p.id = :id")
    T getById(@Param("id") UUID id);

}
