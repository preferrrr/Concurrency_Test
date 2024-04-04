package com.example.concurrency_test.pessimistic.repository;

import com.example.concurrency_test.pessimistic.PessimisticBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PessimisticBoardRepository extends JpaRepository<PessimisticBoard, Long>, PessimisticBoardRepositoryCustom {

}
