package com.example.concurrency_test.pessimistic.repository;

import com.example.concurrency_test.pessimistic.PessimisticBoard;
import com.example.concurrency_test.pessimistic.QPessimisticBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PessimisticBoardRepositoryCustomImpl implements PessimisticBoardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QPessimisticBoard board = QPessimisticBoard.pessimisticBoard;

    @Override
    public Optional<PessimisticBoard> getBoardWithPessimisticLock(Long id) {

        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(board)
                        .where(board.boardId.eq(id))
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne());
    }
}
