package org.zerock.todoserviceproject.domain.entity.archive;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QArchiveTodoEntity is a Querydsl query type for ArchiveTodoEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArchiveTodoEntity extends EntityPathBase<ArchiveTodoEntity> {

    private static final long serialVersionUID = -2060246226L;

    public static final QArchiveTodoEntity archiveTodoEntity = new QArchiveTodoEntity("archiveTodoEntity");

    public final org.zerock.todoserviceproject.domain.entity.base.QBaseArchiveTimeEntity _super = new org.zerock.todoserviceproject.domain.entity.base.QBaseArchiveTimeEntity(this);

    public final BooleanPath complete = createBoolean("complete");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Integer> delta = _super.delta;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> expireDate = _super.expireDate;

    public final DateTimePath<java.time.LocalDateTime> from = createDateTime("from", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regdate = _super.regdate;

    public final StringPath title = createString("title");

    public final NumberPath<Long> tno = createNumber("tno", Long.class);

    public final DateTimePath<java.time.LocalDateTime> to = createDateTime("to", java.time.LocalDateTime.class);

    public final StringPath writer = createString("writer");

    public QArchiveTodoEntity(String variable) {
        super(ArchiveTodoEntity.class, forVariable(variable));
    }

    public QArchiveTodoEntity(Path<? extends ArchiveTodoEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArchiveTodoEntity(PathMetadata metadata) {
        super(ArchiveTodoEntity.class, metadata);
    }

}

