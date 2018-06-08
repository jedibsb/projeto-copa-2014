package controller;

import java.util.Map;

public class Jogo {
	
	private Integer codigo = null;
	private Integer codigos1 = null;
	private Integer codigos2 = null;
	
	
	//retorna string formatada para ser apresentada no combobox JOGOS aba CADASTRAR GOL
	@Override
	public String toString() {		
		
		Map<Integer, String> selecoes = new ControleGeral().getSelecoes();
		
		String s = selecoes.get(this.getCodigos1()) + " X ";
		s += selecoes.get(this.getCodigos2());

		return s;
	}
	
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCodigos1() {
		return codigos1;
	}
	
	public void setCodigos1(Integer codigos1) {
		this.codigos1 = codigos1;
	}
	
	public Integer getCodigos2() {
		return codigos2;
	}
	
	public void setCodigos2(Integer codigos2) {
		this.codigos2 = codigos2;
	}
	
	
	
}
