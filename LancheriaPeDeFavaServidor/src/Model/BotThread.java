/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Bot.*;
import DAL.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author William
 */
public class BotThread extends Thread {

    //Criação da classe Bot
    private Bot b;
    private DAO<Cliente> d;
    private Tratamento_String t;
    private Cliente c;
    private ArrayList<RespThread> lstResp;
    private ArrayList lst;
    private RespThread rspt;

    public BotThread() throws ClassNotFoundException, SQLException {
        this.b = new Bot("990042089:AAGrvUkaaUzU3xCiIFywOZn_agsFYgAqnaw");
        this.d = new DAO<Cliente>();
//        this.t = new Tratamento_String();
//        this.c = new Cliente();
        this.lstResp = new ArrayList<RespThread>();
        this.lst = new ArrayList();
    }

    public void run() {
        try {
            //Alocação dos clientes na lista para procurar se os clientes estão no banco de dados
            lst = d.todosRegistros(Cliente.class, 0, 0);
            Cliente auxCli = new Cliente();

            for (int i = 0; i < lst.size(); i++) {
                auxCli = (Cliente) lst.get(i);
                RespThread rsp = new RespThread(auxCli.getIdCliente(), auxCli.getNomeCliente());
                lstResp.add(rsp);
            }

            //Loop infinito de recepção de mensagens
            while (true) {

                //Faz o recebmento da mensagem
                t = b.recebeMensagem();

                //Caso foi recebido alguma mensagem
                if (t != null) {

                    try {
                        //Criação do cliente
                        c = d.consultaCliente(t.getUser_id());

                        //Consulta se o cliente ja existe
                        if (c == null) {//Se ainda não existe
                            //Cria o novo cliente e ja insere no banco de dados
                            c = new Cliente();
                            c.setIdCliente(t.getUser_id());
                            c.setNomeCliente(t.getNome());
                            d.insertRegistro(c);

                            try {
                                //Manda a mensagem inicial para um novo contato
                                rspt = new RespThread(t);
                                rspt.responder(t, b);
                                lstResp.add(rspt);

                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(BotThread.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {//Se ja existe
                            //Recebe a lista de clientes no banco de dados para procurar em qual trhead esta sendo tratado
                            lst = d.todosRegistros(Cliente.class, 0, 0);

                            //For para localizar a Trhead do cliente
                            for (int i = 0; i < lst.size(); i++) {
                                //Testa se o id do cliente é o mesmo da Trhead encontrada
                                if (t.getUser_id() == lstResp.get(i).getCliId()) {
                                    //Responde o cliente
                                    lstResp.get(i).responder(t, b);
                                }
                            }
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(BotThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BotThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
