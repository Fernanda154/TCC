package br.edu.ifrn.cda.ui;


import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.R.layout;
import br.edu.ifrn.cda.DAO.SolicitacaoDAO;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RequisicaoActivity extends Activity {
	
	private Solicitacao objSolicitacao;
	private int posicaoSelecionada;
	private EditText etMatriculaR, etNomeR, etTurmaR, etDescricao;
	private Spinner spDescricao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requisicao2);
		spDescricao = (Spinner) findViewById(R.id.spDescricao);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.spDescricoes,
				android.R.layout.simple_spinner_item);
		spDescricao.setAdapter(adapter);
		
		etMatriculaR = (EditText)findViewById(R.id.etMatriculaR);
		etNomeR = (EditText)findViewById(R.id.etNomeR);
		etTurmaR = (EditText)findViewById(R.id.etTurmaR);
		etDescricao = (EditText)findViewById(R.id.etDescricao);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
					new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		Bundle dados = getIntent().getExtras();
		
		if (dados.containsKey("itemSelecionado")) {
			
			objSolicitacao = (Solicitacao) dados.getSerializable("itemSelecionado");
			etMatriculaR.setText(objSolicitacao.getMatriculaAluno());
			etNomeR.setText(objSolicitacao.getNomeAluno());
			etTurmaR.setText(objSolicitacao.getNomeTurma());
			etDescricao.setText(objSolicitacao.getDescricao());
			
		}
	}
	
	public void aceitar(View view) {
		
		Toast.makeText(getBaseContext(), objSolicitacao.getNomeAluno() + " " + posicaoSelecionada , Toast.LENGTH_LONG).show();
		
		objSolicitacao.setDescricaoServidor(0);
		objSolicitacao.setStatus(2);
		//mudar o código do libera
		
		SolicitacaoDAO objNovo = new SolicitacaoDAO();
		boolean resultado = objNovo.atualizar(objSolicitacao);
		
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("retornoItemSelecionado", objSolicitacao);
		
		
		Intent it = new Intent();
		it.putExtras(bundle);
		
		setResult(RESULT_OK, it);
		finish();
		
	}
	
	public void rejeitar(View view) {
		
		objSolicitacao.setStatus(3);
		objSolicitacao.setDescricaoServidor(spDescricao.getSelectedItemPosition());
		SolicitacaoDAO objNovo = new SolicitacaoDAO();
		boolean resultado = objNovo.atualizar(objSolicitacao);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable("retornoItemSelecionado", objSolicitacao);
		
		
		
		Intent it = new Intent();
		it.putExtras(bundle);
		setResult(RESULT_OK, it);
		finish();
	}

	
}
