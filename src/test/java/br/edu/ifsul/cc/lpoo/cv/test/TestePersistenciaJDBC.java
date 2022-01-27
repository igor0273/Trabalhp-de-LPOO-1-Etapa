/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;

/**
 *
 * @author Igor Rocha
 */
public class TestePersistenciaJDBC {
    
    public void testConexao()throws Exception{
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        if(persistenciaJDBC.conexaoAberta()){
            System.out.println("Abriu conexao com BD via JDBC");
            persistenciaJDBC.fecharConexao();
        }else{
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    } 
    
    public void testRemoverCliente() throws Exception{
        Cliente c = new Cliente();
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        c.setId(1);
        persistenciaJDBC.remover(c);
    }
    
    public void testRemoverPessoa() throws Exception{
        Pessoa p = new Pessoa();
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        p.setId(1);
        persistenciaJDBC.remover(p);
    }


}
