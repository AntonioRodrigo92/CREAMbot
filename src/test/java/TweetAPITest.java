import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TweetAPITest {

    @InjectMocks
    private TweetAPI tweetAPI;

    @Mock
    private Twitter twitterMock;

//    @Test
//    void should_ThrowException_when_PostTweet() throws TwitterException {
//        //  given
//        doThrow(TwitterException.class).when(tweetAPIMock).postTweet(anyString());
//        //  when
//        Executable executable = () -> tweetAPIMock.postTweet("");
//        //  then
//        assertThrows(TwitterException.class, executable);
//    }
//
//    @Test
//    void should_NotThrowException_when_PostTweet() throws TwitterException {
//        //  given
//        doNothing().when(tweetAPIMock).postTweet(anyString());
//        //  when
//        tweetAPIMock.postTweet("");
//        //  then
//        //  No Exception Thrown
//    }
//
//    @Test
//    void should_callPostTweet_once() throws TwitterException {
//        //  given
//
//        //  when
//        doNothing().when(tweetAPIMock).postTweet(isA(String.class));
//        tweetAPIMock.postTweet("");
//        //  then
//        verify(tweetAPIMock, times(1)).postTweet("");
//    }

    @Test
    void should_call_updateStatus_once() throws TwitterException {
        //  given
        String tweet = "";
        //  when
        when(twitterMock.updateStatus(anyString())).thenReturn(null);
        tweetAPI.postTweet(tweet);
        //  then
        verify(twitterMock).updateStatus(tweet);
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