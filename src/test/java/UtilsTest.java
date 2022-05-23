import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.FileNotFoundException;
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
            Map<String, String> actual = Utils.paramGetter("C:\\Users\\AntónioRodrigo\\CREAMbot\\src\\test\\resources\\parameters.txt");

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
        Map<String, String> actual = Utils.paramGetter("C:\\Users\\AntónioRodrigo\\CREAMbot\\src\\test\\resources\\parameters.txt");

        //then
        assertEquals(actual, expected);
    }

    @Test
    void should_ReturnLastWorkDay() throws IOException {
        //given
        LocalDate expected = LocalDate.of(2022, Month.MAY, 31);
        //when
        LocalDate actual = Utils.getLastWorkDay();
        //then
        assertEquals(expected, actual);
    }


    @Test
    void should_ReturnLastWorkDay_NoConnection_Exception() {
        try(MockedStatic<Utils> mockedUtils = mockStatic(Utils.class)) {
            mockedUtils.when(() -> Utils.getLastWorkDay()).thenThrow(IOException.class);
            Executable executable = () -> Utils.getLastWorkDay();
            assertThrows(IOException.class, executable);
        }
    }

}
