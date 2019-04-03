package br.edu.ifrn.cda.DAO;


import java.util.ArrayList;

import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.edu.ifrn.cda.dominio.Refeicao;
import android.util.Log;
public class RefeicaoDAO {


	private static final String url = "http://192.168.1.100:8080/CDAWS/services/FacadaDAO?wsdl"; 
	private static final String namespace = "http://data.cda.ifrn.edu.br"; 
	private static final String inserirRefeicao= "inserirRefeicao"; 
	private static final String buscarRefeicao = "buscarRefeicao";
	private static final String  buscarRefeicoes = "buscarRefeicoes";
	private static final String deletarProduto = "deletarProduto";//temos o método cancelar?
	private static final String atualizarRefeicao = "atualizarRefeicao";
	//private static final String logar = "logar";

	public boolean inserir(Refeicao refeicao) {

		SoapObject requisicao = new SoapObject(namespace, inserirRefeicao); 
		SoapObject refeicao1 = new SoapObject(namespace, "refeicao"); 

		
		refeicao1.addProperty("dataAvaliacao", refeicao.getDataAvaliacao()); 
		refeicao1.addProperty("dataRefeicao", refeicao.getDataRefeicao()); 
		refeicao1.addProperty("libera", refeicao.getLibera()); 
		refeicao1.addProperty("tipoRefeicao", refeicao.getTipoRefeicao());

		requisicao.addSoapObject(refeicao1);

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
			http.call("urn:" + inserirRefeicao, envelope);
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

	public Refeicao buscarRefeicao(Refeicao refeicao) {

		Refeicao a = null;

		SoapObject bRefeicao = new SoapObject(namespace, buscarRefeicao);
		SoapObject refeicao1 = new SoapObject(namespace, "produto"); 

		refeicao1.addProperty("codSolicitacao", refeicao.getCodSolicitacao()); 
		refeicao1.addProperty("dataAvaliacao", refeicao.getDataAvaliacao()); 
		refeicao1.addProperty("dataRefeicao", refeicao.getDataRefeicao()); 
		refeicao1.addProperty("libera", refeicao.getLibera()); 
		refeicao1.addProperty("tipoRefeicao", refeicao.getTipoRefeicao()); 

		bRefeicao.addSoapObject(refeicao1);

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(buscarRefeicao);
		envelope.implicitTypes = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;

		// MarshalDouble md = new MarshalDouble();
		// md.register(envelope);

		HttpTransportSE http = new HttpTransportSE(url);

		try {

			http.call("urn:" + buscarRefeicao, envelope);

			SoapObject resposta = (SoapObject) envelope.getResponse();
			Log.d("52aq", "aqq");
			if (resposta != null) {

				a = new Refeicao();

				a.setCodSolicitacao(Integer.parseInt(resposta.getProperty("cod_Solicitacao").toString()));

				//	SoapObject tipo =  (SoapObject) resposta.getProperty("tipoRefeicao");

				//Dados da classe TipoRefeicao do SoapObject que está dentro de Refeição
				//	String descricao = tipo.getProperty("descricao").toString();
				//	int id = Integer.parseInt( tipo.getProperty("id").toString() );

				//	TipoRefeicao tipoRefeicao = new TipoRefeicao();
				//	tipoRefeicao.setDescricao(descricao);
				//	tipoRefeicao.setId(id);

				//	a.setTipoRefeicao(tipoRefeicao);

				int libera = Integer.parseInt( resposta.getProperty("libera").toString() );
				String dataA = resposta.getProperty("dataAvaliacao").toString();
				long data = Long.parseLong(dataA);
				a.setDataAvaliacao(data);
				a.setLibera(libera);
				String dataR = resposta.getProperty("dataRefeicao").toString();
				long data1 = Long.parseLong(dataR);
				a.setDataRefeicao(data1);

			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}


	public ArrayList <Refeicao> buscarRefeicoes() {

		ArrayList <Refeicao> refeicoes = new ArrayList<Refeicao>();

		SoapObject bRefeicao = new SoapObject(namespace, buscarRefeicoes);


		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(bRefeicao);
		envelope.implicitTypes = true;

		HttpTransportSE http = new HttpTransportSE(url);


		try {

			http.call("urn:" + buscarRefeicoes, envelope);

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


				int libera = Integer.parseInt( soupObject.getProperty("libera").toString() );
				String dataA = soupObject.getProperty("dataAvaliacao").toString();
				long data = Long.parseLong(dataA);
				String dataR = soupObject.getProperty("dataRefeicao").toString();
				long data1 = Long.parseLong(dataR);


				Refeicao a= new Refeicao();
				a.setCodSolicitacao(Integer.parseInt(soupObject.getProperty("codSolicitacao").toString()));
				a.setDataAvaliacao(data);
				a.setLibera(libera);
				a.setDataRefeicao(data1);


				int id = Integer.parseInt(soupObject.getProperty("tipoRefeicao").toString());
				a.setTipoRefeicao(id);

				refeicoes.add(a);

			}
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return refeicoes;
	}

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
	//			
	//
	//		} catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			Log.i("ERRO", e.getMessage());
	//			e.printStackTrace();
	//			return false;
	//		}
	//	}
	//	

	public boolean atualizar(Refeicao refeicao) {

		SoapObject atualizar1 = new SoapObject(namespace, atualizarRefeicao); 
		SoapObject refeicao1 = new SoapObject(namespace, "Refeicao"); 

		refeicao1.addProperty("codSolicitacao",refeicao.getCodSolicitacao()); 
		refeicao1.addProperty("dataAvaliacao", refeicao.getDataAvaliacao()); 
		refeicao1.addProperty("dataRefeicao", refeicao.getDataRefeicao()); 
		refeicao1.addProperty("libera",refeicao.getLibera()); 
		refeicao1.addProperty("tipoRefeicao",refeicao.getTipoRefeicao()); 


		atualizar1.addSoapObject(refeicao1);

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
			http.call("urn:" + atualizarRefeicao, envelope);
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
