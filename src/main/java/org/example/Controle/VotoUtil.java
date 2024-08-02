package org.example.Controle;

import org.example.DAO.Database;
import org.example.Modelo.Voto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VotoUtil {
    private final Database db = new Database();

    public void computa(String voto) {
        db.adiciona_registro("tb_voto", "ds_numero", voto);
    }


    public void reinicia_votacao() {

        //Cria uma Lista com todas as tabelas
        ArrayList<String> tabelas = new ArrayList<>();
        tabelas.add("tb_candidato");
        tabelas.add("tb_voto");

        for (String tabela : tabelas) {
            db.exclui_registro(tabela, "1", "1");
        }

    }

    public ArrayList<Voto> retorna_numero_votos() {
        ArrayList<Voto> lista = new ArrayList<>();

        String query = """
                SELECT\s
                	CASE\s
                		when tv.ds_numero = '0000' then 'branco'\s
                		when tc.nome is null then 'nulo'
                		else tc.nome\s
                	END as nome_col,\s
                	count(tv.id) contagem\s
                FROM\s
                	tb_voto tv\s
                	left join tb_candidato tc on tv.ds_numero = tc.ds_numero\s
                group by nome_col""";
        ResultSet result = db.executa_query(query, 1);

        try {
            while (result.next()) {
                Voto c = new Voto(
                        result.getString("nome_col"),
                        result.getString("contagem")
                );
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
