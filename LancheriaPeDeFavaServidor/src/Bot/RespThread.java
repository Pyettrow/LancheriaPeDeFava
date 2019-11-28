/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bot;

import Model.Categoria;
import Model.Produto;
import Model.Pedido;
import Model.PedidoItem;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dougl
 */
public class RespThread extends Thread {

    private int cliId;
    private String msg;
    private int cntr;
    private PedidoItem pedI;
    private Pedido ped;
    private ArrayList<Categoria> lstCat;
    private ArrayList<Produto> lstProd;
    private ArrayList<Pedido> lstPed;
    private int cat;
    private int prod;
    private int quant;
    private boolean boo;
    private boolean aux;

    public RespThread(Tratamento_String t) throws ClassNotFoundException, SQLException {
        this.cliId = t.getUser_id();
        this.msg = t.getMensagem();
        this.cntr = 0;
        this.pedI = new PedidoItem();
        this.ped = new Pedido();
        this.cat = 0;
        this.prod = 0;
        this.quant = 0;
        this.boo = false;
        this.aux = true;
    }

    public RespThread(int cliId, String cliNome) {
        this.cliId = cliId;
        this.cntr = 0;
        this.pedI = new PedidoItem();
        this.ped = new Pedido();
        this.cat = 0;
        this.prod = 0;
        this.quant = 0;
        this.boo = false;
        this.aux = true;
    }

