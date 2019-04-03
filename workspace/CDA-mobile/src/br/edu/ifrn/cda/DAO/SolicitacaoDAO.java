package br.edu.ifrn.cda.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Servidor;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.util.Log;

public class SolicitacaoDAO {

	private static final String url = "http://192.168.1.100:8080/CDAWS/services/FacadaDAO?wsdl"; 
	private static final String namespace = "http://data.cda.ifrn.edu.br"; 
	private static final String inserirSolicitacao= "inserirSolicitacao"; 
	private static final String buscarSolicitacao = "buscarSolicitacao";
	private static final String buscarSolicitacoes = "buscarSolicitacoes";
	private static final String loginEstudante = "loginEstudante";
	private static final String loginServidor = "loginServidor";
	private static final String confirmarPresenca = "confirmarPresenca";
	private static final String deletarSolicitacoes = "deletarSolicitacoes";//temos o método cancelar?Não, mas ninguém liga
	private static final String atualizarSolicitacao = "atualizarSolicitacao";
	private static final String  buscarRefeicaoDoEstudante = "buscarRefeicaoDoEstudante";
	//private static final String logar = "logar";

	public boolean inserir(Solicitacao solicitacao) {

		SoapObject requisicao = new SoapObject(namespace, inserirSolicitacao); 
		SoapObject solicitacao1 = new SoapObject(namespace, "pedir"); 

		solicitacao1.addProperty("codSolicitacao", solicitacao.getCodSolicitacao()); 
		solicitacao1.addProperty("status", solicitacao.getStatus()); 
		solicitacao1.addProperty("matriculaAluno", solicitacao.getMatriculaAluno());
		solicitacao1.addProperty("descricao", solicitacao.getDescricao());
		
		requisicao.addSoapObject(solicitacao1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.implicitTypes = true;
		// envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(requisicao);
		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {
			Log.d("exemplo", "Aqui");
			http.call("urn:" + inserirSolicitacao, envelope);
			Log.d("exemplo", "Aqui2");
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();


		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("ERRO", e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<Solicitacao> buscarSolicitacoes(Refeicao refeicao) {

		ArrayList <Solicitacao> refeicoes = new ArrayList<Solicitacao>();

		SoapObject bSolicitacao= new SoapObject(namespace, buscarSolicitacoes);
		SoapObject solicitacao1 = new SoapObject(namespace, "refeicao"); 

		solicitacao1.addProperty("codSolicitacao", refeicao.getCodSolicitacao()); 
		bSolicitacao.addSoapObject(solicitacao1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(bSolicitacao);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(url);

		try {

			http.call("urn:" + buscarSolicitacoes, envelope);


			Vector<SoapObject> resposta = null;
			
			Object response = envelope.getResponse();
			if(response instanceof Vector){
				resposta = (Vector<SoapObject>) envelope.getResponse();
			}else if(response instanceof SoapObject){
				SoapObject soupObject = (SoapObject)response;
				resposta = new Vector<SoapObject>();
				resposta.add(soupObject);
			}

			for (SoapObject soapObj : resposta) {

				Solicitacao a = new Solicitacao();

				a.setCodSolicitacao(Integer.parseInt(soapObj.getProperty("codSolicitacao").toString()));
				a.setStatus(Integer.parseInt(soapObj.getProperty("status").toString()));
				a.setDescricaoServidor(Integer.parseInt(soapObj.getProperty("descricaoServidor").toString()));
				a.setMatriculaAluno(soapObj.getProperty("matriculaAluno").toString());
			//	a.setMatriculaAssistente(soapObj.getProperty("matriculaAssistente").toString());
				a.setNomeTurma(soapObj.getProperty("nomeTurma").toString());
				a.setNomeAluno(soapObj.getProperty("nomeAluno").toString());
				a.setDescricao(soapObj.getPropertyAsString("descricao"));
				refeicoes.add(a);

			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return refeicoes;
	}


//	public Solicitacao buscarRefeicao(Solicitacao solicitacao) {
//
//		Solicitacao a = null;
//
//		SoapObject bSolicitacao= new SoapObject(namespace, buscarSolicitacao);
//		SoapObject solicitacao1 = new SoapObject(namespace, "produto"); 
//
//		solicitacao1.addProperty("codSolicitacao", solicitacao.getCodSolicitacao()); 
//		solicitacao1.addProperty("dataAvaliacao",solicitacao.getDataAvaliacao()); 
//		//solicitacao1.addProperty("dataRefeicao", solicitacao.getDataRefeicao()); 
//		solicitacao1.addProperty("status", solicitacao.getStatus()); 
//
//		bSolicitacao.addSoapObject(solicitacao1);
//
//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
//				SoapEnvelope.VER11);
//		envelope.setOutputSoapObject(buscarSolicitacao);
//		envelope.implicitTypes = true;
//		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
//
//		// MarshalDouble md = new MarshalDouble();
//		// md.register(envelope);
//
//		HttpTransportSE http = new HttpTransportSE(url);
//
//		try {
//
//			http.call("urn:" + buscarSolicitacao, envelope);
//
//			SoapObject resposta = (SoapObject) envelope.getResponse();
//			Log.d("52aq", "aqq");
//			if (resposta != null) {
//
//				a = new Solicitacao();
//
//				a.setCodSolicitacao(Integer.parseInt(resposta.getProperty("codSolicitacao").toString()));
//				a.setMatriculaAluno(resposta.getProperty("matriculaAluno").toString());
//				a.setMatriculaServidor(resposta.getProperty("matriculaServidor").toString());
//			
//				int status = Integer.parseInt(resposta.getProperty("status").toString());
//				a.setStatus(status);
//				String dataA = resposta.getProperty("dataAvaliacao").toString();
//				a.setDataAvaliacao(Long.parseLong(dataA));
//
//				//	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//				//	Date dataAvaliacao = simpleDateFormat.parse(dataA);
//				//	a.setDataAvaliacao(dataAvaliacao);
//
//				//	String dataR = resposta.getProperty("dataRefeicao").toString();
//				//	SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
//				//	Date dataRefeicao = simpleDateFormat.parse(dataR);
//				//	a.setDataRefeicao(Long.parseLong(dataR));
//
//			}
//		}
//
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return a;
//	}

	//	public boolean deletar(Refeicao refeicao) {
	//
	//		SoapObject deletar1 = new SoapObject(namespace, deletarProduto); 
	//		SoapObject refeicao1 = new SoapObject(namespace, "produto"); 
	//		
	//		refeicao1.addProperty("codSolicitacao",refeicao.getCodSolicitacao()); 
	//		refeicao1.addProperty("dataAvaliacao", refeicao.getDataAvaliacao()); 
	//		refeicao1.addProperty("dataRefeicao", refeicao.getDataRefeicao()); 
	//		refeicao1.addProperty("libera",refeicao.getLibera()); 
	//		refeicao1.addProperty("tipoRefeicao",refeicao.getTipoRefeicao());
	//		deletar1.addSoapObject(refeicao1);
	//
	//		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
	//				SoapEnvelope.VER11);
	//
	//		envelope.implicitTypes = true;
	//		// envelope.encodingStyle = SoapSerializationEnvelope.XSD;
	//		envelope.setOutputSoapObject(deletar1);
	//		// MarshalDouble md = new MarshalDouble();
	//		// md.register(envelope);
	//
	//		HttpTransportSE http = new HttpTransportSE(url);
	//
	//		try {
	//			Log.d("exemplo", "Aqui");
	//			http.call("urn:" + deletarProduto, envelope);
	//			Log.d("exemplo", "Aqui2");
	//			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();
	//
	//			return Boolean.parseBoolean(resposta.toString());
	//
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			Log.i("ERRO", e.getMessage());
	//			e.printStackTrace();
	//			return false;
	//		}
	//	}
	//	

	public boolean atualizar(Solicitacao solicitacao) {

		SoapObject atualizar1 = new SoapObject(namespace, atualizarSolicitacao); 
		SoapObject solicitacao1 = new SoapObject(namespace, "solicitacao"); 

		solicitacao1.addProperty("codSolicitacao",solicitacao.getCodSolicitacao()); 
		solicitacao1.addProperty("status",solicitacao.getStatus()); 
		solicitacao1.addProperty("descricaoServidor", solicitacao.getDescricaoServidor());
		solicitacao1.addProperty("matriculaAluno",solicitacao.getMatriculaAluno()); 
		solicitacao1.addProperty("matriculaServidor",solicitacao.getMatriculaServidor()); 


		atualizar1.addSoapObject(solicitacao1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.implicitTypes = true;
		// envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(atualizar1);
		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {
			Log.d("exemplo", "Aqui");
			http.call("urn:" + atualizarSolicitacao, envelope);
			Log.d("exemplo", "Aqui2");
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(resposta.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("ERRO", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	public boolean confirmarPresenca(Solicitacao solicitacao) {

		SoapObject confirmar1 = new SoapObject(namespace, confirmarPresenca); 
		SoapObject solicitacao1 = new SoapObject(namespace, "solicitacao"); 

		solicitacao1.addProperty("horarioRefeicao",solicitacao.getHorarioRefeicao()); 
		solicitacao1.addProperty("codSolicitacao",solicitacao.getCodSolicitacao()); 
		solicitacao1.addProperty("status",solicitacao.getStatus()); 
		solicitacao1.addProperty("matriculaAluno",solicitacao.getMatriculaAluno()); 
		solicitacao1.addProperty("matriculaServidor",solicitacao.getMatriculaServidor()); 


		confirmar1.addSoapObject(solicitacao1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.implicitTypes = true;
		// envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(confirmar1);
		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {
			Log.d("exemplo", "Aqui");
			http.call("urn:" + confirmarPresenca, envelope);
			Log.d("exemplo", "Aqui2");
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(resposta.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("ERRO", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public Estudante loginEstudante(Estudante e) {

		Estudante a = null;

		SoapObject bEstudante = new SoapObject(namespace, loginEstudante);
		SoapObject estudante1 = new SoapObject(namespace, "estudante"); 

		estudante1.addProperty("matriculaEstudante", e.getMatriculaEstudante()); 
		estudante1.addProperty("senha", e.getSenha()); 
		

		bEstudante.addSoapObject(estudante1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(bEstudante);
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;

		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {

			http.call("urn:" + loginEstudante, envelope);

			SoapObject resposta = (SoapObject) envelope.getResponse();
			Log.d("52aq", "aqq");
			if (resposta != null) {

				a = new Estudante();
				a.setMatriculaEstudante(resposta.getProperty("matriculaEstudante").toString());
				a.setNome(resposta.getProperty("nome").toString());

			}
		}

		catch (Exception est) {
			//Log.e("ERRO", est.getMessage());
		}
		return a;
	}
	
	public Servidor loginServidor(Servidor servidor) {

		Servidor a = null;

		SoapObject bAssistente = new SoapObject(namespace, loginServidor);
		SoapObject assistente1 = new SoapObject(namespace, "servidor"); 

		assistente1.addProperty("matricula", servidor.getMatricula());
		assistente1.addProperty("senha", servidor.getSenha());
		bAssistente.addSoapObject(assistente1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(bAssistente);
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;

		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {

			http.call("urn:" + loginServidor, envelope);

			SoapObject resposta = (SoapObject) envelope.getResponse();
			Log.d("52aq", "aqq");
			if (resposta != null) {

				a = new Servidor();

				a.setMatricula(resposta.getProperty("matricula").toString());
				a.setNome(resposta.getProperty("nome").toString());
				
			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	public ArrayList<Solicitacao> buscarRefeicaoDoEstudante(String matricula) {
		ArrayList <Solicitacao> refeicoes = new ArrayList<Solicitacao>();

		SoapObject bRefeicao = new SoapObject(namespace, buscarRefeicaoDoEstudante);
		bRefeicao.addProperty("matricula", matricula);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(bRefeicao);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(url);


		try {

			http.call("urn:" + buscarRefeicaoDoEstudante, envelope);
			Vector<SoapObject> resposta = null;
			Object response = envelope.getResponse();
			if(response instanceof Vector){
				resposta = (Vector<SoapObject>) envelope.getResponse();
			}else if(response instanceof SoapObject){
				SoapObject soupObject = (SoapObject)response;
				resposta = new Vector<SoapObject>();
				resposta.add(soupObject);
			}

			//if (resposta1 != null) {
			for(SoapObject soupObject : resposta){


				Solicitacao a= new Solicitacao();
				a.setCodSolicitacao(Integer.parseInt(soupObject.getProperty("codSolicitacao").toString()));
				a.setMatriculaAluno(soupObject.getProperty("matriculaAluno").toString());
				a.setStatus(Integer.parseInt(soupObject.getProperty("status").toString()));
				a.setDescricaoServidor(Integer.parseInt(soupObject.getProperty("descricaoServidor").toString()));
				a.setNomeTurma(soupObject.getProperty("nomeTurma").toString());
				a.setNomeAluno(soupObject.getProperty("nomeAluno").toString());
				a.setDataRefeicao(Long.parseLong(soupObject.getProperty("dataRefeicao").toString()));
				a.setTipoRefeicao(Integer.parseInt(soupObject.getProperty("tipoRefeicao").toString()));
				refeicoes.add(a);

			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return refeicoes;
	}
	/*public boolean logar(Aluno aluno) {

		SoapObject inserir1 = new SoapObject(namespace, logar);
		SoapObject aluno1 = new SoapObject(namespace, "aluno");

		aluno1.addProperty("matricula", aluno.getMatricula());
		aluno1.addProperty("imeiCelular", aluno.getImeiCelular());

		inserir1.addSoapObject(aluno1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.implicitTypes = true;
		// envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		envelope.setOutputSoapObject(inserir1);
		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {
			http.call("urn:" + logar, envelope);
			SoapPrimitive resposta = (SoapPrimitive) envelope.getResponse();

			return Boolean.parseBoolean(resposta.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("ERRO", e.getMessage());
			e.printStackTrace();
			return false;
		}
	}*/
}
