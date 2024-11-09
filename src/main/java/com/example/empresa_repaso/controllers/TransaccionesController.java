package com.example.empresa_repaso.controllers;

import com.example.empresa_repaso.EmpresaApplication;
import com.example.empresa_repaso.classes.TipoTransaccion;
import com.example.empresa_repaso.classes.Transacciones;
import com.example.empresa_repaso.util.Alerta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TransaccionesController {

    @FXML
    private Button bt_actualizar;

    @FXML
    private Button bt_cancelar;

    @FXML
    private Button bt_eliminar;

    @FXML
    private Button bt_nuevo;

    @FXML
    private Button bt_volver;

    @FXML
    private ChoiceBox<TipoTransaccion> cb_tipo;

    @FXML
    private DatePicker dt_fecha;

    @FXML
    private Text id_nombrecliente;

    @FXML
    private TableColumn<Transacciones, String> tc_cliente;

    @FXML
    private TableColumn<Transacciones, String> tc_email;

    @FXML
    private TableColumn<Transacciones, String> tc_fecha;

    @FXML
    private TableColumn<Transacciones, Integer> tc_id;

    @FXML
    private TableColumn<Transacciones, String> tc_telefono;

    @FXML
    private TableView<Transacciones> tv_transacciones;

    @FXML
    private TextField txt_cliente;

    @FXML
    private TextField txt_email;

    @FXML
    void OnActualizarClick(ActionEvent event) {

    }

    @FXML
    void OnCancelarClick(ActionEvent event) {

    }

    @FXML
    void OnEliminarClick(ActionEvent event) {

    }

    @FXML
    void OnNuevoClick(ActionEvent event) {

    }

    @FXML
    void OnTransaccionClick(MouseEvent event) {

    }

    @FXML
    void OnVolverClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(EmpresaApplication.class.getResource("clientes.fxml"));
            Parent root = fxmlLoader.load();
            ClientesController controller = fxmlLoader.getController();

            Scene scene = new Scene(root);
            Stage stage = (Stage) bt_volver.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            Alerta.mensajeError(e.getMessage());
        }
    }

}
