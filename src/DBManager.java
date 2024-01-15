import java.sql.*;

public class DBManager {

    //Credenciales
    private static final String user = "postgres";
    private static final String pass = "123456";

    private static Connection connection;

    // Inicializa la conexion
    public static void InitConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Usuarios", user, pass);
            System.out.println("conectado con exito");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Inserta una persona
    public static void insertPerson(String nombre, String apellido, int edad) {

        try {

            PreparedStatement stmn = connection.prepareStatement("INSERT INTO persona (nombre, apellido, edad) VALUES (?, ?, ?)");
            stmn.setString(1, nombre);
            stmn.setString(2, apellido);
            stmn.setInt(3, edad);
            stmn.execute();

            System.out.println("creado exitoso");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //tras datos de la tabla persona
    public static void getPersons() {

        try {
            PreparedStatement stmn = connection.prepareStatement("SELECT * FROM persona ORDER by id");
            ResultSet result = stmn.executeQuery();

            System.out.println("ID|Nombre|Apellido|Edad");
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int edad = result.getInt("edad");

                System.out.println(id+"\t"+nombre+"\t"+apellido+"\t"+edad);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //modifica un registro en la tabla persona
    public static void updatePerson(long id, String nuevoNomb, String nuevoApelli, int nuevaEdad){
        try {
            PreparedStatement stmn = connection.prepareStatement("UPDATE persona SET nombre = ?, apellido = ?, edad = ? WHERE id = ?");
            stmn.setString(1,nuevoNomb);
            stmn.setString(2,nuevoApelli);
            stmn.setInt(3, nuevaEdad);
            stmn.setLong(4,id);
            int row = stmn.executeUpdate();

            if(row == 0){
                System.out.println("No se modifica");
            }else{
                System.out.println("actualizacion correcta");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //elimina un registro en la tabla persona
    public static void deletePerson(long id){
        try{
            PreparedStatement stmn = connection.prepareStatement("DELETE FROM persona WHERE id = ?");
            stmn.setLong(1, id);

            int row = stmn.executeUpdate();

            if (row == 0){
                System.out.println("no se puede borrar el registro com el id " + id);
            } else {
                System.out.println("borrado exitoso");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //cierra la conexion con la bd
    public static void closeConection() throws SQLException {
        connection.close();
    }

}
