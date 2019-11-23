/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import Model.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author m101611
 */
public class DAO<T> {
    public Connection con;
    
    public DAO() throws ClassNotFoundException, SQLException {
        Conexao c = new Conexao();
        con = c.getConexao();
    }
    
    public void insertRegistro(Object obj) throws SQLException{
        Statement st = con.createStatement();
        
        String queryInsert = "insert into";
        
        /*Caso a classe seja uma categoria*/
        if(obj instanceof Categoria){
            Categoria cat = (Categoria) obj;
            queryInsert += " categoria(descricao) values ('"+cat.getDescricao()+"')";
            st.execute(queryInsert);
        }else if(obj instanceof Produto){
            Produto pro = (Produto) obj;
            double novoPreco = pro.getCategoria_idCategoria();
            queryInsert += " produto (descricao, preco, Categoria_idCategoria) VALUES ('"+pro.getDescricao()+"',"+pro.getPreco()+", "+pro.getCategoria_idCategoria()+")";
            st.execute(queryInsert);
        }else{
            JOptionPane.showMessageDialog(null, "Falta tratar essa classe!");
        }
        
        JOptionPane.showMessageDialog(null, "Registro cadastrada!");
    }
    
    /**
     * Consulta todos os registros de uma certa classe, porém para o PedidoItem
     * era necessário realizar a consulta tendo com resultado somentes os itens 
     * do pedido passado
     * Classe - Enviar a classe para realizar a constula(Pedido.class)
     * Pedido - Passar o numero do pedido para realizar a consulta, caso não 
     * precisa passar 0
     * @param classe
     * @param pedido
     * @return
     * @throws SQLException 
     */
    public ArrayList todosRegistros(Class classe, int pedido) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs;
        
        String queryConsulta = "SELECT * FROM";
        
        ArrayList<Categoria> lstCategoria = new ArrayList();
        ArrayList<Produto> lstProduto = new ArrayList();
        ArrayList<Pedido> lstPedido = new ArrayList();
        ArrayList<PedidoItem> lstPedidoItem = new ArrayList();
        
