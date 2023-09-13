package models;

import java.math.BigDecimal;
import java.util.Date;

public class TradeConfirmation extends Report {
    private String tradeId;
    private String tradeType;
    private BigDecimal tradePrice;
    private String currency;
    private Date tradeDate;
    private Date confirmationDate;

    public TradeConfirmation(String tradeId, String tradeType,BigDecimal tradePrice, String currency, Date tradeDate, Date confirmationDate) {
        super("Trade Confirmation");
        this.tradeId = tradeId;
        this.tradeType = tradeType;
        this.tradePrice = tradePrice;
        this.currency = currency;
        this.tradeDate = tradeDate;
        this.confirmationDate = confirmationDate;
    }

	@Override
	public void generateReport() {
		 System.out.println("Generating Trade Confirmation report...");
	        System.out.println("Trade ID: " + tradeId);
	        System.out.println("Trade Type: " + tradeType);
	        System.out.println("Trade Price: " + tradePrice);
	        System.out.println("Currency: " + currency);
	        System.out.println("Trade Date: " + tradeDate);
	        System.out.println("Confirmation Date: " + confirmationDate);
	}
	public boolean isTradeDateBeforeConfirmationDate() {
	    return tradeDate.before(confirmationDate);
	}
//	public boolean isValidTradeId() {
//        // Validation logic for tradeId
//		
//    }

    	public boolean isValidTradeType() {
    	    String[] validTradeTypes = {"stock", "mutual fund", "etf"};
    	    String lowercaseTradeType = tradeType.toLowerCase();   
    	    for (String validType : validTradeTypes) {
    	        if (lowercaseTradeType.equals(validType)) {
    	            return true; // Found a valid trade type
    	        }
    	    }
     return false; // None of the valid trade types matched	
    	}

	
	public boolean isValidTradeConfirmation() {
	    return   isValidTradeType()  && isTradeDateBeforeConfirmationDate() ;
	}
//
//	public String getTradeId() {
//		// TODO Auto-generated method stub
//		return tradeId;
//	}

	

	}
	






