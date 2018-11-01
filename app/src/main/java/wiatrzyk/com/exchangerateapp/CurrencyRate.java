package wiatrzyk.com.exchangerateapp;

import java.util.HashMap;

public class CurrencyRate {
    boolean success;
    long timestamp;
    String base;
    String date;
    HashMap<String, Double> rates;

    public CurrencyRate(boolean success, long timestamp, String base, String date, HashMap<String, Double> rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public String getDate() {
        return date;
        }
}
