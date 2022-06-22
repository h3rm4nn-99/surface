import junit.framework.TestCase;
import org.surface.surface.Starter;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurfaceCR1 extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Starting Test Cases for CR1");
    }

    public void testParser() {
        String[] args = {
                "CLI",
                "-metrics",
                "CA,CM,CIVA,CCVA,CMA,RP,CMR,CAI,CC,CCR,CCE,CME,CSCR,SCCR",
                "-project",
                "src/main/resources/IGESThirdTest",
                "-export",
                "csv"
        };

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        Starter.main(args);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String[] splitOutput = byteArrayOutputStream.toString().split("\n");
        boolean flag = false;
        String output = "";
        for (String s : splitOutput) {
            if (s.startsWith("* Printing Project Metrics")) {
                flag = true;
                continue;
            }
            if (flag) {
                s += "\n";
                output += s;
            }
        }
        String oracle1 = "Project: IGESThirdTest\n" +
                "CC = 0\n" +
                "CCR = 0.0\n" +
                "CCE = 0.0\n" +
                "CME = 0.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.0";
        String oracle2 = "Class: GuessingGame\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        List<String> arrOutput = Arrays.asList(output.split("\n\n"));
        List<String> trimmedOutput = new ArrayList<>();
        for(String s : arrOutput){
            trimmedOutput.add(s.trim());
        }
        System.out.println(trimmedOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
    }
}
