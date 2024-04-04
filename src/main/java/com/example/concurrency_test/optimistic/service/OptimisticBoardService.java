package com.example.concurrency_test.optimistic.service;

import com.example.concurrency_test.optimistic.OptimisticBoard;
import com.example.concurrency_test.optimistic.repository.OptimisticBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptimisticBoardService {

    private final OptimisticBoardRepository boardRepository;

    @Transactional
    public void getBoardWithOptimisticLock(Long id) {
        OptimisticBoard board = boardRepository.findByBoardId(id).orElseThrow();
        board.addView();
    }

}
