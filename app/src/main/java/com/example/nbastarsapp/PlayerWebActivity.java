package com.example.nbastarsapp;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nbastarsapp.data.DatabaseProvider;
import com.example.nbastarsapp.model.Player;

public class PlayerWebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_web);

        WebView webView = findViewById(R.id.webViewPlayer);

        int playerId = getIntent().getIntExtra("player_id", -1);

        Player player = DatabaseProvider
                .getInstance(this)
                .playerDao()
                .getPlayerById(playerId);

        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        if (player != null) {
            webView.loadUrl(player.getWebUrl());
        }
    }
}