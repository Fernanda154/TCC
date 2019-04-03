package br.edu.ifrn.cda.dominio;



public class Estudante  {

    public Estudante() {
    }

    private String cidade;
    private long dataNascimento;
    private String matriculaEstudante;
    private String nome;
    private String senha;
    private String email;
    
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public long getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(long dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getMatriculaEstudante() {
		return matriculaEstudante;
	}
	public void setMatriculaEstudante(String matriculaEstudante) {
		this.matriculaEstudante = matriculaEstudante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}   
}
