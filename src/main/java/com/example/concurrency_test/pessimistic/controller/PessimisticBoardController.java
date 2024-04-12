package com.example.concurrency_test.pessimistic.controller;

import com.example.concurrency_test.pessimistic.service.PessimisticBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PessimisticBoardController {

    private final PessimisticBoardService boardService;

    @GetMapping("/pessimistic")
    public ResponseEntity<Long> createBoard() {
        Long id = boardService.create();

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/pessimistic/{id}")
    public ResponseEntity<Integer> getBoard(@PathVariable Long id) {
        int view = boardService.getBoardWithPessimisticLock(id);

        return new ResponseEntity<>(view, HttpStatus.OK);
    }
}
