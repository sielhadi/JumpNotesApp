package com.example.jumpnotesapp;

import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchField = findViewById(R.id.searchField);
        EditText noteContent = findViewById(R.id.noteContent);
        Button jumpButton = findViewById(R.id.jumpButton);
        ScrollView scrollView = findViewById(R.id.scrollView);

        jumpButton.setOnClickListener(v -> {
            String query = searchField.getText().toString().trim();
            String text = noteContent.getText().toString();

            if (query.isEmpty()) {
                Toast.makeText(this, "Enter a search term", Toast.LENGTH_SHORT).show();
                return;
            }

            int index = text.toLowerCase().indexOf(query.toLowerCase());
            if (index != -1) {
                noteContent.setSelection(index, index + query.length());
                noteContent.requestFocus();

                Layout layout = noteContent.getLayout();
                if (layout != null) {
                    int line = layout.getLineForOffset(index);
                    int y = layout.getLineTop(line);
                    scrollView.smoothScrollTo(0, y);
                }
            } else {
                Toast.makeText(this, "Word not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

