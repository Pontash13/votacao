/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author alyss
 */
public class Candidato 
{
  
    
    private String ds_numero;
    private String nome; 
    private String blob_foto;
    private String ds_legenda; 
    private String ds_cargo;
    
    public Candidato(String ds_numero, String nome, String blob_foto, String ds_legenda, String ds_cargo)
    {
        this.ds_numero  = ds_numero;
        this.nome       = nome; 
        this.blob_foto  = blob_foto;
        this.ds_legenda = ds_legenda; 
        this.ds_cargo   = ds_cargo;
    }
    
    
    /**
     * @return the ds_numero
     */
    public String getDs_numero() {
        return ds_numero;
    }

    /**
     * @param ds_numero the ds_numero to set
     */
    public void setDs_numero(String ds_numero) {
        this.ds_numero = ds_numero;
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

    /**
     * @return the blob_foto
     */
    public String getBlob_foto() {
        return blob_foto;
    }

    /**
     * @param blob_foto the blob_foto to set
     */
    public void setBlob_foto(String blob_foto) {
        this.blob_foto = blob_foto;
    }

    /**
     * @return the ds_legenda
     */
    public String getDs_legenda() {
        return ds_legenda;
    }

    /**
     * @param ds_legenda the ds_legenda to set
     */
    public void setDs_legenda(String ds_legenda) {
        this.ds_legenda = ds_legenda;
    }

    /**
     * @return the ds_cargo
     */
    public String getDs_cargo() {
        return ds_cargo;
    }

    /**
     * @param ds_cargo the ds_cargo to set
     */
    public void setDs_cargo(String ds_cargo) {
        this.ds_cargo = ds_cargo;
    }
    
 
    
}
