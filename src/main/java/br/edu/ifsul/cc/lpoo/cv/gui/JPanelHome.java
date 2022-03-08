package br.edu.ifsul.cc.lpoo.cv.gui;

import br.edu.ifsul.cc.lpoo.cv2.Controle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Igor Rocha
 */
public class JPanelHome extends JPanel{
    
    private JLabel lblMensagem;
    private JLabel lblImagem;
    private JLabel lblData;
    private BorderLayout layoutGeo;
    
    private Controle controle;
    
    public JPanelHome(Controle controle){
        this.controle = controle;
        
        initComponents();
    }
    
    private void initComponents(){
        
        layoutGeo = new BorderLayout();
        this.setLayout(layoutGeo);
        
        lblMensagem = new JLabel("Tela de Boas Vindas ao Sisteminha");
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblMensagem, BorderLayout.NORTH);
        
        //Colocar Imagem
        
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        lblData = new JLabel(sdf.format(c.getTime()));
        lblData.setBorder(BorderFactory.createLineBorder(Color.green));
        lblData.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblData, BorderLayout.SOUTH);
    }
}
