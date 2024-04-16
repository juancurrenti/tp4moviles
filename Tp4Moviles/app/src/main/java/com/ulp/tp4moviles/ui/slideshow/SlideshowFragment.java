package com.ulp.tp4moviles.ui.slideshow;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ulp.tp4moviles.R;

public class SlideshowFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        Button salirButton = root.findViewById(R.id.salirbutton);
        salirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avisoSalida();
            }
        });

        return root;
    }

    private void avisoSalida() {
        new AlertDialog.Builder(getContext())
                .setTitle("Confirmar Salida")
                .setMessage("¿Estás seguro de que deseas salir de la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    getActivity().finish();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
