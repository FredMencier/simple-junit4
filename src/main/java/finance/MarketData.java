package finance;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MarketData {

    private final String url;

    /**
     * Url exemple :
     * https://query1.finance.yahoo.com/v8/finance/chart/GOOG?interval=1d&range=5d
     * @param protocol
     * @param hostname
     * @param version
     */
    public MarketData(String protocol, String hostname, String version) {
        this.url = String.format("%s://%s/%s/finance/chart/", protocol, hostname, version);
    }

    public String getMarketData(String symbol) throws MarketDataException {
        String path = String.format("%s?interval=1d&range=5d", symbol);
        HttpGet request = new HttpGet(url + path);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            } else {
                throw new MarketDataException("No data for symbol " + symbol);
            }
        } catch (IOException ioe) {
            throw new MarketDataException(ioe.getMessage());
        }
    }
}
