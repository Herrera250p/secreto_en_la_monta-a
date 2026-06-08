import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroDAO {

    public void registrarHospital(String nombreHospital, String correo,
                                  String password, String identificador) throws SQLException {

        String sqlUsuario = """
            INSERT INTO usuario (nom_usu, pas_usu, stat_usu, identificador)
            VALUES (?, ?, 'ACTIVO', ?)
        """;

        String sqlHospital = """
            INSERT INTO hospital (nom_hos, correo, id_usu)
            VALUES (?, ?, ?)
        """;

        try (Connection con = ConexionBD.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement psHospital = con.prepareStatement(sqlHospital)) {

                psUsuario.setString(1, nombreHospital);
                psUsuario.setString(2, password);
                psUsuario.setString(3, identificador);
                psUsuario.executeUpdate();

                int idUsuario = obtenerId(psUsuario);

                psHospital.setString(1, nombreHospital);
                psHospital.setString(2, correo);
                psHospital.setInt(3, idUsuario);
                psHospital.executeUpdate();

                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public void registrarIngeniero(String nombreCompleto, String password,
                                   String identificador) throws SQLException {

        String sqlUsuario = """
            INSERT INTO usuario (nom_usu, pas_usu, stat_usu, identificador)
            VALUES (?, ?, 'ACTIVO', ?)
        """;

        String sqlIngeniero = """
            INSERT INTO ingeniero (nom_ing, apellido_paterno, apellido_materno, id_empl, id_usu)
            VALUES (?, '', '', ?, ?)
        """;

        try (Connection con = ConexionBD.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS);
                 PreparedStatement psIngeniero = con.prepareStatement(sqlIngeniero)) {

                psUsuario.setString(1, nombreCompleto);
                psUsuario.setString(2, password);
                psUsuario.setString(3, identificador);
                psUsuario.executeUpdate();

                int idUsuario = obtenerId(psUsuario);

                psIngeniero.setString(1, nombreCompleto);
                psIngeniero.setInt(2, idUsuario + 1000);
                psIngeniero.setInt(3, idUsuario);
                psIngeniero.executeUpdate();

                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    private int obtenerId(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        throw new SQLException("No se pudo obtener el ID generado.");
    }
}