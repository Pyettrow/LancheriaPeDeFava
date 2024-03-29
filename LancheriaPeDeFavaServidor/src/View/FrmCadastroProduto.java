/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Categoria;
import Model.Produto;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author William
 */
public class FrmCadastroProduto extends javax.swing.JDialog {
    boolean adicionarRegistroHabilitado = false;
    boolean alteraRegistroHabilitado = false;
    
    /**
     * Creates new form FrmCadastroProduto
     */
    public FrmCadastroProduto() {
        initComponents();
        this.setModal(true);
        desativaTelaLimpaCampos();
        preencheComboBox(0);
        jBCancelar.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTFNomeProduto = new javax.swing.JTextField();
        jCBCategoria = new javax.swing.JComboBox();
        jLCodigoProduto = new javax.swing.JLabel();
        jFTFPreco = new javax.swing.JFormattedTextField();
        jBPesquisar = new javax.swing.JButton();
        jBExcluir = new javax.swing.JButton();
        jBAlterar = new javax.swing.JButton();
        jBAdd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jBCancelar = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produto");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Produto"));

        jLabel1.setText("Código");
        jLabel1.setToolTipText("Código do Produto");

        jLabel3.setText("Nome:");
        jLabel3.setToolTipText("Nome do Produto");

        jLabel4.setText("Preço:");
        jLabel4.setToolTipText("Preço do Produto");

        jTFNomeProduto.setToolTipText("Nome do Produto");

        jCBCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Categoria" }));
        jCBCategoria.setToolTipText("Categoria  do Produto");

        jLCodigoProduto.setText("1");
        jLCodigoProduto.setToolTipText("Código do Produto");

        jFTFPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFTFPreco.setToolTipText("Preço do Produto");
        jFTFPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFTFPrecoKeyPressed(evt);
            }
        });

        jBPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Pesquisar.png"))); // NOI18N
        jBPesquisar.setToolTipText("Pesquisar Registro");
        jBPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPesquisarActionPerformed(evt);
            }
        });

        jBExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Remove.png"))); // NOI18N
        jBExcluir.setToolTipText("Apagar registro");
        jBExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBExcluirActionPerformed(evt);
            }
        });

        jBAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Editar.png"))); // NOI18N
        jBAlterar.setToolTipText("Alterar registro");
        jBAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAlterarActionPerformed(evt);
            }
        });

        jBAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/imagens/Add.png"))); // NOI18N
        jBAdd.setToolTipText("Adicionar Registro");
        jBAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddActionPerformed(evt);
            }
        });
        jBAdd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBAddKeyPressed(evt);
            }
        });

        jLabel2.setText("R$");
        jLabel2.setToolTipText("Preço do Produto");

        jBCancelar.setMnemonic('C');
        jBCancelar.setText("Cancelar");
        jBCancelar.setToolTipText("Cancelar Operação");
        jBCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .addGap(107, 107, 107))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jFTFPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(105, 105, 105))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLCodigoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jCBCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFNomeProduto))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jBExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jBPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                                .addGap(19, 19, 19))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLCodigoProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jCBCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jFTFPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jBCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botão Adcionar: Se variavel novoRegistroHabilitado == true irá habilitar 
     * os campos para cadastrar o produto e irá pegar o novo codigo 
     * para o novo cadastro com base no ultimo registro add mais um.
     * @param evt 
     */
    private void jBAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddActionPerformed
        botaoAdd();
    }//GEN-LAST:event_jBAddActionPerformed

    /**
     * Botão Alterar: Se alteraRegistroHabilitado == true irá alterar o 
     * registro no banco de dados com base no que foi escrito
     * senão ira dar aviso
     * @param evt 
     */
    private void jBAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAlterarActionPerformed
        if(alteraRegistroHabilitado == true){
            try {
                
                DAL.DAO<Produto> dao = new DAL.DAO();
                Produto alterarProd = new Produto();
                alterarProd.setIdProduto(Integer.parseInt(jLCodigoProduto.getText()));
                alterarProd.setDescricao(jTFNomeProduto.getText());
                String selecionado = String.valueOf(jCBCategoria.getSelectedItem());
                String[] codigoDaCategoria = selecionado.split("-");
                alterarProd.setCategoria_idCategoria(Integer.parseInt(codigoDaCategoria[0]));
                alterarProd.setPreco(Double.parseDouble(jFTFPreco.getText().replaceAll(",", ".")));
                dao.alterarRegistro(alterarProd);
                 
                desativaTelaLimpaCampos();
                
                alteraRegistroHabilitado = false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            if(!jTFNomeProduto.getText().equals("")){
                jTFNomeProduto.setEditable(true);
                jCBCategoria.setEnabled(true);
                jFTFPreco.setEditable(true);
                jBAdd.setEnabled(false);
                jBExcluir.setEnabled(false);
                jBPesquisar.setEnabled(false);
                jBCancelar.setVisible(true);
                alteraRegistroHabilitado = true;
            }else{
                JOptionPane.showMessageDialog(null, "Escolha um produto para alterar!");
            }
        }
    }//GEN-LAST:event_jBAlterarActionPerformed

    /**
     * Botão Pesquisar: Irá abrir o FrmPesquisa com a listagem dos produtos
     * e após selecionar irá retornar um frm.getIdProduto() com o id do produto 
     * selecionada.
     * @param evt 
     */
    private void jBPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPesquisarActionPerformed
        try {
            FrmPesquisa frm = new FrmPesquisa(2);
            frm.setVisible(true);
            
            DAL.DAO<Produto> dao = new DAL.DAO();
            Produto prod = dao.consultaProduto(frm.getIdProduto());
            
            if(prod != null){
                jLCodigoProduto.setText(String.valueOf(prod.getIdProduto()));
                jTFNomeProduto.setText(prod.getDescricao());
                preencheComboBox(prod.getCategoria_idCategoria());
                jFTFPreco.setText(String.valueOf(prod.getPreco()));
                jBCancelar.setVisible(true);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBPesquisarActionPerformed

    /**
     * Tratamento do botão para excluir o registro com base nos dados pegos na 
     * pesquisa
     * @param evt 
     */
    private void jBExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBExcluirActionPerformed
        if(!jTFNomeProduto.getText().equals("")){
            try {
                
                DAL.DAO<Produto> dao = new DAL.DAO();
                dao.excluirRegistro(2, jLCodigoProduto.getText());
                
                desativaTelaLimpaCampos();
                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                if(String.valueOf(ex).contains("Cannot delete or update a parent row")){
                    JOptionPane.showMessageDialog(null, "Produto possui vinculo.\nNão é possivel realizar sua exclusão!");
                }else{
                    Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Escolha um registro para exlcusão!");
        }
    }//GEN-LAST:event_jBExcluirActionPerformed

    /**
     * Botão de limpar os campos e para qualquer alteraçao/inserção
     * @param evt 
     */
    private void jBCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCancelarActionPerformed
        desativaTelaLimpaCampos();
    }//GEN-LAST:event_jBCancelarActionPerformed

    /**
     * Tratando as teclas "CTRL + I" para habilitar o modo de inclusão de uma 
     * categoria
     * @param evt 
     */
    private void jBAddKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBAddKeyPressed
        if(evt.isControlDown() == true){
            if(evt.getKeyCode() == KeyEvent.VK_I){
                botaoAdd();
            }
        }
    }//GEN-LAST:event_jBAddKeyPressed

    /**
     * Tratando as telcas "CTRL + S" para quando prestes a inserir registro e 
     * chama função para inserção
     * @param evt 
     */
    private void jFTFPrecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFTFPrecoKeyPressed
        if(alteraRegistroHabilitado == false && adicionarRegistroHabilitado == true){
            if(evt.isControlDown() == true){
                if(evt.getKeyCode() == KeyEvent.VK_S){
                    botaoAdd();
                }
            }
        }
    }//GEN-LAST:event_jFTFPrecoKeyPressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAdd;
    private javax.swing.JButton jBAlterar;
    private javax.swing.JButton jBCancelar;
    private javax.swing.JButton jBExcluir;
    private javax.swing.JButton jBPesquisar;
    private javax.swing.JComboBox jCBCategoria;
    private javax.swing.JFormattedTextField jFTFPreco;
    private javax.swing.JLabel jLCodigoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTFNomeProduto;
    // End of variables declaration//GEN-END:variables

    
    /**
     * Botão Adcionar: Se variavel adicionarRegistroHabilitado == false irá habilitar 
     * os campos para cadastrar o produto e irá pegar o novo codigo 
     * para o novo cadastro com base no ultimo registro add mais um. Se igual a 
     * true ira chamar o metodo para inserir o registro no banco
     */
    public void botaoAdd(){
        try {
            DAL.DAO<Produto> novoRegistroProd = new DAL.DAO();
            
            if(adicionarRegistroHabilitado == true){
                if(!jTFNomeProduto.getText().equals("")){
                    if(jCBCategoria.getSelectedIndex() != 0){
                        if(!jFTFPreco.getText().equals("")){
                            
                            Produto newProduto = new Produto();
                            
                            newProduto.setDescricao(jTFNomeProduto.getText());
                            String selecionado = String.valueOf(jCBCategoria.getSelectedItem());
                            String[] codigoDaCategoria = selecionado.split("-");
                            newProduto.setCategoria_idCategoria(Integer.parseInt(codigoDaCategoria[0]));
                            newProduto.setPreco(Double.parseDouble(jFTFPreco.getText().replaceAll(",", ".")));
                            
                            novoRegistroProd.insertRegistro(newProduto);
                            
                            jBCancelar.setVisible(false);
                            adicionarRegistroHabilitado = false;
                            desativaTelaLimpaCampos();
                        }else{
                            JOptionPane.showMessageDialog(null, "Informe um preço para o produto!");
                            jFTFPreco.requestFocus();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Informe uma categoria!");
                        jCBCategoria.requestFocus();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Informe um nome para o produto!");
                    jTFNomeProduto.requestFocus();
                }
            }else{
                desativaTelaLimpaCampos();
                
                String ultimoReg = novoRegistroProd.ultimoRegistro(Produto.class);
                
                jLCodigoProduto.setText(ultimoReg);
                jTFNomeProduto.setEditable(true);
                jTFNomeProduto.requestFocus();
                jCBCategoria.setEnabled(true);
                jFTFPreco.setEditable(true);
                jBAlterar.setEnabled(false);
                jBExcluir.setEnabled(false);
                jBPesquisar.setEnabled(false);
                jBCancelar.setVisible(true);
                adicionarRegistroHabilitado = true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodos para limpar os campos e habiltar os botões para nova interação
     */
    public void desativaTelaLimpaCampos(){
        jLCodigoProduto.setText("");
        jTFNomeProduto.setEditable(false);
        jTFNomeProduto.setText("");
        jCBCategoria.setSelectedIndex(0);
        jCBCategoria.setEnabled(false);
        jFTFPreco.setEditable(false);
        jFTFPreco.setText("");
        jBAdd.setEnabled(true);
        jBAlterar.setEnabled(true);
        jBExcluir.setEnabled(true);
        jBPesquisar.setEnabled(true);
        jBCancelar.setVisible(false);
        adicionarRegistroHabilitado = false;
        alteraRegistroHabilitado = false;
    }
    
    /**
     * Serve para preencher o Combo Box com base em um ArrayList, porém se passar
     * um um valor para o selecionar irá deixar uma opção pré-selecionada
     * 0 - Não selecionar
     * <> 0  - Selecionar(Passar o codigo da Categoria)
     * @param selecionar 
     */
    public void preencheComboBox(int selecionar){
        jCBCategoria.removeAllItems();
        
        int indexParaSelecionar = 0;
        try {
            
            DAL.DAO dao = new DAL.DAO();
            
            ArrayList<Categoria> lstCategoria = dao.todosRegistros(Categoria.class, 0, 0);
            
            for (int i = 0; i < lstCategoria.size(); i++) {
                if(i == 0){
                    jCBCategoria.addItem("Categoria");
                }
                Categoria ob = lstCategoria.get(i);
                jCBCategoria.addItem(ob.getIdCategoria()+"-"+ob.getDescricao());
                indexParaSelecionar = i;
            }
            
            if(selecionar != 0){
                jCBCategoria.setSelectedIndex(indexParaSelecionar);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FrmCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
