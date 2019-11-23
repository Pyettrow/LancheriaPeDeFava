/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author m101611
 */
public class Pedido {
    /**
     * finalizado:
     * 0 - Em aberto
     * 1 - Finalizado
     * Entregue:
     * 0 - Não Entregue
     * 1 - Entregue
     */
    private int idPedido;    
    private Date Data;
    private int finalizado; 
    private int entregue;
    private int cliente_idCliente;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

    public int getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(int finalizado) {
        this.finalizado = finalizado;
    }

    public int getEntregue() {
        return entregue;
    }

    public void setEntregue(int entregue) {
        this.entregue = entregue;
    }

    public int getCliente_idCliente() {
        return cliente_idCliente;
    }

    public void setCliente_idCliente(int cliente_idCliente) {
        this.cliente_idCliente = cliente_idCliente;
    }
}
