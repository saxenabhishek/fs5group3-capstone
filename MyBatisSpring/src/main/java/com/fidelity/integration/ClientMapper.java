package com.fidelity.integration;

import org.apache.ibatis.annotations.Select;

public interface ClientMapper {
	@Select("select email from ft_person where email=#{value}")
	String getEmailExistence(String email);	
	
	@Select("select id from ft_person where email=#{arg0} and password=#{arg1}")
	String getPasswordExistence(String email, String password);
}
