package org.example.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Candidato {
    private String ds_numero;
    private String nome;
    private String blob_foto;
    private String ds_legenda;
    private String ds_cargo;
}
