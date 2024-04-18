package com.ulp.tp4moviles;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText pass;
    private Button loginBtn;
    private TextView errorTV;
    private LoginViewModel viewModel;
    private WifiConectado wifiConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        solicitarPermisos();
        registrarBroadcast();

        usuario = findViewById(R.id.usuario);
        pass = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.loginBtn);
        errorTV = findViewById(R.id.errorTV);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.autentificador(usuario.getText().toString(), pass.getText().toString());
            }
        });

        viewModel.getLoginCorrecto().observe(this, esCorrecto -> {
            if (Boolean.TRUE.equals(esCorrecto)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getMensajeError().observe(this, message -> errorTV.setText(message));
    }

    private void solicitarPermisos(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},2000);
        }

    }
    private void registrarBroadcast(){
        this.wifiConectado=new WifiConectado();
        registerReceiver(wifiConectado,new IntentFilter( "android.net.wifi.supplicant.CONNECTION_CHANGE"
        ));
    }

}
