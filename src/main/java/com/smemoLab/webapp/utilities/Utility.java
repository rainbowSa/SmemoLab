package com.smemoLab.webapp.utilities;

import java.time.LocalDate;
import java.time.Period;

public class Utility {

	public static boolean controlloNomeCognome (String st) {
		String controllo = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		if(st != null && !st.isEmpty() && st.matches(controllo)) {
			return true;
		}
		return false;	
	}

	public static boolean controlloPassword (String password) {
		String controllo =  "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
		if(password != null && !password.isEmpty() && password.matches(controllo)) {
			return true;
		}
		return false;
	}

	public static boolean controlloEmail (String email) {
		String controllo = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		if(email != null && !email.isEmpty() && email.matches(controllo)) {
			return true;
		}
		return false;
	}

    public static boolean controlloUsername(String username) {
        if(username != null && !username.isEmpty()) {
            return true;
        }
        return false;
    }

}
