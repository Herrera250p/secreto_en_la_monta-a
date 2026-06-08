import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario login(String nombre, String password, String identificador) {
        String sql = """
            SELECT
                u.id_usu,
                u.nom_usu,
                u.pas_usu,
                u.stat_usu,
                h.id_hos,
                i.id_ing,
                a.id_admin,
                CASE
                    WHEN a.id_admin IS NOT NULL THEN 'Administrador'
                    WHEN h.id_hos IS NOT NULL THEN 'Hospital'
                    WHEN i.id_ing IS NOT NULL THEN 'Ingeniero'
                    ELSE 'Sin rol'
                END AS rol
            FROM usuario u
            LEFT JOIN hospital h ON h.id_usu = u.id_usu
            LEFT JOIN ingeniero i ON i.id_usu = u.id_usu
            LEFT JOIN administrador a ON a.id_usu = u.id_usu
            WHERE u.nom_usu = ?
              AND u.pas_usu = ?
              AND (u.identificador = ? OR ? = '')
              AND (u.stat_usu IS NULL OR UPPER(u.stat_usu) <> 'INACTIVO')
            LIMIT 1
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, password);
            ps.setString(3, identificador);
            ps.setString(4, identificador);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt("id_usu"));
                    usuario.setNombreUsuario(rs.getString("nom_usu"));
                    usuario.setPassword(rs.getString("pas_usu"));
                    usuario.setEstado(rs.getString("stat_usu"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setIdHospital(rs.getInt("id_hos"));
                    usuario.setIdIngeniero(rs.getInt("id_ing"));
                    usuario.setIdAdministrador(rs.getInt("id_admin"));

                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error login: " + e.getMessage());
        }

        return null;
    }
}