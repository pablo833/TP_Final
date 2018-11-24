package DAOS.impl;

import DAOS.ContratoDAO;
import DAOS.DAO;
import DB.DBManager;
import ENTIDADES.Contrato;
import EXCEPTIONS.RadioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ContratoDAOImpl extends AbstractImpl implements DAO<Contrato>, ContratoDAO {

    private final String CONTRATO_NOT_FOUND_ERRROR = "Hubo un error en la búsqueda de CONTRATOS";

    @Override
    public void insert(Contrato contrato) throws RadioException {

        String query = "INSERT INTO contratos " +
                "(programa, auspiciante, tiempoDePauta) " +
                "VALUES" +
                "(?, ?, ?)";

        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {
            dml = connection.prepareStatement(query);
            dml.setInt(1, contrato.getPrograma().getCodigo());
            dml.setInt(2, contrato.getAuspiciante().getCode());
            dml.setInt(3, contrato.getTiempoDePauta());

            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException("Hubo un error en la insercion de contrato", e);
        }
    }

    @Override
    public void update(Contrato contrato) {

    }

    @Override
    public Contrato get(int codigo) {
        return null;
    }

    @Override
    public void delete(int codigo) throws RadioException {
        String query = "DELETE FROM contratos WHERE id = ?";

        Connection connection = DBManager.connect();

        PreparedStatement dml;
        try {

            dml = connection.prepareStatement(query);
            dml.setInt(1, codigo);

            executeQuery(connection, dml);
        } catch (SQLException e) {
            throw new RadioException(BD_ERROR, e);
        }
    }

    @Override
    public List<Contrato> getAll() {
        return null;
    }

    @Override
    public Contrato getByInternalID(Contrato contrato) {
        return null;
    }

    @Override
    public List<Contrato> getByPrograma(int programa) throws RadioException {

        List<Contrato> contratos = new ArrayList<Contrato>();
        Contrato c = null;

        String query = "SELECT * " +
                "FROM contratos " +
                "INNER JOIN programas ON " +
                "contratos.programa = programas.id " +
                "INNER JOIN auspiciantes ON " +
                "contratos.auspiciante = auspiciantes.id " +
                "WHERE programas.id = ?";

        Connection connection = DBManager.connect();

        try {
            PreparedStatement dml = connection.prepareStatement(query);

            dml.setInt(1, programa);

            dml.executeQuery();
            ResultSet rs = dml.getResultSet();

            while (rs.next()) {
                c = new Contrato(rs.getInt("id"),
                        new ProgramaDAOImpl().get(rs.getInt("programa")),
                        new AuspicianteDaoImpl().get(rs.getInt("auspiciante")),
                        rs.getInt("tiempoDePauta")
                );
                contratos.add(c);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new RadioException(CONTRATO_NOT_FOUND_ERRROR, e1);
            }
            throw new RadioException(CONTRATO_NOT_FOUND_ERRROR, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e2) {
                throw new RadioException(CONTRATO_NOT_FOUND_ERRROR, e2);
            }
        }
        return contratos;
    }
}
