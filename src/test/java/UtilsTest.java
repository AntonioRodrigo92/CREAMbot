import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

public class UtilsTest {

    @Test
    void should_ReturnRightParameters_when_RightPath_Mockito() {
        try (MockedStatic<Utils> mockedUtils = mockStatic(Utils.class)) {
            //given
            Map<String, String> expected = new HashMap<>();
            expected.put("teste1", "teste2");
            mockedUtils.when(() -> Utils.paramGetter(anyString())).thenReturn(expected);

            //when
            Map<String, String> actual = Utils.paramGetter("C:\\Users\\Antonio\\IdeaProjects\\CREAMbot\\src\\test\\resources\\parameters.txt");

            //then
            assertEquals(actual, expected);
        }
    }

    @Test
    void should_ReturnRightParameters_when_RightPath_JUnit() {
        //given
        Map<String, String> expected = new HashMap<>();
        expected.put("teste1", "teste2");

        //when
        Map<String, String> actual = Utils.paramGetter("C:\\Users\\Antonio\\IdeaProjects\\CREAMbot\\src\\test\\resources\\parameters.txt");

        //then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnLastWorkDay() throws IOException {
        //given
        LocalDate expected = LocalDate.of(2022, Month.APRIL, 29);
        //when
        LocalDate actual = Utils.getLastWorkDay();
        //then
        assertEquals(expected, actual);
    }


    @Disabled
    @Test
    void should_ReturnLastWorkDay_NoConnection_Exception() {
        //  given
        //NO INTERNET CONNECTION!!!
        //  when
        Executable executable = () -> Utils.getLastWorkDay();
        //  then
        assertThrows(IOException.class, executable);
    }

}
