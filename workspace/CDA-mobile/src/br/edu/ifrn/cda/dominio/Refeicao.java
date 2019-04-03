package br.edu.ifrn.cda.dominio;

import java.io.Serializable;

public class Refeicao implements Serializable {

    public Refeicao() {
    }
    private long dataRefeicao;
    private int libera;
    private long dataAvaliacao;
    private int codSolicitacao;
    private int tipoRefeicao;
    private String matriculaServidor;
    
    public String getMatriculaServidor() {
        return matriculaServidor;
    }
    

    public void setMatriculaServidor(String matriculaServidor) {
        this.matriculaServidor = matriculaServidor;
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

    public void setCodSolicitacao(int codSolitacao) {
        this.codSolicitacao = codSolitacao;
    }

    /**
     * @return the dRefeicao
     */
    public long getDataRefeicao() {
        return dataRefeicao;
    }

    /**
     * @param dRefeicao the dRefeicao to set
     */
    public void setDataRefeicao(long dataRefeicao) {
        this.dataRefeicao = dataRefeicao;
    }

    /**
     * @return the libera
     */
    public int getLibera() {
        return libera;
    }

    /**
     * @param libera the libera to set
     */
    public void setLibera(int libera) {
        this.libera = libera;
    }

    /**
     * @return the tipoRefeicao
     */
    public int getTipoRefeicao() {
        return tipoRefeicao;
    }

    /**
     * @param tipoRefeicao the tipoRefeicao to set
     */
    public void setTipoRefeicao(int tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

	
}
