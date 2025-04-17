package org.zerock.todoserviceproject.domain.entity.base;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseTImeEntity is a Querydsl query type for BaseTImeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseTImeEntity extends EntityPathBase<BaseTImeEntity> {

    private static final long serialVersionUID = 92134925L;

    public static final QBaseTImeEntity baseTImeEntity = new QBaseTImeEntity("baseTImeEntity");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regdate = createDateTime("regdate", java.time.LocalDateTime.class);

    public QBaseTImeEntity(String variable) {
        super(BaseTImeEntity.class, forVariable(variable));
    }

    public QBaseTImeEntity(Path<? extends BaseTImeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseTImeEntity(PathMetadata metadata) {
        super(BaseTImeEntity.class, metadata);
    }

}

