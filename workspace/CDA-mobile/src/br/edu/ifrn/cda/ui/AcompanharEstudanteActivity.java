package br.edu.ifrn.cda.ui;

import java.util.ArrayList;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.DAO.RefeicaoDAO;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Solicitacao;
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

public class AcompanharEstudanteActivity extends Activity {

	ArrayList<Solicitacao> solicitacao = new ArrayList<Solicitacao>();
	SolicitacaoAdapter adaptador;
	Estudante estudante ;
	private int posicao;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhar_estudante);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		Intent i = getIntent();
									//Isso é uma chave
		if(i.getExtras().containsKey("estudante1")){
			                                       //Isso é uma chave
			estudante = (Estudante) i.getExtras().get("estudante1");
		}


		SolicitacaoDAO obj = new SolicitacaoDAO();
		solicitacao = obj.buscarRefeicaoDoEstudante(estudante.getMatriculaEstudante());

		ListView lv = (ListView) findViewById(R.id.lvEstudante);

		adaptador = new SolicitacaoAdapter(this, solicitacao);

		lv.setAdapter(adaptador);
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {


//				Refeicao r = (Refeicao) adaptador.getItem(position);
				//Toast.makeText(getApplicationContext(), "Refeição: " +r.getCodSolicitacao(), Toast.LENGTH_LONG).show();

				
				
				Solicitacao objSol = (Solicitacao) adaptador.getItem(position);

				Intent intent = new Intent (getBaseContext(), RequisicaoAlunoActivity.class);
				intent.putExtra("itemSelecionado", objSol);
//				intent.putExtra("posicaoSelecionada", position);
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if(requestCode == 0 && RESULT_OK == resultCode) {


			Bundle dados = data.getExtras();

			Solicitacao s = (Solicitacao) dados.getSerializable("retornoItemSelecionado");
//			int posicaoSelecionada = dados.getInt("retornoPosicaoSelecionada");

//			s.setMatriculaAssistente(matricula);
			
			//s.setNomeTurma("INFO3V");
			adaptador.setExibirStatus(true);
			
			adaptador.atualizar(posicao, s);
			adaptador.notifyDataSetChanged();

		}
		else {
			Toast.makeText(getBaseContext(), "Não funciona", Toast.LENGTH_LONG).show();
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acompanhar_estudante, menu);
		return true;
	}

	
}
