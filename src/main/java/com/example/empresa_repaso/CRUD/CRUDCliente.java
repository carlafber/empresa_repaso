package com.example.empresa_repaso.CRUD;

import com.example.empresa_repaso.classes.Cliente;

import java.util.List;

public interface CRUDCliente {
    List<Cliente> obtenerClientes();

    boolean actualizarCliente(Cliente cliente);

    boolean eliminarCliente(Cliente cliente);

    boolean insertarCliente(Cliente cliente);
}
