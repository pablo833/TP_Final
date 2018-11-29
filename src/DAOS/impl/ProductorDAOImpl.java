package DAOS.impl;

import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Productor;
import EXCEPTIONS.RadioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductorDAOImpl extends AbstractImpl implements DAO<Productor> {

    private final String PRODUCTOR_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de productores";

    @Override
    public void insert(Productor productor) throws RadioException {
        String queryProductor = "INSERT INTO productores (dniPersona) VALUES (?)";
        String queryPersona = "INSERT INTO personas (dni, nombre, apellido) VALUES (?, ?, ?)";

        Connection connection = DBManager.connect();

        PreparedStatement dmlProductor;
        PreparedStatement dmlPersona;
        try {
            dmlProductor = connection.prepareStatement(queryProductor);
            dmlProductor.setInt(1, productor.getDni());


            dmlPersona = connection.prepareStatement(queryPersona);
            dmlPersona.setInt(1, productor.getDni());
            dmlPersona.setString(2, productor.getNombre());
            dmlPersona.setString(3, productor.getApellido());

            executeQuery(connection, dmlPersona, dmlProductor);
        } catch (SQLException e) {
            throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public void update(Productor productor) throws RadioException {
        String queryPersona = "UPDATE personas set dni = ?, nombre = ?, apellido = ? WHERE dni = ?";

        Connection connection = DBManager.connect();

        PreparedStatement dmlPersona;
        try {

            dmlPersona = connection.prepareStatement(queryPersona);
            dmlPersona.setInt(1, productor.getDni());
            dmlPersona.setString(2, productor.getNombre());
            dmlPersona.setString(3, productor.getApellido());
            dmlPersona.setInt(4, productor.getDni());

            executeQuery(connection, dmlPersona);
        } catch (SQLException e) {
            throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public Productor get(int codigo) throws RadioException {
        String query = "SELECT * " +
                "FROM personas " +
                "INNER JOIN productores ON " +
                "personas.dni = productores.dniPersona " +
                "where productores.id = ? ";


        return executeQuery(query, codigo);
    }

    @Override
    public void delete(int codigo) throws RadioException {
        String query = "DELETE FROM productores " +
                "WHERE id = ?";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, codigo);
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public List<Productor> getAll() throws RadioException {
        List<Productor> productores = new ArrayList<Productor>();

        String sql = "SELECT * " +
                "FROM personas " +
                "INNER JOIN productores ON " +
                "personas.dni = productores.dniPersona ";

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Productor productor = new Productor(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"));
                productores.add(productor);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                // no hago nada
            }
            throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                c.close();

            } catch (SQLException e1) {
                throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e1);
            }
        }
        return productores;
    }

    @Override
    public Productor getByInternalID(Productor productor) throws RadioException {
        String query = "SELECT * " +
                "FROM personas " +
                "INNER JOIN productores ON " +
                "personas.dni = productores.dniPersona " +
                "where personas.dni = ? ";

        return executeQuery(query, productor.getDni());
    }

    private Productor executeQuery(String query, int param) throws RadioException {
        Productor productor = null;

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, param);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                productor = new Productor(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("dni"));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(PRODUCTOR_NOT_FOUND_ERRROR, e2);
            }
        }

        return productor;
    }

}
