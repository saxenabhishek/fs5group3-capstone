package com.fidelity.integration.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fidelity.business.*;

@Mapper
public interface PreferenceMapper {
	List<Preference> getAllPreference();
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);
	

}