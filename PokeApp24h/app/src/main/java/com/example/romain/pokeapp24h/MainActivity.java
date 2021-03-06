package com.example.romain.pokeapp24h;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CONSOLE_LOGS = "ConsoleLogs";

    public void connectPokemon(View v){

        logInConsole("Psykokwak Go !");

        connectWifi();

        bottomConsole();

        Toast.makeText(this, "Psykokwak GO !", Toast.LENGTH_SHORT).show();
    }

    public void stopPokemon(View v){

        logInConsole("Psykokwak Stop !");

        bottomConsole();
    }

    public void goForward(View v){

        logInConsole("Psykokwak Avance !");

        bottomConsole();
    }

    public void goBackward(View v){

        logInConsole("Psykokwak Recule !");

        bottomConsole();
    }

    public void goRight(View v){

        logInConsole("Psykokwak à Droite !");

        bottomConsole();
    }

    public void goLeft(View v){

        logInConsole("Psykokwak à Gauche");

        bottomConsole();
    }

    private void logInConsole(String log){

        TextView console = (TextView)findViewById(R.id.consoleText);

        console.append(log+"\n");
    }

    private void bottomConsole(){

        ScrollView console = (ScrollView)findViewById(R.id.scrollView);

        console.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    // Conservation des logs de la console lors d'un changement d'orientation du téléphone.

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        TextView console = (TextView)findViewById(R.id.consoleText);

        CharSequence consoleLogs = console.getText();

        outState.putCharSequence(CONSOLE_LOGS, consoleLogs);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        CharSequence consoleLogs = savedInstanceState.getCharSequence(CONSOLE_LOGS);

        TextView console = (TextView)findViewById(R.id.consoleText);

        console.setText(consoleLogs);
    }

    private void connectWifi(){

        String networkSSID = "24HDUCODE";
        String networkPass = "2018#24hcode!";

        WifiConfiguration wifiConfig = new WifiConfiguration();

        wifiConfig.SSID = String.format("\"%s\"", networkSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", networkPass);

        WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);

        int netId = wifiManager.addNetwork(wifiConfig);

        wifiManager.disconnect();

        wifiManager.enableNetwork(netId, true);

        wifiManager.reconnect();
    }
}