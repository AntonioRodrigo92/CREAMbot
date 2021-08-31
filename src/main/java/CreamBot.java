import java.time.LocalDate;
import java.util.Map;
import twitter4j.TwitterException;

public class CreamBot {

    public static void main(String[] args) {
        String fileLocation = args[0];

        LocalDate today = LocalDate.now();
        LocalDate lastWorkDay = Utils.getLastWorkDay();

        if (today.equals(lastWorkDay)) {
            String post = "*C.R.E.A.M.*";

            Map<String, String> params = Utils.paramGetter(fileLocation);
            String twitterAPIKey = params.get("twitterAPIKey");
            String twitterAccessToken = params.get("twitterAccessToken");
            String twitterAPISecret = params.get("twitterAPISecret");
            String twitterAccessSecretToken = params.get("twitterAccessSecretToken");

            TweetAPI tweet = new TweetAPI(twitterAPIKey, twitterAPISecret, twitterAccessToken, twitterAccessSecretToken);
            try {
                tweet.postTweet(post);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Nao e fim do mes =(");
        }

    }
}
