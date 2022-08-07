package eapli.base.customermanagement.domain.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ValidVat {

    static final String ACCESS_KEY = "b1a4956995bad69f7fc8293af3a7ecb1";
    static final String ACCESS_KEY1 = "955ec69ead523b156a04d754980f3b91";
    static final String ACCESS_KEY2 = "b919fa5a3ffc31919d94bb4e927ce5e9";
    static final String ACCESS_KEY3 = "e6a4593d16f7a95d9898755d78ea5812";
    static final String ACCESS_KEY4 = "1c18483b1abc346be90f5f71a9b2df25";

    static final String BASE_URL = "http://www.apilayer.net/api/validate?access_key=";

    static boolean check(String vat) {

        try {
            String response = "";
            URL yahoo = new URL(BASE_URL + ACCESS_KEY4 + "&vat_number=" + vat);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine;

            int i = 0;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("format_valid")) {
                    response = inputLine;
                }
            }
            in.close();

            if (response.contains("true")) {
                return true;
            } else {
                return false;
            }


        } catch (Exception ignored) {

        }
        return false;
    }
}
