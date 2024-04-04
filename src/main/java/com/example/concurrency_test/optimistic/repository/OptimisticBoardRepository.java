package com.example.concurrency_test.optimistic.repository;

import com.example.concurrency_test.optimistic.OptimisticBoard;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OptimisticBoardRepository extends JpaRepository<OptimisticBoard, Long> {
    @Lock(LockModeType.OPTIMISTIC)
    Optional<OptimisticBoard> findByBoardId(Long boardId);
}
