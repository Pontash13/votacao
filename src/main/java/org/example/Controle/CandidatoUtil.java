package org.example.Controle;

import org.example.Apresentacao.FrmUrna;
import org.example.DAO.Database;
import org.example.Modelo.Candidato;

import java.awt.Image;
import java.sql.ResultSet;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

public class CandidatoUtil {
    Database db = new Database();
    ImageUtil image = new ImageUtil();

    //Verifica se o usuário está cadastrado
    public boolean verifica_voto(String voto) {
        String query = String.format("SELECT nome, ds_legenda, ds_cargo, blob_foto FROM tb_candidato WHERE ds_numero = '%s'", voto);
        ResultSet result = db.executa_query(query, 1);

        try {
            if (result.next()) {
                FrmUrna.txt_nome.setText(result.getString("nome"));
                FrmUrna.txt_cargo.setText(result.getString("ds_legenda"));
                InputStream blobFoto = result.getBinaryStream("blob_foto");
                if (blobFoto != null) {
                    ImageIcon foto = new ImageIcon(image.obtem_imagem_blob(blobFoto));
                    Image oldImage = foto.getImage(); // transform it
                    Image newImage = oldImage.getScaledInstance(313, 288, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    ImageIcon imageIcon = new ImageIcon(newImage);
                    FrmUrna.pn_image.setIcon(imageIcon);
                }
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Boolean adiciona_candidato(String nome, String cargo, String numero, String imagem) throws SQLException, IOException {
        String query = String.format("SELECT ds_numero FROM tb_candidato WHERE ds_numero = '%s'", numero);
        ResultSet result = db.executa_query(query, 1);
        boolean existe = false;


        try {
            while (result.next()) {

                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!existe) {
            db.insert_imagem(
                    String.format("INSERT INTO tb_candidato(ds_numero, nome, ds_cargo, blob_foto)"
                            + "VALUES('%s', '%s', '%s', ?)", numero, nome, cargo),
                    imagem
            );

        }

        return existe;
    }

    public DefaultListModel devolve_modelo_candidatos() {
        ArrayList<Candidato> lista = new ArrayList<Candidato>();
        DefaultListModel model = new DefaultListModel();

        ResultSet result = db.executa_query("SELECT ds_numero, nome, blob_foto, ds_cargo, ds_legenda FROM tb_candidato", 1);

        try {
            while (result.next()) {
                Candidato c = new Candidato(
                        result.getString("ds_numero"),
                        result.getString("nome"),
                        result.getString("blob_foto"),
                        result.getString("ds_legenda"),
                        result.getString("ds_cargo")
                );

                model.addElement(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
}
