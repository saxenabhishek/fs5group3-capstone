package com.fidelity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientMapper {
	//Preference
    List<Preference> getAllPreference();
	Preference getPreferenceById();
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);

	@Select("select email from ft_person where email=#{value}")
	String getEmailExistence(String email);	
	
	@Select("select id from ft_person where email=#{email} and password=#{password}")
	String getPasswordExistence(String email, String password);
}
