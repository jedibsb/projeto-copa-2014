package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connection.Conexao;
import controller.Selecao;

public class ControleGeral {

	//objeto para se comunicar com classe Conexao
	Conexao conexao = new Conexao();
	
	
	
	public void salvarEstadios(final String caminhoArquivo) throws Exception {		
		
		/*
		 * chama o metodo lerArquivoSelecoes porque a logica é a mesma
		 */
		conexao.salvarEstadios(lerArquivoSelecoes(caminhoArquivo));
	}
	
	
	public void salvarSelecoes(final String caminhoArquivo) throws Exception {
		
		conexao.salvarSelecoes(lerArquivoSelecoes(caminhoArquivo));
	}
	
	public void salvarJogadores(final String caminhoArquivo) throws Exception {
		
		conexao.salvarJogadores(lerArquivoJogadores(caminhoArquivo));
	}
	
	public void salvarJogo(String codSel1, String codSel2, String codEstadio, String codTipoJogo, String data) throws Exception {
		
		int s1 = Integer.valueOf(codSel1);
		int s2 = Integer.valueOf(codSel2);
		int est = Integer.valueOf(codEstadio);
		int tipo = Integer.valueOf(codTipoJogo);
		
		conexao.salvarJogo(s1, s2, est, tipo, data);
	}
	
	public void salvarGol(int codJogo, String codJogador, String codTipo, String tempo) throws Exception {
		
		int j = Integer.valueOf(codJogador);
		int t = Integer.valueOf(codTipo);
		
		conexao.salvarGol(codJogo, j, t, tempo);
	}
	
	
	
	/*
	 * recebe o caminho do arquivo contendo nomes dos jogadores
	 * precorre o arquivo e monta uma List com os nomes
	 */
	private List<String> lerArquivoJogadores(final String caminho) throws Exception {		
 			
		//arquivo linux
			File file = new File(caminho);

			//objeto para percorrer as linhas do arquivo
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			
			String st; //para receber uma linha do arquivo  
			String aux = ""; //auxiliar para montar o nome dos jogadores 			

			//lista com jogadores 			
			List<String> jogadores = new ArrayList<>();

			//lê as linhas do arquivo
			while ((st = br.readLine()) != null) {
				
				//percorre cada caracter do arquivo
				for (int i = 0; i < st.length(); i++) {      

					//verifica caracter separador
					if(st.charAt(i) == ';') {
						
						jogadores.add(aux.trim());
						aux = ""; 		
						continue;
					} 
					else {				
						aux += st.charAt(i);
					}	
				} 				
			}
			br.close();
			return jogadores;
	}
	
	/*
	 * recebe o caminho do arquivo contendo nomes das selecoes ou estadios
	 * precorre o arquivo e monta uma List com os nomes
	 */
	private List<Selecao> lerArquivoSelecoes(final String caminho) throws Exception {

		//arquivo linux
		File file = new File(caminho);		

		//objeto para percorrer as linhas do arquivo
		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String st; //para receber uma linha do arquivo  
		String aux = ""; //auxiliar para montar o nome das selecoes
		int auxx = 1; //auxiliar para montar objeto Selecao
		Selecao sel = new Selecao();

		List<Selecao> selecoes = new ArrayList<>();

		//lê as linhas do arquivo
		while ((st = br.readLine()) != null) {

			//percorre cada caracter do arquivo
			for (int i = 0; i < st.length(); i++) {
				
				aux += st.charAt(i);

				//verifica caracter separador
				if(st.charAt(i) == ';') {
					//se auxiliar for impar insere nome do pais
					if ((auxx%2)!=0) {
						sel.setPais(aux.replace(';', ' ').trim());
						aux = "";
						auxx++;
						//se auxiliar for par insere nome do tecnico, adiciona objeto na lista, zera o objeto
					}else {
						sel.setTecnico(aux.replace(';', ' ').trim());
						System.out.println(aux);
						selecoes.add(sel);
						aux = "";
						auxx++;	
						sel = new Selecao();
					}
				}          
			} 				    	
		}
		br.close();
		return selecoes;
	}	
	
	
	/*
	 * getSelecoes, getEstadio, getTipoJogo, getTipoGol, getJogos
	 * chamam metodos da classe conexao que retorna valores para preenhcer os combobox do frontend
	 */
	public Map<Integer, String> getSelecoes() {
				
		return conexao.getLista(1);
	}
	
	
	public Map<Integer, String> getEstadios() {
		
		return conexao.getLista(2);
	}
	
	public Map<Integer, String> getTipoJogo() {

		return conexao.getLista(3);
	}
	
	public Map<Integer, String> getTipoGol() {

		return conexao.getLista(4);
	}
	
	public List<Jogo> getJogos() {
	
		return conexao.getListaJogos();
	}
	
	
	/*
	 * retorna uma List para montar o combobox comboJogador da aba salvar gol
	 */
	public Map<Integer, String> montarComboJogadores(int codigoJogo) {
		
		return conexao.montarComboJogadores(codigoJogo);
	}
	
	
	
	//recebe Map e um valor
	//efetua busca no Map
	//retorna o codigo (para inserir no bd) do valor passado
	public <K, V> String getCodigoComboBox(Map<K, V> mapa, V valor) {
		
		// Iterate over each entry of map using entrySet
		for (Map.Entry<K, V> entry : mapa.entrySet()) 

			// Check if value matches with given value
			if (entry.getValue().equals(valor))
			{
				return(entry.getKey() + "");
			}

		return null;	
	}
	
	
	
	public void testarConexao(String camingo, String user, String senha) throws Exception {
		
		conexao.conectar(camingo, user, senha);
		
	}
	
}

