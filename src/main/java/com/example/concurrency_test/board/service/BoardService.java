package com.example.concurrency_test.board.service;

import com.example.concurrency_test.board.Board;
import com.example.concurrency_test.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void getBoard(Long id) {
        Board board = boardRepository.findByBoardId(id).orElseThrow();
        board.addView();
    }
}
