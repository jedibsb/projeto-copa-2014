package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import controller.ControleGeral;
import controller.Jogo;

import java.io.File;

import java.util.List;
import java.util.Map;

import connection.Conexao;




public class SampleController {	
	
	//objeto para se comunicar com classe ControleGeral
	ControleGeral controle = new ControleGeral();	
	
	//map com codigo e pais de selecoes
	private Map<Integer, String> selecoes = null;
	
	//map com codigo e nome dos jogadores
	private Map<Integer, String> jogadores = null;
	
	//map com codigo e nome dos estadios
	private Map<Integer, String> estadios = null;
	
	//map com codigo e nome do tipo de jogo
	private Map<Integer, String> tipoJogo = null;
	
	//map com dados dos jogos
	private List<Jogo> jogos = null;
	
	//map com codigo e nome do tipo de gol
	private Map<Integer, String> tipoGol = null;
	
	
	/*
	 * INICIO - CAMPOS DA ABA MENU CONEXAO
	 */
	 
	@FXML
	private TextField txtFieldUrlConexao;
	
	@FXML
	private TextField txtFieldUsuario;
	
	@FXML
	private PasswordField txtFieldPassword;
	
	@FXML
	private Button botaoTestarConexao;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR TIPO JOGO
	 */
	
	@FXML
	private Button botaoSalvarTipoJogo;
	
	@FXML
	private TextField txtFieldSalvarTipoJogo;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR TIPO GOL
	 */
	
	@FXML 
	private Button botaoSalvarTipoGol;
	
	@FXML
	private TextField txtFieldSalvarTipoGol;
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR SELECAO
	 */
	
	@FXML
	private Button botaoArquivoSelecao;
	
	@FXML
	private TextField txtFieldSelecao;
	
	@FXML
	private Button botaoSalvarSelecao;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR ESTADIO
	 */
	
	@FXML
	private TextField txtFieldEstadio;
	
	@FXML
	private Button botaoArquivoEstadio;
	
	@FXML
	private Button botaoSalvarEstadio;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR JOGADOR
	 */
	
	@FXML
	private TextField txtFieldJogador;
	
	@FXML
	private Button botaoArquivoJogador;
	
	@FXML
	private Button botaoSalvarJogador;
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR PENALTI
	 */
	
	@FXML
	private ListView<String> listViewJogoPenalti;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR JOGO
	 */
	
	@FXML
	private ComboBox<String> comboS1;
	
	@FXML
	private ComboBox<String> comboS2;
	
	@FXML
	private ComboBox<String> comboEstadio;
	
	@FXML
	private ComboBox<String> comboTipoJogo;
	
	@FXML
	private TextField txtFieldDataJogo;
	
	@FXML
	private Button botaoSalvarJogo;
	
		
	/*
	 * INICIO - CAMPOS DA ABA SALVAR GOL
	 */
	
	@FXML
	private ComboBox<Jogo> comboJogo;
	
	@FXML
	private ComboBox<String> comboJogador;
	
	@FXML
	private ComboBox<String> comboTipoGol;
	
	@FXML
	private TextField txtFieldTempoGol;
	
	@FXML
	private Button botaoPreencherGol;	
	
	
	
	
	
	
	/*
	 * ao clicar na aba GOL monta os combobox da tela com opcoes disponiveis
	 */
	public void preencherSalvarGol() throws Exception {		
		
		//monta map com informacoes dos jogos
		jogos = controle.getJogos();
		
		//monta combobox dos jogos
		comboJogo.setItems(FXCollections.observableArrayList(jogos));	
		
		//monta combobox tipo de gol
		tipoGol = controle.getTipoGol();
		comboTipoGol.setItems(FXCollections.observableArrayList(tipoGol.values()));		
	}
	
	
	
	//preencher combobox jogadores da tela cadastrar gol
	public void testeComboBox() throws Exception {
		
		jogadores = controle.montarComboJogadores(comboJogo.getValue().getCodigo());
		
		comboJogador.setItems(FXCollections.observableArrayList(jogadores.values()));		
	}
	
	
	
	
	
