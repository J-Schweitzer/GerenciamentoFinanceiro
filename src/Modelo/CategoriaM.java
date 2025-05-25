package Modelo;

/**
 * Classe que representa uma Categoria no sistema de gerenciamento de gastos.
 */
public class CategoriaM {
    private int id; // Identificador único da categoria
    private String nome; // Nome da categoria
    private String tipo; // Tipo da categoria (ex: "Receita", "Despesa")
    private int usuarioId; // ID do usuário que possui a categoria

    // Construtor
    public CategoriaM(int id, String nome, String tipo, int usuarioId) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.tipo + ")"; // Representação da categoria
    }
}
