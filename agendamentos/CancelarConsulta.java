/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package agendamentos;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import java.time.format.DateTimeFormatter;

public class CancelarConsulta extends javax.swing.JFrame {

    /**
     * Creates new form CancelarConsulta
     */
    public CancelarConsulta() {
        initComponents();
    }
    
    private void atualizarListaConsultas() {
    String sus = numeroSUS.getText();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = ConexaoBanco.getConnection();
        
        String sql = "SELECT c.data, e.nome AS especialidade " +
                     "FROM consulta c " +
                     "JOIN paciente p ON c.id_paciente = p.id_paciente " +
                     "JOIN contrato ct ON c.id_contrato = ct.id_contrato " +
                     "JOIN especialidade e ON ct.id_especialidade = e.id_especialidade " +
                     "WHERE c.status = 'Agendada' AND p.numero_sus = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, sus);
        rs = stmt.executeQuery();
        
        ListaConsultas.removeAllItems(); // Limpa o JComboBox antes de adicionar novos itens
        
        while (rs.next()) {
            String dataConsulta = rs.getString("data");
            String especialidade = rs.getString("especialidade");
            ListaConsultas.addItem(dataConsulta + " - " + especialidade);
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

    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ListaConsultas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeroSUS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        botaoCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ListaConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Selecione a consulta que deseja cancelar");

        jLabel2.setText("Digite seu número do SUS");

        numeroSUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroSUSActionPerformed(evt);
            }
        });

        jLabel3.setText("Cancelamento");

        botaoCancelar.setText("Cancelar Consulta");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botaoCancelar)
                .addGap(121, 121, 121))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(numeroSUS, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ListaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jButton1)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeroSUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ListaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numeroSUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroSUSActionPerformed
        atualizarListaConsultas();
    }//GEN-LAST:event_numeroSUSActionPerformed

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        String sus = numeroSUS.getText();
        String consultaSelecionada = (String) ListaConsultas.getSelectedItem();
        
        if(consultaSelecionada == null){
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para cancelar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Connection conn = null;
    PreparedStatement stmt = null;

    try {
        conn = ConexaoBanco.getConnection();
        
        String dataString = consultaSelecionada.split(" - ")[0]; // Extrai a data do item selecionado
        LocalDate dataConsulta = LocalDate.parse(dataString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        
        String sql = "UPDATE consulta " +
             "SET status = 'Cancelada' " +
             "WHERE status = 'Agendada' " +
             "AND id_paciente = (SELECT id_paciente FROM paciente WHERE numero_sus = ?) " +
             "AND data = ?";

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, sus);
        stmt.setDate(2, Date.valueOf(dataConsulta));

        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Consulta cancelada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            atualizarListaConsultas(); // Atualiza a lista de consultas
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cancelar a consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Erro ao cancelar a consulta: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Fecha a conexão e o PreparedStatement
        try {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DashboardPaciente.voltar();
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
            java.util.logging.Logger.getLogger(CancelarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CancelarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CancelarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CancelarConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CancelarConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ListaConsultas;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField numeroSUS;
    // End of variables declaration//GEN-END:variables
}
