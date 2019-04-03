package br.edu.ifrn.cda.ui;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.DAO.RefeicaoDAO;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ConfirmarSolicitacaoActivity extends ActionBarActivity {
	private Refeicao refeicao;
	private int posicao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmar_solicitacao);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.confirmar_solicitacao, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
public void aceitar(View view) {
		
		
		
		
	    refeicao.setLibera(1);
		//Aguardando avaliação
		
		RefeicaoDAO objNovo = new RefeicaoDAO();
		boolean resultado = objNovo.atualizar(refeicao);
		
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("retornoItemSelecionado", refeicao);
		bundle.putInt("retornoPosicaoSelecionada", posicao);
		
		Intent it = new Intent();
		it.putExtras(bundle);
		
		setResult(RESULT_OK, it);
		finish();
		
	}
public void rejeitar(View view) {
	
	refeicao.setLibera(4);
	//Não necessito
	
	RefeicaoDAO objNovo = new RefeicaoDAO();
	boolean resultado = objNovo.atualizar(refeicao);
	
	Bundle bundle = new Bundle();
	bundle.putSerializable("retornoItemSelecionado", refeicao);
	bundle.putInt("retornoPosicaoSelecionada", posicao);
	
	
	Intent it = new Intent();
	it.putExtras(bundle);
	setResult(RESULT_OK, it);
	finish();
}
	
}
