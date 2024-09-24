package com.example.parcial.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.parcial.MainActivity;
import com.example.parcial.models.Auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListarAutosViewModel extends ViewModel {

    private MutableLiveData<List<Auto>> autosOrdenados;

    public LiveData<List<Auto>> getAutosOrdenados() {
        if (autosOrdenados == null) {
            autosOrdenados = new MutableLiveData<>();
            mostrarAutos();
        }
        return autosOrdenados;
    }

    public void mostrarAutos() {
        List<Auto> listaOrdenada = new ArrayList<>(MainActivity.listaAutos);
        Collections.sort(listaOrdenada, (a1, a2) -> Double.compare(a2.getPrecio(), a1.getPrecio()));
        autosOrdenados.setValue(listaOrdenada);
    }
}
