package com.example.empresa_repaso.classes;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Transacciones")
public class Transacciones{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_transaccion")
    private int id_transaccion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "valor")
    private double valor;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public Transacciones() {
    }

    public Transacciones(int id_transaccion, LocalDate fecha, double valor, TipoTransaccion tipo, Cliente cliente) {
        this.id_transaccion = id_transaccion;
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public Transacciones(LocalDate fecha, double valor, TipoTransaccion tipo) {
        this.fecha = fecha;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id_transaccion;
    }

    public void setId(int id_transaccion) {
        this.id_transaccion = id_transaccion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreCliente() {
        if (cliente != null) {
            return cliente.getNombre() + " " + cliente.getApellidos();
        }
        return "";
    }
}