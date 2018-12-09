package DAOS.impl;

import EXCEPTIONS.RadioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbstractDAOImpl {

    protected static final String BD_ERROR = "Hubo un error en la ejecución a la BD";

    protected void executeQuery(Connection connection, PreparedStatement dml) throws RadioException {
        try {
            dml.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();

            } catch (SQLException e1) {
                throw new RadioException(BD_ERROR, e);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e1) {
                throw new RadioException(BD_ERROR, e1);
            }
        }
    }

    protected void executeQuery(Connection connection, PreparedStatement dml1, PreparedStatement dml2) throws RadioException {
        try {

            dml1.executeUpdate();
            dml2.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new RadioException(BD_ERROR, e);
            } catch (SQLException e1) {
                throw new RadioException(BD_ERROR, e);
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e1) {
                throw new RadioException(BD_ERROR, e1);
            }
        }
    }
}
