package com.fidelity.business;


import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(RiskTolerance.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class RiskToleranceHandler extends EnumTypeHandler<RiskTolerance>{
	public RiskToleranceHandler(Class<RiskTolerance> type) {
        super(type);
    }

}
