import models.ConsentCategory;

public class Main {

	public static void main(String[] args) {
		String cookieString="c0=0,c1=1,c2=2,c3=0,c4=0,cid=random-consent-id";
		
		// c0
		System.out.println(ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.REQUIRED));
		// c1
		System.out.println(ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.THIRDPARTYANALYTICS));
		// c2
		System.out.println(ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.SOCIALMEDIA));
		// c3
		System.out.println(ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.ADVERTISING));
		// c4
		System.out.println(ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.PERSONALIZATION));
		
		System.out.println(ConsentClient.GetConsentForCategory("NR", ConsentCategory.PERSONALIZATION));

		System.out.println(ConsentClient.GetConsentForCategory("NR, c0=1", ConsentCategory.PERSONALIZATION));
		
		System.out.println(ConsentClient.GetConsentForCategory("c0=1,NR", ConsentCategory.PERSONALIZATION));
		
		// Get entire user consent
		System.out.println(ConsentClient.GetUserConsent(cookieString));
		
		System.out.println(ConsentClient.GetUserConsent("NR"));

		System.out.println(ConsentClient.GetUserConsent("NR, c0=1"));
		
		System.out.println(ConsentClient.GetUserConsent("c0=1,NR"));
		
	}

}
