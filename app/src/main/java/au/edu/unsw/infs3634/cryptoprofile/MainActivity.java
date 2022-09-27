package au.edu.unsw.infs3634.cryptoprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static String COIN_SYMBOL_KEY = "coin symbol";
    private Button btnLaunchActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate: Starting App!");

        setContentView(R.layout.activity_main);
        btnLaunchActivity = findViewById(R.id.btnLaunch);
        btnLaunchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //launchDetailActivity("Message from MainActivity");

                //new detail intent
                Intent intent = new Intent (MainActivity.this, DetailActivity.class);
                intent.putExtra(COIN_SYMBOL_KEY, "BTC");
                startActivity(intent);
            }
        });

    }

    // Called when the user taps launch button
    public void launchDetailActivity(String msg) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_MESSAGE ,msg);
        startActivity(intent);
    }

}