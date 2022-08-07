package eapli.base.ordermanagement.domain.utils;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import com.posadskiy.currencyconverter.enums.Currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

public class CountryCurrency {

    public static CurrencyConverter converter;
    public static final String CURRENCY_CONVERTER_API_API_KEY = "9115a198b8ff9d37b7ae";
    public static final String CURRENCY_LAYER = "DhcYglitSa6T5rVaX6zkucmKYhfr3RE9";
    public static final String OPEN_EXCHANGE_RATES = "51b8501115be4b828953cd53fc0533ea";


    private static final Currency BASE_CURRENCY_EUR = Currency.EUR;

    private static LinkedHashMap<String, String> data = new LinkedHashMap<>();

    public CountryCurrency() {
        converter = new CurrencyConverter(
                new ConfigBuilder()
                        .currencyConverterApiApiKey(CURRENCY_CONVERTER_API_API_KEY)
                        .currencyLayerApiKey(CURRENCY_LAYER)
                        .openExchangeRatesApiKey(OPEN_EXCHANGE_RATES)
                        .build()
        );
        data = getMap();
    }

    private LinkedHashMap<String, String> getMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try {
            String line = "";
            String splitBy = ",";
            //String path = CountryTaxes.class.getClassLoader().getResource("countryTaxes.csv").getPath();

            BufferedReader br = new BufferedReader(new FileReader("files/countryCurrency.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] info = line.split(splitBy);    // use comma as separator
                map.put(info[0], info[1]);
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }
        return map;
    }

    public static Double convert(String country, Double price) {
        try {
            String countryCode = data.get(country);
            Double rate = converter.rate(Currency.valueOf(countryCode), BASE_CURRENCY_EUR);
            return price * rate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("No information about currency rate in that country");
        }
    }
}
