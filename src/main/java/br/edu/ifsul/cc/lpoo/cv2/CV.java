package br.edu.ifsul.cc.lpoo.cv2;

import javax.swing.JOptionPane;

/**
 *
 * @author Igor Rocha
 */
public class CV {
    
    private Controle controle;
    
    public CV(){
        
         try {
                controle = new Controle();//cria a instancia e atribui para o atributo controle.

                //"caminho feliz" : passo 3
                if(controle.conectaBD()){

                    //"caminho feliz" : passo 4
                    controle.initComponents();

                }else{

                        JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                }

        } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "Erro ao tentar conectar no Banco de Dados: "+ex.getLocalizedMessage(), "Banco de Dados", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception{
        new CV();
    }
}
