import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsolePrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintContentToOutputStreamWhenIWantToPrintIt() {
        ConsolePrinter printer = new ConsolePrinter();

        printer.print("print me to console");

        assertThat(outContent.toString(), is("print me to console"));
    }

    @Test
    public void shouldCanPrintSpecialCharacter() {
        ConsolePrinter printer = new ConsolePrinter();

        printer.print("@#$%^&*\n");

        assertThat(outContent.toString(), is("@#$%^&*\n"));
    }
}
