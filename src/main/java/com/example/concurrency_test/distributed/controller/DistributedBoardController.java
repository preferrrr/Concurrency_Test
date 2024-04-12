package com.example.concurrency_test.distributed.controller;

import com.example.concurrency_test.distributed.service.DistributedBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistributedBoardController {

    private final DistributedBoardService boardService;

    @GetMapping("/distributed")
    public ResponseEntity<Long> createBoard() {
        Long id = boardService.createBoard();

        return new ResponseEntity(id, HttpStatus.OK);
    }
    @GetMapping("/distributed/{id}")
    public ResponseEntity<String> getBoardWithDistributedLock(@PathVariable Long id) {
        boardService.getBoardWithDistributedLock(id);

        return ResponseEntity.ok("view");
    }
}
