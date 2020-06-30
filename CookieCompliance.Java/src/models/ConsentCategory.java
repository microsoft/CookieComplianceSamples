package models;

import java.util.HashMap;
import java.util.Map;

public enum ConsentCategory {

	REQUIRED("c0"), 
	ANALYTICS("c1"), 
	SOCIALMEDIA("c2"), 
	ADVERTISING("c3"), 
	PERSONALIZATION("c4");

	private String categoryString;
	private static final Map<String, ConsentCategory> reverseLookUp = new HashMap<>();

	static {
		for (ConsentCategory consentCategory : ConsentCategory.values()) {
			reverseLookUp.put(consentCategory.getCategoryString(), consentCategory);
		}
	}

	ConsentCategory(String categoryString) {
		this.categoryString = categoryString;
	}

	public String getCategoryString() {
		return categoryString;
	}

	public static ConsentCategory getConsentCategory(String categoryRepresentation) {
		ConsentCategory category = reverseLookUp.get(categoryRepresentation);
		if (category == null) {
			throw new IllegalArgumentException("Cookie category does not exist");
		}
		return category;
	}
}
