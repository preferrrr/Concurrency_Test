package com.example.concurrency_test.pessimistic.service;

import com.example.concurrency_test.pessimistic.PessimisticBoard;
import com.example.concurrency_test.pessimistic.repository.PessimisticBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class PessimisticBoardService {

    private final PessimisticBoardRepository boardRepository;

    @Transactional(readOnly = false)
    public void getBoardWithPessimisticLock(Long id) {
        PessimisticBoard board = boardRepository.getBoardWithPessimisticLock(id).orElseThrow();
        board.addView();
    }

}
