import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetAPITest {

    @InjectMocks
    private TweetAPI tweetAPI = new TweetAPI("", "", "", "");

    @Mock
    private Twitter twitterMock;

    @Test
    void should_call_updateStatus_once() throws TwitterException {
        //  given
        String tweet = "top kek";
        //  when
        tweetAPI.postTweet(tweet);
        //  then
        verify(twitterMock, times(1)).updateStatus(tweet);
    }

    @Test
    void should_ThrowException_when_PostTweet() throws TwitterException {
        //  given
        when(twitterMock.updateStatus(anyString())).thenThrow(TwitterException.class);
        //  when
        Executable executable = () -> tweetAPI.postTweet(anyString());
        //  then
        assertThrows(TwitterException.class, executable);
    }

}