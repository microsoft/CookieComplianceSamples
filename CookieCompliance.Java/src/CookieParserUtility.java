import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.ConsentCategory;

public class CookieParserUtility {

	private static final String NRString = "NR";
	private static final int ConsentGrantedStatus = 2;

	public static Map<ConsentCategory, Boolean> ParseCookiesString(String cookieString) {
		if (cookieString == null || cookieString.trim().isEmpty()) {
			return GetDefaultConsents(false);
		}

		if (IsConsentNotRequired(cookieString)) {
			return GetDefaultConsents(true);
		}

		Map<ConsentCategory, Boolean> categoryConsents = GetDefaultConsents(false);
		Map<String, String> rawKeyValuePairs = GetRawKeyValuePairs(cookieString);

		rawKeyValuePairs.forEach((rawConsentCategory, rawConsentCategoryStatus) -> {
			if (!rawConsentCategory.equalsIgnoreCase("ConsentId")) {
				ConsentCategory consentCategory = ConsentCategory.getConsentCategory(rawConsentCategory);
				boolean consentStatus = GetConsentStatus(rawConsentCategoryStatus);
				categoryConsents.put(consentCategory, consentStatus);
			}
		});

		return categoryConsents;
	}

	private static boolean GetConsentStatus(String rawConsentCategoryStatus) {

		int consentStatus = Integer.parseInt(rawConsentCategoryStatus);
		if (!(consentStatus >= 0 && consentStatus < 3)) {
			throw new IllegalArgumentException("Category consent value is not valid.");
		}
		return consentStatus == ConsentGrantedStatus;
	}

	private static Map<String, String> GetRawKeyValuePairs(String cookieString) {
		Map<String, String> keyValuePairs = new HashMap<String, String>();

		String[] rawCookieCategory = cookieString.split(";");
		for (String rawConsentValue : rawCookieCategory) {
			String trimmedValue = rawConsentValue.trim();
			if (trimmedValue == null || trimmedValue.isEmpty()) {
				continue;
			}

			String[] cookieKeyValue = trimmedValue.split("=");

			if (cookieKeyValue.length != 2) {
				throw new IllegalArgumentException("Error with the cookie value. Ensure that it is passed in as set.");
			}

			keyValuePairs.put(cookieKeyValue[0], cookieKeyValue[1]);
		}

		return keyValuePairs;
	}

	private static boolean IsConsentNotRequired(String cookieString) {
		List<String> keyValuePair = Arrays.asList(cookieString.split(";"));

		return keyValuePair.stream().anyMatch(NRString::equalsIgnoreCase);
	}

	private static Map<ConsentCategory, Boolean> GetDefaultConsents(Boolean defaultValue) {
		defaultValue = defaultValue == null ? false : true;
		Map<ConsentCategory, Boolean> defaultConsents = new HashMap<>();
		for (ConsentCategory consentCategory : ConsentCategory.values()) {
			defaultConsents.put(consentCategory, defaultValue);
		}

		return defaultConsents;
	}
}
