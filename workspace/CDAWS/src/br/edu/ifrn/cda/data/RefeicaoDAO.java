
package br.edu.ifrn.cda.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;


import br.edu.ifrn.cda.dominio.Refeicao;





public class RefeicaoDAO {

	public boolean inserir(Refeicao r){

		boolean resultado = false;
		String inserir = "INSERT INTO Refeicao(data_Refeicao, Libera, data_Avaliacao, id_Trefeicao) VALUES(?,?,?,?)";


		Connection con = Conexao.conectar();

		try {
			//manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(inserir);
			comando.setDate(1, new Date( r.getDataRefeicao() )); 
			comando.setInt(2, r.getLibera());
			comando.setTimestamp(3, new Timestamp( r.getDataAvaliacao() )); 
			comando.setInt(4, r.getTipoRefeicao());

			comando.execute();
			resultado = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}


	public boolean atualizar(Refeicao r){

		boolean resultado = false;

		String atualizar = "UPDATE Refeicao set Libera = ? , data_Avaliacao = ?, data_Refeicao = ?, id_Trefeicao = ? WHERE cod_Solicitacao = ?   ";


		Connection con = Conexao.conectar();

		try {
			//manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(atualizar);
			comando.setInt(1, r.getLibera());
			comando.setInt(2, r.getLibera());
			comando.setTimestamp(3, new Timestamp( r.getDataRefeicao() )); 
			comando.setTimestamp(4, new Timestamp( r.getDataAvaliacao() )); 
			comando.setInt(5, r.getCodSolicitacao());
			

			comando.execute();
			resultado = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}


		return resultado;
	}

	public Refeicao buscarRefeicao(Refeicao r) {

		Refeicao objRefeicao = null;

		String queryInserir = "SELECT data_Refeicao, cod_Solicitacao, Libera, data_Avaliacao, id_Trefeicao FROM Refeicao WHERE cod_Solicitacao = ?; ";


		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setInt(1, r.getCodSolicitacao());
			
			
			ResultSet tuplasBD = comando.executeQuery();

			if(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				objRefeicao = new Refeicao();

				objRefeicao.setDataRefeicao( tuplasBD.getDate("data_refeicao").getTime() );
				objRefeicao.setMatriculaServidor( tuplasBD.getString("matriculaServidor") );
				objRefeicao.setCodSolicitacao(tuplasBD.getInt("cod_Solicitacao"));
				objRefeicao.setTipoRefeicao(tuplasBD.getInt("id_Trefeicao"));
				objRefeicao.setDataAvaliacao( tuplasBD.getDate("data_Avaliacao").getTime() );
				objRefeicao.setLibera(tuplasBD.getInt("Libera"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return objRefeicao;
	}

	public ArrayList<Refeicao> buscarRefeicoes() {

		ArrayList<Refeicao> lista = new  ArrayList<Refeicao>();

		//boolean resultado = false;


		String queryInserir = "SELECT cod_Solicitacao, id_Trefeicao, data_Refeicao, Libera , data_Avaliacao from Refeicao;";


		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(queryInserir);
			
			
			ResultSet tuplasBD = comando.executeQuery();

			while(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				Refeicao objRefeicao2 = new Refeicao();

				objRefeicao2.setDataRefeicao( tuplasBD.getDate("data_Refeicao").getTime() );
				objRefeicao2.setCodSolicitacao(tuplasBD.getInt("cod_Solicitacao"));
				objRefeicao2.setDataAvaliacao( tuplasBD.getDate("data_Avaliacao").getTime() );
				objRefeicao2.setLibera(tuplasBD.getInt("Libera"));
				objRefeicao2.setTipoRefeicao(tuplasBD.getInt("id_Trefeicao"));
				lista.add(objRefeicao2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}
		return lista;
	}
	
}
