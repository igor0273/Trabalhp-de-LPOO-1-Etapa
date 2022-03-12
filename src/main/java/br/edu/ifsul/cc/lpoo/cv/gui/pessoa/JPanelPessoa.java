package br.edu.ifsul.cc.lpoo.cv.gui.pessoa;

import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Igor Rocha
 */
public class JPanelPessoa extends JPanel{
    
    private CardLayout cardLayout;
    private Controle controle;
    
    private JPanelPessoaListagem listagem;
    private JPanelPessoaFormulario formulario;
    
    public JPanelPessoa(Controle controle){
        
        this.controle = controle;
        initComponents();
    }
    
    public void initComponents(){
        
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        listagem = new JPanelPessoaListagem(this, controle);
        formulario = new JPanelPessoaFormulario(this, controle);
        
        this.add(listagem, "tela_pessoa_listagem");
        this.add(getFormulario(), "tela_pessoa_formulario");
    }
    
    public void showTela(String nomeTela){
        
        if(nomeTela.equals("tela_pessoa_listagem")){
            listagem.populaTable();
        }else if(nomeTela.equals("tela_pessoa_formulario")){
            getFormulario().populaComboEndereco();
        }
         cardLayout.show(this, nomeTela);
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }

    /**
     * @return the formulario
     */
    public JPanelPessoaFormulario getFormulario() {
        return formulario;
    }
    
    
    
    
}
