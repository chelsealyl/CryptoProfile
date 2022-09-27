package au.edu.unsw.infs3634.cryptoprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import au.edu.unsw.infs3634.cryptoprofile.api.Coin;

public class DetailActivity extends AppCompatActivity {
    public static final String INTENT_MESSAGE = "intent_message";
    private static final String TAG = "DetailActivity";
    private TextView detailMessage;
    private Button btnOpenUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail Activity");
        // Get the intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.COIN_SYMBOL_KEY)) {
            String message = intent.getStringExtra(MainActivity.COIN_SYMBOL_KEY);
            Log.d(TAG, "Intent Message = " + message);

            // Capture the layout's TextView and set the string as its text
            detailMessage = findViewById(R.id.coin_name_text);
            detailMessage.setText(message);

            //GET UI COMPONENTS
            TextView coin_name_text = findViewById(R.id.coin_name_text);
            TextView coin_symbol_text = findViewById(R.id.coin_symbol_text);
            TextView value_text = findViewById(R.id.value);
            TextView change1h_text = findViewById(R.id.change1h);
            TextView change24h_text = findViewById(R.id.change24h);
            TextView change7d_text = findViewById(R.id.change7d);
            TextView marketcap_text = findViewById(R.id.marketcap_text);
            TextView volume_text = findViewById(R.id.volume24h);
            ImageButton search_button = findViewById(R.id.search_symbol);
            ImageView coin_image = findViewById(R.id.coin_image);

            //GET INTENT INFORMATION
            //Intent intent = getIntent();
            String coinSymbol = intent.getStringExtra(MainActivity.COIN_SYMBOL_KEY);

            Coin coin = Coin.getCoinBySymbol(coinSymbol);
            if (coin != null) {
                coin_name_text.setText(coin.getName());
                coin_symbol_text.setText(coin.getSymbol());
                value_text.setText(coin.getPriceUsd());
                change1h_text.setText(coin.getPercentChange1h());
                change24h_text.setText(coin.getPercentChange24h());
                change7d_text.setText(coin.getPercentChange7d());
                marketcap_text.setText(coin.getMarketCapUsd());
                volume_text.setText(String.format("%.2f", coin.getVolume24()));
            } else {
                coin_name_text.setText("Coin not Found!");
            }

            search_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + coin.getSymbol()));

                    startActivity(google_search);

                }
            });
        }
        btnOpenUrl = findViewById(R.id.btnUrl);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrl("https://youtu.be/M0HqrPDyA6I");
            }
        });
    }

    // Called when the user taps the open url button
    public void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}