package br.edu.ifrn.cda.dominio;

public class Solicitacao {


	private int codSolicitacao; 
	private long dataAvaliacao;
	private long dataRefeicao;
	private int status;
	private String matriculaAluno;
	private String matriculaServidor;
	private String descricao;
	private String nomeAluno;
	private String nomeTurma;
	private long horarioRefeicao;
	private int tipoRefeicao;
	private int descricaoServidor;
	
	public Solicitacao() {
    }
	public void setTipoRefeicao(int tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
	public int getTipoRefeicao() {
		return tipoRefeicao;
	}
	
	public long getHorarioRefeicao(){
		return horarioRefeicao;
	}
	
	public void setHorarioRefeicao(long horarioRefeicao){
		this.horarioRefeicao = horarioRefeicao;
	}
	

	public int getCodSolicitacao() {
		return codSolicitacao;
	}


	public void setCodSolicitacao(int codSolicitacao) {
		this.codSolicitacao = codSolicitacao;
	}


	public long getDataAvaliacao() {
		return dataAvaliacao;
	}


	public void setDataAvaliacao(long dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	
	


	public long getDataRefeicao() {
		return dataRefeicao;
	}

	public void setDataRefeicao(long dataRefeicao) {
		this.dataRefeicao = dataRefeicao;
	}

	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getMatriculaAluno() {
		return matriculaAluno;
	}


	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}


	public String getMatriculaServidor() {
		return matriculaServidor;
	}


	public void setMatriculaServidor(String matriculaServidor) {
		this.matriculaServidor = matriculaServidor;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNomeAluno() {
		return nomeAluno;
	}


	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}


	public String getNomeTurma() {
		return nomeTurma;
	}


	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	public int getDescricaoServidor() {
		return descricaoServidor;
	}
	public void setDescricaoServidor(int descricaoServidor) {
		this.descricaoServidor = descricaoServidor;
	}
	
	
}
