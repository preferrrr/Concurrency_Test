package com.example.concurrency_test.pessimistic;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPessimisticBoard is a Querydsl query type for PessimisticBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPessimisticBoard extends EntityPathBase<PessimisticBoard> {

    private static final long serialVersionUID = 1108905405L;

    public static final QPessimisticBoard pessimisticBoard = new QPessimisticBoard("pessimisticBoard");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QPessimisticBoard(String variable) {
        super(PessimisticBoard.class, forVariable(variable));
    }

    public QPessimisticBoard(Path<? extends PessimisticBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPessimisticBoard(PathMetadata metadata) {
        super(PessimisticBoard.class, metadata);
    }

}

