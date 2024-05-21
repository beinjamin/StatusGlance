package com.jaures.statusglanceandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingWidgetView extends LinearLayout {

    // Déclaration des TextView pour afficher les informations sur la batterie et le réseau
    private TextView batteryTextView;
    private Textview networkTextView;

    public FloatingWidgetView(Context context) {
        super(context);
        init (context);
    }

    public FloatingWidgetViewView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    // Méthode d'initialisation de la vue
    private void init(Context context) {
        // Gonfle le layout spécifié dans le fichier XML dans cette vue
        LayoutInflater.from(context).inflate(R.layout.floating_widget_layout, this);
        // Initialise les références aux TextView pour afficher les informations sur la batterie et le réseau
        batteryTextView = findViewById(R.id.batteryTextView);
        networkTextView = findViewById(R.id.networkTextView);
    }
    // Méthode pour définir les informations sur la batterie
    public void setBatteryInfo(String info) {
        // Vérifie si le TextView de la batterie est initialisé
        if (batteryTextView != null) {
            // Définit le texte avec les informations sur la batterie
            batteryTextView.setText(info);
        }
    }

    // Méthode pour définir les informations sur le réseau
    public void setNetworkInfo(String info) {
        // Vérifie si le TextView du réseau est initialisé
        if (networkTextView != null) {
            // Définit le texte avec les informations sur le réseau
            networkTextView.setText(info);
        }
    }
}
