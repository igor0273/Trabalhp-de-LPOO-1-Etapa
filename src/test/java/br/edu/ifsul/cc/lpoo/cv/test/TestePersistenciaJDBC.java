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
        
        c.setCpf("123456789");
        persistenciaJDBC.remover(c);
    }
    
    public void testRemoverPessoa() throws Exception{
        Pessoa p = new Pessoa();
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        
        p.setCpf("123456789");
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
                System.out.println("Encontrou a pessoa:"+ p.getCpf());
                p.setSenha("456789");
                persistenciaJDBC.persist(p);
                System.out.println("Alterou a senha da pessoa: "+p.getCpf());
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
                

                persistenciaJDBC.persist(c);
                System.out.println("Inseriu o Cliente: " + c.getNome());
                
            }else{
                System.out.println("Encontrou clienete");
                persistenciaJDBC.persist(c);
                System.out.println("Alterou o cliente: "+ c.getNome());
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
            
            
            System.out.println("Cliente: "+c.getNome());
            System.out.println("Pessoa { ");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Cpf: "+ c.getCpf());
            System.out.println("RG: "+ c.getRg());
            System.out.println("Cep: "+ c.getCep());
            System.out.println("Enderco: "+ c.getEndereco());
            System.out.println("Senha: "+ c.getSenha());
            System.out.println("Data cadastro: "+ sdf.format(c.getData_cadastro()));
            System.out.println("Data nascimento: "+ sdf.format(c.getData_nascimento()));
            System.out.println("Numero telefone: "+ c.getNumero_celular());
            System.out.println(c.getEmail()+ "Email: ");
            System.out.println("}");
         
        }
    }


}
