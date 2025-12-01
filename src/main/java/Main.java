import org.example.model.Titores;
import org.example.services.AlumnoServices;
import org.example.services.TitoresServices;

public class Main {
    public static void main(String[] args) {
        TitoresServices titoresServices = new TitoresServices();
        AlumnoServices alumnoService = new AlumnoServices();


        //Crear titores
        titoresServices.crearTitor("Maria", "García López");
        titoresServices.crearTitor("Carlos", "Pérez Silva");

        Titores titor1 = titoresServices.lerTitor(1);
        Titores titor2 = titoresServices.lerTitor(2);

        //Crear alumons
        alumnoService.crearAlumno("Ana", titor1);
        alumnoService.crearAlumno("Luis", titor2);

        System.out.println("\n--- seleccionar un tutor y todos sus alumnos ---");
        titoresServices.lerTitorConAlumnos(1);

        //Modificar alumnos
        alumnoService.actualizarNomeAlumno(1, "Juan Nuevo");

        // Modificar titores
        titoresServices.actualizarTitor(1, "Maria nuevo", "García");

        // Eliminar alumnos
        alumnoService.eliminarAlumno(2);

        // Eliminar titores
        titoresServices.eliminarTitor(2);

        // Verificar cambios
        System.out.println("\n--- Estado final ---");
        titoresServices.lerTitorConAlumnos(1);
    }
}