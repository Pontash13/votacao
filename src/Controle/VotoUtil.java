/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import DAO.Database;
import Modelo.Voto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author alyss
 */
public class VotoUtil 
{
    private Database db = new Database();
   
    public void computa(String voto)
    {
        db.adiciona_registro("tb_voto", "ds_numero", voto);
    }
    
    
    public void reinicia_votacao()
    {
              
        //Cria uma Lista com todas as tabelas
        ArrayList<String> tabelas = new ArrayList();
        tabelas.add("tb_candidato");
        tabelas.add("tb_voto");
        
        for(int i = 0; i < tabelas.size(); i++)
        {
            db.exclui_registro(tabelas.get(i), "1", "1");
        }
        
    }
    
    public ArrayList<Voto> retorna_numero_votos()
    {
        ArrayList<Voto> lista = new ArrayList<Voto>();
        
        
        String query = "SELECT \n" +
"	CASE \n" +
"		when tv.ds_numero = '0000' then 'branco' \n" +
"		when tc.nome is null then 'nulo'\n" +
"		else tc.nome \n" +
"	END as nome_col, \n" +
"	count(tv.id) contagem \n" +
"FROM \n" +
"	tb_voto tv \n" +
"	left join tb_candidato tc on tv.ds_numero = tc.ds_numero \n" +
"group by nome_col";
        ResultSet result = db.executa_query(query, 1);
        
        try 
        {
            while(result.next())
            {
                Voto c = new Voto(
                    result.getString("nome_col"), 
                    result.getString("contagem")
                    
                );
                
                lista.add(c);
            }
        }  
        
        catch (SQLException ex) 
        {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
