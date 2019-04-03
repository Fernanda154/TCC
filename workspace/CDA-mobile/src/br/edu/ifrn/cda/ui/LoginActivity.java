package br.edu.ifrn.cda.ui;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.R.menu;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Servidor;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText etMatricula, etSenha;  
	private Spinner spModulos;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		etMatricula = (EditText)findViewById(R.id.etLogin);
		etSenha = (EditText)findViewById(R.id.etSenha);
		spModulos = (Spinner)findViewById(R.id.spModulos);
		//ArrayAdapter adapter= ArrayAdapter.createFromResource(this, R.array.spModulos, android.R.layout.simple_spinner_item);
		//spModulos.setAdapter(adapter);

	}

	public void entrar(View view) {
		String matricula = etMatricula.getText().toString();
		String senha =  etSenha.getText().toString();
		int modulo = spModulos.getSelectedItemPosition();

		//Toast.makeText(getBaseContext(), modulo, Toast.LENGTH_LONG).show();
//Módulo do estudante igual a 1
		if(modulo == 1){

			Estudante objE = new Estudante();
			objE.setMatriculaEstudante(matricula);
			objE.setSenha(senha);
			SolicitacaoDAO obj = new SolicitacaoDAO();
			Estudante objEstudante = obj.loginEstudante(objE);
			
			if(objEstudante != null){
				Intent intent = new Intent(this,AlunoActivity.class);
				intent.putExtra("estudante", objEstudante);
				startActivity(intent);
			}else{
				Toast.makeText(getBaseContext(), "Senha ou login inválido", Toast.LENGTH_LONG).show();
			}
		}
//Módulo do servidor igual a 2
		else if(modulo == 2){

			Servidor objS = new Servidor();
			objS.setMatricula(matricula);
			objS.setSenha(senha);
			SolicitacaoDAO obj = new SolicitacaoDAO();
			Servidor objServidor = obj.loginServidor(objS);
			
			if(objServidor != null){
				Toast.makeText(getBaseContext(), "Assistente Social", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(this,AssistenteActivity.class);
				intent.putExtra("assistente", objServidor);
				startActivity(intent);
			}
			else{
				Toast.makeText(getBaseContext(), "Senha ou login inválido", Toast.LENGTH_LONG).show();
			}
		}
		//Módulo inválido
		else{
			Toast.makeText(getBaseContext(), "Opção invalida", Toast.LENGTH_LONG).show();
		}


	}	

}
