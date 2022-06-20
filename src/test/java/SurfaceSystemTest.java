import junit.framework.TestCase;
import org.surface.surface.core.control.ProjectsControl;
import org.surface.surface.core.control.RemoteSnapshotsProjectsControl;
import org.surface.surface.core.control.SingleLocalProjectControl;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurfaceSystemTest extends TestCase {
    @Override
    protected void setUp() throws Exception {
        System.out.println("Starting...");
        metricsCode = new String[]{"CA", "CM", "CIVA", "CCVA", "CMA", "RP", "CMR", "CAI", "CC", "CCR", "CCE", "CME", "CSCR", "SCCR"};
    }

    public void testFirstRemoteProject(){
        ProjectsControl remoteSnapshotsProjectsControl = new RemoteSnapshotsProjectsControl(metricsCode, Paths.get("snapshots1.csv"), "csv");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        remoteSnapshotsProjectsControl.run();
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
                "RP = false\n" +
                "CMR = 1.0\n" +
                "CAI = 1.0";
        String oracle3 = "Class: User\n" +
                "CA = 4\n" +
                "CM = 10\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.6666666666666666\n" +
                "CAI = 0.3";
        String oracle4 = "Class: Spec\n" +
                "CA = 1\n" +
                "CM = 4\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.09090909090909091\n" +
                "CAI = 1.0";
        String oracle5 = "Class: UserManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle6 = "Class: SpecsManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle7 = "Class: UserDao\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle8 = "Class: SpecDao\n" +
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
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
        assertTrue(trimmedOutput.contains(oracle7));
        assertTrue(trimmedOutput.contains(oracle8));
        assertTrue(trimmedOutput.contains(oracle4));
        assertTrue(trimmedOutput.contains(oracle5));
        assertTrue(trimmedOutput.contains(oracle6));

    }

    public void testSecondRemoteProject() {
        ProjectsControl remoteSnapshotsProjectsControl = new RemoteSnapshotsProjectsControl(metricsCode, Paths.get("snapshots2.csv"), "csv");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        remoteSnapshotsProjectsControl.run();
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
                "Project: IGESSecondTest.git\n" +
                "CC = 3\n" +
                "CCR = 0.42857142857142855\n" +
                "CCE = 1.0\n" +
                "CME = 1.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.0";
        String oracle2 = "Class: Patient\n" +
                "CA = 5\n" +
                "CM = 13\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = true\n" +
                "CMR = 0.6190476190476191\n" +
                "CAI = 0.38461538461538464";
        String oracle3 = "Class: HealthWorkerManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle4 = "Class: HealthWorker\n" +
                "CA = 5\n" +
                "CM = 13\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.5652173913043478\n" +
                "CAI = 0.38461538461538464";
        String oracle5 = "Class: PatientManager\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle6 = "Class: Swab\n" +
                "CA = 2\n" +
                "CM = 7\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.6363636363636364\n" +
                "CAI = 0.7142857142857143";
        String oracle7 = "Class: HealthController\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle8 = "Class: PatientController\n" +
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
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
        assertTrue(trimmedOutput.contains(oracle7));
        assertTrue(trimmedOutput.contains(oracle8));
        assertTrue(trimmedOutput.contains(oracle4));
        assertTrue(trimmedOutput.contains(oracle5));
        assertTrue(trimmedOutput.contains(oracle6));
    }

    public void testFirstLocalProject() {
        SingleLocalProjectControl singleLocalProjectControl = new SingleLocalProjectControl(metricsCode, Paths.get("src/main/resources/IGESThirdTest"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        singleLocalProjectControl.run();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String[] splitOutput = byteArrayOutputStream.toString().split("\n");
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
                "Project: IGESThirdTest\n" +
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
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
    }

    public void testSecondLocalProject() {
        SingleLocalProjectControl singleLocalProjectControl = new SingleLocalProjectControl(metricsCode, Paths.get("src/main/resources/IGESFourthTest"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        singleLocalProjectControl.run();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String[] splitOutput = byteArrayOutputStream.toString().split("\n");
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
                "Project: IGESFourthTest\n" +
                "CC = 1\n" +
                "CCR = 0.3333333333333333\n" +
                "CCE = 1.0\n" +
                "CME = 1.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.0";

        String oracle2 = "Class: Gameplay\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle3 = "Class: MapGenerator\n" +
                "CA = 1\n" +
                "CM = 1\n" +
                "CIVA = 1.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.5\n" +
                "CAI = 1.0";

        String oracle4 = "Class: Main\n" +
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
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
        assertTrue(trimmedOutput.contains(oracle4));
    }

    public void testThirdLocalProject(){
        SingleLocalProjectControl singleLocalProjectControl = new SingleLocalProjectControl(metricsCode, Paths.get("src/main/resources/IGESFifthTest"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        singleLocalProjectControl.run();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String[] splitOutput = byteArrayOutputStream.toString().split("\n");
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
                "Project: IGESFifthTest\n" +
                "CC = 1\n" +
                "CCR = 0.5\n" +
                "CCE = 1.0\n" +
                "CME = 1.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.0";
        String oracle2 = "Class: BankApplication\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";
        String oracle3 = "Class: BankAccount\n" +
                "CA = 1\n" +
                "CM = 1\n" +
                "CIVA = 1.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.25\n" +
                "CAI = 1.0";
        List<String> arrOutput = Arrays.asList(output.split("\n\n"));
        List<String> trimmedOutput = new ArrayList<>();
        for(String s : arrOutput){
            trimmedOutput.add(s.trim());
        }
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
    }

    public void testFourthLocalProject(){
        SingleLocalProjectControl singleLocalProjectControl = new SingleLocalProjectControl(metricsCode, Paths.get("src/main/resources/IGESSixthTest"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        singleLocalProjectControl.run();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String[] splitOutput = byteArrayOutputStream.toString().split("\n");
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
                "Project: IGESSixthTest\n" +
                "CC = 9\n" +
                "CCR = 0.3103448275862069\n" +
                "CCE = 1.0\n" +
                "CME = 1.0\n" +
                "CSCR = 0.0\n" +
                "SCCR = 0.5555555555555556";
        String oracle2 = "Class: CaseEditriciServiceImpl\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle3 = "Class: LibroDTO\n" +
                "CA = 2\n" +
                "CM = 3\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.2\n" +
                "CAI = 0.5";

        String oracle4 = "Class: LocazioneRest\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle5 = "Class: Libro\n" +
                "CA = 1\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.14285714285714285\n" +
                "CAI = 1.0";

        String oracle6 = "Class: LibroServiceImpl\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle7 = "Class: AutoreServiceImpl\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle8 = "Class: CasaEditriceDTO\n" +
                "CA = 2\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.3333333333333333\n" +
                "CAI = 0.5";

        String oracle9 = "Class: ServletInitializer\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle10 = "Class: LocazioniServiceImpl\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle11 = "Class: LibroController\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle12 = "Class: utilCasaEditrice\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle13 = "Class: LibreriaApplication\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle14 = "Class: utilAutore\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle15 = "Class: LibroRest\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle16 = "Class: SwaggerConfig\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle17 = "Class: AutoreRest\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle18 = "Class: DirtyFixConfig\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = true\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle19 = "Class: AutoreDTO\n" +
                "CA = 2\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.25\n" +
                "CAI = 0.5";

        String oracle20 = "Class: CasaEditrice\n" +
                "CA = 1\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.3333333333333333\n" +
                "CAI = 1.0";

        String oracle21 = "Class: RisultatoDTO\n" +
                "CA = 1\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle22 = "Class: AutoreController\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle23 = "Class: CasaEditriceRest\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle24 = "Class: utilLibro\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle25 = "Class: LocazioneController\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle26 = "Class: CasaEditriceController\n" +
                "CA = 0\n" +
                "CM = 0\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 0.0\n" +
                "RP = false\n" +
                "CMR = 0.0\n" +
                "CAI = 0.0";

        String oracle27 = "Class: LocazioneDTO\n" +
                "CA = 2\n" +
                "CM = 2\n" +
                "CIVA = 0.0\n" +
                "CCVA = 0.0\n" +
                "CMA = 1.0\n" +
                "RP = false\n" +
                "CMR = 0.25\n" +
                "CAI = 0.5";

        String oracle28 = "Class: utilLocazione\n" +
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
        System.out.println(arrOutput);
        assertTrue(trimmedOutput.contains(oracle1));
        assertTrue(trimmedOutput.contains(oracle2));
        assertTrue(trimmedOutput.contains(oracle3));
        assertTrue(trimmedOutput.contains(oracle4));
        assertTrue(trimmedOutput.contains(oracle5));
        assertTrue(trimmedOutput.contains(oracle6));
        assertTrue(trimmedOutput.contains(oracle7));
        assertTrue(trimmedOutput.contains(oracle8));
        assertTrue(trimmedOutput.contains(oracle9));
        assertTrue(trimmedOutput.contains(oracle10));
        assertTrue(trimmedOutput.contains(oracle11));
        assertTrue(trimmedOutput.contains(oracle12));
        assertTrue(trimmedOutput.contains(oracle13));
        assertTrue(trimmedOutput.contains(oracle14));
        assertTrue(trimmedOutput.contains(oracle15));
        assertTrue(trimmedOutput.contains(oracle16));
        assertTrue(trimmedOutput.contains(oracle17));
        assertTrue(trimmedOutput.contains(oracle18));
        assertTrue(trimmedOutput.contains(oracle19));
        assertTrue(trimmedOutput.contains(oracle20));
        assertTrue(trimmedOutput.contains(oracle21));
        assertTrue(trimmedOutput.contains(oracle22));
        assertTrue(trimmedOutput.contains(oracle23));
        assertTrue(trimmedOutput.contains(oracle24));
        assertTrue(trimmedOutput.contains(oracle25));
        assertTrue(trimmedOutput.contains(oracle26));
        assertTrue(trimmedOutput.contains(oracle27));
        assertTrue(trimmedOutput.contains(oracle28));

    }

    private String metricsCode[];

}