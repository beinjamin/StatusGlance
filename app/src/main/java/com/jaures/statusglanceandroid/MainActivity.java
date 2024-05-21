package com.jaures.statusglanceandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private void registerBatteryReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(new BatteryBroadcastReceiver(), filter);
    }

    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryPercent = (int) ((level / (float) scale) * 100);
            String batteryLabel = getString(R.string.battery_info_label);
            String batteryInfo = batteryLabel + ": " + batteryPercent + "%";
            floatingWidgetView.setBatteryInfo(batteryInfo);
        }
    }

    // Classe interne pour écouter les changements de force du signal réseau
    private class NetworkPhoneStateListener extends PhoneStateListener {
        @Override
        public void onSignalStrengthsChanged(android.telephony.SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            // Création de la chaîne d'information sur le réseau
            String networkLabel = getString(R.string.network_info_label);
            String networkInfo = networkLabel + ": " + signalStrength.getLevel();
            // Mise à jour du TextView avec les informations sur le réseau
            floatingWidgetView.setNetworkInfo(networkInfo);
        }
    }
    // Méthode pour afficher la date actuelle
    private void displayCurrentDate() {
        // Obtention de la date actuelle
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        // Référence au TextView de la date
        TextView dateTextView = findViewById(R.id.dateTextView);
        // Mise à jour du texte du TextView avec la date actuelle
        dateTextView.setText("Date du jour : " + currentDate);
    }
    // Méthode pour basculer entre les thèmes clair et sombre
    private void toggleTheme() {
        // Inversion de l'état du thème
        isDarkTheme = !isDarkTheme;
        // Application du thème approprié
        if (isDarkTheme) {
            // Activer le mode sombre du système
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Désactiver le mode sombre du système
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // Mise à jour du texte du bouton de bascule en fonction du nouveau thème
        toggleThemeButton.setText(isDarkTheme ? "Mode Clair" : "Mode Sombre");
    }

}