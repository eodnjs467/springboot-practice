package com.example.testdb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserReply is a Querydsl query type for UserReply
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserReply extends EntityPathBase<UserReply> {

    private static final long serialVersionUID = -676827615L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserReply userReply = new QUserReply("userReply");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QUserBoard board;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> rno = createNumber("rno", Long.class);

    public final StringPath writer = createString("writer");

    public QUserReply(String variable) {
        this(UserReply.class, forVariable(variable), INITS);
    }

    public QUserReply(Path<? extends UserReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserReply(PathMetadata metadata, PathInits inits) {
        this(UserReply.class, metadata, inits);
    }

    public QUserReply(Class<? extends UserReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QUserBoard(forProperty("board"), inits.get("board")) : null;
    }

}

