package com.example.empresa_repaso.controllers;

import com.example.empresa_repaso.CRUD.ClienteCRUD;
import com.example.empresa_repaso.EmpresaApplication;
import com.example.empresa_repaso.classes.Cliente;
import com.example.empresa_repaso.util.Alerta;
import com.example.empresa_repaso.util.Datos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientesController implements Initializable {

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

    private List<Cliente> clientes;

    private Cliente cliente_seleccionado;

    private ClienteCRUD clienteCRUD;

    @FXML
    void OnActualizarClick(ActionEvent event) {
        if(cliente_seleccionado == null){
            Alerta.mensajeError("Selecciona un cliente para poder modifcarlo.");
        } else {
            cliente_seleccionado.setNombre(txt_nombre.getText());
            cliente_seleccionado.setApellidos(txt_apellidos.getText());
            cliente_seleccionado.setEmail(txt_email.getText());
            cliente_seleccionado.setTelefono(txt_telefono.getText());

            if(clienteCRUD.actualizarCliente(cliente_seleccionado)){
                cargarClientes();
                Alerta.mensajeInfo("ÉXITO","Cliente actualizado correctamente.");
            }
        }
    }

    @FXML
    void OnCancelarClick(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void OnClienteClick(MouseEvent event) {
        cliente_seleccionado = tv_clientes.getSelectionModel().getSelectedItem();
        if (cliente_seleccionado != null) {
            txt_nombre.setText(cliente_seleccionado.getNombre());
            txt_apellidos.setText(cliente_seleccionado.getApellidos());
            txt_email.setText(cliente_seleccionado.getEmail());
            txt_telefono.setText(cliente_seleccionado.getTelefono());
        }
    }

    @FXML
    void OnEliminarClick(ActionEvent event) {
        if(cliente_seleccionado == null){
            Alerta.mensajeError("Selecciona un cliente para poder eliminarlo.");
        } else {
            if(clienteCRUD.eliminarCliente(cliente_seleccionado)){
                cargarClientes();
                Alerta.mensajeInfo("ÉXITO","Cliente eliminado correctamente.");
            }
        }
    }

    @FXML
    void OnNuevoClick(ActionEvent event) {
        if(txt_nombre.getText().isEmpty() || txt_apellidos.getText().isEmpty() || txt_email.getText().isEmpty() || txt_telefono.getText().isEmpty()){
            Alerta.mensajeError("Completa todos los campos, por favor.");
        } else {
            Cliente nuevo_cliente = new Cliente(txt_nombre.getText(), txt_apellidos.getText(), txt_email.getText(), txt_telefono.getText());
            if(clienteCRUD.insertarCliente(nuevo_cliente)){
                cargarClientes();
                Alerta.mensajeInfo("ÉXITO","Cliente creado correctamente.");
            }
        }
    }

    @FXML
    void OnVerTransaccionesClick(ActionEvent event) {
        if(cliente_seleccionado == null){
            Alerta.mensajeError("Selecciona un cliente para poder ver sus transacciones.");
        } else {
            Datos.getInstancia().setCliente(cliente_seleccionado);
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(EmpresaApplication.class.getResource("transacciones.fxml"));
                Parent root = fxmlLoader.load();
                TransaccionesController controller = fxmlLoader.getController();
                System.out.println("DATOS C1: " + Datos.getInstancia().getCliente());
                controller.obtenerCliente();

                Scene scene = new Scene(root);
                Stage stage = (Stage) bt_transacciones.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                Alerta.mensajeError(e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteCRUD = new ClienteCRUD();

        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tc_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tc_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tc_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        cargarClientes();

        Datos.getInstancia().setCliente(cliente_seleccionado);
    }

    public void cargarClientes(){
        clientes = clienteCRUD.obtenerClientes();
        tv_clientes.getItems().setAll(clientes);
    }

    public void limpiarCampos(){
        txt_nombre.clear();
        txt_apellidos.clear();
        txt_email.clear();
        txt_telefono.clear();
    }
}