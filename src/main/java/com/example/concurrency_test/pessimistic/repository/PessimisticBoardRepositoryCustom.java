package com.example.concurrency_test.pessimistic.repository;

import com.example.concurrency_test.pessimistic.PessimisticBoard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessimisticBoardRepositoryCustom {

    public Optional<PessimisticBoard> getBoardWithPessimisticLock(Long id);
}
