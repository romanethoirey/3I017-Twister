package services.relation;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.user.UserBDTools;

public class ListFriend {
	
	public static JSONObject getListFriends(String userKey) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(userKey==null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			if(!UserBDTools.checkKey(userKey))
				return ErrorJSON.serviceRefused("Echec authentification cl� utilisateur", 1000);
			
			//Manque BD
			
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}

}