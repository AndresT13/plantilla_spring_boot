package com.progamming.lite.thinking.tareapractica.repository;

import com.progamming.lite.thinking.tareapractica.model.ClientModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    Optional<ClientModel> findByNumberDocument(Integer numberDocument);

    boolean existsByNumberDocument(Integer numberDocument);
}
