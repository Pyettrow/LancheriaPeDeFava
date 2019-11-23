/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author m101611
 */
public class Conexao {
    private final String drive = "org.gjt.mm.mysql.Driver";
    private final String url = "jdbc:mysql://localhost/" + "lancheriapedefava";
    private final String usuario = "root";
    private final String senha = "";
    private Connection conexao;

    public Conexao() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
        conexao = DriverManager.getConnection(url, usuario, senha);
    }
    
    public boolean testaConexao() throws SQLException{
        if(conexao.isValid(5)){
            return true;
        }else{
            return false;
        }
    }

    public Connection getConexao() {
        return conexao;
    }
}
