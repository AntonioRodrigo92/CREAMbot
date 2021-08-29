import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TweetAPI {
    private String api_key;
    private String api_secret_key;
    private String access_token;
    private String access_token_secret;
    private Twitter twitter;

    public TweetAPI(String api_key, String api_secret, String access_token, String access_token_secret) {
        this.api_key = api_key;
        this.api_secret_key = api_secret;
        this.access_token = access_token;
        this.access_token_secret = access_token_secret;
        authenticate();
    }

    private void authenticate() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(api_key)
                .setOAuthConsumerSecret(api_secret_key)
                .setOAuthAccessToken(access_token)
                .setOAuthAccessTokenSecret(access_token_secret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public void postTweet(String tweet) throws TwitterException {
        Status status = twitter.updateStatus(tweet);
        System.out.println("Tweet postado: " + status.getText());
    }

}
