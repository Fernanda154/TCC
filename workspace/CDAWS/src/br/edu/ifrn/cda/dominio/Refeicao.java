package br.edu.ifrn.cda.dominio;

public class Refeicao {

	private long dataRefeicao;
	private int libera;
	private long dataAvaliacao;
	private String matriculaServidor;
	private int codSolicitacao;
	private int tipoRefeicao;

	public Refeicao() {
	}
	
	public String getMatriculaServidor() {
		return matriculaServidor;
	}

	public void setMatriculaServidor(String matriculaServidor) {
		this.matriculaServidor = matriculaServidor;
	}

	public long getDataRefeicao() {
		return dataRefeicao;
	}

	public void setDataRefeicao(long dataRefeicao) {
		this.dataRefeicao = dataRefeicao;
	}

	public int getLibera() {
		return libera;
	}

	public void setLibera(int libera) {
		this.libera = libera;
	}

	public long getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(long dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public int getCodSolicitacao() {
		return codSolicitacao;
	}

	public void setCodSolicitacao(int codSolicitacao) {
		this.codSolicitacao = codSolicitacao;
	}

	public int getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(int tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
}
