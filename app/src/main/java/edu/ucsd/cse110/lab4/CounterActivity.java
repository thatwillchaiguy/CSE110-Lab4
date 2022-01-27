package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CounterActivity extends AppCompatActivity {

    private int maxCount;
    private ExecutorService backgroundThreadExecutor = Executors.newSingleThreadExecutor();
    private Future<Void> future;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        Bundle extras = getIntent().getExtras();
        this.maxCount = extras.getInt("max_count");

        TextView counterView = findViewById(R.id.counter_view);

        this.future = backgroundThreadExecutor.submit(() -> {
            int count = 0;
            do {
                // Because we're sending this value back to the UI thread,
                // we have to copy it to a final variable first. Otherwise,
                // you will get a weird error.
                final int countCopy = count;

                // Update the counter.
                runOnUiThread(() -> {
                    counterView.setText(String.valueOf(countCopy));
                });

                // Increment the count
                count++;

                // Sleep for half a second.
                Thread.sleep(500);
            } while (count < maxCount + 1);

            runOnUiThread(() -> {
                Utilities.showAlert(this, "Count is finished!");
            });

            return null;
        });


    }

    public void onGoBackClicked(View view) {
        this.future.cancel(true);
        finish();
    }
}