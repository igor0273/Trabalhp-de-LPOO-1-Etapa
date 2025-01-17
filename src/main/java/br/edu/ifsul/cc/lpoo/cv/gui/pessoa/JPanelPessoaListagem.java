/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.gui.pessoa;

import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor Rocha
 */
public class JPanelPessoaListagem extends javax.swing.JPanel {

    private Controle controle;
    private JPanelPessoa pnlPessoa;
    private SimpleDateFormat format;
    
    /**
     * Creates new form JPanelPessoaListagem
     */
    public JPanelPessoaListagem() {
        initComponents();
    }
    
    public JPanelPessoaListagem(JPanelPessoa pnlPessoa, Controle controle){
        this.pnlPessoa = pnlPessoa;
        this.controle = controle;
        initComponents();
        format = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void populaTable(){
        DefaultTableModel model = (DefaultTableModel) tblListagem.getModel();
        
        model.setRowCount(0);
        
        try{
            
            List<Pessoa> listPessoa = controle.getConexaoJDBC().getListPessoa();
            
            for(Pessoa p : listPessoa){
                model.addRow(new Object[]{
                    p.getCpf(),
                    format.format(p.getData_cadastro().getTime()),
                    p.getNome(),
                    p.getCep()
                });
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Erro ao listar Pessoa:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
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

        pnlFiltro = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        txtfFiltro = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        pnlSul = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        scpRolagem = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFiltro.setText("Filtrar por CPF:");
        pnlFiltro.add(lblFiltro);

        txtfFiltro.setColumns(10);
        pnlFiltro.add(txtfFiltro);

        btnFiltrar.setText("Filtrar");
        pnlFiltro.add(btnFiltrar);

        add(pnlFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, -1));

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        pnlSul.add(btnNovo);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlSul.add(btnEditar);

        jButton1.setText("Cancelar");
        pnlSul.add(jButton1);

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        pnlSul.add(btnRemover);

        add(pnlSul, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 260, 410, 40));

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Data de Cadastro", "Nome", "RG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scpRolagem.setViewportView(tblListagem);

        add(scpRolagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 410, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed

        pnlPessoa.showTela("tela_pessoa_formulario");            
            
        pnlPessoa.getFormulario().setPessoaFormulario(null); //limpando o formulário.     


    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

         int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Pessoa p = (Pessoa) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                pnlPessoa.showTela("tela_pessoa_formulario");
                pnlPessoa.getFormulario().setPessoaFormulario(p); 

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
       
          int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if(indice > -1){

                DefaultTableModel model =  (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Pessoa p = (Pessoa) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlPessoa.getControle().getConexaoJDBC().remover(p);
                    JOptionPane.showMessageDialog(this, "Jogador removido!", "Pessoa", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Pessoa -:"+ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }                        

            }else{
                  JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlSul;
    private javax.swing.JScrollPane scpRolagem;
    private javax.swing.JTable tblListagem;
    private javax.swing.JTextField txtfFiltro;
    // End of variables declaration//GEN-END:variables
}
