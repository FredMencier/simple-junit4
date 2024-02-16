package finance;

import jdk.jfr.Enabled;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

public class FinancialTestReview {

    private static Financial financial;
    private static List<Double> doubleList;

    @BeforeClass
    public static void init() {
        System.out.println("Initialisation du test");
        financial = new Financial();
        doubleList = Arrays.asList(1D, 2D, 3D, 4D, 5D, 6D, 7D, 8D, 9D, 10D);
    }

    @AfterClass
    public static void teardown() {
        System.out.println("Libération des resources");
        financial = null;
        doubleList = null;
    }

    @Test
    @Ignore
    public void calculateAverage() {
        double average = financial.calculateAverage(doubleList);
        Assert.assertEquals(5.5D, average, 0D);
    }

    @Test
    public void calculateSum() {
        double sum = financial.calculateSum(doubleList);
        Assert.assertEquals(55D, sum, 0D);
    }

    @Test
    public void calculateChange() throws RateUnavailableException {
        double change = financial.calculateChange("CHF", "EUR", 1000D);
        Assert.assertEquals(1060D, change, 0D);
    }

    @Test(expected = RateUnavailableException.class)
    public void calculateChangeWithException() throws RateUnavailableException {
        financial.calculateChange("CHF", "JPY", 1000D);
    }
}