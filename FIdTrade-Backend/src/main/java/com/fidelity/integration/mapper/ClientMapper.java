package com.fidelity.integration.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper {
	@Select("select email from ft_person where email=#{value}")
	String getEmailExistence(String email);	
	
	@Select("select id from ft_person where email=#{email} and password=#{password}")
	String getPasswordExistence(String email, String password);
}
