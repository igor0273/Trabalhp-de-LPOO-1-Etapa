package br.edu.ifsul.cc.lpoo.cv.gui;

import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor Rocha
 */
public class JMenuBarHome extends JMenuBar implements ActionListener{

    private JMenu menuArquivo;
    private JMenuItem menuItemLogout;
    private JMenuItem menuItemSair;
    
    private JMenu menuCadastro;
    private JMenuItem menuItemPessoa;
    private JMenuItem menuItemPessoaDesigner;
    
    private Controle controle;
    
    public JMenuBarHome(Controle controle){
        this.controle = controle;
        initComponents();
        
    }
    
    private void initComponents(){
        
        menuArquivo = new JMenu("Arquivo");
        menuArquivo.setMnemonic(KeyEvent.VK_A);
        menuArquivo.setToolTipText("Arquivo");
        menuArquivo.setFocusable(true);
        
        menuItemSair = new JMenuItem("Sair");
        menuItemSair.setToolTipText("Sair");
        menuItemSair.setFocusable(true);
        
        menuItemLogout = new JMenuItem("Logout");
        menuItemLogout.setToolTipText("Logout");
        menuItemLogout.setFocusable(true);
        
        menuItemLogout.addActionListener(this);
        menuItemLogout.setActionCommand("menu_logout");
        menuArquivo.add(menuItemLogout);

        menuItemSair.addActionListener(this);
        menuItemSair.setActionCommand("menu_sair");
        menuArquivo.add(menuItemSair);

        menuCadastro = new JMenu("Cadastros");
        menuCadastro.setMnemonic(KeyEvent.VK_C);//ativa o ALT + C para acessar esse menu - acessibilidade
        menuCadastro.setToolTipText("Cadastro"); //acessibilidade
        menuCadastro.setFocusable(true); //acessibilidade
        
        menuItemPessoa = new JMenuItem("Pessoa");
        menuItemPessoa.setToolTipText("Pessoa");
        menuItemPessoa.setFocusable(true);
        
        menuItemPessoa.addActionListener(this);
        menuItemPessoa.setActionCommand("menu_pessoa");
        menuItemPessoa.setFocusable(true);
        
        menuItemPessoaDesigner = new JMenuItem("Pessoa (Designer)");
        menuItemPessoaDesigner.setToolTipText("Pessoa (Desingner)");
        menuItemPessoaDesigner.setFocusable(true);
        
        menuItemPessoaDesigner.addActionListener(this);
        menuItemPessoaDesigner.setActionCommand("menu_pessoa_designer");
        menuCadastro.add(menuItemPessoaDesigner);
        
        this.add(menuArquivo);
        this.add(menuCadastro);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
       if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
           int d = JOptionPane.showConfirmDialog(this, "Deseja Realmente sair do sistema", "Sair",JOptionPane.YES_NO_OPTION);
           if(d==0){
               controle.fecharBD();
               System.exit(0);
           }
       }else if(e.getActionCommand().equals(menuItemPessoa.getActionCommand())){
           controle.showTela("tela_pessoa_a");
       }else if(e.getActionCommand().equals(menuItemLogout.getActionCommand())){
            
                        controle.showTela("tela_autenticacao");    
                        
        }else if(e.getActionCommand().equals(menuItemPessoaDesigner.getActionCommand())){
            
                        controle.showTela("tela_pessoa_designer");
        }
    }
    
}
