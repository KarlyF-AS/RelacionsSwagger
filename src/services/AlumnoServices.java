package services;

import config.HibernateConfig;
import model.Alumno;
import model.Titores;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AlumnoServices {

    public void crearAlumno(String nome, Titores titor) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Alumno novoAlumno = new Alumno();
            novoAlumno.setNome(nome);
            novoAlumno.setTitores(titor);
            session.save(novoAlumno);
            transaction.commit();
            System.out.println("Alumno creado correctamente: " + nome);
        } catch (Exception e) {
            System.out.println("Error al crear un alumno: " + e.getMessage());
        }
    }

    public Alumno leerAlumno(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Alumno.class, id);
        } catch (Exception e) {
            System.out.println("Error al leer o Alumno: " + e.getMessage());
            return null;
        }
    }

    public void actualizarAlumno(int id, String novoNome, Titores novoTitor) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                alumno.setNome(novoNome);
                alumno.setTitores(novoTitor);
                session.update(alumno);
                transaction.commit();
                System.out.println("Alumno actualizado correctamente: " + novoNome);
            } else {
                System.out.println("No se actualizo al Alumno con id " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar al Alumno: " + e.getMessage());
        }
    }

    public void actualizarNomeAlumno(int id, String novoNome) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                alumno.setNome(novoNome);
                session.update(alumno);
                transaction.commit();
                System.out.println("Nombre del alumno actualizado correctamente: " + novoNome);
            } else {
                System.out.println("No se actualizó al Alumno con id " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el nome del Alumno: " + e.getMessage());
        }
    }

    public void eliminarAlumno(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Alumno alumno = session.get(Alumno.class, id);
            if (alumno != null) {
                session.delete(alumno);
                transaction.commit();
                System.out.println("Alumno eliminado correctamente con id: " + id);
            } else {
                System.out.println("No se encontró al Alumno con id " + id);
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar al Alumno: " + e.getMessage());
        }
    }

    public List<Alumno> listarAlumnos() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List<Alumno> alumnoList = session.createQuery("FROM Alumno", Alumno.class).list();
            return alumnoList;
        } catch (Exception e) {
            System.out.println("Error al listar Alumnos: " + e.getMessage());
            return null;
        }
    }

    public List<Alumno> listarAlumnosPorTitor(int titorId) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List<Alumno> alumnoList = session.createQuery(
                            "FROM Alumno a WHERE a.titor.id_titor = :titorId", Alumno.class)
                    .setParameter("titorId", titorId)
                    .list();
            return alumnoList;
        } catch (Exception e) {
            System.out.println("Erroe al listar Alumnos por Titores: " + e.getMessage());
            return null;
        }
    }
}