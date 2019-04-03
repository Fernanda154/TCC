package br.edu.ifrn.cda.ui;


import java.util.ArrayList;



import br.edu.ifrn.cda.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifrn.cda.DAO.RefeicaoDAO;
import br.edu.ifrn.cda.dominio.Refeicao;



public class AcompanharActivity extends Activity {

	ArrayList<Refeicao>refeicoes = new ArrayList<Refeicao>();
	RefeicaoAdapter adaptador;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acompanhar);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		RefeicaoDAO chamando = new RefeicaoDAO(); 
		refeicoes = chamando.buscarRefeicoes();
		

//		for(int i = 0; i < 5; i++){
//
//			TipoRefeicao tipo = new TipoRefeicao();
//			tipo.setDescricao("Almoço");
//
//			Refeicao refeicao = new Refeicao();
//			refeicao.setDataRefeicao(new Date().getTime());
//			refeicao.setTipoRefeicao(tipo);
//
//			refeicoes.add(refeicao);
//		}
		ListView lv = (ListView) findViewById(R.id.lv);

		adaptador = new RefeicaoAdapter(this, refeicoes);

		lv.setAdapter(adaptador);



		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				
				
				Refeicao r = (Refeicao) adaptador.getItem(position);
				//Toast.makeText(getApplicationContext(), "Refeição: " +r.getCodSolicitacao(), Toast.LENGTH_LONG).show();

				Intent intent = new Intent(getBaseContext(), AcompanharDetalhesActivity.class);
				
				intent.putExtra("refeicao", r);
				
				startActivity(intent);
				
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
