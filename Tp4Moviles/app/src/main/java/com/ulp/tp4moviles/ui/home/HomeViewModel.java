package com.ulp.tp4moviles.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.Manifest;
public class HomeViewModel extends ViewModel {
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void makeCall(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            errorLiveData.setValue("Por favor, ingresa un número válido.");
        } else {
            errorLiveData.setValue("");
        }
    }
}