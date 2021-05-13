package com.example.testdb.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBoard is a Querydsl query type for UserBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserBoard extends EntityPathBase<UserBoard> {

    private static final long serialVersionUID = -691320291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBoard userBoard = new QUserBoard("userBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public final QUserInfo writer;

    public QUserBoard(String variable) {
        this(UserBoard.class, forVariable(variable), INITS);
    }

    public QUserBoard(Path<? extends UserBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBoard(PathMetadata metadata, PathInits inits) {
        this(UserBoard.class, metadata, inits);
    }

    public QUserBoard(Class<? extends UserBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writer = inits.isInitialized("writer") ? new QUserInfo(forProperty("writer")) : null;
    }

}

