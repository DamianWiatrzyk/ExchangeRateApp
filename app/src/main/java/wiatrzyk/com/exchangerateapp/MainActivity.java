package wiatrzyk.com.exchangerateapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListItem> mItems = new ArrayList<>();

    private RecyclerViewAdapter mAdapter;
    private int mDaysToSubtract = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new RecyclerViewAdapter(this, mItems);
        recyclerView.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        addCurrencyRates(getSubtractedDate(mDaysToSubtract));


        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                addCurrencyRates(getSubtractedDate(mDaysToSubtract));
            }
        });


    }


    //date format YYYY-MM_DD
    private void addCurrencyRates(String date) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://data.fixer.io/api/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        FixerClient client = retrofit.create(FixerClient.class);


        Call<CurrencyRate> call = (Call<CurrencyRate>) client.currencyRate(date);

        call.enqueue(new Callback<CurrencyRate>() {
            @Override
            public void onResponse(Call<CurrencyRate> call, Response<CurrencyRate> response) {
                CurrencyRate rate = response.body();
                HashMap<String, Double> ratesMap = rate.getRates();
                mItems.add(new ListItem("Day: ", rate.getDate(), 1, rate.getDate()));
                for (Map.Entry<String, Double> x : ratesMap.entrySet()) {
                    mItems.add(new ListItem(x.getKey(), x.getValue().toString(), 0, rate.getDate()));
                }
                mAdapter.notifyDataSetChanged();
                mDaysToSubtract--;
            }

            @Override
            public void onFailure(Call<CurrencyRate> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something wrong connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getSubtractedDate(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(cal.getTime());
        return date;
    }

}
