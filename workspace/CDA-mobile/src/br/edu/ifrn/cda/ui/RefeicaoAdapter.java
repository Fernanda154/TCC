package br.edu.ifrn.cda.ui;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.edu.ifrn.cda.R;
import br.edu.ifrn.cda.dominio.Refeicao;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class RefeicaoAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Refeicao> lista;



	public RefeicaoAdapter(Context context, ArrayList<Refeicao> lista){
		this.context = context;
		this.lista = lista;

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

		Refeicao item = lista.get(position);
		View layout;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			layout = inflater.inflate(R.layout.refeicoes, null);
		}
		else{
			layout = convertView;
		}


		TextView tipoRefeicao = (TextView) layout.findViewById(R.id.t1);

		if(item.getTipoRefeicao()==1){
			tipoRefeicao.setText("Almoço");
		}
		else if(item.getTipoRefeicao()==2){
			tipoRefeicao.setText("Jantar");
		}
		else if(item.getTipoRefeicao()== 3){
			tipoRefeicao.setText("Lanche matutino");
		}
		else if(item.getTipoRefeicao()==4){
			tipoRefeicao.setText("Lanche vespertino");
		}
		else{
			tipoRefeicao.setText("Lanche noturno");
		}



		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = simpleDateFormat.format(new Date(item.getDataRefeicao()));


		TextView data = (TextView) layout.findViewById(R.id.t2);
		data.setText(dataFormatada);


	/*	TextView status = (TextView) layout.findViewById(R.id.idStatus);




		if(item.getLibera() == 1){
			status.setText("Aguardando avaliação");

		}

		if(item.getLibera() == 2){
			status.setText("Liberado");


		}else if(item.getLibera() == 3){

			status.setText("Não liberado");

		}else if(item.getLibera() == 4){

			status.setText("Cancelado");

		}


		status.setVisibility(0);*/


		//		ImageView iv = (ImageView) layout.findViewById(R.id.iv);
		//		iv.setImageResource(item.getCarroImagem(position));

		if(position % 2 == 0){
			layout.setBackgroundColor(Color.TRANSPARENT);
		}

		return layout;
	}}

