/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author alyss
 */
public class Voto {
    
  
    
    
    private String nome; 
    private String contagem;
    
    public Voto(String nome, String contagem)
    {
        this.nome      = nome; 
        this.contagem  = contagem;
    }
    
    
    /**
     * @return the contagem
     */
    public String getContagem() {
        return contagem;
    }

    /**
     * @param Contagem the ds_numero to set
     */
    public void setContagem(String contagem) {
        this.contagem = contagem;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
