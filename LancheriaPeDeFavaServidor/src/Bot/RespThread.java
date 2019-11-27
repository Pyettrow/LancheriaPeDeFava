/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bot;

import DAL.DAO;
import Model.Categoria;
import Model.Produto;
import Model.Pedido;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dougl
 */
public class RespThread extends Thread {

    private int cliId;
    private String cliNome;
    private String msg;
    private String data;
    private String hora;
    private String resp;
    private int cntr;
    private Tratamento_String tr;
    private DAO dCat;
    private Categoria cat;
    private Produto prod;
    private Pedido ped;
//    private ArrayList<Categoria> lstCat;
    private ArrayList<Produto> lstProd;

    public RespThread(Tratamento_String t) throws ClassNotFoundException, SQLException {
        this.cliId = t.getUser_id();
        this.cliNome = t.getNome();
        this.msg = t.getMensagem();
        this.data = t.getData();
        this.hora = t.getHora();
        this.resp = "";
        this.tr = t;
        this.cntr = 0;
        this.dCat = new DAO();
//        this.lstCat = new ArrayList<>();
//        this.lstProd = new ArrayList<>();

    }

    public RespThread(int cliId, String cliNome) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cntr = 0;
    }

    public void responder(Tratamento_String t, Bot b) throws SQLException {
        this.msg = t.getMensagem();
        this.data = t.getData();
        this.hora = t.getHora();
        this.resp = "";
        this.tr = t;
        cntr = 0;
        System.out.println(cntr);

        if (cntr == 0) {
            try {
                //Envia a mensagem padrão inicial
                b.enviarMensagem(Integer.toString(cliId), "Ola, seja bem vindo ao restaurante Pé de Fava, digite o codigo da categoria de seu interresse a seguir:");

                //Faz a recepção da lista das categorias no BD para informar ao cliente
                DAL.DAO dao = new DAL.DAO();
                ArrayList<Categoria> lstCat = dao.todosRegistros(Categoria.class, 0, 0);

                //Enviar outras mensagens contendo as categorias
                for (int i = 0; i < lstCat.size(); i++) {
                    b.enviarMensagem(Integer.toString(cliId), (Integer.toString(lstCat.get(i).getIdCategoria()) + "%20" + lstCat.get(i).getDescricao()));
                }

                //Incrementa no contador para saber em qual processo do pedido esta
                cntr++;

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RespThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getCliId() {
        return cliId;
    }

    public int getCntr() {
        return cntr;
    }

}
