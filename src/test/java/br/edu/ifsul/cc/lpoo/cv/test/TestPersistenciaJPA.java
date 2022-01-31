/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author Igor Rocha
 */
public class TestPersistenciaJPA {
    
    /**
     * Teste Reponsavel pela criação da tabelas do banco de dados
     */
     @Test
    public void testConexaoGeracaoTabelas(){
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            System.out.println("abriu a conexao com o BD via JPA");
       
            persistencia.fecharConexao();
        
        }else{
            System.out.println("Nao abriu a conexao com o BD via JPA");
    }
        
}
}
