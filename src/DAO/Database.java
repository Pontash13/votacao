/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Controle.ImageUtil;
import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alyss
 */

public class Database 
{
    static private Connection conexao;
   
    /**
     * 
     * Conecta, se não existir ele cria
     * 
     * @return boolean
     */
    public boolean conectar()
    {
        try
        {
            try {
                //conectando
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String path = "";
            try {
                path = new File(".").getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            String url = "jdbc:sqlite:"+path+"\\banco.db";
            
            this.conexao = DriverManager.getConnection(url);
                    
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
            return false;
        }
        
        return true; 
    }
    
    public boolean desconectar()
    {
        try 
        {
            if (this.conexao.isClosed() == false) 
            {
                this.conexao.close();
            }

        }
        catch (SQLException e) 
        {

            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou!!!");
        return true;
    }
        
    
    public void criar_tabelas()
    {
        String query_candidado = ""
                + "CREATE TABLE tb_candidato"
                + "("
                + "	ds_numero text PRIMARY KEY NOT NULL unique,"
                + "	nome TEXT,"
                + "	blob_foto BLOB,"
                + "	ds_legenda TEXT,"
                + "	ds_cargo INTEGER"
                + ");";
       
        String query_voto      = ""
                + "CREATE TABLE tb_voto"
                + "("
                + "	id INTEGER PRIMARY KEY,"
                + "	ds_numero TEXT NOT NULL"
                + ");";
        
        executa_query(query_candidado, 2);
        executa_query(query_voto, 2); 
    }
    
    
    /**
     * Função que registra os dados no banco.
     * 
     *
     * os tipos de int definem o tipo de operação
     * 
     * 1 - Voto
     * 2 - Candidato
     */
    
    public void adiciona_registro(String tabela, String campo, String valor)
    {
        String query = ""
                + "INSERT INTO %s (%s) "
                + "VALUES('%s')";
        
        executa_query(String.format(query, tabela, campo, valor), 2);
    }
    
    public void exclui_registro(String tabela, String campo, String valor)
    {
        
        String query = ""
                + "DELETE FROM %s "
                + "WHERE"
                + "     '%s' = '%s';"
        ;
        
        executa_query(String.format(query, tabela, campo, valor), 2);
    }      
    
    
    /**
     * 
     * Executa a query
     * 
     * tipo:
     * 1 - consulta
     * 2 - atualização
     */
    public ResultSet executa_query(String query, int tipo)
    {
        ResultSet result = null;
        
        try 
        {
            if(tipo == 1)
            {
                result = this.conexao.createStatement().executeQuery(query);       
            }
            else 
            {
                this.conexao.createStatement().executeUpdate(query);
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
    public void insert_imagem(String query, String image) throws SQLException, IOException
    {
        PreparedStatement trans = conexao.prepareStatement(query);
            
        ImageUtil u = new ImageUtil();
        
        trans.setBytes(1, u.imagem_para_blob(image));

        trans.execute();
    }
}
