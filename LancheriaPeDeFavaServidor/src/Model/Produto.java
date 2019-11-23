/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author m101611
 */
public class Produto {
    private int idProduto;
    private String descricao;
    private double preco;
    private int categoria_idCategoria;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCategoria_idCategoria() {
        return categoria_idCategoria;
    }

    public void setCategoria_idCategoria(int categoria_idCategoria) {
        this.categoria_idCategoria = categoria_idCategoria;
    }    
}
