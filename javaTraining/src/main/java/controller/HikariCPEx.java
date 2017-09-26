package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistence.daoUtil.ConnectionManager;

public class HikariCPEx {
    private static final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public static void main(String[] args) {
        Logger lgr = Logger.getLogger(HikariCPEx.class.getName());
        //
        // String configFile = "src/main/resources/db.properties";
        //
        // HikariConfig cfg = new HikariConfig(configFile);
        // HikariDataSource ds = new HikariDataSource(cfg);

        Connection connexion = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            connexion = connectionManager.getConnection();
            pst = connexion.prepareStatement("SELECT * FROM computer");
            rs = pst.executeQuery();

            while (rs.next()) {

                System.out.format("%d %s %s %s %d %n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3), rs.getInt(3));
            }

        } catch (SQLException ex) {

            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (pst != null) {
                    pst.close();
                }

                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ex) {

                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
