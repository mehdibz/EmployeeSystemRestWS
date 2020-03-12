package ca.web.employee.jpa.data.util;

import java.util.regex.Pattern;

public class ValidateInfo {
	static final String EMP_ID_PATTERN="[A][0][0-9]{7}";
	public static int result = 0;
	public static String desc = "";
	
	public static int fieldsCheck(String id, String firstName, String lastName, String dob) {

		
		if (!Pattern.matches( EMP_ID_PATTERN, id)){
			desc="Invalid employee Id!";
			result = 701;
		}
		if(firstName.trim().length() == 0) {
			desc="Invalid first name!";
			result = 702;
		}
		if(lastName.trim().length() == 0) {
			desc="Invalid last name!";
			result = 703;
		}
		if(dob.trim().equals("")) {
			desc="Invalid date value!";
			result = 704;
		}
		return result;
		
	}
}
