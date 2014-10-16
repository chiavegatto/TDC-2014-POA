package br.com.calcdesempenho.helper;

import java.text.ParseException;

import android.widget.EditText;
import br.com.calcdesempenho.CadastrarAbastecimento;
import br.com.calcdesempenho.R;
import br.com.calcdesempenho.modelo.Abastecimento;
import br.com.calcdesempenho.util.Util;

public class CadastrarAbastecimentoHelper {
	protected EditText kmAbastecimento;
	protected EditText quantidade;
	protected EditText valor;
	protected EditText data;
	
	public CadastrarAbastecimentoHelper(CadastrarAbastecimento cadastrarAbastecimento) {
		kmAbastecimento = (EditText) cadastrarAbastecimento.findViewById(R.id.editText_km_abastecimento);
		quantidade = (EditText) cadastrarAbastecimento.findViewById(R.id.editText_quantidade_litros);
		valor = (EditText) cadastrarAbastecimento.findViewById(R.id.editText_valor);
		data = (EditText) cadastrarAbastecimento.findViewById(R.id.editText_data);
		data.setText(Util.getDateTime());
		data.setInputType(0);
	}

	public Abastecimento cadastraAbastecimento() {
		Abastecimento abastecimento = new Abastecimento();
		abastecimento.setKMAbastecimento(Integer.valueOf(kmAbastecimento.getText().toString()));
		abastecimento.setQuantidadeLitros(Double.valueOf(quantidade.getText().toString()));
		abastecimento.setValor(Double.valueOf(valor.getText().toString()));
		
		try {
			abastecimento.setData(Util.converteExibicaoTelaParaFormatoBanco(data.getText().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return abastecimento;
	}

	public void colocaAbastecimentoNaActivity(Abastecimento abastecimentoParaSerAlterado) {
		kmAbastecimento.setText(abastecimentoParaSerAlterado.getKMAbastecimento().toString());
		quantidade.setText(abastecimentoParaSerAlterado.getQuantidadeLitros().toString());
		valor.setText(abastecimentoParaSerAlterado.getValor().toString());
			try {
				data.setText(Util.converteDataParaExibicaoGrid(abastecimentoParaSerAlterado.getData().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	} 
}
