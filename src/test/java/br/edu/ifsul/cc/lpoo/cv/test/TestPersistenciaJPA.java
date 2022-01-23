/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJPA;

/**
 *
 * @author Igor Rocha
 */
public class TestPersistenciaJPA {
    
    /**
     * Teste Reponsavel pela criação da tabelas do banco de dados
     */
    public void testConexaoGeracaoTabelas(){
        
        PersistenciaJPA persistenciaJPA = new PersistenciaJPA();
        
        if(persistenciaJPA.conexaoAberta()){
            System.out.println("Abriu conexao com o BD via JPA");
            persistenciaJPA.fecharConexao();
        }else{
            System.out.println("Nso abriu a conexao com o bd via JPA");
        }
    }
}
