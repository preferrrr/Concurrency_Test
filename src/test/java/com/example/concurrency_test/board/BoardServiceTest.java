package com.example.concurrency_test.board;

import com.example.concurrency_test.board.repository.BoardRepository;
import com.example.concurrency_test.board.service.BoardService;
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
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    public void tearDown() {
        boardRepository.deleteAllInBatch();
    }


    @DisplayName("동시성 문제가 발생하여 100명이 동시에 조회했을 때 조회 수가 100보다 작다.")
    @Test
    void concurrencyTest() throws InterruptedException {

        //given
        Board board = Board.create();
        boardRepository.save(board);

        //when
        final int threadCount = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(32);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    boardService.getBoard(1l);

                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();


        //then
        Board result = boardRepository.findById(1l).orElseThrow();

        assertThat(result.getView()).isLessThan(100);
    }
}