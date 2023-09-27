package com.fidelity.business;

import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(IncomeCategory.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class IncomeCategoryHandler extends EnumTypeHandler<IncomeCategory>{
	public IncomeCategoryHandler(Class<IncomeCategory> type) {
        super(type);
    }

}
