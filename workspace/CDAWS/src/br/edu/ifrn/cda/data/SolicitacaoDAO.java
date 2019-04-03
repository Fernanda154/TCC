package br.edu.ifrn.cda.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Servidor;
import br.edu.ifrn.cda.dominio.Solicitacao;

public class SolicitacaoDAO {
	public boolean inserir(Solicitacao pedir){

		boolean resultado = false;
		String inserir = "INSERT INTO Solicitacao(cod_Solicitacao,  matricula_Estudante, status, descricao, descricao_Servidor) VALUES(?,?,?,?,?)";


		Connection con = Conexao.conectar();

		try {
			//manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(inserir);
			
			comando.setInt(1, pedir.getCodSolicitacao());
			comando.setString(2, pedir.getMatriculaAluno());
			comando.setInt(3, pedir.getStatus());
			comando.setString(4, pedir.getDescricao());
			comando.setInt(5, pedir.getDescricaoServidor());
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

	public boolean confirmarPresenca(Solicitacao solicitacao){

		boolean resultado = false;

		String atualizar = "UPDATE Solicitacao set horarioRefeicao = ?, status = ?  WHERE cod_solicitacao = ? and matricula_Estudante = ?;";

		Connection con = Conexao.conectar();
		
		System.out.println(solicitacao.getStatus() + " " + solicitacao.getCodSolicitacao() + " "+ solicitacao.getMatriculaAluno() );
 
		try {
			//manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(atualizar);
			
			comando.setTimestamp(1, new Timestamp( System.currentTimeMillis() )); 
			comando.setInt(2,solicitacao.getStatus());
			comando.setInt(3,  solicitacao.getCodSolicitacao());
			comando.setString(4, solicitacao.getMatriculaAluno());
			
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
	public boolean atualizar(Solicitacao solicitacao){

		boolean resultado = false;

		String atualizar = "UPDATE Solicitacao set data_Avaliacao = ?, matricula_Servidor = ?,  status = ?, descricao_Servidor = ?  WHERE cod_solicitacao = ? and matricula_Estudante = ?;";

		Connection con = Conexao.conectar();

		try {
			//manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(atualizar);
			
			comando.setTimestamp(1, new Timestamp( System.currentTimeMillis() )); 
			comando.setString(2,solicitacao.getMatriculaServidor());
			comando.setInt(3, solicitacao.getStatus());
			comando.setInt(4,  solicitacao.getDescricaoServidor());
			comando.setInt(5,  solicitacao.getCodSolicitacao());
			comando.setString(6, solicitacao.getMatriculaAluno());
			
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

	public ArrayList<Solicitacao> buscarSolicitacoes(Refeicao refeicao) {

		ArrayList<Solicitacao> lista = new  ArrayList<Solicitacao>();

		//boolean resultado = false;
		Refeicao objRefeicao = null;

		String queryInserir = "SELECT t1.cod_solicitacao, t1.matricula_Estudante, t1.status, t1.descricao_Servidor, t1.matricula_Servidor, t1.descricao, t2.nome as NomeEst, t4.Nome as turmaEst from solicitacao t1 " +
" inner join estudante t2 on (t1.matricula_Estudante = t2.matricula_estudante) " +
"inner join turma_estudante t3 on (t2.matricula_Estudante = t3.matricula_Estudante) " +
"inner join turma t4 on (t3.Codigo = t4.Codigo) " +
"inner join tipo_status t5 on (t1.status = t5.codigo_Status) "+
"where t1.cod_Solicitacao = ?; ";


		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setInt(1, refeicao.getCodSolicitacao());
			
			
			ResultSet tuplasBD = comando.executeQuery();

			while(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				Solicitacao objS = new Solicitacao();

				objS.setMatriculaAluno( tuplasBD.getString("matricula_Estudante") );
				objS.setCodSolicitacao(tuplasBD.getInt("cod_solicitacao"));
				objS.setNomeTurma(tuplasBD.getString("turmaEst"));
				objS.setNomeAluno(tuplasBD.getString("NomeEst"));
				objS.setDescricao(tuplasBD.getString("descricao"));
//				objS.setDataAvaliacao( tuplasBD.getDate("data_Avaliacao").getTime());
//				objS.setLibera(tuplasBD.getBoolean("Libera"));
				objS.setMatriculaServidor( tuplasBD.getString("matricula_Servidor") );
				objS.setStatus( tuplasBD.getInt("status") );
				objS.setDescricaoServidor(tuplasBD.getInt("descricao_Servidor"));

				lista.add(objS);

				//comando.execute();
				//resultado = true;
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
	public ArrayList<Solicitacao> buscarRefeicaoDoEstudante(String matricula) {
		ArrayList<Solicitacao> solicitacoes = new ArrayList<>();
		String queryInserir = "SELECT solicitacao.cod_Solicitacao, solicitacao.matricula_Estudante, Estudante.nome, solicitacao.status, solicitacao.descricao_Servidor,  turma.nome as nomeTurma, Refeicao.data_Refeicao, Refeicao.id_Trefeicao " +
"from solicitacao inner join Refeicao on (Refeicao.cod_Solicitacao = Solicitacao.cod_Solicitacao) inner join turma_estudante on (solicitacao.matricula_Estudante = turma_estudante.matricula_Estudante) " +
"inner join turma on (turma_estudante.codigo = turma.codigo) inner join Estudante on (solicitacao.matricula_Estudante = Estudante.matricula_Estudante) inner join tipo_status on(tipo_status.codigo_Status = solicitacao.status) where solicitacao.matricula_Estudante = ?;";

		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interrogação

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setString(1, matricula);
			
			
			ResultSet tuplasBD = comando.executeQuery();

			while(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				 Solicitacao solicitacao = new Solicitacao();
				 solicitacao.setCodSolicitacao(tuplasBD.getInt("cod_Solicitacao"));
				 solicitacao.setMatriculaAluno( tuplasBD.getString("matricula_Estudante"));
				 solicitacao.setStatus(tuplasBD.getInt("status"));
				 solicitacao.setNomeTurma(tuplasBD.getString("nomeTurma"));
				 solicitacao.setDataRefeicao(tuplasBD.getDate("data_Refeicao").getTime());
				 solicitacao.setTipoRefeicao(tuplasBD.getInt("id_Trefeicao"));
				 solicitacao.setNomeAluno(tuplasBD.getString("nome"));
				 solicitacao.setDescricaoServidor(tuplasBD.getInt("descricao_Servidor"));
				 solicitacoes.add(solicitacao);				 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return solicitacoes;
	}
	
	
	
	public Estudante loginEstudante (Estudante e) {

		Estudante objLogando = null;

		String queryInserir = "select matricula_Estudante, nome  from estudante where matricula_Estudante = ? and senha = ?";


		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interroga��o

			PreparedStatement comando = con.prepareStatement(queryInserir);
			
			comando.setString(1, e.getMatriculaEstudante());
			comando.setString(2, e.getSenha());
			
			ResultSet tuplasBD = comando.executeQuery();

			if(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				objLogando = new Estudante();

//				objLogando.setDataNascimento(tuplasBD.getDate("dataNascimento") );
				objLogando.setMatriculaEstudante(tuplasBD.getString("matricula_Estudante"));
//				objLogando.setEmail(tuplasBD.getString("email"));
				objLogando.setNome(tuplasBD.getString("nome"));
			
				
				}

		} catch (SQLException est) {
			// TODO Auto-generated catch block
			est.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return objLogando;
	}
	public Servidor loginServidor (Servidor a) {

		Servidor objLogando = null;

		String queryInserir = "select matricula_Servidor, nome from Servidor where matricula_Servidor = ? and senha = ?";


		Connection con = Conexao.conectar();

		try {
			// manipula os elementos onde tem interroga��o

			PreparedStatement comando = con.prepareStatement(queryInserir);
			comando.setString(1, a.getMatricula());
			comando.setString(2, a.getSenha());
			
			ResultSet tuplasBD = comando.executeQuery();

			if(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio

				objLogando = new Servidor();

				objLogando.setMatricula(tuplasBD.getString("matricula_Servidor"));
				objLogando.setNome(tuplasBD.getString("nome"));
				
				}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			Conexao.desconectar();
		}

		return objLogando;
	}
//não modofiquei nada
//	public ArrayList<Refeicao> buscarRefeicoes(Refeicao data) {
//
//		ArrayList<Refeicao> lista = new  ArrayList<Refeicao>();
//
//		//boolean resultado = false;
//
//
//		String queryInserir = "select t1.data_refeicao, t1.libera , t1.data_Avaliacao from Refeicao where t1.data_refeicao=?;";
//
//
//		Connection con = Conexao.conectar();
//
//		try {
//			// manipula os elementos onde tem interrogação
//
//			PreparedStatement comando = con.prepareStatement(queryInserir);
//			comando.setDate(1, (java.sql.Date) data.getDataRefeicao());
//			ResultSet tuplasBD = comando.executeQuery();
//
//			while(tuplasBD.next()){//vai execulta esse codigo enquanto tiver o proximo patrimonio
//
//				Refeicao objRefeicao = new Refeicao();
//
//				objRefeicao.setDataRefeicao( tuplasBD.getDate("data_refeicao") );
//				objRefeicao.setCodSolicitacao(tuplasBD.getString("codsolicitacao"));
//				objRefeicao.setDataAvaliacao( tuplasBD.getDate("data_Avaliacao") );
//				objRefeicao.setLibera(tuplasBD.getString("libera"));
//
//
//				lista.add(objRefeicao);
//
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		finally {
//			Conexao.desconectar();
//		}
//
//		return lista;
//	}
}
