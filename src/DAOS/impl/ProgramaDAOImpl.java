package DAOS.impl;

import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Programa;
import EXCEPTIONS.RadioException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAOImpl extends AbstractImpl implements DAO<Programa> {

    private final String PROGRAMA_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de PROGRAMAS";

    @Override
    public void insert(Programa programa) throws RadioException {
        String query = "INSERT INTO programas " +
                "(nombre, horario, valorSegundoAlAire, conductor, productor ) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setString(1, programa.getNombre());
            dml.setString(2, programa.getHorario());
            dml.setDouble(3, programa.getValorSegundoAlAire());
            dml.setInt(4, programa.getProductor().getCodigo());
            dml.setInt(5, programa.getConductor().getCodigo());
            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la insercion del programa", e);
        }
    }

    @Override
    public void update(Programa programa) throws RadioException {
        String query = "UPDATE programas set " +
                "nombre = ?, " +
                "horario = ?, " +
                "valorSegundoAlAire = ?, " +
                "conductor = ?, " +
                "productor = ? " +
                "where id = ?";
        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setString(1, programa.getNombre());
            dml.setString(2, programa.getHorario());
            dml.setDouble(3, programa.getValorSegundoAlAire());
            dml.setInt(4, programa.getProductor().getCodigo());
            dml.setInt(5, programa.getConductor().getCodigo());
            dml.setInt(6, programa.getCodigo());

            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la actualizacion del programa", e);
        }
    }

    @Override
    public Programa get(int codigo) throws RadioException {
        Programa programa = null;

        String query = "SELECT * " +
                "FROM programas " +
                "INNER JOIN conductores ON " +
                "conductores.id = programas.conductor " +
                "inner join productores ON " +
                "productores.id = programas.productor " +
                "where id = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, codigo);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                programa = new Programa(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("horario"),
                        rs.getDouble("valorSegundoAlAire"),
                        new ConductorDAOImpl().get(rs.getInt("conductor")),
                        new ProductorDAOImpl().get(rs.getInt("productor"))
                );
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e2);
            }
        }
        return programa;
    }

    @Override
    public void delete(int codigo) throws RadioException {

    }

    @Override
    public List<Programa> getAll() throws RadioException {
        List<Programa> programas = new ArrayList<Programa>();
        Programa p = null;

        String query = "SELECT * " +
                "FROM programas " +
                "INNER JOIN conductores ON " +
                "conductores.id = programas.conductor " +
                "inner join productores ON " +
                "productores.id = programas.productor ";

        Connection connection = DBManager.connect();

        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);


            while (rs.next()) {
                Programa programa = new Programa(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("horario"),
                        rs.getDouble("valorSegundoAlAire"),
                        new ConductorDAOImpl().get(rs.getInt("conductor")),
                        new ProductorDAOImpl().get(rs.getInt("productor"))

                );
                programas.add(programa);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e2);
            }
        }
        return programas;
    }

    @Override
    public Programa getByInternalID(Programa programa) throws RadioException {
        Programa p = null;

        String query = "SELECT * " +
                "FROM programas " +
                "INNER JOIN conductores ON " +
                "conductores.id = programas.conductor " +
                "inner join productores ON " +
                "productores.id = programas.productor " +
                "where nombre = ? ";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setString(1, programa.getNombre());

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                p = new Programa(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("horario"),
                        rs.getDouble("valorSegundoAlAire"),
                        new ConductorDAOImpl().get(rs.getInt("conductor")),
                        new ProductorDAOImpl().get(rs.getInt("productor"))
                );

            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(PROGRAMA_NOT_FOUND_ERRROR, e2);
            }
        }
        return p;
    }

}
