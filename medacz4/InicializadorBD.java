import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBD {

    private InicializadorBD() {
    }

    public static void prepararBase() throws SQLException {
        try (Connection con = ConexionBD.conectar();
             Statement st = con.createStatement()) {

            agregarColumna(st, "usuario", "identificador", "VARCHAR(30) DEFAULT NULL");

            agregarColumna(st, "equipo_medico", "ubicacion", "VARCHAR(100) DEFAULT NULL");
            agregarColumna(st, "equipo_medico", "ultimo_mantenimiento", "DATE DEFAULT NULL");
            agregarColumna(st, "equipo_medico", "proximo_mantenimiento", "DATE DEFAULT NULL");

            agregarColumna(st, "solicitud_servicio", "id_eqpo", "INT DEFAULT NULL");
            agregarColumna(st, "solicitud_servicio", "id_ing", "INT DEFAULT NULL");
            agregarColumna(st, "solicitud_servicio", "fecha_visita", "DATE DEFAULT NULL");

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS ingeniero (
                    id_ing INT AUTO_INCREMENT PRIMARY KEY,
                    nom_ing VARCHAR(50) NOT NULL,
                    apellido_paterno VARCHAR(50),
                    apellido_materno VARCHAR(50),
                    id_empl INT NOT NULL,
                    id_usu INT NOT NULL,
                    FOREIGN KEY (id_usu) REFERENCES usuario(id_usu)
                )
            """);

            st.executeUpdate("""
                CREATE TABLE IF NOT EXISTS checklist_registro (
                    id_check_reg INT AUTO_INCREMENT PRIMARY KEY,
                    id_eqpo INT NOT NULL,
                    verificaciones TEXT,
                    observaciones TEXT,
                    anomalias TEXT,
                    fecha_check DATETIME DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (id_eqpo) REFERENCES equipo_medico(id_eqpo)
                )
            """);

            st.executeUpdate("""
                INSERT INTO usuario (nom_usu, pas_usu, stat_usu, identificador)
                SELECT 'admin', '1234', 'ACTIVO', 'ADMIN'
                WHERE NOT EXISTS (
                    SELECT 1 FROM usuario WHERE nom_usu = 'admin'
                )
            """);

            st.executeUpdate("""
                INSERT INTO administrador (nom_admin, apellido_paterno, apellido_materno, id_usu)
                SELECT 'Administrador', 'Principal', 'Medacz', u.id_usu
                FROM usuario u
                WHERE u.nom_usu = 'admin'
                AND NOT EXISTS (
                    SELECT 1 FROM administrador WHERE id_usu = u.id_usu
                )
            """);
        }
    }

    private static void agregarColumna(Statement st, String tabla, String columna, String definicion) {
        try {
            st.executeUpdate("ALTER TABLE " + tabla + " ADD COLUMN " + columna + " " + definicion);
        } catch (SQLException e) {
            // Si ya existe la columna, no pasa nada.
        }
    }
}