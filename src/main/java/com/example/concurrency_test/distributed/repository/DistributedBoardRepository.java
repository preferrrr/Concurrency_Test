package com.example.concurrency_test.distributed.repository;

import com.example.concurrency_test.distributed.DistributedBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributedBoardRepository extends JpaRepository<DistributedBoard, Long> {
}
