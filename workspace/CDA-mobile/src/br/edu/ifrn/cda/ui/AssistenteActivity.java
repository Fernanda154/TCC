package br.edu.ifrn.cda.ui;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.R.menu;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AssistenteActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assistente);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assistente, menu);
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
	public void sair(View view){
		finish();
	}
	public void gerar(View view){
		Intent intent = new Intent(this,CadastroRActivity.class);
		startActivity(intent);
	}
	public void acompanhar(View view){
		Intent intent = new Intent(this,AcompanharActivity.class);
		startActivity(intent);
	}
}
