package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    private static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DB_URL = "jdbc:hsqldb:file:sql/radioDB;shutdown=true;hsqldb.default_table_type=cached";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static Connection connection = null;

    private static String ddlUsuarioTable = "CREATE TABLE users (id INTEGER IDENTITY PRIMARY KEY, userName VARCHAR(256), password VARCHAR(100), firstName VARCHAR(256), lastName VARCHAR(256))";

    private static String ddlAuspicianteTable = "CREATE TABLE auspiciantes (id INTEGER IDENTITY PRIMARY KEY, razonSocial VARCHAR(256))";

    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    Class.forName(DB_DRIVER);
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                    connection.setAutoCommit(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void shutdown() throws Exception {
        Connection connection = connect();
        Statement s = connection.createStatement();
        s.execute("SHUTDOWN");
        connection.close();
    }

    public static void createDB() {

       // createUsuarioTable();
        createAuspicianteTable();
    }

    private static void createUsuarioTable() {
        Connection connection = DBManager.connect();

        String sql = ddlUsuarioTable;
        try {
            Statement s = connection.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private static void createAuspicianteTable() {
        Connection connection = DBManager.connect();

        String sql = ddlAuspicianteTable;
        try {
            Statement s = connection.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void borrarTabla() {
        Connection connection = DBManager.connect();

        String sql = "DROP TABLE users";
        try {
            Statement s = connection.createStatement();
            s.execute(sql);
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
