import java.io.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.util.Scanner;

public class RegistrarUsuario extends JFrame{

    private JTextField campoNuevoUsuario;
    private JTextField campoNuevaContraseña;
    private JTextField campoConfirmarContraseña;
    private JLabel etiquetaIngresarUsuario;
    private JLabel etiquetaIngresarContraseña;
    private JLabel etiquetaConfirmarContraseña;
    private JButton botonRegresar;
    private JButton botonRegistrar;
    private static Scanner sc;

    boolean encontrado = false;

    public RegistrarUsuario(){
        super("Registrar Nuevo Usuario");

        LayoutManager layout = new GridLayout(4, 2, 5, 5);
        setLayout(layout);

        etiquetaIngresarUsuario = new JLabel("Usuario: ");
        add(etiquetaIngresarUsuario);

        campoNuevoUsuario = new JTextField();
        add(campoNuevoUsuario);

        etiquetaIngresarContraseña = new JLabel("Contraseña: ");
        add(etiquetaIngresarContraseña);

        campoNuevaContraseña = new JTextField();
        add(campoNuevaContraseña);

        etiquetaConfirmarContraseña = new JLabel("Confirmar Contraseña: ");
        add(etiquetaConfirmarContraseña);

        campoConfirmarContraseña = new JTextField();
        add(campoConfirmarContraseña);

        botonRegresar = new JButton("Regresar");
        add(botonRegresar);

        botonRegistrar = new JButton("Registrar");
        add(botonRegistrar);

        ActionListener eventoBotonRegresar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                regresarLoginParaApps();

            }
        };

        ActionListener eventoBotonRegistrar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                registrarUsuario();
            }
        };

        botonRegresar.addActionListener(eventoBotonRegresar);
        botonRegistrar.addActionListener(eventoBotonRegistrar);
    }

    public void regresarLoginParaApps(){
        LoginParaApps loginParaApps = new LoginParaApps();
        loginParaApps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginParaApps.setLocationRelativeTo(null);
        loginParaApps.setSize(500, 180);
        loginParaApps.setVisible(true);
        this.setVisible(false);
    }

    public void validarDisponibilidad(String usuarios[], String user, String password){
        
        for(int x=0; x < usuarios.length; x++){
            if(usuarios[x].equalsIgnoreCase(user) && usuarios[x+1].equals(password)){
                String respuesta = "El usuario ya esta registrado "+ user;
                encontrado = true;
                JOptionPane.showMessageDialog(null, respuesta, "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        } // Fin del ciclo for
    }

    public void registrarUsuario(){
        File archivo; // Variable para manipular el archivo
        FileWriter escribir; // Variable para escribir en el archivo
        PrintWriter linea; // Para escribir en el archivo
        String nombreUsuario = "";
        String contraseñaUsuario = "";

        FileReader lectorDeArchivo = null;
        BufferedReader buferLector;

        int numLineas = 0; // Variable para conocer el número de lineas del archivo
        int i = 0; // Indice para recorrer el arreglo
        String [] usuarios = null; 

        archivo = new File("bd.txt"); // Preparando al archivo

        if(!archivo.exists()){ // Comprobar si el archivo existe
            // Si no existe lo creamos
            try{
                archivo.createNewFile();
                nombreUsuario = campoNuevoUsuario.getText();
                contraseñaUsuario = campoNuevaContraseña.getText();

                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);

                if(campoConfirmarContraseña.getText().equals(contraseñaUsuario)){
                // Escribimos en el archivo
                linea.println(nombreUsuario);
                linea.println(contraseñaUsuario);
                // Cerrar variables de escritura
                linea.close();
                escribir.close();

                JOptionPane.showMessageDialog(null, "Usuario registrado con exito", "Usuario Registrado", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinsiden", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo!");
            }
        }else{ // Si el archivo existe entonces solo escribimos en el archivo
            try{
                escribir = new FileWriter(archivo, true);
                linea = new PrintWriter(escribir);
                // Escribimos en el archivo
                
                sc = new Scanner(new File("bd.txt"));
                lectorDeArchivo = new FileReader(archivo);
                buferLector = new BufferedReader(lectorDeArchivo);

                try {
                    while((buferLector.readLine()) != null){
                        numLineas++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
                usuarios = new String[numLineas];
    
                while(sc.hasNextLine()){
                    usuarios[i++] = sc.nextLine(); // Almacenamos cada linea en una posicion del arreglo
                }

                nombreUsuario = campoNuevoUsuario.getText();
                contraseñaUsuario = campoNuevaContraseña.getText();

                ValidarDisponibilidad validarDisponibilidad = new ValidarDisponibilidad();
                validarDisponibilidad.validarUsuario(usuarios, nombreUsuario, contraseñaUsuario);


                if(ValidarDisponibilidad.encontrado == true){ // Si el usuario existe no registrar
                    JOptionPane.showMessageDialog(null, "El usuario ya esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(campoConfirmarContraseña.getText().equals(contraseñaUsuario)){
                        // Escribimos en el archivo
                        linea.println(nombreUsuario);
                        linea.println(contraseñaUsuario);
                        // Cerrar variables de escritura
                        linea.close();
                        escribir.close();
    
                        JOptionPane.showMessageDialog(null, "Usuario registrado con exito", "Usuario Registrado", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinsiden", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo!");
            }
        }
    }
    
}
