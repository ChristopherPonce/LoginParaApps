import javax.swing.JOptionPane;

public class ValidarUsuario {

    String respuesta;

    public void validarUsuario(String usuarios[], String user, String password){
        boolean encontrado = false;
        
        for(int x=0; x < usuarios.length; x++){
            if(usuarios[x].equalsIgnoreCase(user) && usuarios[x+1].equals(password)){
                //respuesta = "Bienvenido "+ user;
                encontrado = true;
                //JOptionPane.showMessageDialog(null, respuesta, "Inicio de sesión", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        } // Fin del ciclo for

        if(encontrado == false){
            respuesta = "Usuario y/o Contraseña incorrecta";
            JOptionPane.showMessageDialog(null, respuesta, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
