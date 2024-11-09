package com.example.empresa_repaso.CRUD;

import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.classes.Transacciones;

import java.util.List;

public interface CRUDTransacciones {
    List<Transacciones> obtenerTransaccionesCliente(Cliente cliente);
}
