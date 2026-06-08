import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedaczDAO {

    public List<Hospital> listarHospitales() throws SQLException {
        List<Hospital> lista = new ArrayList<>();

        String sql = """
            SELECT id_hos, nom_hos, correo, id_usu
            FROM hospital
            ORDER BY nom_hos
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Hospital hospital = new Hospital(
                        rs.getInt("id_hos"),
                        rs.getString("nom_hos"),
                        rs.getString("correo"),
                        rs.getInt("id_usu")
                );

                lista.add(hospital);
            }
        }

        return lista;
    }

    public List<Ingeniero> listarIngenieros() throws SQLException {
        List<Ingeniero> lista = new ArrayList<>();

        String sql = """
            SELECT id_ing, nom_ing, apellido_paterno, apellido_materno, id_empl, id_usu
            FROM ingeniero
            ORDER BY nom_ing
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ingeniero ingeniero = new Ingeniero(
                        rs.getInt("id_ing"),
                        rs.getString("nom_ing"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getInt("id_empl"),
                        rs.getInt("id_usu")
                );

                lista.add(ingeniero);
            }
        }

        return lista;
    }

    public List<EquipoMedico> listarEquipos(int idHospital) throws SQLException {
        List<EquipoMedico> lista = new ArrayList<>();

        String sql = """
            SELECT e.id_eqpo, e.nom_eqpo, e.modelo, e.marca, e.num_serie,
                   e.estado, e.ubicacion, e.ultimo_mantenimiento,
                   e.proximo_mantenimiento, e.id_hos, h.nom_hos
            FROM equipo_medico e
            INNER JOIN hospital h ON h.id_hos = e.id_hos
            WHERE (? = 0 OR e.id_hos = ?)
            ORDER BY e.nom_eqpo
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHospital);
            ps.setInt(2, idHospital);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EquipoMedico equipo = new EquipoMedico();

                    equipo.setIdEquipo(rs.getInt("id_eqpo"));
                    equipo.setTipoEquipo(rs.getString("nom_eqpo"));
                    equipo.setModelo(rs.getString("modelo"));
                    equipo.setMarca(rs.getString("marca"));
                    equipo.setNumeroSerie(rs.getInt("num_serie"));
                    equipo.setEstado(rs.getString("estado"));
                    equipo.setUbicacion(rs.getString("ubicacion"));
                    equipo.setUltimoMantenimiento(String.valueOf(rs.getDate("ultimo_mantenimiento")));
                    equipo.setProximoMantenimiento(String.valueOf(rs.getDate("proximo_mantenimiento")));
                    equipo.setIdHospital(rs.getInt("id_hos"));
                    equipo.setNombreHospital(rs.getString("nom_hos"));

                    lista.add(equipo);
                }
            }
        }

        return lista;
    }

    public void agregarEquipo(EquipoMedico equipo) throws SQLException {
        String sql = """
            INSERT INTO equipo_medico
            (nom_eqpo, modelo, marca, num_serie, estado, ubicacion, id_hos)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo.getTipoEquipo());
            ps.setString(2, equipo.getModelo());
            ps.setString(3, equipo.getMarca());
            ps.setInt(4, equipo.getNumeroSerie());
            ps.setString(5, equipo.getEstado());
            ps.setString(6, equipo.getUbicacion());
            ps.setInt(7, equipo.getIdHospital());

            ps.executeUpdate();
        }
    }

    public void editarEquipo(EquipoMedico equipo) throws SQLException {
        String sql = """
            UPDATE equipo_medico
            SET nom_eqpo = ?, modelo = ?, marca = ?, num_serie = ?,
                estado = ?, ubicacion = ?, id_hos = ?
            WHERE id_eqpo = ?
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, equipo.getTipoEquipo());
            ps.setString(2, equipo.getModelo());
            ps.setString(3, equipo.getMarca());
            ps.setInt(4, equipo.getNumeroSerie());
            ps.setString(5, equipo.getEstado());
            ps.setString(6, equipo.getUbicacion());
            ps.setInt(7, equipo.getIdHospital());
            ps.setInt(8, equipo.getIdEquipo());

            ps.executeUpdate();
        }
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        String sql = "DELETE FROM equipo_medico WHERE id_eqpo = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);
            ps.executeUpdate();
        }
    }

    public void registrarSolicitud(SolicitudServicio solicitud) throws SQLException {
        String sql = """
            INSERT INTO solicitud_servicio
            (fecha_soli, tipo_servi, observaciones, estado, id_hos, id_eqpo)
            VALUES (?, ?, ?, 'Pendiente', ?, ?)
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, solicitud.getFecha());
            ps.setString(2, solicitud.getTipoServicio());
            ps.setString(3, solicitud.getFalla());
            ps.setInt(4, solicitud.getIdHospital());
            ps.setInt(5, solicitud.getIdEquipo());

            ps.executeUpdate();
        }
    }

    public List<SolicitudServicio> listarSolicitudes(int idHospital) throws SQLException {
        List<SolicitudServicio> lista = new ArrayList<>();

        String sql = """
            SELECT s.id_soli, s.fecha_soli, s.tipo_servi, s.observaciones,
                   s.estado, s.id_hos, s.id_eqpo, s.id_ing, s.fecha_visita
            FROM solicitud_servicio s
            WHERE (? = 0 OR s.id_hos = ?)
            ORDER BY s.id_soli DESC
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHospital);
            ps.setInt(2, idHospital);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SolicitudServicio solicitud = new SolicitudServicio();

                    solicitud.setIdSolicitud(rs.getInt("id_soli"));
                    solicitud.setFecha(String.valueOf(rs.getDate("fecha_soli")));
                    solicitud.setTipoServicio(rs.getString("tipo_servi"));
                    solicitud.setFalla(rs.getString("observaciones"));
                    solicitud.setEstado(rs.getString("estado"));
                    solicitud.setIdHospital(rs.getInt("id_hos"));
                    solicitud.setIdEquipo(rs.getInt("id_eqpo"));
                    solicitud.setIdIngeniero(rs.getInt("id_ing"));
                    solicitud.setFechaVisita(String.valueOf(rs.getDate("fecha_visita")));

                    lista.add(solicitud);
                }
            }
        }

        return lista;
    }

    public void asignarSeguimiento(int idSolicitud, int idIngeniero, String fechaVisita) throws SQLException {
        String sql = """
            UPDATE solicitud_servicio
            SET id_ing = ?, fecha_visita = ?, estado = 'Asignada'
            WHERE id_soli = ?
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idIngeniero);
            ps.setString(2, fechaVisita);
            ps.setInt(3, idSolicitud);

            ps.executeUpdate();
        }
    }

    public void guardarMantenimiento(SolicitudServicio solicitud, String estadoEquipo,
                                     String observaciones, String proximoMantenimiento) throws SQLException {

        String sqlEquipo = """
            UPDATE equipo_medico
            SET estado = ?, ultimo_mantenimiento = CURDATE(), proximo_mantenimiento = ?
            WHERE id_eqpo = ?
        """;

        String sqlSolicitud = """
            UPDATE solicitud_servicio
            SET estado = 'Terminada', observaciones = ?
            WHERE id_soli = ?
        """;

        try (Connection con = ConexionBD.conectar()) {
            con.setAutoCommit(false);

            try (PreparedStatement psEquipo = con.prepareStatement(sqlEquipo);
                 PreparedStatement psSolicitud = con.prepareStatement(sqlSolicitud)) {

                psEquipo.setString(1, estadoEquipo);
                psEquipo.setString(2, proximoMantenimiento);
                psEquipo.setInt(3, solicitud.getIdEquipo());
                psEquipo.executeUpdate();

                psSolicitud.setString(1, observaciones);
                psSolicitud.setInt(2, solicitud.getIdSolicitud());
                psSolicitud.executeUpdate();

                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }
        }
    }

    public void guardarChecklist(int idEquipo, String verificaciones,
                                 String observaciones, String anomalias) throws SQLException {

        String sql = """
            INSERT INTO checklist_registro
            (id_eqpo, verificaciones, observaciones, anomalias)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);
            ps.setString(2, verificaciones);
            ps.setString(3, observaciones);
            ps.setString(4, anomalias);

            ps.executeUpdate();
        }
    }

    public String ultimoChecklist(int idEquipo) throws SQLException {
        String sql = """
            SELECT verificaciones, observaciones, anomalias, fecha_check
            FROM checklist_registro
            WHERE id_eqpo = ?
            ORDER BY id_check_reg DESC
            LIMIT 1
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEquipo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "Fecha: " + rs.getString("fecha_check")
                            + "\n\nVerificaciones:\n" + rs.getString("verificaciones")
                            + "\n\nObservaciones:\n" + rs.getString("observaciones")
                            + "\n\nAnomalías:\n" + rs.getString("anomalias");
                }
            }
        }

        return "Este equipo todavía no tiene checklist registrado.";
    }

    public void crearPlantilla(String nombre, String elementos) throws SQLException {
        String sql = """
            INSERT INTO plantilla_checklist (nom_plan, descripcion, fecha_crea)
            VALUES (?, ?, NOW())
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, elementos);
            ps.executeUpdate();
        }
    }

    public void editarPlantilla(int idPlantilla, String nombre, String elementos) throws SQLException {
        String sql = """
            UPDATE plantilla_checklist
            SET nom_plan = ?, descripcion = ?
            WHERE id_plan = ?
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, elementos);
            ps.setInt(3, idPlantilla);
            ps.executeUpdate();
        }
    }

    public void eliminarPlantilla(int idPlantilla) throws SQLException {
        String sql = "DELETE FROM plantilla_checklist WHERE id_plan = ?";

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPlantilla);
            ps.executeUpdate();
        }
    }

    public List<String[]> listarPlantillas() throws SQLException {
        List<String[]> lista = new ArrayList<>();

        String sql = """
            SELECT id_plan, nom_plan, descripcion, fecha_crea
            FROM plantilla_checklist
            ORDER BY id_plan DESC
        """;

        try (Connection con = ConexionBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("id_plan")),
                    rs.getString("nom_plan"),
                    rs.getString("descripcion"),
                    String.valueOf(rs.getDate("fecha_crea"))
                });
            }
        }

        return lista;
    }
}