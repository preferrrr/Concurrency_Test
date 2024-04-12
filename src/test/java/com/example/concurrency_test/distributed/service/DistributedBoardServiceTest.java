package com.example.concurrency_test.distributed.service;

import com.example.concurrency_test.distributed.DistributedBoard;
import com.example.concurrency_test.distributed.repository.DistributedBoardRepository;
import com.example.concurrency_test.optimistic.OptimisticBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DistributedBoardServiceTest {

    @Autowired
    private DistributedBoardService boardService;

    @Autowired
    private DistributedBoardRepository boardRepository;


    @DisplayName("redisson 분산락을 적용하여, 100명이 동시에 조회했을 때 조회 수가 100이 된다.")
    @Test
    void distributedLockTest() throws InterruptedException {

        //given
        DistributedBoard board = DistributedBoard.create();
        boardRepository.save(board);

        //when
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    boardService.getBoardWithDistributedLock(board.getBoardId());

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        //then
        DistributedBoard result = boardRepository.findById(board.getBoardId()).orElseThrow();

        assertThat(result.getView()).isEqualTo(100);
    }
}