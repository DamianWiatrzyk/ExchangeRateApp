package wiatrzyk.com.exchangerateapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

getIncomingIntent();

    }

private void getIncomingIntent(){
        if(getIntent().hasExtra("item_key")
                && getIntent().hasExtra("item_value")
                && getIntent().hasExtra("item_date")){

            String itemKey=getIntent().getStringExtra("item_key");
            String itemValue=getIntent().getStringExtra("item_value");
            String itemDate=getIntent().getStringExtra("item_date");

            TextView date = findViewById(R.id.detailDate);
            date.setText(itemDate);

            TextView detailRate = findViewById(R.id.detailRate);
            detailRate.setText(itemValue +" "+itemKey);
        }
}

}
