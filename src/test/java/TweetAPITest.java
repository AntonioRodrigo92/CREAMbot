import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.TwitterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetAPITest {
    @Mock
    private TweetAPI tweetAPIMock;

    @Test
    void should_ThrowException_when_PostTweet() throws TwitterException {
        //  given
        doThrow(TwitterException.class).when(tweetAPIMock).postTweet(anyString());
        //  when
        Executable executable = () -> tweetAPIMock.postTweet("");
        //  then
        assertThrows(TwitterException.class, executable);
    }

    @Test
    void should_NotThrowException_when_PostTweet() throws TwitterException {
        //  given
        doNothing().when(tweetAPIMock).postTweet(anyString());
        //  when
        tweetAPIMock.postTweet("");
        //  then
        //  No Exception Thrown
    }

    @Test
    public void should_callPostTweet_once() throws TwitterException {
        //  given

        //  when
        doNothing().when(tweetAPIMock).postTweet(isA(String.class));
        tweetAPIMock.postTweet("");
        //  then
        verify(tweetAPIMock, times(1)).postTweet("");
    }


}