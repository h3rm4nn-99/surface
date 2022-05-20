import junit.framework.TestCase;
import org.surface.surface.CLIStarter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurfaceSystemTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        System.out.println("Starting...");
    }

    public void testFirstRemote() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        String args[] = new String[]{"", "-metrics", "CA", "CM", "CIVA", "CCVA", "CMA", "CMR", "CAI", "RP", "CC", "CCR", "CCE", "CME", "CSCR", "SCCR", "-remoteProjects", "snapshots.csv", "-export", "csv"};
        CLIStarter.main(args);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String splitOutput[] = byteArrayOutputStream.toString().split("\n");
        boolean flag = false;
        String output = "";
        for (String s : splitOutput) {
            if (s.startsWith("* Printing Project Metrics")) {
                flag = true;
            }
            if (flag) {
                s += "\n";
                output += s;
            }
        }
        String oracle1 = "* Printing Project Metrics\n" +
                "Project: IGESFirstTest.git\n" +
                "CC = 3\n" +
                "CCR = 0.42857142857142855\n" +
                "CCE = 1.0\n" +
                "CME = 1.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.0";
        String oracle2 = "Class: Manager\n" +
                "CA = 1\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "CMR = 1.0\n" +
                "CAI = 1.0\n" +
                "RP = false";
        String oracle3 = "Class: User\n" +
                "CA = 4\n" +
                "CM = 10\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "CMR = 0.6666666666666666\n" +
                "CAI = 0.3333333333333333\n" +
                "RP = false";
        String oracle4 = "Class: Spec\n" +
                "CA = 1\n" +
                "CM = 4\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "CMR = 0.09090909090909091\n" +
                "CAI = 1.0\n" +
                "RP = false";
        String oracle5 = "Class: UserManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0\n" +
                "RP = false";
        String oracle6 = "Class: SpecsManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0\n" +
                "RP = false";
        String oracle7 = "Class: UserDao\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0\n" +
                "RP = false";
        String oracle8 = "Class: SpecDao\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0\n" +
                "RP = false";
        List<String> arrOutput = Arrays.asList(output.split("\n\n"));
        List<String> trimmedOutput = new ArrayList<>();
        for(String s : arrOutput){
            trimmedOutput.add(s.trim());
        }
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
        assertTrue(trimmedOutput.contains(oracle4));
        assertTrue(trimmedOutput.contains(oracle5));
        assertTrue(trimmedOutput.contains(oracle6));
        assertTrue(trimmedOutput.contains(oracle7));
        assertTrue(trimmedOutput.contains(oracle8));
    }
}