package finance;

import java.util.List;
import java.util.Map;

/**
 * javac src/finance/*.java -d classes
 * java -cp ./classes finance.TestMyLib
 * jar -cvf MyFinancialLib.jar -C ./classes .
 * jar tf MyFinancialLib.jar
 */
public class Financial implements FinancialInterface {

    private Map<String, Double> mapCurrenciesRate;

    public Financial() {
        mapCurrenciesRate = Map.of("CHFEUR", 1.06D, "CHFUSD", 1.14D);
    }

    /**
     * Calcule la moyenne d'une liste de double
     * @param doubleList
     * @return
     */
    public double calculateAverage(final List<Double> doubleList) {
        double sum = 0D;
        for (Double dbl : doubleList) {
            sum = sum + dbl;
        }
        return sum / doubleList.size();
    }

    /**
     * Calcule la somme d'une liste de double
     * @param doubleList
     * @return
     */
    public double calculateSum(final List<Double> doubleList) {
        double sum = 0D;
        for (Double dbl : doubleList) {
            sum = sum + dbl;
        }
        return sum;
    }

    /**
     * Calcule le change entre 2 monnaies
     * @param fromCurrency
     * @param toCurrency
     * @param amount
     * @return
     * @throws RateUnavailableException
     */
    public double calculateChange(final String fromCurrency, final String toCurrency, final double amount) throws RateUnavailableException {
        Double rate = mapCurrenciesRate.get(fromCurrency + toCurrency);
        if (rate == null) {
            throw new RateUnavailableException("Currency rate not found for change " + fromCurrency + "/" + toCurrency);
        }
        return amount * rate;
    }
}