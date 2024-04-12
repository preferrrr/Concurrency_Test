package com.example.concurrency_test.optimistic.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockBoardSupport {

    private final OptimisticBoardService boardService;

    public int getBoard(Long id) {
        while (true) {
            try {
                return boardService.getBoardWithOptimisticLock(id);
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
