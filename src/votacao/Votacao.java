/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package votacao;

import Apresentacao.*;
import DAO.Database;

/**
 *
 * @author alyss
 */
public class Votacao 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        
        //Conectar ao banco quando iniciar 
        Database db = new Database();
        db.conectar();
        
        
        frmPrincipal u  = new frmPrincipal(null, true);
        u.setVisible(true);
    }
    
}
