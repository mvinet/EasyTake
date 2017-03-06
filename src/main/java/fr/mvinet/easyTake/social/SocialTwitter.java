package fr.mvinet.easyTake.social;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Twitter
 *
 * @author mvinet
 */
public class SocialTwitter {

	public SocialTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("", "");
	}
	
}
