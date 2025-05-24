/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Joao
 */
public class TransacaoM {
    private int id;
    private String descricao;
    private double valor;
    private String data; // Formato recomendado: "yyyy-MM-dd"
    private int categoriaId;
    private int contaId;
    private int usuarioId;

    public TransacaoM(int id, String descricao, double valor, String data, int categoriaId, int contaId, int usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.categoriaId = categoriaId;
        this.contaId = contaId;
        this.usuarioId = usuarioId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getContaId() {
        return contaId;
    }

    public void setContaId(int contaId) {
        this.contaId = contaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    
}
