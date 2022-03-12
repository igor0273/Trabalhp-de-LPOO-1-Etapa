/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.gui.pessoa.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.gui.pessoa.JPanelPessoaFormulario;
import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Igor Rocha
 */
public class JPanelAPessoa extends JPanel {

    private CardLayout cardLayout;
    private Controle controle;

    private JPanelAPessoaFormulario formulario;
    private JPanelAPessoaListagem listagem;

    public JPanelAPessoa(Controle controle) {
        this.controle = controle;
        initComponents();
    }

    private void initComponents() {

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        formulario = new JPanelAPessoaFormulario(this, controle);
        listagem = new JPanelAPessoaListagem(this, controle);

        this.add(getFormulario(), "tela_pessoa_formulario");
        this.add(listagem, "tela_pessoa_listagem");
    }

    public void showTela(String nomeTela) {

        if (nomeTela.equals("tela_pessoa_listagem")) {

            listagem.populaTable();
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
    public JPanelAPessoaFormulario getFormulario() {
        return formulario;
    }

}
