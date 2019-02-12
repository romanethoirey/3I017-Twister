package services.users;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.user.UserBDTools;

public class LogUser {

	public static JSONObject login(String login, String mdp) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null ) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(!UserBDTools.checkUserExist(login))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			if(!UserBDTools.checkUserMdp(login,mdp))
				return ErrorJSON.serviceRefused("Mot de passe oublie ?", 1000);
			int id_user = UserBDTools.getUserId(login);
			String key = UserBDTools.insertConnexion(id_user, false);
			retour = ErrorJSON.serviceAccepted();
			retour.put("key", key);
		}
		catch (JSONException e){
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme", 1000);
		}
		
		return retour;
	}
	
}