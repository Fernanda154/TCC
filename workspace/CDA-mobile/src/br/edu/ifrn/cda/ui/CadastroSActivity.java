package br.edu.ifrn.cda.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.R.id;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.R.menu;
import br.edu.ifrn.cda.DAO.RefeicaoDAO;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Estudante;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
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

@SuppressLint("NewApi")
public class CadastroSActivity extends ActionBarActivity {
	
	private Spinner spRefeicao;
	Estudante estudante ;
	private ArrayList <Refeicao> refeicoes2;
	private ArrayAdapter<String> adapter;
	private EditText descricao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_s);
		
		Intent i = getIntent();

		if(i.getExtras().containsKey("estudante")){

			estudante = (Estudante) i.getExtras().get("estudante");
		}
		
		if (android.os.Build.VERSION.SDK_INT>9){
			StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		descricao = (EditText)findViewById(R.id.etDescricao);
		spRefeicao = (Spinner)findViewById(R.id.spRefeicao);
		adapter= new ArrayAdapter<String> (this,android.R.layout.simple_spinner_dropdown_item,prepararVetor());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spRefeicao.setAdapter(adapter);
		
		
	}
	
	public String[] prepararVetor(){
		RefeicaoDAO objNovo = new RefeicaoDAO();
		refeicoes2 = objNovo.buscarRefeicoes();
		
		String [] vetor = new String[refeicoes2.size()];
		
		for(int i = 0; i < refeicoes2.size(); i ++){
			vetor[i] = prepararValor(refeicoes2.get(i));
		}
		return vetor;
	}
	
	public String prepararValor(Refeicao r) {
		
		String tipo = null;
		if(r.getTipoRefeicao()==1){
			tipo = "Almo�o";
		}
		else if(r.getTipoRefeicao()==2){
			tipo = "Jantar";
		}
		else if(r.getTipoRefeicao()== 3){
			tipo = "Lanche matutino";
		}
		else if(r.getTipoRefeicao()==4){
			tipo = "Lanche vespertino";
		}
		else{
			tipo = "Lanche noturno";
		}		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = simpleDateFormat.format(new Date(r.getDataRefeicao()));
		
		String resultado = dataFormatada + " (" + tipo + ")";
		
		return resultado;	
	}

	

	public void gerarSolicitacao(View view){
		
		int posicao = spRefeicao.getSelectedItemPosition();
		
		Refeicao r = refeicoes2.get(posicao);
		
		Solicitacao obj = new Solicitacao();
		obj.setCodSolicitacao(r.getCodSolicitacao());
		obj.setStatus(1);
		obj.setDescricao(descricao.getText().toString());
		obj.setMatriculaAluno(estudante.getMatriculaEstudante());
		SolicitacaoDAO obj3 = new SolicitacaoDAO();
		obj3.inserir(obj);
		Toast.makeText(getBaseContext(), "Cadastrado", Toast.LENGTH_LONG).show();
	}
}
