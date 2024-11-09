package com.example.empresa_repaso.controllers;

import com.example.empresa_repaso.CRUD.ClienteCRUD;
import com.example.empresa_repaso.CRUD.TransaccionesCRUD;
import com.example.empresa_repaso.EmpresaApplication;
import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.classes.TipoTransaccion;
import com.example.empresa_repaso.classes.Transacciones;
import com.example.empresa_repaso.util.Alerta;
import com.example.empresa_repaso.util.Datos;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TransaccionesController implements Initializable {

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
    private TableColumn<Transacciones, String> tc_fecha;

    @FXML
    private TableColumn<Transacciones, Integer> tc_id;

    @FXML
    private TableColumn<Transacciones, String> tc_tipo;

    @FXML
    private TableColumn<Transacciones, String> tc_valor;

    @FXML
    private TableView<Transacciones> tv_transacciones;

    @FXML
    private TextField txt_cliente;

    @FXML
    private TextField txt_valor;

    private Cliente cliente;

    private List<Transacciones> transacciones;

    private Transacciones transacccion_seleccionada;

    private TransaccionesCRUD transaccionesCRUD;

    public void obtenerCliente() {
        cliente  = Datos.getInstancia().getCliente();
        if (cliente != null) {
            id_nombrecliente.setText(cliente.getNombre() + " " + cliente.getApellidos());
            cargarTransacciones(cliente);
        } else {
            Alerta.mensajeError("");
        }
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        transaccionesCRUD = new TransaccionesCRUD();

        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_cliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreCliente()));
        tc_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tc_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tc_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
    }

    public void cargarTransacciones(Cliente cliente){
        //transacciones = transaccionesCRUD.obtenerTransaccionesCliente(cliente);
        //tv_transacciones.getItems().setAll(transacciones);
    }

    public void limpiarCampos(){
        txt_cliente.clear();
        dt_fecha.setValue(null);
        txt_valor.clear();
        cb_tipo.setValue(null);
    }
}
