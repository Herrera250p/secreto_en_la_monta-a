import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/medacz?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USUARIO = "medacz";

    private static final String PASSWORD = "MeDaCz";

    private ConexionBD() {
    }

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL. Revisa que el .jar esté en la carpeta lib.", e);
        }

        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}