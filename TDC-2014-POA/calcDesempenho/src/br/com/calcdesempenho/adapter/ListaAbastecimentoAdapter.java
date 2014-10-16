package br.com.calcdesempenho.adapter;

import java.text.ParseException;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.calcdesempenho.R;
import br.com.calcdesempenho.modelo.Abastecimento;
import br.com.calcdesempenho.util.Util;

public class ListaAbastecimentoAdapter extends ArrayAdapter<Abastecimento> {
	private Activity activity;
	private List<Abastecimento> abastecimentos = null;

	public ListaAbastecimentoAdapter(Activity activity, List<Abastecimento> abastecimentos) {
		super(activity, 0, abastecimentos);
		this.abastecimentos = abastecimentos;
		this.activity = activity;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Abastecimento abastecimento = abastecimentos.get(position);

		if (view == null) {
			view = LayoutInflater.from(activity).inflate(R.layout.activity_item_lista_abastecimento, null);
		}

		alteraCorLinhasListView(position, view);

		TextView textViewData = (TextView) view.findViewById(R.id.textView_valor_data_do_abastecimento);
		
		try {
			textViewData.setText(Util.converteDataParaExibicaoGrid(abastecimento.getData()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		TextView textViewLitros = (TextView) view.findViewById(R.id.textView_valor_litros_do_abastecimento);
		textViewLitros.setText(String.valueOf(abastecimento.getQuantidadeLitros()));
		
		TextView textViewValor = (TextView) view.findViewById(R.id.textView_valor_abastecido);
		textViewValor.setText(String.valueOf(abastecimento.getValor()));
		
		TextView textViewKM = (TextView) view.findViewById(R.id.textView_valor_km_no_abastecimento);
		textViewKM.setText(String.valueOf(abastecimento.getKMAbastecimento()));
		return view;
	}

	private void alteraCorLinhasListView(int position, View view) {
		if (position % 2 ==0){
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
		}else{
			view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
		}
	}

}
