package com.example.concurrency_test.optimistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockBoardSupport {

    private final OptimisticBoardService boardService;

    public void getBoard(Long id) {
        while (true) {
            try {
                boardService.getBoardWithOptimisticLock(id);
                break;
            } catch (Exception e) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
