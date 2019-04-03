package br.edu.ifrn.cda.ui;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.dominio.Refeicao;
import br.edu.ifrn.cda.dominio.Solicitacao;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SolicitacaoAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Solicitacao> lista;
	private boolean exibirStatus;

	
	public SolicitacaoAdapter(Context context, ArrayList<Solicitacao> lista){
		this.context = context;
		this.lista = lista;
		this.exibirStatus = false;
	}
	
	public void atualizar(int posicao, Solicitacao objSol) {
		
		this.lista.set(posicao, objSol);
		
	}
	
	public void setExibirStatus(boolean exibirStatus){
		this.exibirStatus = exibirStatus;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Solicitacao item = lista.get(position);
		View layout;
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.solicitacoes, null);
		}
		else{
			layout = convertView;
		}
		
		
		TextView matricula = (TextView) layout.findViewById(R.id.idMatricula);
		matricula.setText(item.getMatriculaAluno());
		
		TextView dataRefeicao = (TextView) layout.findViewById(R.id.idData);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = simpleDateFormat.format(new Date(item.getDataRefeicao()));
		dataRefeicao.setText(dataFormatada);
		
		TextView tipo = (TextView) layout.findViewById(R.id.idTipo);
		if(item.getTipoRefeicao() == 1){
			tipo.setText("Almoço");
		}
		else if(item.getTipoRefeicao() == 2){
			tipo.setText("Jantar");
		}
		else if(item.getTipoRefeicao() == 3){
			tipo.setText("Lanche matutino");
		}
		else if(item.getTipoRefeicao() == 4){
			tipo.setText("Lanche vespertino");
		}
		else{
			tipo.setText("Lanche noturno");
		}
		
		
		TextView status = (TextView) layout.findViewById(R.id.idStatus);
		
		
		
		if(item.getStatus() == 2){
			status.setText("Liberado");
			
			
		}else if(item.getStatus() == 3){
			
			status.setText("Não liberado");
			
			
		}
		else if(item.getStatus() == 4){

			status.setText("Cancelado");

		}
		else if(item.getStatus() == 5){
			status.setText("Presença confirmada");
		}
		else{
			status.setText("Aguardando avaliação");
		}
		
		
		
		
	
		
//		ImageView iv = (ImageView) layout.findViewById(R.id.iv);
//		iv.setImageResource(item.getCarroImagem(position));
		
		if(position % 2 == 0){
			layout.setBackgroundColor(Color.TRANSPARENT);
		}
		
		return layout;
	}}


