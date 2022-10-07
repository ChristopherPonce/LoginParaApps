public class ValidarDisponibilidad {
    String respuesta;
    static boolean encontrado = false;

    public void validarUsuario(String usuarios[], String user, String password){
        
        for(int x=0; x < usuarios.length; x++){
            if(usuarios[x].equalsIgnoreCase(user) && usuarios[x+1].equals(password)){
                respuesta = "Ya esta registrado el usuario: "+ user;
                encontrado = true;
                break;
            }
        } // Fin del ciclo for
    }
}
