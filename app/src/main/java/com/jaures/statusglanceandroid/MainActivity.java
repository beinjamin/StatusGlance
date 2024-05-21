package com.jaures.statusglanceandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private FloatingWidgetView floatingWidgetView;
    private Button showDateButton;
    private Button toggleThemeButton;
    private boolean isDarkTheme = false; // Indique l'état actuel du thème
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }
}