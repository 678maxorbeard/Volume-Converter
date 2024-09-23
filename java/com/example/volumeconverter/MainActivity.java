package com.example.volumeconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputVolume;
    private Spinner inputUnit;
    private TextView outputVolume;

    private static final String[] UNITS = {"Liters", "Milliliters", "Gallons", "Cubic Meters"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputVolume = findViewById(R.id.input_volume);
        inputUnit = findViewById(R.id.input_unit);
        outputVolume = findViewById(R.id.output_volume);
        Button convertButton = findViewById(R.id.convert_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UNITS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputUnit.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertVolume();
            }
        });
    }

    private void convertVolume() {
        String inputValue = inputVolume.getText().toString();
        if (inputValue.isEmpty()) {
            outputVolume.setText("Please enter a value.");
            return;
        }

        double volume = Double.parseDouble(inputValue);
        String selectedUnit = inputUnit.getSelectedItem().toString();
        double convertedVolume;

        switch (selectedUnit) {
            case "Liters":
                convertedVolume = volume;
                break;
            case "Milliliters":
                convertedVolume = volume * 1000;
                break;
            case "Gallons":
                convertedVolume = volume * 0.264172;
                break;
            case "Cubic Meters":
                convertedVolume = volume / 1000;
                break;
            default:
                convertedVolume = volume;
                break;
        }

        outputVolume.setText(String.format("Converted Volume: %.4f", convertedVolume));
    }
}
