import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NavigatorTest {
    private static Navigator navigator;
    private static String baseDirectory;

    @BeforeAll
    private static void setUp() {
        navigator = new Navigator("chrome",false);
        baseDirectory = Paths.get(System.getProperty("user.dir")).toString();
    }

    @ParameterizedTest
    @MethodSource("loginTestCases")
    void loginTest(String[] loginData) {
        boolean testResult = navigator.login(loginData[0], loginData[1], loginData[2]);
        assertTrue(testResult);
    }

    private static Stream<Arguments> loginTestCases() throws Exception {
        String loginDataFile = Paths.get(baseDirectory, "loginData.csv").toString();
        File inputFile = new File(loginDataFile);

        ArrayList<String[]> loginData = new ArrayList();
        try {
            Scanner scanner = new Scanner(inputFile);
            while( scanner.hasNextLine() ) {
                loginData.add(scanner.nextLine().split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if( loginData.size()<=0 ) throw new Exception("No Login Data");

        Stream dataStream = Stream.of(Arguments.of((Object) loginData.get(0)));
        for( int i=1; i<=loginData.size()-1; i++) {
            dataStream = Stream.concat(dataStream, Stream.of(Arguments.of((Object) loginData.get(i))));
        }
        return dataStream;
    }

//    @ParameterizedTest
//    @MethodSource("registerTestCases")
//    void registerTest(int[] numbers) {
//
//    }
//
//    private static Stream<int[]> registerTestCases() {
//        return Stream.of();
//    }

    @AfterAll
    private static void tearDown() { navigator = null; }
}
