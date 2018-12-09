package DAOS.impl;

import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Conductor;
import EXCEPTIONS.RadioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAOImpl extends AbstractDAOImpl implements DAO<Conductor> {
    private final String CONDUCTORES_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de CONDUCTORES";

    @Override
    public void insert(Conductor conductor) throws RadioException {
        String queryConductor = "INSERT INTO conductores (dniPersona, sueldo) VALUES (?, ?)";
        String queryPersona = "INSERT INTO personas (dni, nombre, apellido) VALUES (?, ?, ?)";

        Connection connection = DBManager.connect();

        PreparedStatement dmlConductor;
        PreparedStatement dmlPersona;
        try {
            dmlConductor = connection.prepareStatement(queryConductor);
            dmlConductor.setInt(1, conductor.getDni());
            dmlConductor.setDouble(2, conductor.getSueldo());

            dmlPersona = connection.prepareStatement(queryPersona);
            dmlPersona.setInt(1, conductor.getDni());
            dmlPersona.setString(2, conductor.getNombre());
            dmlPersona.setString(3, conductor.getApellido());

            executeQuery(connection, dmlConductor, dmlPersona);
        } catch (SQLException e) {
            throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public void update(Conductor conductor) throws RadioException {
        String queryConductor = "UPDATE conductores set dniPersona = ?, sueldo = ?  WHERE id = ?";
        String queryPersona = "UPDATE personas set dni = ?, nombre = ?, apellido = ? WHERE dni = ?";

        Connection connection = DBManager.connect();

        PreparedStatement dmlConductor;
        PreparedStatement dmlPersona;
        try {
            dmlConductor = connection.prepareStatement(queryConductor);
            dmlConductor.setInt(1, conductor.getDni());
            dmlConductor.setDouble(2, conductor.getSueldo());
            dmlConductor.setInt(3, conductor.getCodigo());

            dmlPersona = connection.prepareStatement(queryPersona);
            dmlPersona.setInt(1, conductor.getDni());
            dmlPersona.setString(2, conductor.getNombre());
            dmlPersona.setString(3, conductor.getApellido());
            dmlPersona.setInt(4, conductor.getDni());

            executeQuery(connection, dmlConductor, dmlPersona);
        } catch (SQLException e) {
            throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e);
        }
    }

    @Override
    public Conductor get(int codigo) throws RadioException {

        String query = "SELECT * " +
                "FROM personas " +
                "INNER JOIN conductores ON " +
                "personas.dni = conductores.dniPersona " +
                "where conductores.id = ? ";

        return executeQuery(query, codigo);

    }

    @Override
    public void delete(int codigo) {

    }

    @Override
    public List<Conductor> getAll() throws RadioException {
        List<Conductor> conductores = new ArrayList<Conductor>();

        String sql = "SELECT * " +
                "FROM personas " +
                "INNER JOIN conductores ON " +
                "personas.dni = conductores.dniPersona ";

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Conductor conductor = new Conductor(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("id"), rs.getDouble("sueldo"));
                conductores.add(conductor);
            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                // no hago nada
            }
            throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                c.close();

            } catch (SQLException e1) {
                throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e1);
            }
        }
        return conductores;
    }

    @Override
    public Conductor getByInternalID(Conductor conductor) throws RadioException {
        String query = "SELECT * " +
                "FROM personas " +
                "INNER JOIN conductores ON " +
                "personas.dni = conductores.dniPersona " +
                "where personas.dni = ? ";

        return executeQuery(query, conductor.getDni());
    }

    private Conductor executeQuery(String query, int param) throws RadioException {
        Conductor conductor = null;

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, param);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                conductor = new Conductor(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("id"), rs.getDouble("sueldo"));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(CONDUCTORES_NOT_FOUND_ERRROR, e2);
            }
        }

        return conductor;
    }

}
