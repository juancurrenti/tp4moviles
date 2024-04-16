package com.ulp.tp4moviles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Boolean> loginCorrecto = new MutableLiveData<>();
    private final MutableLiveData<String> mensajeError = new MutableLiveData<>();

    public LiveData<Boolean> getLoginCorrecto() {
        return loginCorrecto;
    }

    public LiveData<String> getMensajeError() {
        return mensajeError;
    }

    public void autentificador(String usuario, String contrasena) {
        if ("admin".equals(usuario) && "admin".equals(contrasena)) {
            loginCorrecto.setValue(true);
        } else {
            mensajeError.setValue("Usuario y/o contraseña incorrecto");
        }
    }
}
