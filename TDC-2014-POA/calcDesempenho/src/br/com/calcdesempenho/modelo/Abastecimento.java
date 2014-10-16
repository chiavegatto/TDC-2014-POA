package br.com.calcdesempenho.modelo;

import java.io.Serializable;

public class Abastecimento implements Serializable{
	private static final long serialVersionUID = 4698390150560523182L;
	private Integer id;
	private Integer KMAbastecimento;
	private Double quantidadeLitros;
	private Double valor;
	private String data;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getKMAbastecimento() {
		return KMAbastecimento;
	}

	public void setKMAbastecimento(Integer kMAbastecimento) {
		KMAbastecimento = kMAbastecimento;
	}

	public Double getQuantidadeLitros() {
		return quantidadeLitros;
	}

	public void setQuantidadeLitros(Double quantidadeLitros) {
		this.quantidadeLitros = quantidadeLitros;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Data Abastecimento: " + this.data + "\n"+ " Litros: " + this.quantidadeLitros + "  Valor: " + this.valor + "\n" + " KM Abastecimento: " + this.KMAbastecimento;
	}
}
