/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agendamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author guilherme
 */
public class EmitirPedidoExame extends javax.swing.JFrame {

    /**
     * Creates new form EmitirPedidoExame
     */
    public EmitirPedidoExame() {
        initComponents();
    }

    private void atualizarListaConsultas() {
    String crmMedico = crm.getText();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = ConexaoBanco.getConnection();
        
        String sql = "SELECT c.id_consulta as id, c.data as data, e.nome AS especialidade " +
                     "FROM consulta c " +
                     "JOIN contrato ct ON c.id_contrato = ct.id_contrato " +
                     "JOIN medico m ON ct.id_medico = m.id_medico " +
                     "JOIN especialidade e ON ct.id_especialidade = e.id_especialidade " +
                     "WHERE c.status = 'Realizada' and m.crm = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, crmMedico);
        rs = stmt.executeQuery();
        
        listaConsultas.removeAllItems(); // Limpa o JComboBox antes de adicionar novos itens
        
       if (!rs.isBeforeFirst()) { // Verifica se o ResultSet está vazio
            JOptionPane.showMessageDialog(this, "Nenhuma consulta realizada encontrada", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            while (rs.next()) {
                String id = rs.getString("id");
                String dataConsulta = rs.getString("data");
                String especialidade = rs.getString("especialidade");
                listaConsultas.addItem(id + ": " + dataConsulta + " - " + especialidade);
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar consultas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Fecha a conexão e o PreparedStatement
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        listaConsultas = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        crm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        botaoEmitir = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Insira seu CRM");

        jLabel2.setText("Selecione uma consulta realizada");

        jLabel3.setText("Emitir Pedido de Exame");

        crm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crmActionPerformed(evt);
            }
        });

        jLabel4.setText("Informe a requisição");

        botaoEmitir.setText("Emitir Pedido de Exame");
        botaoEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEmitirActionPerformed(evt);
            }
        });

        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel4)
                                .addComponent(listaConsultas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(crm)
                                .addComponent(jTextField2)
                                .addComponent(botaoEmitir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jButton1)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(botaoEmitir)
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEmitirActionPerformed
        String crmMedico = crm.getText();
    String consultaSelecionada = (String) listaConsultas.getSelectedItem();
    String requisicao = jTextField2.getText();

    if (consultaSelecionada == null || requisicao.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, selecione uma consulta e insira a requisição.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Extrair o ID da consulta da seleção
    String[] partes = consultaSelecionada.split(": ");
    int idConsulta = Integer.parseInt(partes[0]); // O ID da consulta é a primeira parte

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = ConexaoBanco.getConnection();
        
        // Inserir o pedido de exame e obter o ID gerado
        String sqlPedidoExame = "INSERT INTO pedido_exame (id_consulta, requisicao, data) VALUES (?, ?, now())";
        stmt = conn.prepareStatement(sqlPedidoExame, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, idConsulta);
        stmt.setString(2, requisicao);
        stmt.executeUpdate();

        // Obter o ID do pedido de exame inserido
        rs = stmt.getGeneratedKeys();
        int idPedidoExame = 0;
        if (rs.next()) {
            idPedidoExame = rs.getInt(1); // ID gerado do pedido de exame
        }

        // Inserir um registro na tabela resultado
        String sqlResultado = "INSERT INTO resultado (observacoes, id_pedido_exame) VALUES (?, ?)";
        PreparedStatement stmtResultado = conn.prepareStatement(sqlResultado, PreparedStatement.RETURN_GENERATED_KEYS);
        stmtResultado.setString(1, "Resultado do exame a ser preenchido"); // Substitua pelo valor real
        stmtResultado.setInt(2, idPedidoExame);
        stmtResultado.executeUpdate();

        // Obter o ID do resultado inserido
        ResultSet rsResultado = stmtResultado.getGeneratedKeys();
        int idResultado = 0;
        if (rsResultado.next()) {
            idResultado = rsResultado.getInt(1); // ID gerado do resultado
        }

        // Atualizar o pedido de exame com o id_resultado
        String sqlUpdatePedidoExame = "UPDATE pedido_exame SET id_resultado = ? WHERE id_pedido_exame = ?";
        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdatePedidoExame);
        stmtUpdate.setInt(1, idResultado);
        stmtUpdate.setInt(2, idPedidoExame);
        stmtUpdate.executeUpdate();

        // Fechar os PreparedStatements
        stmtResultado.close();
        stmtUpdate.close();

        JOptionPane.showMessageDialog(this, "Pedido de exame e registro de resultado emitidos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao emitir pedido de exame ou registrar resultado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Fecha a conexão e os PreparedStatements
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_botaoEmitirActionPerformed

    private void crmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crmActionPerformed
        atualizarListaConsultas();
    }//GEN-LAST:event_crmActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DashboardMedico.voltar();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmitirPedidoExame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmitirPedidoExame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmitirPedidoExame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmitirPedidoExame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmitirPedidoExame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEmitir;
    private javax.swing.JTextField crm;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox<String> listaConsultas;
    // End of variables declaration//GEN-END:variables
}
