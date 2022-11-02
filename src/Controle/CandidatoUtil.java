/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Apresentacao.frmUrna;
import DAO.Database;
import Modelo.Candidato;
import java.awt.Image;
import java.awt.List;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.io.*;
import java.net.CacheRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author alyss
 */
public class CandidatoUtil 
{
    Database db = new Database();
    ImageUtil image = new ImageUtil();
    
    //Verifica se o usuário está cadastrado 
    public boolean verifica_voto(String voto)
    {
        String query = String.format("SELECT nome, ds_legenda, ds_cargo, blob_foto FROM tb_candidato WHERE ds_numero = '%s'", voto);
        ResultSet result = db.executa_query(query, 1);
        
        try 
        {  
            //Ao entrar aqui significa que ele achou alguma ocorrencia
            while (result.next())
            {
                frmUrna.txt_nome.setText(result.getString("nome"));
                frmUrna.txt_cargo.setText(result.getString("ds_legenda"));
                
                try 
                {
                    ImageIcon foto = new ImageIcon(image.obtem_imagem_blob(result.getBinaryStream("blob_foto")));
                    
                    Image oldImage = foto.getImage(); // transform it 
                    Image newImage = oldImage.getScaledInstance(313, 288,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    ImageIcon imageIcon = new ImageIcon(newImage);
                    frmUrna.pn_image.setIcon(imageIcon);
                } 
                
                catch (IOException ex) 
                {
                    Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                return true;
                
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public Boolean  adiciona_candidato(String nome, String cargo, String numero, String imagem) throws SQLException, IOException
    {
        String query = String.format("SELECT ds_numero FROM tb_candidato WHERE ds_numero = '%s'", numero);
        ResultSet result = db.executa_query(query, 1);
        Boolean existe = false;
        
        
        try {
            while (result.next())
            {
               
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!existe)
        {
            db.insert_imagem(
               String.format("INSERT INTO tb_candidato(ds_numero, nome, ds_cargo, blob_foto)"
                + "VALUES('%s', '%s', '%s', ?)", numero, nome, cargo), 
                imagem    
            );
            
        }
       
        return existe;    
    }
    
    public DefaultListModel devolve_modelo_candidatos()
    { 
        ArrayList<Candidato> lista = new ArrayList<Candidato>();
        DefaultListModel model = new DefaultListModel();
        
        ResultSet result = db.executa_query("SELECT ds_numero, nome, blob_foto, ds_cargo, ds_legenda FROM tb_candidato", 1);
        
        try 
        {
            while(result.next())
            {
                Candidato c = new Candidato(
                    result.getString("ds_numero"),
                    result.getString("nome"),
                    result.getString("blob_foto"),
                    result.getString("ds_legenda") ,
                    result.getString("ds_cargo")   
                );
                
                model.addElement(c);
            }
        } 
        
        catch (SQLException ex) 
        {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return model;
    }
}
