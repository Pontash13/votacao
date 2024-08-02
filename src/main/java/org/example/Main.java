package org.example;

import org.example.Apresentacao.FrmPrincipal;
import org.example.DAO.Database;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.conectar();

        FrmPrincipal u = new FrmPrincipal(null, true);
        FrmPrincipal.setDatabase(db);
        u.setVisible(true);
    }
}