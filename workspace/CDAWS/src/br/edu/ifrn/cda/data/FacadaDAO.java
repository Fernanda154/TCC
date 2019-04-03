package br.edu.ifrn.cda.data;

import java.util.List;

import org.apache.axis2.jaxws.util.SoapUtils;

import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Servidor;
import br.edu.ifrn.cda.dominio.Solicitacao;

public class FacadaDAO {

	public boolean inserirRefeicao(Refeicao refeicao) {

		RefeicaoDAO objDAO = new RefeicaoDAO();
		return objDAO.inserir(refeicao);
	}
	public boolean atualizarRefeicao(Refeicao refeicao) {
		
		RefeicaoDAO objDAO = new RefeicaoDAO();
		return objDAO.inserir(refeicao);
	}
	public boolean buscarRefeicao(Refeicao refeicao) {

		RefeicaoDAO objDAO = new RefeicaoDAO();
		return objDAO.inserir(refeicao);
	}
	
	public Estudante loginEstudante(Estudante estudante) {

		SolicitacaoDAO objDAO = new SolicitacaoDAO();
		System.out.println("Tentando logar");
		return objDAO.loginEstudante(estudante);
		
	}
	public Servidor loginServidor(Servidor servidor) {

		SolicitacaoDAO objDAO = new SolicitacaoDAO();
		return objDAO.loginServidor(servidor);
	}
	public List<Refeicao> buscarRefeicoes() {
	
		System.out.println("Chamando método buscar refeições");
		
		RefeicaoDAO objDAO = new RefeicaoDAO();
		
		return objDAO.buscarRefeicoes();
	}
	
	public boolean inserirSolicitacao(Solicitacao pedir) {
		SolicitacaoDAO objSDAO = new SolicitacaoDAO();
		return objSDAO.inserir(pedir);
	}

	public boolean  atualizarSolicitacao(Solicitacao solicitacao) {
		System.out.println("Chamando método atualizar solicitação");
		SolicitacaoDAO objSDAO = new SolicitacaoDAO();
		return objSDAO.atualizar(solicitacao);
	}
	public boolean  confirmarPresenca(Solicitacao solicitacao) {
		System.out.println("Chamando método confirmar presença");
		SolicitacaoDAO objSDAO = new SolicitacaoDAO();
		return objSDAO.confirmarPresenca(solicitacao);
	}
	public List<Solicitacao> buscarSolicitacoes(Refeicao refeicao) {
		System.out.println("Chamando método buscar solicitações");
		SolicitacaoDAO objSDAO = new SolicitacaoDAO();
		return objSDAO.buscarSolicitacoes(refeicao) ;
	}

	public List<Solicitacao> buscarRefeicaoDoEstudante(String matricula){
		
		System.out.println("Chamando método buscarRefeicaoDoEstudante");
		
		SolicitacaoDAO refeicaoE = new SolicitacaoDAO();
		return refeicaoE.buscarRefeicaoDoEstudante(matricula);
	}

}
