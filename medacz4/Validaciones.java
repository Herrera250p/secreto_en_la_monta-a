import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class Validaciones {

    private Validaciones() {
    }

    public static boolean vacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean correoValido(String correo) {
        return correo != null
                && correo.contains("@")
                && correo.contains(".")
                && correo.indexOf("@") > 0
                && correo.lastIndexOf(".") > correo.indexOf("@");
    }

    public static void soloNumeros(JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char tecla = e.getKeyChar();

                if (!Character.isDigit(tecla)) {
                    e.consume();
                }
            }
        });
    }
}