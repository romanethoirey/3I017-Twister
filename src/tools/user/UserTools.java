package tools.user;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTools {

	public static boolean checkFormatMdp(String mdp) {

		if(mdp.length()<8)	
			return false;
		if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"))
			return false; 
		return true;
	}

	public static boolean checkFormatMail(String mdp) {


		if(mdp.length()<8)	
			return false;
		if (!mdp.matches("^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$"))
			return false;
		return true;
	}
	


}