package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMyProfileClicked(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onShowCounterClicked(View view) {
        Intent intent = new Intent(this, CounterActivity.class);

        TextView maxCountView = findViewById(R.id.max_count_view);
        String maxCountStr = maxCountView.getText().toString();

        Optional<Integer> maybeMaxCount = Utilities.parseCount(maxCountStr);

        // Check if the integer parsed correctly.
        if (!maybeMaxCount.isPresent()) {
            // If not, show an error, and then return
            Utilities.showAlert(this, "That isn't a number!");
            return;
        }

        // Get the integer
        int maxCount = maybeMaxCount.get();

        // If it's not positive, show an error and then return.
        if (maxCount <= 0) {
            Utilities.showAlert(this, "Please enter a positive number!");
            return;
        }

        Utilities.showAlert(this, String.format("I got the number %d", maxCount));

        intent.putExtra("max_count", maxCount);
        startActivity(intent);
    }
}