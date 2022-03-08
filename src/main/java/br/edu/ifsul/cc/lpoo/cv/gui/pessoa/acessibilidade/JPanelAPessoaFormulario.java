package br.edu.ifsul.cc.lpoo.cv.gui.pessoa.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Igor Rocha
 */
public class JPanelAPessoaFormulario extends JPanel implements ActionListener {

    private JPanelAPessoa pnlAPessoa;
    private Controle controle;

    private BorderLayout borderLayout;
    private JTabbedPane tbpAbas;

    private JPanel pnlDadosCadastrais;
    private JPanel pnlCentroDadosCadastrais;

    private GridBagLayout gridBagLayoutDadosCadastrais;
    private JLabel lblCpf;
    private JTextField txfCpf;

    private JLabel lblSenha;
    private JPasswordField txfSenha;

    private JLabel lblEmail;
    private JTextField txfEmail;

    private JLabel lblCep;
    private JTextField txfCep;

    private Pessoa pessoa;
    private SimpleDateFormat format;

    private JPanel pnlSul;
    private JButton btnGravar;
    private JButton btnCancelar;

    private JPanel pnlDadosClientes;
    private JPanel pnlDadosFuncionario;

    public JPanelAPessoaFormulario(JPanelAPessoa pnlApessoa, Controle controle) {
        this.pnlAPessoa = pnlApessoa;
        this.controle = controle;
        
        initComponents();
    }

    public Pessoa getPessoaByFormulario() {
        if (txfCep.getText().trim().length() > 5 && new String(txfSenha.getPassword()).trim().length() > 3) {
            Pessoa p = new Pessoa();
            p.setCpf(txfCpf.getText().trim());
            p.setCep(txfCep.getText().trim());
            p.setSenha(new String(txfSenha.getPassword()).trim());
            p.setEmail(txfEmail.getText().trim());

            if (pessoa != null) {
                p.setData_cadastro(pessoa.getData_cadastro());
            }
            return p;
        }
        return null;
    }

    public void setPessoaFormulario(Pessoa p) {

        if (p == null) {
            txfCpf.setText("");
            txfEmail.setText("");
            txfSenha.setText("");
            txfCep.setText("");
            txfCpf.setEditable(true);
            pessoa = null;
        } else {

            pessoa = p;
            txfCpf.setEditable(false);
            txfCpf.setText(pessoa.getCpf());
            txfSenha.setText(pessoa.getSenha());
            txfCep.setText(pessoa.getCep());
            txfEmail.setText(pessoa.getEmail());

        }
    }

    private void initComponents() {

        borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        tbpAbas = new JTabbedPane();
        this.add(tbpAbas, BorderLayout.CENTER);

        pnlDadosCadastrais = new JPanel();
        gridBagLayoutDadosCadastrais = new GridBagLayout();
        pnlDadosCadastrais.setLayout(gridBagLayoutDadosCadastrais);

        lblCpf = new JLabel("CPF:");
        GridBagConstraints posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCpf, posicionador);

        txfCpf = new JTextField(11);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCpf, posicionador);

        lblSenha = new JLabel("Senha:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblSenha, posicionador);//o add adiciona o rotulo no painel  

        txfSenha = new JPasswordField(10);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfSenha, posicionador);

        lblCep = new JLabel("Quantidade de Pontos:");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        pnlDadosCadastrais.add(lblCep, posicionador);//o add adiciona o rotulo no painel  

        txfCep = new JTextField(8);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        posicionador.anchor = java.awt.GridBagConstraints.LINE_START;//ancoragem a esquerda.
        pnlDadosCadastrais.add(txfCep, posicionador);//o add adiciona o rotulo no painel  

        tbpAbas.addTab("Dados Cadastrais", pnlDadosCadastrais);

        pnlDadosClientes = new JPanel();
        tbpAbas.addTab("Cliente", pnlDadosClientes);

        pnlDadosFuncionario = new JPanel();
        tbpAbas.addTab("Funcionarios", pnlDadosFuncionario);

        pnlSul = new JPanel();
        pnlSul.setLayout(new FlowLayout());

        btnGravar = new JButton("Gravar");
        btnGravar.addActionListener(this);
        btnGravar.setFocusable(true);    //acessibilidade    
        btnGravar.setToolTipText("btnGravarJogador"); //acessibilidade
        btnGravar.setMnemonic(KeyEvent.VK_G);
        btnGravar.setActionCommand("botao_gravar_formulario_jogador");

        pnlSul.add(btnGravar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(this);
        btnCancelar.setFocusable(true);    //acessibilidade    
        btnCancelar.setToolTipText("btnCancelarPessoa"); //acessibilidade
        btnCancelar.setActionCommand("botao_cancelar_formulario_pessoa");

        pnlSul.add(btnCancelar);

        this.add(pnlSul, BorderLayout.SOUTH);

        format = new SimpleDateFormat("dd/MM/yyyy");

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().equals(btnGravar.getActionCommand())) {

            Pessoa p = getPessoaByFormulario();//recupera os dados do formulÃ¡rio

            if (p != null) {

                try {

                    pnlAPessoa.getControle().getConexaoJDBC().persist(p);

                    JOptionPane.showMessageDialog(this, "Jogador armazenado!", "Salvar", JOptionPane.INFORMATION_MESSAGE);

                    pnlAPessoa.showTela("tela_jogador_listagem");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao salvar Jogador! : " + ex.getMessage(), "Salvar", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            } else {

                JOptionPane.showMessageDialog(this, "Preencha o formulário!", "Edição", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (arg0.getActionCommand().equals(btnCancelar.getActionCommand())) {

            pnlAPessoa.showTela("tela_jogador_listagem");

        }
    }
}
