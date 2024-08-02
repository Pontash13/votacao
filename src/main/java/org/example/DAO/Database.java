package org.example.DAO;

import org.example.Controle.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    static private Connection conexao;

    public void conectar() {
        try {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }

            String path = getAppDataPath();
            String url = "jdbc:sqlite:" + path + "\\banco.db";

            conexao = DriverManager.getConnection(url);

            // Verificar e criar as tabelas se necessário
            if (!checkTableExists()) {
                criar_tabelas();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public boolean desconectar() {
        try {
            if (!conexao.isClosed()) {
                conexao.close();
            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return false;
        }
        System.out.println("desconectou!!!");
        return true;
    }


    public void criar_tabelas() {
        String queryCandidado = "CREATE TABLE tb_candidato" + "(" + "	ds_numero text PRIMARY KEY NOT NULL unique," + "	nome TEXT," + "	blob_foto BLOB," + "	ds_legenda TEXT," + "	ds_cargo INTEGER" + ");";

        String queryVoto = "CREATE TABLE tb_voto" + "(" + "	id INTEGER PRIMARY KEY," + "	ds_numero TEXT NOT NULL" + ");";

        executa_query(queryCandidado, 2);
        executa_query(queryVoto, 2);
    }


    /**
     * Função que registra os dados no banco.
     * <p>
     * <p>
     * os tipos de int definem o tipo de operação
     * <p>
     * 1 - Voto
     * 2 - Candidato
     */
    public void adiciona_registro(String tabela, String campo, String valor) {
        String query = "INSERT INTO %s (%s) " + "VALUES('%s')";

        executa_query(String.format(query, tabela, campo, valor), 2);
    }

    public void exclui_registro(String tabela, String campo, String valor) {

        String query = "DELETE FROM %s " + "WHERE" + "     '%s' = '%s';";

        executa_query(String.format(query, tabela, campo, valor), 2);
    }


    /**
     * Executa a query
     * <p>
     * tipo:
     * 1 - consulta
     * 2 - atualização
     */
    public ResultSet executa_query(String query, int tipo) {
        ResultSet result = null;

        try {
            if (tipo == 1) {
                result = conexao.createStatement().executeQuery(query);
            } else {
                conexao.createStatement().executeUpdate(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }


        return result;
    }

    public void insert_imagem(String query, String image) throws SQLException, IOException {
        PreparedStatement trans = conexao.prepareStatement(query);

        ImageUtil u = new ImageUtil();

        trans.setBytes(1, u.imagem_para_blob(image));

        trans.execute();
    }

    private String getAppDataPath() {
        String appDataPath = System.getenv("APPDATA");
        if (appDataPath == null) {
            appDataPath = System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Roaming";
        }
        File appDataDir = new File(appDataPath);
        if (!appDataDir.exists()) {
            appDataDir.mkdirs();
        }
        return appDataPath;
    }

    private boolean checkTableExists() {
        boolean exists = false;
        try {
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + "tb_candidato" + "'");
            exists = resultSet.next();
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, e);
        }
        return exists;
    }
}
