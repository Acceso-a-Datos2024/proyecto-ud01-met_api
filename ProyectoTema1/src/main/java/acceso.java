import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class acceso {
    public static void main(String[] args) {
        Properties prop = new Properties();
        Scanner scanner = new Scanner(System.in);
        FileInputStream input = null;
        try {
            input = new FileInputStream("acceso.properties");

        prop.load(input);




        String username = prop.getProperty("user");
        String password = prop.getProperty("password");


            System.out.print("Introduce el usuario: ");
            String inputUser = scanner.nextLine();

            System.out.print("Introduce la contraseña: ");
            String inputPassword = scanner.nextLine();

            if (inputUser.equals(username) && inputPassword.equals(password)) {
                System.out.println("Acceso correcto");
            } else {
                System.out.println("Usuario o contraseña incorrectos");
            }


        }  catch (IOException ex) {
            ex.printStackTrace();
        }


    }
    }
