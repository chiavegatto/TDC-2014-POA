package br.com.calcdesempenho.dao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.calcdesempenho.modelo.Abastecimento;

public class AbastecimentoDAO extends DatabaseAbastecimentos{
	
	public AbastecimentoDAO(Context context) {
		super(context);
	}
	
	public void salvar(Abastecimento abastecimento) {
		ContentValues values = new ContentValues();
		values.put("kmAbastecimento", abastecimento.getKMAbastecimento());
		values.put("quantidade", abastecimento.getQuantidadeLitros());
		values.put("valor", abastecimento.getValor());
		values.put("data", abastecimento.getData());
		
		getWritableDatabase().insert("abastecimento", null, values);
		
	}

	public List<Abastecimento> getListaAbastecimento(String dataInicio,String dataFinal) {
		String[] columns = {"id","kmAbastecimento", "quantidade", "valor", "data"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		ArrayList<Abastecimento> abastecimentos = new ArrayList<Abastecimento>();
		while (curso.moveToNext()){
			Abastecimento abastecimento = new Abastecimento();
			abastecimento.setId(curso.getInt(0));
			abastecimento.setKMAbastecimento(curso.getInt(1));
			abastecimento.setQuantidadeLitros(curso.getDouble(2));
			abastecimento.setValor(curso.getDouble(3));
			abastecimento.setData(curso.getString(4));
			abastecimentos.add(abastecimento);
		}
		return abastecimentos;
	}
	
	public String getMaxKMAbastecimento(String dataInicio,String dataFinal){
		String[] columns = {"max(kmAbastecimento)-min(kmAbastecimento)"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		curso.moveToNext();
		String maxAbastecimento = curso.getString(0);
		return maxAbastecimento;
	}
	
	public String getSomaQuantidadeLitros(String dataInicio,String dataFinal){
		String[] columns = {"sum(quantidade)"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		curso.moveToNext();
		String quantidadeLitros = curso.getString(0);
		return quantidadeLitros;
	}
	
	public String getQuantidadeVezesAbastecimento(String dataInicio,String dataFinal){
		String[] columns = {"count(kmAbastecimento)"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		curso.moveToNext();
		String quantidadeVezesAbastecimento = curso.getString(0);
		return quantidadeVezesAbastecimento;
	}
	
	public String getValorAbastecimento(String dataInicio,String dataFinal){
		String[] columns = {"sum(valor)"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		curso.moveToNext();
		String valorAbastecimento = curso.getString(0);
		return valorAbastecimento;
	}
	
	public String getKMLitro(String dataInicio,String dataFinal){
		String[] columns = {"id","kmAbastecimento", "quantidade", "valor", "data"};
		Cursor curso=getWritableDatabase().query("abastecimento", columns , "data BETWEEN '" +dataInicio+ "' and '"+dataFinal+"'", null, null, null, null, null);
		ArrayList<Abastecimento> abastecimentos = new ArrayList<Abastecimento>();
		while (curso.moveToNext()){
			Abastecimento abastecimento = new Abastecimento();
			abastecimento.setId(curso.getInt(0));
			abastecimento.setKMAbastecimento(curso.getInt(1));
			abastecimento.setQuantidadeLitros(curso.getDouble(2));
			abastecimento.setValor(curso.getDouble(3));
			abastecimento.setData(curso.getString(4));
			abastecimentos.add(abastecimento);
		}
				
		NumberFormat decimal = DecimalFormat.getInstance(Locale.ENGLISH);
		decimal.setMaximumFractionDigits(2);

		String kmLitro = null;
		for (int i=1; i< abastecimentos.size(); i++) {
			kmLitro = decimal.format((abastecimentos.get(i).getKMAbastecimento()-abastecimentos.get(i-1).getKMAbastecimento())/abastecimentos.get(i-1).getQuantidadeLitros());
		}
		return kmLitro;
	}

	public void deletar(Abastecimento abastecimento) {
		String[] args={abastecimento.getId().toString()};
		getWritableDatabase().delete("abastecimento", "id=?", args);		
	}

	public void alterar(Abastecimento abastecimento) {
		ContentValues values = new ContentValues();
		values.put("kmAbastecimento", abastecimento.getKMAbastecimento());
		values.put("quantidade", abastecimento.getQuantidadeLitros());
		values.put("valor", abastecimento.getValor());
		values.put("data", abastecimento.getData());
		
		String[] args={abastecimento.getId().toString()};
		getWritableDatabase().update("abastecimento", values,"id=?", args);	
	}

}
