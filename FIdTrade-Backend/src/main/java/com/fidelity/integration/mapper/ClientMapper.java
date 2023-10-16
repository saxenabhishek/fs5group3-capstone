package com.fidelity.integration.mapper;

import java.util.List;
import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.fidelity.business.Client;
import com.fidelity.business.ClientIdentification;
import com.fidelity.business.Person;
import com.fidelity.business.Preference;

@Mapper
public interface ClientMapper {
	Client getClientsByID(@Param("id") String id);
	Client getClientsByEmail(@Param("email") String email);
	String getIdFromEmail(@Param("email") String email);
	Integer checkIfRowExists(String string);
	
	void insertPerson(Person person);
	void insertClientIdentification(ClientIdentification clientIdentification, String clientId);
	void insertBalance(@Param("clientId") String clientId, @Param("balance") BigDecimal balance);
	int doesEmailAlreadyExist(@Param("email") String email);
	int doesClientIdentificationAlreadyExist(@Param("clientIdentification") ClientIdentification clientIdentification);	
	
    List<Preference> getAllPreference();
	Preference getPreferenceById(@Param("id") String id);
    int insertPreference(Preference preference);
    int updatePreference(Preference preference);

	@Update("update FT_CLIENT set wallet_bal = #{balance} WHERE id = #{clientId}")
    int updateClientWalletBalance(@Param("clientId") String clientId,@Param("balance") BigDecimal balance);
}
