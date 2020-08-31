package com.protocolo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Protocolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "codigo_ano", nullable = false, unique = true)
	private String codigo_ano;
	
	@Column(name = "resumo", nullable = false)
	private String resumo;
	
	@Column(name = "solicitante", nullable = false)
	private String solicitante;
	
	@Column(name = "data", nullable = false)
	private String data;
	
	@Column(name = "hora", nullable = false)
	private String hora;

	public Protocolo(long id, String codigo_ano, String resumo, String solicitante, String data, String hora) {
		super();
		this.id = id;
		this.codigo_ano = codigo_ano;
		this.resumo = resumo;
		this.solicitante = solicitante;
		this.data = data;
		this.hora = hora;
	}

	public Protocolo() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo_ano() {
		return codigo_ano;
	}

	public void setCodigo_ano(String codigo_ano) {
		this.codigo_ano = codigo_ano;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Protocolo [id=" + id + ", codigo_ano=" + codigo_ano + ", resumo=" + resumo + ", solicitante="
				+ solicitante + ", data=" + data + ", hora=" + hora + "]";
	}
	
}
