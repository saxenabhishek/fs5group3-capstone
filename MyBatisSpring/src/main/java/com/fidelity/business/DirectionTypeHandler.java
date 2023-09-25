package com.fidelity.business;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(Direction.class)
@MappedJdbcTypes(JdbcType.CHAR)
public class DirectionTypeHandler extends EnumTypeHandler<Direction> {
	public DirectionTypeHandler(Class<Direction> type) {
        super(type);
    }
}
