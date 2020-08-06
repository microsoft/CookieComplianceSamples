import models.ConsentCategory;

public class Main {

	public static void main(String[] args) {
		String cookieString="c0=0-c1=1-c2=2-c3=0-cid=randomconsentid";
		
		// c0
		System.out.println("Should be true (c0) - " + ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.REQUIRED));
		// c1
		System.out.println("Should be false (c1) - " + ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.ANALYTICS));
		// c2
		System.out.println("Should be true (c2) - " + ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.SOCIALMEDIA));
		// c3
		System.out.println("Should be false (c3) - " + ConsentClient.GetConsentForCategory(cookieString, ConsentCategory.ADVERTISING));
		
		System.out.println("Should be true because of NR - " + ConsentClient.GetConsentForCategory("NR", ConsentCategory.SOCIALMEDIA));

		System.out.println("Should be true because of NR - " + ConsentClient.GetConsentForCategory("NR- c0=1", ConsentCategory.ANALYTICS));
		
		System.out.println("Should be true because of NR - " + ConsentClient.GetConsentForCategory("c0=1-NR", ConsentCategory.ADVERTISING));
		
		// Get entire user consent
		System.out.println("SocialMedia and Required should be true, everything else false - " + ConsentClient.GetUserConsent(cookieString));
		
		System.out.println("Everything should be true because of NR - " + ConsentClient.GetUserConsent("NR"));

		System.out.println("Everything should be true because of NR - " + ConsentClient.GetUserConsent("NR- c0=1"));
		
		System.out.println("Everything should be true because of NR - " + ConsentClient.GetUserConsent("c0=1-NR"));
		
		// c0 - true c3- false
		System.out.println("Required & SocialMedia should be true, everything else should be false - " + ConsentClient.GetUserConsent("c1=1-c2=2-cid=randomconsentid"));
	}

}
