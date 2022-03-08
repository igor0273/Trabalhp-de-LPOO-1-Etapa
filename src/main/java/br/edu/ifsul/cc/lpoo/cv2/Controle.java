package br.edu.ifsul.cc.lpoo.cv2;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cc.lpoo.cv.gui.pessoa.JPanelPessoa;
import br.edu.ifsul.cc.lpoo.cv.gui.pessoa.acessibilidade.JPanelAPessoa;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author Igor Rocha
 */
public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    
    private JFramePrincipal frame;
    
    private JPanelAutenticacao pnlAutenticacao;
    
    private JMenuBarHome menuBar;
    
    private JPanelHome pnlHome;
    
    private JPanelPessoa pnlPessoa;
    
    private JPanelAPessoa pnlAPessoa;
    
 
    
    public Controle(){
       
    }
    
    public void initComponents(){
        
        frame = new JFramePrincipal();
        
        menuBar = new JMenuBarHome(this);
        
        pnlHome = new JPanelHome(this);
        
        pnlAPessoa = new JPanelAPessoa(this);
        
        pnlPessoa = new JPanelPessoa(this);
        
        frame.addTela(pnlAutenticacao, "tela_autenticacao");
        frame.addTela(pnlHome, "tela_home");
        frame.addTela(pnlPessoa, "tela_pessoa_a");
        frame.addTela(pnlAPessoa,"tela_pessoa_designer");
         
         frame.showTela("tela_autenticacao");
        frame.setVisible(true); // torna visível o jframe
    }
    
    public boolean conectaBD() throws Exception{
  
        conexaoJDBC = new PersistenciaJDBC();
     
        if(getConexaoJDBC() != null){
            return getConexaoJDBC().conexaoAberta();
        }
        return false;
    }
    
    public void fecharBD(){
        System.out.println("Fechando conexao com bd");
        getConexaoJDBC().fecharConexao();
    }
  
    public void autenticar(String cpf, String senha){
        
        try{
            
            Pessoa p = getConexaoJDBC().doLogin(cpf, senha);
            frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
            frame.showTela("tela_home");
            
        }catch(Exception e){

            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showTela(String nomeTela){
       
         if(nomeTela.equals("tela_autenticacao")){
                        
            pnlAutenticacao.cleanForm();            
            frame.showTela(nomeTela);            
            pnlAutenticacao.requestFocus();
            
        }else if(nomeTela.equals("tela_pessoa_a")){
            
            pnlAPessoa.showTela("tela_pessoa_listagem");
            frame.showTela(nomeTela);
            
        }else if(nomeTela.equals("tela_pessoa_designer")){
            
             
            pnlAPessoa.showTela("listagem");
            frame.showTela(nomeTela);
             
            
        }       
    }

    /**
     * @return the conexaoJDBC
     */
    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
    
    
    
}
