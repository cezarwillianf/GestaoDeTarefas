/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.ui;

import br.newtonpaiva.modelo.Tarefa;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cézar Willian Ferreira - RA 12106991
 */
public class TelaListagemTarefa extends javax.swing.JDialog {

    /**
     * Creates new form TelaListagemTarefa
     */
    public TelaListagemTarefa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlControles = new javax.swing.JPanel();
        txtFiltro = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnNova = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTarefas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem de tarefas");
        setResizable(false);

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/newtonpaiva/ui/icones/filtrar.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnNova.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/newtonpaiva/ui/icones/add.png"))); // NOI18N
        btnNova.setText("Nova");
        btnNova.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/newtonpaiva/ui/icones/remover.png"))); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlesLayout = new javax.swing.GroupLayout(pnlControles);
        pnlControles.setLayout(pnlControlesLayout);
        pnlControlesLayout.setHorizontalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btnNova, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        pnlControlesLayout.setVerticalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFiltrar)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNova)
                        .addComponent(btnRemover)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Núm.", "Nome", "Situação", "Prioridade", "Data Limite", "Percentual"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(tblTarefas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaActionPerformed
        // TODO add your handling code here:
        TelaRegistroTarefa tela = new TelaRegistroTarefa(null, true);
        tela.setVisible(true);
        
        System.out.println("teste");
    }//GEN-LAST:event_btnNovaActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        //Tarefa t = new Tarefa();
        try {
            String filtro = txtFiltro.getText().trim();
            
            List<Tarefa> listaTarefas;
            
            if(filtro.isEmpty())
                listaTarefas = Tarefa.listarDB();
            else
                listaTarefas = Tarefa.listarDB(filtro);
            
            DefaultTableModel modelo = (DefaultTableModel) tblTarefas.getModel();
            modelo.setRowCount(0);
            
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            for(int i = 0; i < listaTarefas.size(); i ++) {
                Tarefa t = listaTarefas.get(i);
                //JOptionPane.showMessageDialog(this, t.toString());
                String dataLimite = dataFormat.format(t.getDataLimite().getTime());
                
                Object[] rowData = {t.getId(), t.getNome(), t.getSituacao(), 
                                    t.getPrioridade(), dataLimite, 
                                    t.getPercentual()};
                
                modelo.addRow(rowData);
            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Não foi possivel recuperar as tarefas.");
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
         // TODO add your handling code here:
        int index = tblTarefas.getSelectedRow();
        
        if(index != -1) {
            int opt = JOptionPane.showConfirmDialog(this, "Confirma a exclusão da tarefa?",
                    "Exclusão de Tarefa", JOptionPane.YES_NO_OPTION);
            
            if(opt == JOptionPane.YES_OPTION) {
                try {
                    
                    DefaultTableModel model = (DefaultTableModel) tblTarefas.getModel();
                    
                    int id = (Integer) model.getValueAt(index, 0);
                    
                    Tarefa.excluirDB(id);                    
                    model.removeRow(index);
                    
                }catch(Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, 
                            "Não foi possivel excluir a informação em disco.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Favor selecionar uma tarefa primeiro.");
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

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
            java.util.logging.Logger.getLogger(TelaListagemTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListagemTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListagemTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListagemTarefa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TelaListagemTarefa dialog = new TelaListagemTarefa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNova;
    private javax.swing.JButton btnRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JTable tblTarefas;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
