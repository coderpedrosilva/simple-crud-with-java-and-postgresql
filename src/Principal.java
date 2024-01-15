import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws SQLException {

        DBManager.InitConnection();

        int opc;

        do {
            System.out.println("\n1. Ver personas");
            System.out.println("2. Crear personas");
            System.out.println("3. Editar personas");
            System.out.println("4. Eliminar personas");
            System.out.println("5. Salir");
            opc = scanner.nextInt();

            if(opc == 1){
                verPersonas();
            } else if (opc == 2){
                crearPersona();
            } else if (opc == 3){
                editarPersona();
            } else if (opc == 4){
                eliminarPersona();
            } else if (opc == 5){
                DBManager.closeConection();
            }

        } while (opc != 5);
    }

    public static void verPersonas(){
        DBManager.getPersons();
    }

    public static void crearPersona(){
        System.out.println("Ingrese nombre");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.println("Ingrese apellido");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese edad");
        int edad = scanner.nextInt();

        DBManager.insertPerson(nombre, apellido, edad);
    }

    public static void editarPersona(){

        DBManager.getPersons();

        System.out.println("Ingrese id de la persona que quiere editar");
        long id = scanner.nextLong();

        System.out.println("Ingrese nuevo nombre");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.println("Ingrese nuevo apellido");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese nueva edad");
        int edad = scanner.nextInt();

        DBManager.updatePerson(id, nombre, apellido, edad);

    }

    public static void eliminarPersona(){

        DBManager.getPersons();
        System.out.println("Ingrese id de la persona que quiere eliminar");
        long id = scanner.nextLong();
        DBManager.deletePerson(id);

    }
}