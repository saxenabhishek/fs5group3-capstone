/*
 * package com.fidelity.services; import java.util.Collections; import
 * java.util.HashSet; import java.util.Set;
 * 
 * import com.fidelity.business.Preference;
 * 
 * public class ClientService { private Set<Preference> preferences ;
 * 
 * 
 * public ClientService() {
 * 
 * this.preferences = new HashSet<>();
 * 
 * 
 * } public void addPreference(Preference pre) { if(pre==null) { throw new
 * NullPointerException("Preference can't be null "); } if(pre.isAccept()) {
 * preferences.add(pre); }
 * 
 * }
 * 
 * public void updatePreference(String purpose,String risk,String
 * category,String length) { if (purpose==null || risk==null || category==null
 * || length==null) { throw new NullPointerException("All fields are required");
 * } for (Preference pre:preferences) { pre.setAccept(true);
 * if(!pre.getInvestmentPurpose().equals(purpose)) { if(purpose.length()>1) {
 * pre.setInvestmentPurpose(purpose); }
 * 
 * }
 * 
 * if(!pre.getRiskTolerance().equals(risk)) { if(risk.length()>1) {
 * pre.setRiskTolerance(risk); }
 * 
 * } if(!pre.getIncomeCategory().equals(category)) { if(category.length()>1) {
 * pre.setIncomeCategory(category); }
 * 
 * } if(!pre.getLengthOfInvestmet().equals(length)) { if(length.length()>1) {
 * pre.setLengthOfInvestmet(length); }
 * 
 * }
 * 
 * } }
 * 
 * public Set<Preference> getPreferences() { return
 * Collections.unmodifiableSet(preferences); }
 * 
 * }
 */
