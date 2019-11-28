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
    
    /**
     * Recebe um objeto para poder diferencia em qual tabela ira inserir, com 
     * base nisso realizara a inserção na categoria ou no produto
     * @param obj
     * @throws SQLException 
     */
    public void insertRegistro(Object obj) throws SQLException{
        Statement st = con.createStatement();
        
        String queryInsert = "insert into";
        
        /*Caso a classe seja uma categoria*/
        if(obj instanceof Categoria){
            Categoria cat = (Categoria) obj;
            queryInsert += " categoria(descricao) values ('"+cat.getDescricao()+"')";
            st.execute(queryInsert);
            JOptionPane.showMessageDialog(null, "Registro cadastrado!");
        }else if(obj instanceof Produto){
            Produto pro = (Produto) obj;
            double novoPreco = pro.getCategoria_idCategoria();
            queryInsert += " produto (descricao, preco, Categoria_idCategoria) VALUES ('"+pro.getDescricao()+"',"+pro.getPreco()+", "+pro.getCategoria_idCategoria()+")";
            st.execute(queryInsert);
            JOptionPane.showMessageDialog(null, "Registro cadastrado!");
        }else if(obj instanceof Cliente){
            Cliente novoCli = (Cliente) obj;
            String query = "INSERT INTO cliente (`idCliente`, `Nome`) VALUES ("+novoCli.getIdCliente()+", '"+novoCli.getNomeCliente()+"')";
            st.execute(query);
        }else if(obj instanceof Pedido){
            Pedido novoPed = (Pedido) obj;
            String query = "INSERT INTO pedido (Data, Finalizado, Entregue, Cliente_idCliente) VALUES ("+novoPed.getData()+", "+novoPed.getFinalizado()+", "+novoPed.getEntregue()+", "+novoPed.getCliente_idCliente()+")";
            st.execute(query);
        }else if(obj instanceof PedidoItem){
            PedidoItem novoPedI = (PedidoItem) obj;
            String query = "INSERT INTO pedidoitem (Produto_idProduto, Pedido_idPedido, Quantidade, Preco, Obeservacao) VALUES ("+novoPedI.getProduto_id()+", "+novoPedI.getPedido_id()+", "+novoPedI.getQuantidade()+", "+novoPedI.getPreco()+", "+novoPedI.getObservacao()+")";
            st.execute(query);
        }else{
            JOptionPane.showMessageDialog(null, "Falta tratar essa classe!");
        }
    }
    
    /**
     * Consulta todos os registros de uma certa tabela, porém para o PedidoItem
     * era necessário realizar a consulta tendo com resultado somentes os itens 
     * do pedido passado e para o alguns casos no bot é necessário passar os 
     * produtos de uma certa categoria.
     * Classe - Enviar a classe para realizar a constula(Pedido.class)
     * Pedido - Passar o numero do pedido para realizar a consulta, caso não 
     * precise passsar 0 
     * Categoria - Passar caso sejá necessário pegar todos os produtos de uma 
     * certa categoria, caso não precise passsar 0 
     * precisa passar 0
     * @param classe
     * @param pedido
     * @param categoria
     * @return
     * @throws SQLException 
     */
    public ArrayList<T> todosRegistros(Class classe, int pedido, int categoria) throws SQLException{
        Statement st = con.createStatement();
        ResultSet rs;
        
        String queryConsulta = "SELECT * FROM";
        
        ArrayList<T> lstRegistros = new ArrayList();
        
        if(classe == Categoria.class){
            lstRegistros = new ArrayList();
            queryConsulta += " categoria";
            rs = st.executeQuery(queryConsulta);
            
            while (rs.next()){
                Categoria newCat = new Categoria();
                newCat.setIdCategoria(rs.getInt("idCategoria"));
                newCat.setDescricao(rs.getString("Descricao"));
                lstRegistros.add((T) newCat);
            }            
            
            return lstRegistros;
        }else if(classe == Produto.class){
            if(categoria > 0){
                queryConsulta += " produto WHERE Categoria_idCategoria ="+categoria;
                rs = st.executeQuery(queryConsulta);

                while (rs.next()){
                    Produto newProduto = new Produto();
                    newProduto.setIdProduto(rs.getInt("idProduto"));
                    newProduto.setDescricao(rs.getString("Descricao"));
                    newProduto.setPreco(rs.getDouble("Preco"));
                    newProduto.setCategoria_idCategoria(rs.getInt("Categoria_idCategoria"));
                    lstRegistros.add((T)newProduto);
                }        
            }else{
                queryConsulta += " produto";
                rs = st.executeQuery(queryConsulta);

                while (rs.next()){
                    Produto newProduto = new Produto();
                    newProduto.setIdProduto(rs.getInt("idProduto"));
                    newProduto.setDescricao(rs.getString("Descricao"));
                    newProduto.setPreco(rs.getDouble("Preco"));
                    newProduto.setCategoria_idCategoria(rs.getInt("Categoria_idCategoria"));
                    lstRegistros.add((T)newProduto);
                }        
            }    
            
            return lstRegistros;
        }else if(classe == Pedido.class){
            queryConsulta += " pedido WHERE entregue = 0 and finalizado = 1";
            rs = st.executeQuery(queryConsulta);
                    
            while (rs.next()){
                Pedido ped = new Pedido();
                ped.setIdPedido(rs.getInt("idPedido"));
                ped.setData(rs.getTimestamp("Data"));
                ped.setFinalizado(rs.getInt("Finalizado"));
                ped.setEntregue(rs.getInt("Entregue"));
                ped.setCliente_idCliente(rs.getInt("Cliente_idCliente"));
                lstRegistros.add((T)ped);
            }
            
            return lstRegistros;
        }else if(classe == PedidoItem.class){
            queryConsulta += " pedidoitem WHERE Pedido_idPedido = "+pedido;
            rs = st.executeQuery(queryConsulta);
            
            while(rs.next()){
                PedidoItem pedItem = new PedidoItem();
                pedItem.setProduto_id(rs.getInt("Produto_idProduto"));
                pedItem.setPedido_id(rs.getInt("Pedido_idPedido"));
                pedItem.setQuantidade(rs.getInt("Quantidade"));
                pedItem.setPreco(rs.getDouble("Preco"));
                pedItem.setObservacao(rs.getString("Observacao"));
                lstRegistros.add((T)pedItem);
            }
            
            return lstRegistros;
        }else if (classe == Cliente.class) {
            lstRegistros = new ArrayList();
            queryConsulta += " cliente";
            rs = st.executeQuery(queryConsulta);

            while (rs.next()) {
                Cliente newCli = new Cliente();
                newCli.setIdCliente(rs.getInt("idCliente"));
                newCli.setNomeCliente(rs.getString("Nome"));
                lstRegistros.add((T) newCli);
            }

            return lstRegistros;
        } else{
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
    
    /**
     * Serve para realizar a alterações em registros especificos, é necessário 
     * ter o objeto alterado passado e com isso sera feito o update no banco.
     * Atualmente existe para a CATEGORIA e PRODUTO
     * @param obj
     * @throws SQLException 
     */
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
     * Serve para realizar a exclusão de um registro especifico, estou com 
     * problemas para realziar a exlcusão do produto em modo generico, então 
     * para apagar um produto no local do OBJ é necessário passar o id do produto
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
     * tentei fazer de modo generico, porém estou com dúvidas no tipo de variavel
     * que tenho que retornar, coloquei "Produto" para quebrar o galho
     * @param codProd
     * @return
     * @throws SQLException 
     */
    public Produto consultaProduto(int codProd) throws SQLException{
        Statement st = con.createStatement();
        
        String queryConsulta = "SELECT * FROM produto WHERE idProduto = "+codProd;
        ResultSet rs = st.executeQuery(queryConsulta);

        if(rs != null && rs.next()){
            Produto prod = new Produto();
            prod.setIdProduto(rs.getInt("idProduto"));
            prod.setDescricao(rs.getString("Descricao"));
            prod.setPreco(rs.getDouble("Preco"));
            prod.setCategoria_idCategoria(rs.getInt("Categoria_idCategoria"));
            return prod;
        }else{
            return null;
        }
    }
    
    /**
     * Será usado no Bot, serve para verificar se o cliente existe atravez de 
     * um select realizado com o where do ID do cliente(ID = id do telegran)
     * @param idTelegran
     * @return
     * @throws SQLException 
     */
    public Cliente consultaCliente(int idTelegran) throws SQLException{
        Statement st = con.createStatement();
        
        String queryConsulta = "SELECT * FROM cliente WHERE idCliente = "+idTelegran;
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
     * consultas. Com opção 1 irá realizara consulta no pedidoItem para retornar
     * o preço total de um pedido especifico.
     * cod1: Serve para validar nos IF's qual consulta seria realizada
     * cod1 = 1(Select que retornar o preço total de um pedido)
     * cod1 = 2(Ira alterar o pedido para entregue = 1)
     * cod2 - Manda a PK para poder fazer a operação necessária
     * @param cod1
     * @param cod2
     */
    public float diversos(int cod1, int cod2) throws SQLException{
        Statement st = con.createStatement();
        
        if(cod1 == 1){
            String query = "SELECT SUM((preco*quantidade)) AS precoTotal FROM pedidoitem WHERE Pedido_idPedido ="+cod2;
            ResultSet rs = st.executeQuery(query);
            
            if(rs != null && rs.next()){
                return rs.getFloat("precoTotal"); 
            }else{
                return 0;
            }
        }else if(cod1 == 2){
            String query = "UPDATE pedido SET entregue = 1 WHERE idPedido = "+cod2;
            st.executeUpdate(query);
            return 0;
        }else{
            JOptionPane.showMessageDialog(null, "Sem tratamento para essa opção!");
            return 0;
        }
    }
}