    public void responder(Tratamento_String t, Bot b) throws SQLException {
        try {
            this.msg = t.getMensagem();

            switch (cntr) {
                case 0:
                    //Faz a recepção da lista das categorias no BD para informar ao cliente
                    DAL.DAO daoC = new DAL.DAO();
                    lstCat = daoC.todosRegistros(Categoria.class, 0, 0);

                    //Envia a mensagem padrão inicial
                    b.enviarMensagem(Integer.toString(cliId), "Ola,%20seja%20bem%20vindo%20ao%20restaurante%20Pé%20de%20Fava,%20digite%20o%20codigo%20da%20categoria%20de%20seu%20interresse%20a%20seguir:");

                    //Enviar outras mensagens contendo as categorias
                    for (int i = 0; i < lstCat.size(); i++) {
                        b.enviarMensagem(Integer.toString(cliId), (Integer.toString(lstCat.get(i).getIdCategoria()) + "%20" + lstCat.get(i).getDescricao()));
                    }
                    //Incrementa no contador para saber em qual processo do pedido esta
                    cntr++;
                    break;

                case 1:
                    //For para verificar qual a categoria selecionada
                    for (int i = 0; i < lstCat.size(); i++) {
                        if (msg.contains(lstCat.get(i).getDescricao()) || msg.contains(Integer.toString(lstCat.get(i).getIdCategoria()))) {
                            cat = i + 1;
                        }
                    }

                    if (cat != 0) {//Se for uma categoria valida

                        //Faz a recepção da lista dos Produtos no BD para informar ao cliente
                        DAL.DAO daoP = new DAL.DAO();
                        lstProd = daoP.todosRegistros(Produto.class, 0, cat);

                        b.enviarMensagem(Integer.toString(cliId), "Escolha%20o%20produto%20desejado:");

                        //Enviar outras mensagens contendo as categorias
                        for (int i = 0; i < lstCat.size(); i++) {
                            b.enviarMensagem(Integer.toString(cliId), (Integer.toString(lstProd.get(i).getIdProduto()) + "%20" + lstProd.get(i).getDescricao() + "%20R$" + Double.toString(lstProd.get(i).getPreco())));
                        }

                        //Incrementa no contador para saber em qual processo do pedido esta
                        cntr++;
                    } else {//Se não for uma categoria valida solicita outra
                        b.enviarMensagem(Integer.toString(cliId), "Categoria%20selecionado%20inexistente,%20tente%20novamente:");
                    }
                    break;

                case 2:
                    //For para verificar qual o produto selecionado
                    for (int i = 0; i < lstProd.size(); i++) {
                        if (msg.contains(lstProd.get(i).getDescricao()) || msg.contains(Integer.toString(lstProd.get(i).getIdProduto()))) {
                            prod = i + 1;
                        }
                    }

                    if (prod != 0) {//Se for um produto valido

                        //Pede a quantidade deste produto
                        b.enviarMensagem(Integer.toString(cliId), "Digite%20a%20quantidade%20do%20produto%20selecionado:");

                        //Incrementa no contador para saber em qual processo do pedido esta
                        cntr++;
                    } else {//Se for um produto invalido solicita outro
                        b.enviarMensagem(Integer.toString(cliId), "Produto%20selecionado%20inexistente,%20tente%20novamente:");
                    }
                    break;

                case 3:
                    //Recebe e testa se é uma quantidade valida
                    quant = testQuant(msg);

                    if (quant != 0) {//Se for uma quantidade valida

                        //Vai setar alguns dados do PedidoItem
                        pedI.setProduto_id(lstProd.get(prod - 1).getIdProduto());
                        pedI.setQuantidade(quant);
                        pedI.setPreco(lstProd.get(prod - 1).getPreco());

                        //Pergunta se o cliente quer adicionar alguma observação
                        b.enviarMensagem(Integer.toString(cliId), "Gostaria%20de%20adicionar%20alguma%20observação?");
                        b.enviarMensagem(Integer.toString(cliId), "1%20Sim");
                        b.enviarMensagem(Integer.toString(cliId), "2%20Não");

                        //Incrementa no contador para saber em qual processo do pedido esta
                        cntr++;
                    } else {//Caso não seja um quantidade valida solicita outra quantidade
                        b.enviarMensagem(Integer.toString(cliId), "Quantidade%20invalida,%20tente%20novamente:");
                    }
                    break;

                case 4:
                    //Se o cliente quis adicionar uma observação
                    if (msg.equals("sim") || msg.equals("1")) {

                        //Solicita a discrição da observação
                        b.enviarMensagem(Integer.toString(cliId), "Digite%20sua%20observação:");

                        //Seta a variavel auxiliar para adicionar uma observação
                        boo = true;

                        //Incrementa no contador para saber em qual processo do pedido esta
                        cntr++;
                    } else {//Se o cliente não quis adicionar uma observação
                        //Insere uma observação vazia para não deixar null
                        pedI.setObservacao("");

                        //Seta a variavel auxiliar para não adicionar uma observação
                        boo = false;

                        //Incrementa no contador para saber em qual processo do pedido esta 
                        cntr++;
                    }

                    //Chamada do metodo novamente para continuar nos processos
                    responder(t, b);
                    break;

                case 5:
                    if (boo) {
                        pedI.setObservacao(msg);
                    }

                    //Mosta toda a informação do pedido do cliente
                    b.enviarMensagem(Integer.toString(cliId), ("Você%20pediu:%20" + quant + "%20" + lstProd.get(prod - 1).getDescricao() + "%20" + pedI.getObservacao() + "%20R$" + Double.toString(quant * pedI.getPreco())));

                    //Pede a confirmação do pedido do cliente
                    b.enviarMensagem(Integer.toString(cliId), "O%20que%20deseja%20realizar%20com%20seu%20pedido%20a%20seguir?");
                    b.enviarMensagem(Integer.toString(cliId), "1%20Finalizar");
                    b.enviarMensagem(Integer.toString(cliId), "2%20Cancelar");
                    b.enviarMensagem(Integer.toString(cliId), "3%20Adicionar%20produto");

                    //Incrementa no contador para saber em qual processo do pedido esta
                    cntr++;
                    break;

                case 6:

                    //Se o cliente so quiser finalizar o pedido
                    if (msg.equals("finalizar") || msg.equals("1")) {
                        //Seta as informações do pedido
                        long dataHora = Long.parseLong(t.getData() + " " + t.getHora());
                        Date dt = new Date(dataHora);
                        Timestamp time = new Timestamp(dt.getTime());
                        ped.setData(time);
                        ped.setFinalizado(1);
                        ped.setEntregue(0);
                        ped.setCliente_idCliente(cliId);

                        DAL.DAO daoPed = new DAL.DAO();
                        if (aux) {
                            //Insere o pedido no banco de dados para gerar o id do pedido que é gerado em AA
                            daoPed.insertRegistro(ped);
                            aux = false;
                        }
                        lstPed = daoPed.todosRegistros(Pedido.class, 0, 0);

                        //Procura o pedido para receber o ID de Pedido e inserir no PedidoItem
                        for (int i = 0; i < lstPed.size(); i++) {
                            if ((lstPed.get(i).getCliente_idCliente() == cliId) && (lstPed.get(i).getData() == time)) {
                                pedI.setPedido_id(lstPed.get(i).getIdPedido());
                            }
                        }

                        //Insere o pedido item
                        daoPed.insertRegistro(pedI);

                        //Reseta as informações depois de finalizado
                        cntr = 0;
                        pedI = new PedidoItem();
                        ped = new Pedido();
                        cat = 0;
                        prod = 0;
                        quant = 0;
                        aux = true;

                        lstCat = new ArrayList<>();
                        lstProd = new ArrayList<>();
                        lstPed = new ArrayList<>();

                        //Informa que o pedido foi finalizado
                        b.enviarMensagem(Integer.toString(cliId), "Pedido%20Finalizado!");

                    } else if (msg.equals("Cancelar") || msg.equals("2")) {

                        //Reseta as informações
                        cntr = 0;
                        pedI = new PedidoItem();
                        ped = new Pedido();
                        cat = 0;
                        prod = 0;
                        quant = 0;
                        aux = true;

                        lstCat = new ArrayList<>();
                        lstProd = new ArrayList<>();
                        lstPed = new ArrayList<>();

                        //Informa que o pedido foi cancelado
                        b.enviarMensagem(Integer.toString(cliId), "Pedido%20Cancelado!");

                    } else if (msg.equals("adicionar produto") || msg.equals("3")) {
                        //Seta as informações do pedido
                        long dataHora = Long.parseLong(t.getData() + " " + t.getHora());
                        Date dt = new Date(dataHora);
                        Timestamp time = new Timestamp(dt.getTime());
                        ped.setData(time);                        
                        ped.setFinalizado(1);
                        ped.setEntregue(0);
                        ped.setCliente_idCliente(cliId);

                        DAL.DAO daoPed = new DAL.DAO();
                        if (aux) {
                            //Insere o pedido no banco de dados para gerar o id do pedido que é gerado em AA
                            daoPed.insertRegistro(ped);
                            aux = false;
                        }
                        lstPed = daoPed.todosRegistros(Pedido.class, 0, 0);

                        //Procura o pedido para receber o ID de Pedido e inserir no PedidoItem
                        for (int i = 0; i < lstPed.size(); i++) {
                            if ((lstPed.get(i).getCliente_idCliente() == cliId) && (lstPed.get(i).getData() == time)) {
                                pedI.setPedido_id(lstPed.get(i).getIdPedido());
                            }
                        }
                        //Insere o pedido item
                        daoPed.insertRegistro(pedI);

                        //Envia o contador direto para o processo seguinte
                        cntr = 7;

                    } else {//Caso o cliente escolha uma opção invalida é solicitado outra opção
                        b.enviarMensagem(Integer.toString(cliId), "Opção%20invalida,%20tente%20novamente:");
                    }

                    break;

                case 7:
                    //Envia a mensagem padrão das categorias
                    b.enviarMensagem(Integer.toString(cliId), "Escolha%20a%20categoria%20desejada:");

                    //Enviar outras mensagens contendo as categorias
                    for (int i = 0; i < lstCat.size(); i++) {
                        b.enviarMensagem(Integer.toString(cliId), (Integer.toString(lstCat.get(i).getIdCategoria()) + "%20" + lstCat.get(i).getDescricao()));
                    }
                    //Envia o contador para a continuação das opções anteriores
                    cntr = 1;
                    break;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RespThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int testQuant(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            switch (s) {
                case "um":
                    return 1;
                case "uma":
                    return 1;
                case "dois":
                    return 2;
                case "duas":
                    return 2;
                case "tres":
                    return 3;
                case "quatro":
                    return 4;
                case "cinco":
                    return 5;
                case "seis":
                    return 6;
                case "sete":
                    return 7;
                case "oito":
                    return 8;
                case "nove":
                    return 9;
                case "dez":
                    return 10;
                case "onze":
                    return 11;
                case "doze":
                    return 12;
                case "treze":
                    return 13;
                case "catorze":
                    return 14;
                case "quatorze":
                    return 14;
                case "quinze":
                    return 15;
                case "dezesseis":
                    return 16;
                case "dezessete":
                    return 17;
                case "dezoito":
                    return 18;
                case "dezenove":
                    return 19;
                case "vinte":
                    return 20;
                default:
                    return 0;
            }
        }
    }

    public int getCliId() {
        return cliId;
    }

}
