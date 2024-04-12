package com.example.concurrency_test.distributed;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDistributedBoard is a Querydsl query type for DistributedBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDistributedBoard extends EntityPathBase<DistributedBoard> {

    private static final long serialVersionUID = 1810076173L;

    public static final QDistributedBoard distributedBoard = new QDistributedBoard("distributedBoard");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath content = createString("content");

    public final StringPath title = createString("title");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public QDistributedBoard(String variable) {
        super(DistributedBoard.class, forVariable(variable));
    }

    public QDistributedBoard(Path<? extends DistributedBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDistributedBoard(PathMetadata metadata) {
        super(DistributedBoard.class, metadata);
    }

}

