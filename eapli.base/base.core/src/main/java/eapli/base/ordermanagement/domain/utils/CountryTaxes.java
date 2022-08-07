package eapli.base.ordermanagement.domain.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;

public class CountryTaxes {
    private LinkedHashMap<String, Double> data = new LinkedHashMap<>();

    public CountryTaxes(){
        data = getMap();
    }

    private LinkedHashMap<String, Double> getMap(){
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();
        try {
            String line = "";
            String splitBy = ";";
            //String path = CountryTaxes.class.getClassLoader().getResource("countryTaxes.csv").getPath();

            BufferedReader br = new BufferedReader(new FileReader("files/countryTaxes.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] info = line.split(splitBy);    // use comma as separator
                map.put(info[0], Double.parseDouble(info[1]));
            }
        }catch (Exception e){
            System.out.println("File not found!");
        }
        return map;
    }

    private Double findTax(String country){
        try {
            return data.get(country);
        }catch (Exception e){
            throw new IllegalArgumentException("No information about taxes in that country");
        }
    }

    public Double calculatePrice(String country, Double price){
        Double tax = findTax(country);
        return ((tax/100) * price) + price;
    }
}
