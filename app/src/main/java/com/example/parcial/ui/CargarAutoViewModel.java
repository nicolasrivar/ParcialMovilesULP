package com.example.parcial.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcial.MainActivity;
import com.example.parcial.models.Auto;

public class CargarAutoViewModel extends ViewModel {
    private final MutableLiveData<Boolean> cargarAutoResult = new MutableLiveData<>();

    public LiveData<Boolean> getCargarAutoResult() {
        return cargarAutoResult;
    }

    public void cargarAuto(Auto nuevoAuto) {
        if (esAutoValido(nuevoAuto)) {
            if (!existeAutoConPatente(nuevoAuto.getPatente())) {
                MainActivity.listaAutos.add(nuevoAuto);
                cargarAutoResult.setValue(true);
            } else {
                cargarAutoResult.setValue(false);
            }
        } else {
            cargarAutoResult.setValue(false);
        }
    }

    private boolean esAutoValido(Auto auto) {
        return auto.getPatente() != null && !auto.getPatente().isEmpty() &&
                auto.getMarca() != null && !auto.getMarca().isEmpty() &&
                auto.getModelo() != null && !auto.getModelo().isEmpty() &&
                auto.getPrecio() > 0;
    }

    private boolean existeAutoConPatente(String patente) {
        for (Auto auto : MainActivity.listaAutos) {
            if (auto.getPatente().equals(patente)) {
                return true;
            }
        }
        return false;
    }
}
