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
        navigator = new Navigator("chrome",true);
        baseDirectory = Paths.get(System.getProperty("user.dir")).toString();
    }

    @ParameterizedTest
    @MethodSource("registerTestCases")
    void registerTest(String[] registerData) {
        boolean testResult = navigator.register(registerData[0], registerData[1], registerData[2], registerData[3],
                registerData[4], registerData[5], registerData[6]);
        assertTrue(testResult);
    }

    private static Stream<Arguments> registerTestCases() throws Exception {
        String registerDataFile = Paths.get(baseDirectory, "registerData.csv").toString();
        File inputFile = new File(registerDataFile);

        ArrayList<String[]> registerData = new ArrayList();
        try {
            Scanner scanner = new Scanner(inputFile);
            while( scanner.hasNextLine() ) {
                registerData.add(scanner.nextLine().split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if( registerData.size()<=0 ) throw new Exception("No Register Data");

        Stream dataStream = Stream.of(Arguments.of((Object) registerData.get(0)));
        for( int i=1; i<=registerData.size()-1; i++) {
            dataStream = Stream.concat(dataStream, Stream.of(Arguments.of((Object) registerData.get(i))));
        }
        return dataStream;
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

    @ParameterizedTest
    @MethodSource("productAddTestCases")
    void productAddTest(String[] loginData) {
        boolean testResult = navigator.addProduct(loginData[0], loginData[1], loginData[2], loginData[3],
                                                  loginData[4], loginData[5], loginData[6], loginData[7]);
        assertTrue(testResult);
    }

    private static Stream<Arguments> productAddTestCases() throws Exception {
        String productDataFile = Paths.get(baseDirectory, "productData.csv").toString();
        File inputFile = new File(productDataFile);

        ArrayList<String[]> loginData = new ArrayList();
        try {
            Scanner scanner = new Scanner(inputFile);
            while( scanner.hasNextLine() ) {
                loginData.add(scanner.nextLine().split(","));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        if( loginData.size()<=0 ) throw new Exception("No Product Data");

        Stream dataStream = Stream.of(Arguments.of((Object) loginData.get(0)));
        for( int i=1; i<=loginData.size()-1; i++) {
            dataStream = Stream.concat(dataStream, Stream.of(Arguments.of((Object) loginData.get(i))));
        }
        return dataStream;
    }

    @AfterAll
    private static void tearDown() { navigator = null; }
}