        if(classe == Categoria.class){
            lstCategoria = new ArrayList();
            queryConsulta += " categoria";
            rs = st.executeQuery(queryConsulta);
            
            while (rs.next()){
                Categoria newCat = new Categoria();
                newCat.setIdCategoria(rs.getInt("idCategoria"));
                newCat.setDescricao(rs.getString("Descricao"));
                lstCategoria.add(newCat);
            }            
            
            return lstCategoria;
        }else if(classe == Produto.class){
            queryConsulta += " produto";
            rs = st.executeQuery(queryConsulta);
            
            while (rs.next()){
                Produto newProduto = new Produto();
                newProduto.setIdProduto(rs.getInt("idProduto"));
                newProduto.setDescricao(rs.getString("Descricao"));
                newProduto.setPreco(rs.getDouble("Preco"));
                newProduto.setCategoria_idCategoria(rs.getInt("Categoria_idCategoria"));
                lstProduto.add(newProduto);
            }            
            
            return lstProduto;
        }else if(classe == Pedido.class){
            queryConsulta += " pedido WHERE finalizado <> 1";
            rs = st.executeQuery(queryConsulta);
                    
            while (rs.next()){
                Pedido ped = new Pedido();
                ped.setIdPedido(rs.getInt("idPedido"));
                ped.setData(rs.getDate("Data"));
                ped.setFinalizado(rs.getInt("Finalizado"));
                ped.setEntregue(rs.getInt("Entregue"));
                ped.setCliente_idCliente(rs.getInt("Cliente_idCliente"));
                lstPedido.add(ped);
            }
            
            return lstPedido;
        }else if(classe == PedidoItem.class){
            queryConsulta += " pedidoitem WHERE Pedido_idPedido = "+pedido;
            rs = st.executeQuery(queryConsulta);
            
            while(rs.next()){
                PedidoItem pedItem = new PedidoItem();
                pedItem.setPedido_id(rs.getInt("Produto_idProduto"));
                pedItem.setProduto_id(rs.getInt(rs.getInt("Pedido_idPedido")));
                pedItem.setQuantidade(rs.getInt("Quantidade"));
                pedItem.setPreco(rs.getDouble("Preco"));
                pedItem.setObservacao(rs.getString("Observacao"));
                lstPedidoItem.add(pedItem);
            }
            return lstPedidoItem;
        }else{
            JOptionPane.showMessageDialog(null, "Sem tratamento para essa classe");
            return null;
        }        
    }
    
    /**
     * Metodo para pegar ultimo id cadastrado e adicionar mais 1, para assim ser
     * possivel mostrar o novo id do novo cadastro, caso o retorno da consulta 
     * seje igual a null seria retornado o valor 1 para primeiro cadastro.
     * Recebe a classe que dever ser realizado a consulta
     * retorna um int com o valor
     * @param classe
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String ultimoRegistro(Class classe) throws SQLException, ClassNotFoundException{
        int ultimoRegistro = 0;
        
        Statement st = con.createStatement();
        ResultSet rs = null;
        
        String queryConstula = "SELECT MAX(";
        
        if(classe == Categoria.class){
            queryConstula += "idCategoria) as ultimoRegistro FROM categoria";
            rs = st.executeQuery(queryConstula);                        
            
            if(rs != null && rs.next()){
                ultimoRegistro = rs.getInt("ultimoRegistro");
                ultimoRegistro++;
            }else{
                ultimoRegistro = 1;
            }
        }else if(classe == Produto.class){
            queryConstula += "idProduto) as ultimoRegistro FROM produto";
            rs = st.executeQuery(queryConstula);
            
            if(rs != null && rs.next()){
                ultimoRegistro = rs.getInt("ultimoRegistro");
                ultimoRegistro++;
            }else{
                ultimoRegistro = 1;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sem tratamento para essa classe!");
        }        
        
        return String.valueOf(ultimoRegistro);
    }
    
    public void alterarRegistro(Object obj) throws SQLException{
        Statement st = con.createStatement();
        
        String query = "";
        
        if(obj instanceof Categoria){
            Categoria alterCat = (Categoria) obj;
            query = "update categoria set descricao = '"+alterCat.getDescricao()+"'"
                    + " where idCategoria = "+alterCat.getIdCategoria()+"";
            
            st.executeUpdate(query);
        }else if(obj instanceof Produto){
            Produto alterProduto = (Produto) obj;
            query = "update produto set descricao = '"+alterProduto.getDescricao()+"', preco = '"+alterProduto.getPreco()+"', "
                    + "Categoria_idCategoria = '"+alterProduto.getCategoria_idCategoria()+"' where idProduto ="+alterProduto.getIdProduto();
            st.executeUpdate(query);
        }else{
            JOptionPane.showMessageDialog(null, "Classe nao possue tratamento");
        }
        
        JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
    }
    
    /**
     * Tentativa de criar uma metodo Generico, porem estou com problema em passar 
     * um objeto da classe produto, com isso estou passando um int OPCAO.
     * 1 - Categoria
     * 2 - Produto
     * @param opcao
     * @param obj
     * @throws SQLException 
     */
    public void excluirRegistro(int opcao, Object obj) throws SQLException{
        Statement st = con.createStatement();
        
        if(opcao == 1){
            
            Categoria excluirCat = (Categoria) obj;
            String query = "DELETE FROM categoria WHERE idCategoria = "+excluirCat.getIdCategoria();
            st.executeUpdate(query);
            
        }else if(opcao == 2){
            
            String query = "DELETE FROM produto WHERE idProduto = "+obj;
            st.executeUpdate(query);
                       
        }else{
            JOptionPane.showMessageDialog(null, "Sem tratamento para essa classe!");
        }
        
        JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");
    }
    
    /**
     * Metodo para realizar a consulta com base no objeto que foi passado
     * tentei fazer de moto generico, porém estou com dúvidas no tipo de variavel
     * que tenho que retornar, coloquei "Produto" para quebrar o galho
     * @param cod    
     * @return
     * @throws SQLException 
     */
    public Produto consultaProduto(int cod) throws SQLException{
        Statement st = con.createStatement();
        
        String queryConsulta = "SELECT * FROM produto WHERE idProduto = "+cod;
        ResultSet rs = st.executeQuery(queryConsulta);

        if(rs != null && rs.next()){
            Produto prod = new Produto();
            prod.setIdProduto(rs.getInt("idProduto"));
            prod.setDescricao(rs.getString("Descricao"));
            prod.setPreco(rs.getDouble("Preco"));
            prod.setCategoria_idCategoria(rs.getInt("Categoria_idCategoria"));
            return prod;
        }else{
            JOptionPane.showMessageDialog(null, "Consulta do produto veio vazia");
            return null;
        }
    }
    
    public Cliente consultaCliente(int id) throws SQLException{
        Statement st = con.createStatement();
        
        String queryConsulta = "SELECT * FROM cliente WHERE idCliente = "+id;
        ResultSet rs = st.executeQuery(queryConsulta);

        if(rs != null && rs.next()){
            Cliente cli = new Cliente();
            cli.setIdCliente(rs.getInt("idCliente"));
            cli.setNomeCliente(rs.getString("Nome"));
            return cli;
        }else{
            JOptionPane.showMessageDialog(null, "Consulta do cliente veio vazia");
            return null;
        }
    }
    /**
     * Consultas no geral, não estava achando um metodo que iria caber para essas
     * consultas
     * cod1 - Serve para validar nos IF's qual consulta seria realizada
     * cod2 - Manda a PK para poder fazer a consulta com base nela
     * @param cod1
     * @param cod2
     */
    public float consultas(int cod1, int cod2) throws SQLException{
        Statement st = con.createStatement();
        
        if(cod1 == 1){
            String query = "SELECT SUM((preco*quantidade)) AS precoTotal FROM pedidoitem WHERE Pedido_idPedido ="+cod2;
            ResultSet rs = st.executeQuery(query);
            
            if(rs != null && rs.next()){
                return rs.getFloat("precoTotal"); 
            }else{
                return 0;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Sem tratamento para essa opção!");
            return 0;
        }
    }
}