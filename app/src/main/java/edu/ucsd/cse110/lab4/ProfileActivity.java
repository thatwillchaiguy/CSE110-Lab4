package edu.ucsd.cse110.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadProfile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // IMPLEMENT
        saveProfile();
    }

    public void onExitSaveClicked(View view) {
        finish();
    }

    public void loadProfile() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        // IMPLEMENT
        String name = preferences.getString("name", "Name");
        String status = preferences.getString("status", "Status");

        TextView nameView = findViewById(R.id.name_textview);
        nameView.setText(name);

        TextView statusView = findViewById(R.id.status_textview);
        statusView.setText(status);

    }

    public void saveProfile() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        TextView nameView = findViewById(R.id.name_textview);
        editor.putString("name", nameView.getText().toString());

        TextView statusView = findViewById(R.id.status_textview);
        editor.putString("status", statusView.getText().toString());

        editor.apply();
    }
}