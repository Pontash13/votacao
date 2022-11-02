/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import java.io.File;
import javax.sound.sampled.*;
/**
 *
 * @author alyss
 */
public class SoundPlayer
{
    private void tocar(String caminho) //Método AudioAcerto para chamar na classe executavel.
    { 
        try 
        {
            //URL do som que no caso esta no pendrive, mais ainda é uma fase de teste.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminho).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
           
        } 
        catch (Exception ex)
        {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
    
    public void tocar_bip()
    {
        tocar("C:\\Users\\alyss\\Documents\\NetBeansProjects\\Votacao\\sounds\\bip.wav");
    }
    
    public void tocar_confirma()
    {
        tocar("C:\\Users\\alyss\\Documents\\NetBeansProjects\\Votacao\\sounds\\confirma.wav");
    }
}  