	/*
	 * ao clicar na aba JOGO monta os combobox da tela com opcoes disponiveis
	 */
	public void preencherSalvarJogo() throws Exception {

		//ifs: checam se o objeto ja foi criado e preenchido
		//caso sim passa direto		

		if(selecoes == null) {
			selecoes = controle.getSelecoes();
			comboS1.setItems(FXCollections.observableArrayList(selecoes.values()));
			comboS2.setItems(FXCollections.observableArrayList(selecoes.values()));
		}		

		if(estadios == null) {
			estadios = controle.getEstadios();
			comboEstadio.setItems(FXCollections.observableArrayList(estadios.values()));
		}

		//estes precisam preencher toda vez
		tipoJogo = controle.getTipoJogo();
		comboTipoJogo.setItems(FXCollections.observableArrayList(tipoJogo.values()));
	}



	/*
	 * PASSAR PARA CONTROLE
	 */
	public void botaoSalvarTipoJogo (ActionEvent event) throws Exception {		
		
		new Conexao().salvarTipoJogo(txtFieldSalvarTipoJogo.getText());
		
	}
	
	/*
	 * PASSAR PARA CONTROLE
	 */
	public void botaoSalvarTipoGol (ActionEvent event) throws Exception {		
		
		new Conexao().salvarTipoGol(txtFieldSalvarTipoGol.getText());
		
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar selecoes
	 */
	public void botaoArquivoSelecao (ActionEvent event) {
		
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			
			txtFieldSelecao.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("Nao selecionou arquivo");
		}
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar estadios
	 */
	public void botaoArquivoEstadio (ActionEvent event) {
		
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			
			txtFieldEstadio.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("Nao selecionou arquivo");
		}
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar jogadores
	 */
	public void botaoArquivoJogador (ActionEvent event) throws Exception {
		
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			
			txtFieldJogador.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("ARQUIVO INVALIDO!!!");
		}		
	}
	
	
	//chama salvar estadio em ControleGeral passando caminho do arquivo
	public void botaoSalvarEstadio (ActionEvent e) throws Exception {
		
		controle.salvarEstadios(txtFieldEstadio.getText());
		
	}
	
	//chama salvar selecoes em ControleGeral passando caminho do arquivo
	public void botaoSalvarSelecao (ActionEvent e) throws Exception {
		
		controle.salvarSelecoes(txtFieldSelecao.getText());;
	}
	
	//chama salvar jogador em ControleGeral passando caminho do arquivo
	public void botaoSalvarJogador (ActionEvent e) throws Exception {
		
		controle.salvarJogadores(txtFieldJogador.getText());;
	}
	
	
	//chama salvar jogo em ControleGeral 
	public void botaoSalvarJogo() throws Exception {
		
		//Strings com o numero dos codigo para serem inseridos no BD
		String sel1 = controle.getCodigoComboBox(selecoes, comboS1.getValue());
		String sel2 = controle.getCodigoComboBox(selecoes, comboS2.getValue());
		String estadio = controle.getCodigoComboBox(estadios, comboEstadio.getValue());
		String tipo = controle.getCodigoComboBox(tipoJogo, comboTipoJogo.getValue());	
		
		controle.salvarJogo(sel1, sel2, estadio, tipo, txtFieldDataJogo.getText());
	}
	
	//chama salvar gol em ControleGeral 
	public void botaoSalvarGol() throws Exception {				
		
		int jogo = 0;
		String jogador = "";
		String tipo = "";
		
		
		if (comboJogo.getValue() != null) {
			jogo = comboJogo.getValue().getCodigo();
		}
		
		if (comboJogador.getValue() != null) {
			jogador = controle.getCodigoComboBox(jogadores, comboJogador.getValue());
		}
		
		if (comboTipoGol.getValue() != null) {
			tipo = controle.getCodigoComboBox(tipoGol, comboTipoGol.getValue());
		}
		
		controle.salvarGol(jogo, jogador, tipo, txtFieldTempoGol.getText());
	}
	
	
	
	public void testarConexao(ActionEvent event) throws Exception {
		
		txtFieldUrlConexao.setText("jdbc:mysql://localhost:3306/copa2015");		
		
		controle.testarConexao(txtFieldUrlConexao.getText(), txtFieldUsuario.getText(), txtFieldPassword.getText());
	}
	
}
