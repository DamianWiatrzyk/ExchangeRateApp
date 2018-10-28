package wiatrzyk.com.exchangerateapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FixerClient {

    @GET("/{date}?access_key=54e1c1ea8dd2bdd727c662ff66d311ba")
    Call<CurrencyRate> currencyRate(@Path("date") String date);
}
