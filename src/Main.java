import model.Titores;
import services.AlumnoServices;
import services.TitoresServices;

public class Main {
    public static void main(String[] args) {
        TitoresServices titoresServices = new TitoresServices();
        AlumnoServices alumnoService = new AlumnoServices();

        // 1. Crear titores
        titoresServices.crearTitor("Maria", "García López");
        titoresServices.crearTitor("Carlos", "Pérez Silva");

        Titores titor1 = titoresServices.lerTitor(1);
        Titores titor2 = titoresServices.lerTitor(2);

        alumnoService.crearAlumno("Ana", titor1);
        alumnoService.crearAlumno("Maria", titor1);
        alumnoService.crearAlumno("Luis", titor2);

        System.out.println("\n--- seleccionar un tutor y todos sus alumnos ---");
        titoresServices.lerTitorConAlumnos(1);

        //Modificar alumnos
        alumnoService.actualizarNomeAlumno(1, "Juan");

        // Modificar titores
        titoresServices.actualizarTitor(1, "Josefa", "García López");

        // Eliminar alumnos
        alumnoService.eliminarAlumno(2);

        // Eliminar titores
        titoresServices.eliminarTitor(2);

        // Verificar cambios
        System.out.println("\n--- Estado final ---");
        titoresServices.lerTitorConAlumnos(1);
    }
}