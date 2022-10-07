import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.awt.GridLayout;
import java.awt.event.*;

public class LoginParaApps extends JFrame{

    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private JLabel etiquetaUsuario;
    private JLabel etiquetaContraseña;
    private JButton botonEntrar;
    private JButton botonRegistrarse;

    private static Scanner sc;
    static String usuario, contraseña;

    public LoginParaApps(){
        super("Login");

        LayoutManager layout = new GridLayout(3, 2, 5, 5);
        setLayout(layout);

        etiquetaUsuario = new JLabel("Usuario: ");
        add(etiquetaUsuario);

        campoUsuario = new JTextField();
        add(campoUsuario);


        etiquetaContraseña = new JLabel("Contraseña :");
        add(etiquetaContraseña);

        campoContraseña = new JPasswordField();
        add(campoContraseña);

        botonEntrar = new JButton("Entrar");
        add(botonEntrar);

        botonRegistrarse = new JButton("Registrar");
        add(botonRegistrarse);

        ActionListener eventoBotonEntrar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                abrirInterfazUsuario();
                entrarUsuario();
            }
        };

        ActionListener eventoBotonRegistrar = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                abrirFrameRegistrarUsuario();
            }
        };

        botonEntrar.addActionListener(eventoBotonEntrar);
        botonRegistrarse.addActionListener(eventoBotonRegistrar);
    }

    public void abrirInterfazUsuario(){
        InterfazUsuario interfazUsuario = new InterfazUsuario();
            interfazUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            interfazUsuario.setLocationRelativeTo(null);
            interfazUsuario.setSize(500, 180);
            interfazUsuario.setVisible(true);
            this.setVisible(false);
    }

    public void abrirFrameRegistrarUsuario(){
            RegistrarUsuario registrarUsuario = new RegistrarUsuario();
            registrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registrarUsuario.setLocationRelativeTo(null);
            registrarUsuario.setSize(500, 180);
            registrarUsuario.setVisible(true);
            this.setVisible(false);
    }

    public void entrarUsuario(){

        FileReader lectorDeArchivo = null;
        File archivo;
        BufferedReader buferLector;

        int numLineas = 0; // Variable para conocer el número de lineas del archivo
        int i = 0; // Indice para recorrer el arreglo
        String [] usuarios = null; // Arreglo para almacenar los datos de los usuarios

        archivo = new File("bd.txt");

        if(!archivo.exists()){
            try {
                archivo.createNewFile();
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
    
                usuario = campoUsuario.getText();
                contraseña = campoContraseña.getText();
    
                ValidarUsuario validarUsuario = new ValidarUsuario();
                validarUsuario.validarUsuario(usuarios, usuario, contraseña);
    
            } catch (IOException ex) {
                System.out.println("El archivo no existe");
            }
        }else{
            try {
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
    
                usuario = campoUsuario.getText();
                contraseña = campoContraseña.getText();
    
                ValidarUsuario validarUsuario = new ValidarUsuario();
                validarUsuario.validarUsuario(usuarios, usuario, contraseña);
    
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                System.out.println("El archivo no existe");
            }
        }
    }
}