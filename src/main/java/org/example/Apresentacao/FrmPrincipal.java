package org.example.Apresentacao;

import org.example.Controle.CandidatoUtil;
import org.example.Controle.Grafico;
import org.example.Controle.VotoUtil;
import org.example.DAO.Database;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmPrincipal extends javax.swing.JDialog {

    private static Database db; // Instância do banco de dados

    public FrmPrincipal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    CandidatoUtil c = new CandidatoUtil();
    VotoUtil v = new VotoUtil();

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        lbl_apresentacao = new javax.swing.JLabel();
        btn_irUrna = new javax.swing.JButton();
        btn_verResultados = new javax.swing.JButton();
        btn_configurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Programa de votação");

        lbl_apresentacao.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 24)); // NOI18N
        lbl_apresentacao.setText("Bem vindo ao Sistema de votação");

        btn_irUrna.setText("Ir para Urna");
        btn_irUrna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_irUrnaActionPerformed(evt);
            }
        });

        btn_verResultados.setText("Ver resultados");
        btn_verResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verResultadosActionPerformed(evt);
            }
        });

        btn_configurar.setText("Configurar");
        btn_configurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbl_apresentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_irUrna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_configurar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_verResultados)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lbl_apresentacao)
                .addGap(52, 52, 52)
                .addComponent(btn_irUrna)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_configurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_verResultados)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);

        // Adiciona um WindowListener para limpar o banco de dados ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (db != null) {
                    db.desconectar();
                }
                System.exit(0);
            }
        });
    }

    private void btn_irUrnaActionPerformed(java.awt.event.ActionEvent evt) {
        if(!c.devolve_modelo_candidatos().isEmpty()) {
            FrmUrna u = new FrmUrna(null, rootPaneCheckingEnabled);
            u.setLocationRelativeTo(this);
            u.setVisible(rootPaneCheckingEnabled);                
        }
        else {
            JOptionPane.showMessageDialog(this, "Não existem candidatos disputando a eleição");
        }
            
    }

    private void btn_configurarActionPerformed(java.awt.event.ActionEvent evt) {
        FrmConfiguracao u = new FrmConfiguracao(null, rootPaneCheckingEnabled);
        u.setLocationRelativeTo(this);
        u.setVisible(rootPaneCheckingEnabled);
    }

    private void btn_verResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verResultadosActionPerformed
        if(!v.retorna_numero_votos().isEmpty()){
            Grafico demo = new Grafico();  
            demo.setSize(560, 367);    
            RefineryUtilities.centerFrameOnScreen(demo);
            this.setVisible(false);
            demo.setVisible(true );
            demo.setAlwaysOnTop(true);
            demo.setFocusableWindowState(true);
            demo.setFocusable(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "Não existem votos válidos ainda!");
        }




    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmPrincipal dialog = new FrmPrincipal(new javax.swing.JFrame(), true);
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

    public static void setDatabase(Database database) {
        db = database;
    }

    private javax.swing.JButton btn_configurar;
    private javax.swing.JButton btn_irUrna;
    private javax.swing.JButton btn_verResultados;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_apresentacao;
}
