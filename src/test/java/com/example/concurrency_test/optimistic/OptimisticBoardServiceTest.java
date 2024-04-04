package com.example.concurrency_test.optimistic;

import com.example.concurrency_test.optimistic.repository.OptimisticBoardRepository;
import com.example.concurrency_test.optimistic.service.OptimisticLockBoardSupport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OptimisticBoardServiceTest {

    @Autowired
    private OptimisticLockBoardSupport optimisticLockBoardSupport;

    @Autowired
    private OptimisticBoardRepository boardRepository;

    @AfterEach
    public void tearDown() {
        boardRepository.deleteAllInBatch();
    }


    @DisplayName("낙관적 락을 적용하여, 100명이 동시에 조회했을 때 조회 수가 100이 된다.")
    @Test
    void optimisticLockTest() throws InterruptedException {

        //given
        OptimisticBoard board = OptimisticBoard.create();
        boardRepository.save(board);

        //when
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    optimisticLockBoardSupport.getBoard(1l);

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();


        //then
        OptimisticBoard result = boardRepository.findById(1l).orElseThrow();

        assertThat(result.getView()).isEqualTo(100);
    }
}