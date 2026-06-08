import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        try {
            Connection conexion = ConexionBD.conectar();

            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos medacz.");
            }

            conexion.close();

        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos.");
            System.out.println("Detalle: " + e.getMessage());
        }
    }
}