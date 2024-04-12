package com.example.concurrency_test.distributed.service;

import com.example.concurrency_test.common.annotation.DistributedLock;
import com.example.concurrency_test.distributed.DistributedBoard;
import com.example.concurrency_test.distributed.repository.DistributedBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DistributedBoardService {

    private final DistributedBoardRepository boardRepository;

    public Long createBoard() {
        DistributedBoard board = DistributedBoard.create();

        boardRepository.save(board);

        return board.getBoardId();
    }

    @DistributedLock(key = "'board'+#id")
    public void getBoardWithDistributedLock(Long id) {
        DistributedBoard distributedBoard = boardRepository.findById(id).orElseThrow();
        distributedBoard.addView();
    }


}
