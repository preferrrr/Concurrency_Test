package com.example.concurrency_test.pessimistic;

import com.example.concurrency_test.pessimistic.repository.PessimisticBoardRepository;
import com.example.concurrency_test.pessimistic.service.PessimisticBoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PessimisticBoardServiceTest {

    @Autowired
    private PessimisticBoardService boardService;

    @Autowired
    private PessimisticBoardRepository boardRepository;

    @DisplayName("비관적 락이 적용되어, 100명이 동시에 조회했을 때 조회수는 100이 된다.")
    @Test
    void pessimisticLock() throws InterruptedException {

        //given
        PessimisticBoard board = PessimisticBoard.create();
        boardRepository.save(board);

        //when
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    boardService.getBoardWithPessimisticLock(1l);

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();


        //then
        PessimisticBoard result = boardRepository.findById(1l).orElseThrow();

        assertThat(result.getView()).isEqualTo(100);
    }
}
