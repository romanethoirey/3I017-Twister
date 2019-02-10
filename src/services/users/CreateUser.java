package services.users;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.BDTools;
import tools.user.UserTools;

public class CreateUser {
	
	public static JSONObject createUser(String login, String mdp, String mail, String nom, String prenom) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null || mail==null || prenom==null || nom == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(BDTools.checkUserExist(login))
				return ErrorJSON.serviceRefused("Utilisateur "+login+" existe deja", 1000);
			
			if(!UserTools.checkFormatMdp(mdp))
				return ErrorJSON.serviceRefused("Mauvais format de mot de passe", -1);
			
			if(!UserTools.checkFormatMail(mail))
				return ErrorJSON.serviceRefused("Mauvais format de mail", -1);
			
			if(!BDTools.insertUser(login, mdp, mail, nom, prenom))
				return ErrorJSON.serviceRefused("Impossible d'inserer dans la BD", 1000);
			
			retour = ErrorJSON.serviceAccepted();
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
