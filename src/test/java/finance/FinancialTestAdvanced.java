package finance;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class FinancialTestAdvanced {

    private static Financial financial;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        System.out.println("Initialisation du test");
        financial = new Financial();
    }

    @AfterClass
    public static void teardown() {
        System.out.println("Libération des resources");
        financial = null;
    }

    @Test
    public void calculateChangeWithExceptionRule() throws RateUnavailableException {
        expectedException.expect(RateUnavailableException.class);
        financial.calculateChange("CHF", "JPY", 1000D);
    }

    @Test
    public void calculateChangeWithExceptionThrows() {
        assertThrows(RateUnavailableException.class, () -> {
            financial.calculateChange("CHF", "JPY", 1000D);
        });
    }

    @Test
    public void getMarketDataGoogle() throws MarketDataException {
        String goog = financial.getMarketData("GOOG");
        assertNotNull(goog);
        assertTrue(goog.contains("GOOG"));
        assertTrue(goog.contains("USD"));
        assertTrue(goog.contains("EQUITY"));
    }

    @Test
    public void getMarketDataApple() throws MarketDataException {
        String aapl = financial.getMarketData("AAPL");
        assertNotNull(aapl);
        assertTrue(aapl.contains("AAPL"));
        assertTrue(aapl.contains("USD"));
        assertTrue(aapl.contains("EQUITY"));
    }

    @Test
    public void getMarketDataHeg() throws MarketDataException {
        String heg = financial.getMarketData("HEG");
        assertNotNull(heg);
        assertTrue(heg.contains("error"));
        assertTrue(heg.contains("Not Found"));
    }

    @Test
    public void getMarketDataHegMock() throws Exception {
        MarketData mockedRequest = Mockito.mock(MarketData.class);
        Mockito.when(mockedRequest.getMarketData("HEG")).thenReturn("{\"chart\":{\"result\":null,\"error\":{\"code\":\"Not Found\",\"description\":\"No data found, symbol may be delisted\"}}}");
        String heg = mockedRequest.getMarketData("HEG");
        assertTrue(heg.contains("error"));
        assertTrue(heg.contains("Not Found"));
    }

}
