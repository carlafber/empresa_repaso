package com.example.empresa_repaso.controllers;

import com.example.empresa_repaso.classes.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClientesController {

    @FXML
    private Button bt_actualizar;

    @FXML
    private Button bt_cancelar;

    @FXML
    private Button bt_eliminar;

    @FXML
    private Button bt_nuevo;

    @FXML
    private Button bt_transacciones;

    @FXML
    private TableColumn<Cliente, String> tc_apellidos;

    @FXML
    private TableColumn<Cliente, String> tc_email;

    @FXML
    private TableColumn<Cliente, Integer> tc_id;

    @FXML
    private TableColumn<Cliente, String> tc_nombre;

    @FXML
    private TableColumn<Cliente, String> tc_telefono;

    @FXML
    private TableView<Cliente> tv_clientes;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_telefono;

    @FXML
    void OnActualizarClick(ActionEvent event) {

    }

    @FXML
    void OnCancelarClick(ActionEvent event) {

    }

    @FXML
    void OnClienteClick(MouseEvent event) {

    }

    @FXML
    void OnEliminarClick(ActionEvent event) {

    }

    @FXML
    void OnNuevoClick(ActionEvent event) {

    }

    @FXML
    void OnVerTransaccionesClick(ActionEvent event) {

    }

}
