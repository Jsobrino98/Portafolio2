package Modelo;

public class Videojuegos {
    private int id;
    private String titulo;
    private String genero;
    private String plataforma;
    private int copias_disponibles;

    public Videojuegos() {
    }

    public Videojuegos(int id, String titulo, String genero, String plataforma, int copias_disponibles) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.copias_disponibles = copias_disponibles;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getCopias_disponibles() {
        return copias_disponibles;
    }

    public void setCopias_disponibles(int copias_disponibles) {
        this.copias_disponibles = copias_disponibles;
    }

    @Override
    public String toString() {
        return "Videojuegos{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", copias_disponibles=" + copias_disponibles +
                '}';
    }
}
