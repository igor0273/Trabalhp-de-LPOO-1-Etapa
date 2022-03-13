package br.edu.ifsul.cc.lpoo.cv.gui.autenticacao;

import br.edu.ifsul.cc.lpoo.cv.util.Util;
import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Igor Rocha
 */
public class JPanelAutenticacao extends JPanel implements ActionListener{

    private Controle controle;
    private GridBagLayout gridLayout;
    private GridBagConstraints posicionador;
    
    private JLabel lblNickname;
    private JLabel lblSenha;
    private JTextField txfCPF;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    private Border defaultBorder;  
    

    public JPanelAutenticacao(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    
    private void initComponents(){
        
        gridLayout = new GridBagLayout();
        this.setLayout(gridLayout);
        
        lblNickname = new JLabel("Nickname");
        lblNickname.setToolTipText("lblNickname");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 0;
        this.add(lblNickname, posicionador);
        
        txfCPF = new JTextField(10);
        txfCPF.setFocusable(true);   
        txfCPF.setToolTipText("txfCPF"); 
        Util.considerarEnterComoTab(txfCPF);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;
        posicionador.gridx = 1;
        defaultBorder = txfCPF.getBorder();
        this.add(txfCPF, posicionador);
        
         lblSenha = new JLabel("Senha:");        
        lblSenha.setToolTipText("lblSenha"); //acessibilidade        
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        this.add(lblSenha, posicionador);//o add adiciona o rotulo no painel
        
        psfSenha = new JPasswordField(10);
        psfSenha.setFocusable(true);    //acessibilidade    
        psfSenha.setToolTipText("psfSenha"); //acessibilidade  
        Util.considerarEnterComoTab(psfSenha);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 1;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        this.add(psfSenha, posicionador);
        
        btnLogar = new JButton("Autenticar");
        btnLogar.setFocusable(true);    //acessibilidade    
        btnLogar.setToolTipText("btnLogar"); //acessibilidade  
        Util.registraEnterNoBotao(btnLogar);
        posicionador = new GridBagConstraints();
        posicionador.gridy = 2;//policao da linha (vertical)
        posicionador.gridx = 1;// posição da coluna (horizontal)
        btnLogar.addActionListener(this);//registrar o botão no Listener
        btnLogar.setActionCommand("comando_autenticar");
        this.add(btnLogar, posicionador);
        
        
    }
    
     public void requestFocus(){
        
        txfCPF.requestFocus();
    }
    
    public void cleanForm(){
        
        txfCPF.setText("");
        psfSenha.setText("");        
        
        txfCPF.setBorder(defaultBorder);        
        psfSenha.setBorder(defaultBorder);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       
         if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            
                //validacao do formulario.
                if(txfCPF.getText().trim().length() > 4){
                                        
                    txfCPF.setBorder(new LineBorder(Color.green,1));
                    
                    if(new String(psfSenha.getPassword()).trim().length() > 3 ){
                                                
                        psfSenha.setBorder(new LineBorder(Color.green,1));
                        
                        controle.autenticar(txfCPF.getText().trim(), new String(psfSenha.getPassword()).trim());
                        
                    }else{
                        
                        JOptionPane.showMessageDialog(this, "Informe Senha com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);
                        psfSenha.setBorder(new LineBorder(Color.red,1));
                        psfSenha.requestFocus();                        
                        
                    }
                    
                }else{
                
                    JOptionPane.showMessageDialog(this, "Informe Nickname com 4 ou mais dígitos", "Autenticação", JOptionPane.ERROR_MESSAGE);                    
                    txfCPF.setBorder(new LineBorder(Color.red,1));
                    txfCPF.requestFocus();
                }
                                      
            
        } 
    }
    
}
