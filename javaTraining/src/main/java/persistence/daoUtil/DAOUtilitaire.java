package persistence.daoUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOUtilitaire {

    /** Initialize the prepared request. PreparedStatement better than createQuery
     * because protect from SQL injection
     * @throws SQLException show the SQL error of the request
     * @param connexion connexion to the database
     * @param sql the SQL request
     * @param objets parameters for the SQL request if needed
     * @return PreparedStatement */
    public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);

        }
        return preparedStatement;
    }
}
