package org.example.services;

import org.example.config.OpenApiConfig;
import org.example.model.Titores;
import org.example.model.Alumno;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TitoresServices {

    public void crearTitor(String nome, String apelidos) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Titores novoTitor = new Titores();
            novoTitor.setNome(nome);
            novoTitor.setApelidos(apelidos);
            session.save(novoTitor);
            transaction.commit();
            System.out.println("Titor creado correctamente: " + nome + " " + apelidos);
        } catch (Exception e) {
            System.out.println("Erro ao crear o Titores: " + e.getMessage());
        }
    }

    public Titores lerTitor(int id) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            return session.get(Titores.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o Titores: " + e.getMessage());
            return null;
        }
    }

    public Titores lerTitorConAlumnos(int id) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            Titores titor = session.get(Titores.class, id);
            if (titor != null) {
                System.out.println("Titor encontrado: " + titor.getNome() + " " + titor.getApelidos());
                if (titor.getAlumnos() != null && !titor.getAlumnos().isEmpty()) {
                    System.out.println("Alumnos deste titor:");
                    for (Alumno alumno : titor.getAlumnos()) {
                        System.out.println(" - " + alumno.getNome());
                    }
                } else {
                    System.out.println("Este titor non ten alumnos asignados.");
                }
            }
            return titor;
        } catch (Exception e) {
            System.out.println("Erro ao ler o Titores cos seus alumnos: " + e.getMessage());
            return null;
        }
    }

    public void actualizarTitor(int id, String novoNome, String novosApelidos) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Titores titor = session.get(Titores.class, id);
            if (titor != null) {
                titor.setNome(novoNome);
                titor.setApelidos(novosApelidos);
                session.update(titor);
                transaction.commit();
                System.out.println("Titor actualizado correctamente: " + novoNome + " " + novosApelidos);
            } else {
                System.out.println("Non se atopou o Titores co id " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o Titores: " + e.getMessage());
        }
    }

    public void actualizarNomeTitor(int id, String novoNome) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Titores titor = session.get(Titores.class, id);
            if (titor != null) {
                titor.setNome(novoNome);
                session.update(titor);
                transaction.commit();
                System.out.println("Nome do titor actualizado correctamente: " + novoNome);
            } else {
                System.out.println("Non se atopou o Titores co id " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o nome do Titores: " + e.getMessage());
        }
    }

    public void eliminarTitor(int id) {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Titores titor = session.get(Titores.class, id);
            if (titor != null) {
                session.delete(titor);
                transaction.commit();
                System.out.println("Titor eliminado correctamente co id: " + id);
            } else {
                System.out.println("Non se atopou o Titores co id " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o Titores: " + e.getMessage());
        }
    }

    public List<Titores> listarTitores() {
        try (Session session = OpenApiConfig.getSessionFactory().openSession()) {
            List<Titores> titorList = session.createQuery("FROM Titores", Titores.class).list();
            return titorList;
        } catch (Exception e) {
            System.out.println("Erro ao listar Titores: " + e.getMessage());
            return null;
        }
    }
}