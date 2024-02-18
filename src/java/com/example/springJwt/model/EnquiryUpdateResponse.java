package com.example.springJwt.model;

public class EnquiryUpdateResponse {

    private String message;
    private Students enquiryDetails;
    
	public EnquiryUpdateResponse(String message, Students enquiryDetails) {
		super();
		this.message = message;
		this.enquiryDetails = enquiryDetails;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Students getEnquiryDetails() {
		return enquiryDetails;
	}
	public void setEnquiryDetails(Students enquiryDetails) {
		this.enquiryDetails = enquiryDetails;
	}
	@Override
	public String toString() {
		return "EnquiryUpdateResponse [message=" + message + ", enquiryDetails=" + enquiryDetails + "]";
	}
    
}
