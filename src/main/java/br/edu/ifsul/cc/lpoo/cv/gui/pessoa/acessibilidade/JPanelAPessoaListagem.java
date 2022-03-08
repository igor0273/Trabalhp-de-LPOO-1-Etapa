package br.edu.ifsul.cc.lpoo.cv.gui.pessoa.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Igor Rocha
 */
public class JPanelAPessoaListagem extends JPanel implements ActionListener {

    private JPanelAPessoa pnlAPessoa;
    private Controle controle;

    private BorderLayout borderLayout;
    private JPanel pnlNorte;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltro;

    private JPanel pnlCentro;
    private JScrollPane scpListagem;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;

    private JPanel pnlSul;
    private JButton btnNovo;
    private JButton btnAlterar;
    private JButton btnRemover;

    private SimpleDateFormat format;

 
    JPanelAPessoaListagem(JPanelAPessoa aThis, Controle controle) {
         this.pnlAPessoa = aThis;
        this.controle = controle;
        
        initComponents();
    }

  

  
    public void populaTable() {

        DefaultTableModel model = (DefaultTableModel) tblListagem.getModel();

        model.setRowCount(0);

        try {

            List<Cliente> listCliente = controle.getConexaoJDBC().getListCliente();
            for (Cliente c : listCliente) {

                model.addRow(new Object[]{c, c.getCpf(), c.getSenha(), c.getCep(), c.getEmail()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar Jogadores -:" + ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void initComponents() {

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        pnlNorte = new JPanel();
        pnlNorte.setLayout(new FlowLayout());

        lblFiltro = new JLabel("Filtrar por CPF:");
        pnlNorte.add(lblFiltro);

        txfFiltro = new JTextField(11);
        pnlNorte.add(txfFiltro);

        btnFiltro = new JButton("Filtrar");
        btnFiltro.addActionListener(this);
        btnFiltro.setFocusable(true);    //acessibilidade    
        btnFiltro.setToolTipText("btnFiltrar"); //acessibilidade  
        btnFiltro.setActionCommand("botao_filtro");
        pnlNorte.add(btnFiltro);

        this.add(pnlNorte, BorderLayout.NORTH);//adiciona o painel na posicao norte.

        pnlCentro = new JPanel();
        pnlCentro.setLayout(new BorderLayout());

        scpListagem = new JScrollPane();
        tblListagem = new JTable();

        modeloTabela = new DefaultTableModel(
                new String[]{
                    "CPF", "Senha", "CEP", "E-mail"
                }, 0);

        tblListagem.setModel(modeloTabela);
        scpListagem.setViewportView(tblListagem);

        pnlCentro.add(scpListagem, BorderLayout.CENTER);

        this.add(pnlCentro, BorderLayout.CENTER);
        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnNovo = new JButton("Novo");
        btnNovo.addActionListener(this);
        btnNovo.setFocusable(true);    //acessibilidade    
        btnNovo.setToolTipText("btnNovo"); //acessibilidade
        btnNovo.setMnemonic(KeyEvent.VK_N);
        btnNovo.setActionCommand("botao_novo");

        pnlSul.add(btnNovo);

        btnAlterar = new JButton("Editar");
        btnAlterar.addActionListener(this);
        btnAlterar.setFocusable(true);    //acessibilidade    
        btnAlterar.setToolTipText("btnAlterar"); //acessibilidade
        btnAlterar.setActionCommand("botao_alterar");

        pnlSul.add(btnAlterar);

        btnRemover = new JButton("Remover");
        btnRemover.addActionListener(this);
        btnRemover.setFocusable(true);    //acessibilidade    
        btnRemover.setToolTipText("btnRemvoer"); //acessibilidade
        btnRemover.setActionCommand("botao_remover");

        pnlSul.add(btnRemover);//adiciona o botao na fila organizada pelo flowlayout

        this.add(pnlSul, BorderLayout.SOUTH);//adiciona o painel na posicao norte.

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getActionCommand().equals(btnNovo.getActionCommand())) {
            pnlAPessoa.showTela("tela_pessoa_formulario");

            pnlAPessoa.getFormulario().setPessoaFormulario(null);
        } else if (arg0.getActionCommand().equals(btnAlterar.getActionCommand())) {

            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if (indice > -1) {

                DefaultTableModel model = (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Pessoa p = (Pessoa) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                pnlAPessoa.showTela("tela_jogador_formulario");
                pnlAPessoa.getFormulario().setPessoaFormulario(p);

            } else {

            }
        } else if (arg0.getActionCommand().equals(btnRemover.getActionCommand())) {

            int indice = tblListagem.getSelectedRow();//recupera a linha selecionada
            if (indice > -1) {

                DefaultTableModel model = (DefaultTableModel) tblListagem.getModel(); //recuperacao do modelo da table

                Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

                Pessoa p = (Pessoa) linha.get(0); //model.addRow(new Object[]{u, u.getNome(), ...

                try {
                    pnlAPessoa.getControle().getConexaoJDBC().remover(p);
                    JOptionPane.showMessageDialog(this, "Jogador removido!", "Jogador", JOptionPane.INFORMATION_MESSAGE);
                    populaTable(); //refresh na tabela

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover Jogador -:" + ex.getLocalizedMessage(), "Jogadores", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover!", "Remoção", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
