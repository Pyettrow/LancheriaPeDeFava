/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author William
 */
public class FrmConsultaPedido extends javax.swing.JDialog {

    /**
     * Creates new form FrmConsultaPedido
     */
    public FrmConsultaPedido() {
        initComponents();
        atualizaListaPedido();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBFinalizarEntrega = new javax.swing.JButton();
        jBAtualizarTela = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTPedidos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTItensPedidos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Pedidos");

        jBFinalizarEntrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Verificado.png"))); // NOI18N
        jBFinalizarEntrega.setText("Finalizar Entrega");
        jBFinalizarEntrega.setToolTipText("Finalizar pedido da Tabela por Entregar");
        jBFinalizarEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFinalizarEntregaActionPerformed(evt);
            }
        });

        jBAtualizarTela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Atualizar.png"))); // NOI18N
        jBAtualizarTela.setText("Atualizar a Tela");
        jBAtualizarTela.setToolTipText("Atualizar a Tabela a Entregar");
        jBAtualizarTela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtualizarTelaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos por Entregar"));

        jTPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Pedido", "Cliente", "Data/Hora", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTPedidos.setToolTipText("Tabela a Entregar");
        jTPedidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPedidosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTPedidos);
        if (jTPedidos.getColumnModel().getColumnCount() > 0) {
            jTPedidos.getColumnModel().getColumn(0).setResizable(false);
            jTPedidos.getColumnModel().getColumn(0).setPreferredWidth(3);
            jTPedidos.getColumnModel().getColumn(1).setResizable(false);
            jTPedidos.getColumnModel().getColumn(2).setResizable(false);
            jTPedidos.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTPedidos.getColumnModel().getColumn(3).setResizable(false);
            jTPedidos.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Itens do Pedido Selecionado"));

        jTItensPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Produto", "Descrição", "Observação", "Qtde", "Vl. Unit.", "Vl. Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTItensPedidos.setToolTipText("Itens do pedido selecionado");
        jScrollPane1.setViewportView(jTItensPedidos);
        if (jTItensPedidos.getColumnModel().getColumnCount() > 0) {
            jTItensPedidos.getColumnModel().getColumn(0).setResizable(false);
            jTItensPedidos.getColumnModel().getColumn(1).setResizable(false);
            jTItensPedidos.getColumnModel().getColumn(2).setResizable(false);
            jTItensPedidos.getColumnModel().getColumn(3).setResizable(false);
            jTItensPedidos.getColumnModel().getColumn(4).setResizable(false);
            jTItensPedidos.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBFinalizarEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBAtualizarTela, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jBAtualizarTela)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBFinalizarEntrega)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Tratando o click da linha na tabela de pedidos, para então poder atualziar 
     * a tabela dos itens desse pedido
     * @param evt 
     */
    private void jTPedidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPedidosMouseClicked
        int linhaSelecionada = jTPedidos.getSelectedRow();
        int codPedido = Integer.parseInt(jTPedidos.getValueAt(linhaSelecionada, 0).toString());

        limpaTabelaListaItens();
        atualizaListaItens(codPedido);
    }//GEN-LAST:event_jTPedidosMouseClicked

    /**
     * Tratamento do botão atualizar tabela de pedidos, realiza uma chama a função
     * de limpar a tabela dos itens e de atualizar a tabela pedido
     * @param evt 
     */
    private void jBAtualizarTelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtualizarTelaActionPerformed
        limpaTabelaListaItens();
        atualizaListaPedido();
    }//GEN-LAST:event_jBAtualizarTelaActionPerformed

    /**
     * Tratamento do click do botão finalizar pedido, onde faz um update no banco
     * e logo em seguida atualiza a lista para o pedido alterado não aparecer 
     * na tabela novamente
     * @param evt 
     */
    private void jBFinalizarEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFinalizarEntregaActionPerformed
        limpaTabelaListaItens();

        if(jTPedidos.getSelectedRow() >= 0){

            try {

                DAL.DAO dao = new DAL.DAO();
                dao.diversos(2, (int) jTPedidos.getValueAt(jTPedidos.getSelectedRow(), 0));
                atualizaListaPedido();

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBFinalizarEntregaActionPerformed

    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtualizarTela;
    private javax.swing.JButton jBFinalizarEntrega;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTItensPedidos;
    private javax.swing.JTable jTPedidos;
    // End of variables declaration//GEN-END:variables

    /**
     * Atualizad a tabela de pedidos com base em uma consulta em sql
     */
    public void atualizaListaPedido(){
        limpaTabelaListaItens();
        DefaultTableModel jTable = (DefaultTableModel) jTPedidos.getModel();
        float sumTotalPedido = 0;
        ArrayList<Pedido> lstPedidos = new ArrayList();
        
        for (int i = 0; i < jTable.getRowCount(); i++) {
            jTable.removeRow(0);
        }
        
        /*Limpando a tabela dos pedidos*/
        for (int i = 0; i < jTable.getRowCount(); i++) {
            jTable.removeRow(i);
        }
        
        try {
            DAL.DAO<Pedido> dao = new DAL.DAO();
            
            lstPedidos = dao.todosRegistros(Pedido.class, 0);            
            
            if(!lstPedidos.isEmpty()){
                for (int i = 0; i < lstPedidos.size(); i++) {
                    sumTotalPedido = dao.diversos(1, lstPedidos.get(i).getIdPedido());
                    Cliente cli = dao.consultaCliente(lstPedidos.get(i).getCliente_idCliente());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    String DataConvertida  = dateFormat.format(lstPedidos.get(i).getData());
                    Object[] add = {lstPedidos.get(i).getIdPedido(), cli.getNomeCliente(), DataConvertida, "R$"+sumTotalPedido};
                    jTable.addRow(add);
                }
            }            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Limpando a tabela que lista os itens do pedido
     */
    public void limpaTabelaListaItens(){
        DefaultTableModel jTable = (DefaultTableModel) jTItensPedidos.getModel();
        
        /*Limpando tabela dos itens*/
        if(jTable.getRowCount() > 0){    
            for (int j = 0 ; j <= jTable.getRowCount() ; j ++) {                
                jTable.removeRow(0);
            }
        }
        
        /*Limpando a tabela dos pedidos*/
        for (int i = 0; i < jTable.getRowCount(); i++) {
            jTable.removeRow(i);
        }
    }
    
    /**
     * Atualiza a tablea de lista itens, onde é necessario passar qual linha da 
     * tabela de pedidos foi clicada
     * @param numeroPedido 
     */
    public void atualizaListaItens(int numeroPedido){
        DefaultTableModel jTable = (DefaultTableModel) jTItensPedidos.getModel();
        
        try {
            
            DAL.DAO dao = new DAL.DAO();

            ArrayList<PedidoItem> lstPedidoItem = dao.todosRegistros(PedidoItem.class, numeroPedido);
            
            if(!lstPedidoItem.isEmpty()){
                for (int i = 0; i < lstPedidoItem.size(); i++) {
                    Produto pro = dao.consultaProduto(lstPedidoItem.get(i).getProduto_id());
                    Object[] add = {lstPedidoItem.get(i).getProduto_id(), pro.getDescricao(),lstPedidoItem.get(i).getObservacao(), 
                        lstPedidoItem.get(i).getQuantidade(), lstPedidoItem.get(i).getPreco(), "R$"+lstPedidoItem.get(i).getQuantidade()*lstPedidoItem.get(i).getPreco()};
                    jTable.addRow(add);
                }
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmConsultaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}