package br.edu.ifrn.cda.ui;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.DAO.RefeicaoDAO;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.R.menu;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.support.v7.app.ActionBarActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DecimalFormat;
import android.app.DialogFragment;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.util.Log;
import android.os.StrictMode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.Button;

@SuppressLint("NewApi")
public class CadastroRActivity extends ActionBarActivity implements FragmentComunicator {
	private Spinner spRefeicao;
	private String data;
	RefeicaoAdapter adaptador;
	public static final int REQUEST_CODE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_r);
		spRefeicao = (Spinner) findViewById(R.id.spRefeicao);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spRefeicao,
				android.R.layout.simple_spinner_item);
		spRefeicao.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_r, menu);
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

	public void show(View view) {
		DatePickerFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "data");
	}

	public void onPassData(String data) {
		this.data = data;
	}

	public void gerarRefeicao(View view) {
		int itemSelecionado = spRefeicao.getSelectedItemPosition();

		if (itemSelecionado == 0) {
			Toast.makeText(getApplicationContext(), "Opção inválida", Toast.LENGTH_SHORT).show();
		} else {

			Refeicao obj = new Refeicao();

			SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date dataN = dataFormatada.parse(data);

				obj.setDataRefeicao(dataN.getTime());
				obj.setLibera(2);
				obj.setDataAvaliacao(new Date().getTime());
				obj.setTipoRefeicao(itemSelecionado);
				RefeicaoDAO obj3 = new RefeicaoDAO();
				obj3.inserir(obj);
				Toast.makeText(getBaseContext(), "Cadastrado", Toast.LENGTH_LONG).show();
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}

	}

}
