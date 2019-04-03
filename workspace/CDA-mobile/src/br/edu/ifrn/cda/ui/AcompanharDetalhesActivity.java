package br.edu.ifrn.cda.ui;

import java.util.ArrayList;
import java.util.Date;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AcompanharDetalhesActivity extends Activity {

	ArrayList<Solicitacao> solicitacoes = new ArrayList<Solicitacao>();
	SolicitacaoAdapter adaptador;
	private int posicao;
	String logado = "20133081";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhar);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		Bundle mapa = getIntent().getExtras();

		if (mapa.containsKey("refeicao")) {

			Refeicao r = (Refeicao) mapa.get("refeicao");

//			Toast.makeText(getBaseContext(), "Refeição: " +r.getCodSolicitacao(), Toast.LENGTH_LONG).show();


			SolicitacaoDAO obj = new SolicitacaoDAO();
			solicitacoes = obj.buscarSolicitacoes(r);
			if(solicitacoes.size()==0){
				Toast.makeText(getApplicationContext(), "Lista vazia", Toast.LENGTH_LONG).show();
			}else{
				
				ListView lv = (ListView) findViewById(R.id.lv);

				adaptador = new SolicitacaoAdapter(this,  solicitacoes);

				lv.setAdapter(adaptador);



				lv.setOnItemClickListener(new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

						
						//Toast.makeText(getBaseContext(), "Selecionado: " + position, Toast.LENGTH_LONG).show();
						
						
						Solicitacao objSol = (Solicitacao) adaptador.getItem(position);
						objSol.setMatriculaServidor(logado);
						Intent intent = new Intent (getBaseContext(), RequisicaoActivity.class);
						intent.putExtra("itemSelecionado", objSol);
//						intent.putExtra("posicaoSelecionada", position);
						posicao = position;
						startActivityForResult(intent, 0);



						//				ItemProduto item = (ItemProduto)adaptador.getItem(position);
						//
						//				Intent intent = new Intent(getBaseContext(), AddActivity.class);
						//				intent.putExtra("itemSelecionado", item);
						//				startActivityForResult(intent, 200); // 200 = atualizar

						//Toast.makeText(getApplicationContext(), "Posição: " + position, Toast.LENGTH_LONG).show();
					}

				});
			}
		}


		/*	for(int i = 0; i < 5; i++){

			TipoRefeicao tipo = new TipoRefeicao();
			tipo.setDescricao("Almoço");

			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setMatriculaAluno("" + i+1);


			 solicitacoes.add(solicitacao);
		}*/


		

		
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == 0 && RESULT_OK == resultCode) {


			Bundle dados = data.getExtras();

			Solicitacao s = (Solicitacao) dados.getSerializable("retornoItemSelecionado");
//			int posicaoSelecionada = dados.getInt("retornoPosicaoSelecionada");

			s.setMatriculaServidor(logado);
			
			//s.setNomeTurma("INFO3V");
			adaptador.setExibirStatus(true);
			
			adaptador.atualizar(posicao, s);
			adaptador.notifyDataSetChanged();

		}
		else {
			Toast.makeText(getBaseContext(), "Não funciona", Toast.LENGTH_LONG).show();
		}

	}
}
