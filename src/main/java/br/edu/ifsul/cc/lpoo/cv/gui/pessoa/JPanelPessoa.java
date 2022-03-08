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
        
        listagem = new JPanelPessoaListagem();
        formulario = new JPanelPessoaFormulario();
        
        this.add(listagem, "listagem");
        this.add(formulario, "formulario");
    }
    
    public void showTela(String nomeTela){
         cardLayout.show(this, nomeTela);
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }
    
    
}
