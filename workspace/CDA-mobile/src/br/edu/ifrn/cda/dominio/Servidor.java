package br.edu.ifrn.cda.dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Servidor implements Serializable{
	 
	private String matricula;
	private String nome;
	private String senha;
	private String email;
    
	public Servidor() {
    }
	
	public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
	
	 private ArrayList<Disciplina> disciplinas;

	   
	    public ArrayList<Disciplina> getDisciplinas() {
	        return disciplinas;
	    }

	    
	    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
	        this.disciplinas = disciplinas;
	    }
	
}
