package com.cerner.pctorion.chart;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;




/*
 * author: rg042938 / rajesh
 * */


public class VisitListDetailsPage{


	@FindBy(how = How.CSS, using="div.terraVM-ActionToolbar-rightButtons > button")
	public WebElement closeBtn;

	@FindBy(how = How.CSS, using="div.terraVM-DetailViewTitle-primaryText")
	public WebElement titleTxt;

	@FindBy(how = How.CSS, using = "div.terraVM-TitleValueCard-titleText")
	public List<WebElement> encDetailFields;

	Boolean status = false;
	public Boolean verifyEncField(EncounterFields encounterFields)
	{
	
		for(WebElement fields : encDetailFields)
		{
			if( fields.getText().equals(getVstLstFieldsData(encounterFields)))
			{
				return true;
			}		
		}
		return status;
	}
	
	//Actual Data Strings

		String admitDate;    String dischargeDate; String reasonForVisit; String service;
		String attendingPhy; String location;      String room;           String fin;
		String encType;      String encStatus;

		public enum EncounterFields
		{ADMIT_DATE, DISCHARGE_DATE, REASON_FOR_VISIT, SERVICE, ATTENDING_PHY, LOCATION, ROOM, FIN, ENC_TYPE, ENC_STATUS};

		
		String getVstLstFieldsData(EncounterFields data)
		{
			String value=null;

			switch(data)
			{
			case ADMIT_DATE: 
				admitDate = "Admit Date";  
				value = admitDate; 
				break;
			case DISCHARGE_DATE: 
				dischargeDate = "Discharge Date";  
				value = dischargeDate; 
				break;
			case REASON_FOR_VISIT:
				reasonForVisit = "Reason for Visit";
				value=reasonForVisit;
				break;
			case SERVICE:
				service = "Service";
				value=service;
				break;
			case ATTENDING_PHY:
				attendingPhy = "Attending";
				value=attendingPhy;
				break;
			case LOCATION:
				location = "Location";
				value=location;
				break;
			case ROOM:
				room = "Room/Bed";
				value=room;
				break;
			case FIN:
				fin = "FIN";
				value=fin;
				break;
			case ENC_TYPE:
				encType = "Type";
				value=encType;
				break;
			case ENC_STATUS:
				encStatus = "Status";	
				value=encStatus;
				break;
			}

			return value;
		}

}
