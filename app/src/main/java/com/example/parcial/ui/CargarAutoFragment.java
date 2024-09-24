package com.example.parcial.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.parcial.databinding.FragmentCargarAutoBinding;
import com.example.parcial.models.Auto;

public class CargarAutoFragment extends Fragment {

    private FragmentCargarAutoBinding binding;
    private CargarAutoViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCargarAutoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CargarAutoViewModel.class);

        TextView tvMensaje = binding.tvMensaje;

        binding.btnCargar.setOnClickListener(v -> {
            Auto auto = obtenerAutoDesdeFormulario();
            viewModel.cargarAuto(auto);
        });

        viewModel.getCargarAutoResult().observe(getViewLifecycleOwner(), success -> {
            if (success) {
                mostrarMensaje("Auto cargado con exito", true);
                limpiarCampos();
            } else {
                mostrarMensaje("Ya existe un auto con esa patente", false);
            }
        });
    }

    private Auto obtenerAutoDesdeFormulario() {
        String patente = binding.editPatente.getText().toString();
        String marca = binding.editMarca.getText().toString();
        String modelo = binding.editModelo.getText().toString();
        double precio = Double.parseDouble(binding.editPrecio.getText().toString());
        return new Auto(patente, marca, modelo, precio);
    }

    private void mostrarMensaje(String mensaje, boolean exito) {
        binding.tvMensaje.setText(mensaje);
        int color = exito ? android.R.color.holo_green_dark : android.R.color.holo_red_dark;
        binding.tvMensaje.setTextColor(getResources().getColor(color));
        binding.tvMensaje.setVisibility(View.VISIBLE);
    }

    private void limpiarCampos() {
        binding.editPatente.setText("");
        binding.editMarca.setText("");
        binding.editModelo.setText("");
        binding.editPrecio.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
