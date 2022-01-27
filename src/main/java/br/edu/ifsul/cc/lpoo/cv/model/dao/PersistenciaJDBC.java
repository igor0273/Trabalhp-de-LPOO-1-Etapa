/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Igor Rocha
 */
public class PersistenciaJDBC implements InterfacePersistencia{

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/trabalhoLPOO_CV";
    private Connection con = null;
    
    public PersistenciaJDBC() throws Exception{
    
        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com :"+URL+" ...");
        
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        
    }
    
    @Override
    public Boolean conexaoAberta() {
       try{
           if(con != null){
               return !con.isClosed();
           }
       } catch(SQLException ex){
           ex.printStackTrace();
       }
       return false;
    }

    @Override
    public void fecharConexao() {
        try{
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if(c == Cliente.class){
            //tb_cliente
            PreparedStatement ps = this.con.prepareStatement("select * from tb_cliente where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                
                Cliente cliente = new Cliente();
                
                //Elementos da consulta
                
                ps.close();
                
                return cliente;
                
            }
        }else if(c == Pessoa.class){
            PreparedStatement ps = this.con.prepareStatement("select * from tb_cliente where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Pessoa pessoa = new Pessoa();
                
                //Elementos de consulta
                
                ps.close();
                
                return pessoa;
            }
        }
        
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {
        
        if(o instanceof Cliente){
            Pessoa p = (Pessoa) o;
            
            if(p.getId() == null){
                
                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (id,*) values (nextval('seq_pessoa_id'),*) returning id");
                
                //Elementos para inserir na tb_pessoa
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    p.setId(rs.getInt(1));
                }
            }else{
                 PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                                                                                + "cep = ?, "
                                                                                + "complemento = ? "
                                                                                + "where id = ?");
               //Elementos de Tb_pessoa
                
                ps.execute();//executa o comando.
            }
        }else if(o instanceof Cliente){
            Cliente c = (Cliente) o;
            
            if(c.getId() == null){
                 PreparedStatement ps = this.con.prepareStatement("insert into tb_cliente (id,*) values (nextval('seq_pessoa_id'),*) returning id");
                
                //Elementos para inserir na tb_pessoa
                
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    c.setId(rs.getInt(1));
                }
            }else{
                  PreparedStatement ps = this.con.prepareStatement("update tb_cliente set "
                                                                                + "cep = ?, "
                                                                                + "complemento = ? "
                                                                                + "where id = ?");
               //Elementos de Tb_pessoa
                
                ps.execute();//executa o comando.
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if(o instanceof Cliente){
            
            Cliente c = (Cliente) o;
            
            PreparedStatement ps = this.con.prepareStatement("delete from tb_cliente where id = ?");
            ps.setInt(1,c.getId());
            ps.execute();
        }else if(o instanceof Pessoa){
            Pessoa p = (Pessoa) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_pessoa where id = ?");
            ps.setInt(1, p.getId());
            ps.execute();
        }
    }
    
}
