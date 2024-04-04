package com.example.concurrency_test.optimistic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptimisticBoard is a Querydsl query type for OptimisticBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOptimisticBoard extends EntityPathBase<OptimisticBoard> {

    private static final long serialVersionUID = -942982505L;

    public static final QOptimisticBoard optimisticBoard = new QOptimisticBoard("optimisticBoard");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath title = createString("title");

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QOptimisticBoard(String variable) {
        super(OptimisticBoard.class, forVariable(variable));
    }

    public QOptimisticBoard(Path<? extends OptimisticBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptimisticBoard(PathMetadata metadata) {
        super(OptimisticBoard.class, metadata);
    }

}

