package com.example.concurrency_test.optimistic.controller;

import com.example.concurrency_test.optimistic.service.OptimisticBoardService;
import com.example.concurrency_test.optimistic.service.OptimisticLockBoardSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OptimisticBoardController {

    private final OptimisticLockBoardSupport optimisticLockBoardSupport;
    private final OptimisticBoardService boardService;
    @GetMapping("/optimistic")
    public ResponseEntity<Long> createBoard() {
        Long id = boardService.createBoard();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/optimistic/{id}")
    public ResponseEntity<Integer> getBoard(@PathVariable Long id) {
        int view = optimisticLockBoardSupport.getBoard(id);

        return new ResponseEntity<>(view, HttpStatus.OK);
    }

}
