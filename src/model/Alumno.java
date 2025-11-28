package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_alumno;
    @Column(name = "nome")
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_titor")
    private Titores titor;

    public Alumno(String nome, Titores titor) {
        this.id_alumno = id_alumno;
        this.nome = nome;
        this.titor = titor;
    }
    public Alumno(){}

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Titores getTitores() {
        return titor;
    }

    public void setTitores(Titores titor) {
        this.titor = titor;
    }
}

