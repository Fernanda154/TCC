package br.edu.ifrn.cda.dominio;

import java.io.Serializable;

import android.text.Editable;

public class Solicitacao implements Serializable {
	
	public Solicitacao() {
    }
	
	
	private int codSolicitacao; 
	private long dataAvaliacao;
	private long dataRefeicao;
	private int status;
	private String matriculaAluno;
	private String matriculaServidor;
	private String descricao;
	private String nomeAluno;
	private String nomeTurma;
	private int tipoRefeicao;
	private long horarioRefeicao;
	private int descricaoServidor;
	
	public int getDescricaoServidor() {
		return descricaoServidor;
	}
	public void setDescricaoServidor(int descricaoServidor) {
		this.descricaoServidor = descricaoServidor;
	}
	public int getTipoRefeicao() {
		return tipoRefeicao;
	}
	public void setTipoRefeicao(int tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}
	public long getHorarioRefeicao(){
		return horarioRefeicao;
	}
	
	public void setHorarioRefeicao(long horarioRefeicao){
		this.horarioRefeicao = horarioRefeicao;
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
	public void setDataRefeicao(long dataRefeicao) {
		this.dataRefeicao = dataRefeicao;
	}
	public long getDataRefeicao(){
		return dataRefeicao;
	}
	
	public String getDescricao(){
		return descricao;
	}
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	public long getDataAvaliacao() {
		
		return dataAvaliacao;
		
	}
	public int getCodSolicitacao() {
		
		return codSolicitacao;
	}
	public int getStatus() {
		
		return status;
	}
	public String getMatriculaAluno() {
		
		return matriculaAluno;
	}
	public String getMatriculaServidor() {
		
		return matriculaServidor;
	}
	
	public void setMatriculaAluno(String matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
		
	}
	public void setCodSolicitacao(int codSolicitacao) {
		this.codSolicitacao = codSolicitacao;
		
	}
	public void setDataAvaliacao(long dataAvaliacao) {
		
		this.dataAvaliacao = dataAvaliacao;
	}
	public void setStatus(int status) {
		this.status = status;
		
	}
	public void setMatriculaServidor(String matriculaServidor) {
		this.matriculaServidor = matriculaServidor;
		
	}
}
