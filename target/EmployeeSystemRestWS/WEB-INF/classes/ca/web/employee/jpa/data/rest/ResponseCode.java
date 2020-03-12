package ca.web.employee.jpa.data.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseCode {
	String code;
	String description; 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ResponseCode [Code=" + code + ", Description ="	+ description + "]";
	}
}
