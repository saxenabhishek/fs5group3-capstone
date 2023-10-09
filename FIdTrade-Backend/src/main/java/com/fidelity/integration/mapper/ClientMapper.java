package com.fidelity.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fidelity.business.Preference;

@Mapper
public interface ClientMapper {
	//Preference
    List<Preference> getAllPreference();
	Preference getPreferenceById(@Param("id") String id);
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);

	/**
	 * @Select("select email from ft_person where email=#{value}")
	 * @param email
	 * @return
	 * String getEmailExistence(String email);	
	
	@Select("select id from ft_person where email=#{email} and password=#{password}")
	String getPasswordExistence(String email, String password);
	 */

}
