/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;

/**
 *
 * @author Igor Rocha
 */
public class TestePersistenciaJDBC {
    
    @Test
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
    
    public void testPersistPessoa() throws Exception{
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        if(persistenciaJDBC.conexaoAberta()){
            Pessoa p = (Pessoa) persistenciaJDBC.find(Pessoa.class, new Integer(1));
            
            if(p == null){
                System.out.println("Nao Encontrou a pessoa");
                
                p = new Pessoa();
                p.setCep("99050310");
                p.setComplemento("proximo a parede");
                p.setCpf("12345678912");
                p.setEmail("teste@teste");
                p.setEndereco("hfuheufeuf");
                p.setNome("igor");
                p.setNumero_celular("96487673");
                p.setRg("1234568795");
                p.setSenha("123456");
                
                persistenciaJDBC.persist(p);
            }else{
                System.out.println("Encontrou a pessoa:"+ p.getId());
                p.setSenha("456789");
                persistenciaJDBC.persist(p);
                System.out.println("Alterou a senha da pessoa: "+p.getId());
            }
            persistenciaJDBC.fecharConexao();
        }
    }
    
    public void testPersistCompra() throws Exception{
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        if(persistenciaJDBC.conexaoAberta()){
            Cliente c = (Cliente) persistenciaJDBC.find(Cliente.class, new Integer(1));
            
            if(c == null){
             
                 System.out.println("Nao encontrou a compra");

                c = new Cliente();
                c.setPessoa(1);
                

                persistenciaJDBC.persist(c);
                System.out.println("Inseriu o Cliente: " + c.getId());
                
            }else{
                System.out.println("Encontrou clienete");
                c.setPessoa(2);
                persistenciaJDBC.persist(c);
                System.out.println("Alterou o cliente: "+ c.getId());
            }
        }
        persistenciaJDBC.fecharConexao();
    }
    
    public void testFindPessoa ()throws Exception{
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        Pessoa p = (Pessoa) persistenciaJDBC.find(Pessoa.class, new Integer(1));
        
        if(p == null){
            System.out.println("nao encontrou a pessoa buscada");
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Pessoa: "+ p.getId());
            System.out.println("Nome: "+ p.getNome());
            System.out.println("Cpf: "+ p.getCpf());
            System.out.println("RG: "+ p.getRg());
            System.out.println("Cep: "+ p.getCep());
            System.out.println("Enderco: "+ p.getEndereco());
            System.out.println("Senha: "+ p.getSenha());
            System.out.println("Data cadastro: "+ sdf.format(p.getData_cadastro()));
            System.out.println("Data nascimento: "+ sdf.format(p.getData_nascimento()));
            System.out.println("Numero telefone: "+ p.getNumero_celular());
            System.out.println("Email: "+ p.getEmail());
        }
        
    }
    
    public void testFindCliente() throws Exception{
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        Cliente c = (Cliente) persistenciaJDBC.find(Cliente.class, new Integer(1));
        
        if(c == null){
            System.out.println("Nao encontrou o cliente");
        }else{
            Pessoa p = new Pessoa();
            if(c.getPessoa() != null){
                p = (Pessoa) persistenciaJDBC.find(Pessoa.class, new Integer(c.getPessoa()));
            }
            
            System.out.println("Cliente: "+c.getId());
            System.out.println("Pessoa { ");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Pessoa: "+ p.getId());
            System.out.println("Nome: "+ p.getNome());
            System.out.println("Cpf: "+ p.getCpf());
            System.out.println("RG: "+ p.getRg());
            System.out.println("Cep: "+ p.getCep());
            System.out.println("Enderco: "+ p.getEndereco());
            System.out.println("Senha: "+ p.getSenha());
            System.out.println("Data cadastro: "+ sdf.format(p.getData_cadastro()));
            System.out.println("Data nascimento: "+ sdf.format(p.getData_nascimento()));
            System.out.println("Numero telefone: "+ p.getNumero_celular());
            System.out.println("Email: "+ p.getEmail());
            System.out.println("}");
            System.out.println("Data Compra: "+sdf.format(c.getData_ultima_visita()));
        }
    }


}
