package com.example.empresa_repaso.util;

import com.example.empresa_repaso.classes.Cliente;

public class Datos {
    private static Datos instancia;
    private Cliente cliente;

    private Datos() {
    }

    public static Datos getInstancia() {
        if (instancia == null) {
            instancia = new Datos();
        }
        return instancia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
