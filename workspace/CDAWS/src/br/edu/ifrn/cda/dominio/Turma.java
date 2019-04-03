package br.edu.ifrn.cda.dominio;

public class Turma {

    public Turma() {
    }

    private String nome;
    private String codigo;
    private String turno;
    private int ano;
    
    public int getAno(){
    	return ano;
    }
    
    public void setAno(int ano){
    	this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
