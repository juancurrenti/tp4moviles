package com.ulp.tp4moviles.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import android.Manifest;
import com.ulp.tp4moviles.R;

public class HomeFragment extends Fragment {
    private EditText numTelefono;
    private TextView mensajeError;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        numTelefono = view.findViewById(R.id.editTextPhone);
        Button callButton = view.findViewById(R.id.buttonCall);
        mensajeError = view.findViewById(R.id.text_home);

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        callButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                viewModel.makeCall(numTelefono.getText().toString());
            }
        });

        viewModel.getErrorLiveData().observe(getViewLifecycleOwner(), error -> {
            mensajeError.setText(error);
            if (error.isEmpty()) {
                hacerLlamada(numTelefono.getText().toString());
            }
        });

        return view;
    }

    private void hacerLlamada(String numTelefono) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + numTelefono));
        startActivity(intent);
    }
}