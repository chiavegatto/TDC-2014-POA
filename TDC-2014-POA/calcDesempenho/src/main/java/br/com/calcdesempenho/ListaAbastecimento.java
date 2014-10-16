package br.com.calcdesempenho;

import java.text.ParseException;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.calcdesempenho.adapter.ListaAbastecimentoAdapter;
import br.com.calcdesempenho.dao.AbastecimentoDAO;
import br.com.calcdesempenho.modelo.Abastecimento;
import br.com.calcdesempenho.util.Util;

public class ListaAbastecimento extends ListActivity {
	private ListView listViewAbastecimento;
	private Abastecimento abastecimento;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_abastecimento);

		AbastecimentoDAO dao = new AbastecimentoDAO(this);
		carregaLista(dao);
		dao.close();
		listViewAbastecimento = getListView();
		registerForContextMenu(listViewAbastecimento);
		
		listViewAbastecimento.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,int posicao, long id) {
				abastecimento=(Abastecimento) adapter.getItemAtPosition(posicao);
				AlertDialog.Builder dialogo = new AlertDialog.Builder(ListaAbastecimento.this);
				dialogo.setMessage("Deseja excluir o abastecimento selecionado?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						AbastecimentoDAO dao = new AbastecimentoDAO(ListaAbastecimento.this);
						dao.deletar(abastecimento);
						Toast.makeText(ListaAbastecimento.this, "Abastecimento excluído com sucesso.", Toast.LENGTH_SHORT).show();
						carregaLista(dao);
						dao.close();
					}
				}).setNegativeButton("Não", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				dialogo.show();
				
				return false;
			}
		});
		listViewAbastecimento.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao,long id) {
				Abastecimento abastecimentoClicado = (Abastecimento) adapter.getItemAtPosition(posicao);
				Intent irParaCadastroAbastecimento = new Intent(ListaAbastecimento.this, CadastrarAbastecimento.class);
				irParaCadastroAbastecimento.putExtra("abastecimentoClicado", abastecimentoClicado);
				startActivity(irParaCadastroAbastecimento);
			}
		});
	}

	private void carregaLista(final AbastecimentoDAO dao) {
		String dataInicio = null;
		String dataFinal = null;
		
		try {
			dataInicio = Util.converteExibicaoTelaParaFormatoBanco("01"
					+ Util.retornaMesAnoAtual());
			dataFinal = Util.converteExibicaoTelaParaFormatoBanco(Util.getDateTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Abastecimento> abastecimentos = dao.getListaAbastecimento(dataInicio, dataFinal);
		
		
		ListaAbastecimentoAdapter listaAbastecimentoAdapter = new ListaAbastecimentoAdapter(
				this, abastecimentos);
		setListAdapter(listaAbastecimentoAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflateratividade = getMenuInflater();
		inflateratividade.inflate(R.menu.lista_abastecimento, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		selecionaOpcoesDoMenu(item);
		return super.onOptionsItemSelected(item);
	}
	
	private void selecionaOpcoesDoMenu(MenuItem item) {
		finish();
	}

}
