package com.fidelity.business;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

@MappedTypes(LengthOfInvestment.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class LengthOfInvestmentHandler extends EnumTypeHandler<LengthOfInvestment>{
	public LengthOfInvestmentHandler(Class<LengthOfInvestment> type) {
        super(type);
    }

}
