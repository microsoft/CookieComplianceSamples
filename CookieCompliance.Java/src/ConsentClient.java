import java.util.Map;

import models.ConsentCategory;

public class ConsentClient {

	public static boolean GetConsentForCategory(String cookieString, ConsentCategory category) {
		Map<ConsentCategory, Boolean> userPermissions = CookieParserUtility.ParseCookiesString(cookieString);

		return userPermissions.get(category);
	}

	public static Map<ConsentCategory, Boolean> GetUserConsent(String cookieString) {
		
		Map<ConsentCategory, Boolean> userPermissions = CookieParserUtility.ParseCookiesString(cookieString);

		return null;
	}

}
