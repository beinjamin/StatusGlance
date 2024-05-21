package com.jaures.statusglanceandroid;

import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
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

        floatingWidgetView = findViewById(R.id.floatingWidgetView);
        showDateButton = findViewById(R.id.showDateButton);
        toggleThemeButton = findViewById(R.id.toggleThemeButton);

        registerBatteryReceiver();

        // Écoute des changements de force du signal
        TelephonyManager telephonyManager = getSystemService(TelephonyManager.class);
        telephonyManager.listen(new NetworkPhoneStateListener(), PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        // Gestion du clic sur le bouton pour afficher la date
        showDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCurrentDate();
            }
        });
        // Gestion du clic sur le bouton pour basculer le thème
        toggleThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTheme();
            }
        });
    }

}