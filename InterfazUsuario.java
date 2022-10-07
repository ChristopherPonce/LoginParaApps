import javax.swing.JFrame;
import javax.swing.JLabel;

public class InterfazUsuario extends JFrame{
    
    private JLabel etiquetaUsuario;
    static String nombreUsuario;


    public InterfazUsuario(){
        super("Interfaz de usuario");

        nombreUsuario = LoginParaApps.usuario;
        etiquetaUsuario = new JLabel("Bienvenido "+ nombreUsuario);
        add(etiquetaUsuario);
    }
}
