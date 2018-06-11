package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;
import controller.ControleGeral;
import controller.Jogo;

import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	
	@FXML
	private ListView<String> listViewTipoJogo;
	
	@FXML
	private Button botaoDeletarTipoJogo;
	
	@FXML
	private Button botaoEditarTipoJogo;
	
	
	/*
	 * INICIO - CAMPOS DA ABA SALVAR TIPO GOL
	 */
	
	@FXML 
	private Button botaoSalvarTipoGol;
	
	@FXML
	private TextField txtFieldSalvarTipoGol;
	
	@FXML
	private ListView<String> listViewTipoGol;
	
	@FXML
	private Button botaoDeletarTipoGol;
	
	@FXML
	private Button botaoEditarTipoGol;
	
	
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
	private DatePicker datePickerDataJogo;
	
	@FXML
	private Button botaoSalvarJogo;
	
	@FXML 
	private ComboBox<Jogo> comboEditarJogo;
	
	@FXML
	private Button botaoEditarJogo;
	
	@FXML
	private Button botaoExcluirJogo;
	
		
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
	 * ao clicar na aba TIPO JOGO preenche ListView da aba
	 */
	public void preencherTipoJogo() {
		
		//carrega map com dados
		tipoJogo = controle.getTipoJogo();
		
		//apresenta na ListView
		listViewTipoJogo.setItems(FXCollections.observableArrayList(tipoJogo.values()));
	}
	
	/*
	 * ao clicar na aba TIPO GOL preenche ListView da aba
	 */
	public void preencherTipoGol() {
		
		//carrega map com dados
		tipoGol = controle.getTipoGol();
		
		//apresenta na ListView
		listViewTipoGol.setItems(FXCollections.observableArrayList(tipoGol.values()));
	}
	
	
	/*
	 * ao clicar na aba GOL preenche os combobox da aba
	 */
	public void preencherSalvarGol() throws Exception {		
		
		//carrega map com informacoes dos jogos
		jogos = controle.getJogos();
		
		//carrega combobox dos jogos
		comboJogo.setItems(FXCollections.observableArrayList(jogos));	
		
		//carrega combobox tipo de gol
		tipoGol = controle.getTipoGol();
		comboTipoGol.setItems(FXCollections.observableArrayList(tipoGol.values()));		
	}
	
	
	
	//preencher combobox jogadores da aba cadastrar gol
	public void testeComboBox() throws Exception {
		
		
		try {
			jogadores = controle.montarComboJogadores(comboJogo.getValue().getCodigo());

			comboJogador.setItems(FXCollections.observableArrayList(jogadores.values()));
		}
		catch(NullPointerException e) { }
	}
	
	
	
	
	
	/*
	 * ao clicar na aba JOGO preenche os combobox da aba
	 */
	public void preencherSalvarJogo() throws Exception {

		selecoes = controle.getSelecoes();	
		estadios = controle.getEstadios();		
		jogos = controle.getJogos();
		tipoJogo = controle.getTipoJogo();		
		
		comboS1.setItems(FXCollections.observableArrayList(selecoes.values()));
		comboS2.setItems(FXCollections.observableArrayList(selecoes.values()));
		comboEstadio.setItems(FXCollections.observableArrayList(estadios.values()));
		comboEditarJogo.setItems(FXCollections.observableArrayList(jogos));		
		comboTipoJogo.setItems(FXCollections.observableArrayList(tipoJogo.values()));
	}



	/*
	 * PASSAR PARA CONTROLE
	 */
	public void botaoSalvarTipoJogo (ActionEvent event) throws Exception {		
		
		new Conexao().salvarTipoJogo(txtFieldSalvarTipoJogo.getText());
		
		//limpa textfield
		txtFieldSalvarTipoJogo.setText("");
		
		//atualiza listview
		preencherTipoJogo();
	}
	
	
	/*
	 * PASSAR PARA CONTROLE
	 */
	public void botaoSalvarTipoGol (ActionEvent event) throws Exception {		
		
		new Conexao().salvarTipoGol(txtFieldSalvarTipoGol.getText());
		
		//atualiza listview
		preencherTipoGol();
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar selecoes
	 */
	public void botaoArquivoSelecao (ActionEvent event) {
		
		//abre janela para selecionar arquivo
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			//pega o caminho do arquivo
			txtFieldSelecao.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("Nao selecionou arquivo");
		}
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar estadios
	 */
	public void botaoArquivoEstadio (ActionEvent event) {
		
		//abre janela para selecionar arquivo
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			//pega o caminho do arquivo
			txtFieldEstadio.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("Nao selecionou arquivo");
		}
	}
	
	
	/*
	 * botao de selecionar arquivo para salvar jogadores
	 */
	public void botaoArquivoJogador (ActionEvent event) throws Exception {
		
		//abre janela para selecionar arquivo
		FileChooser fc = new FileChooser();
		File arquivoSelecionado = fc.showOpenDialog(null);
		
		if (arquivoSelecionado != null) {
			//pega o caminho do arquivo
			txtFieldJogador.setText(arquivoSelecionado.getPath());
		} else {
			System.out.println("Nao selecionou arquivo");
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
		//pega data do DatePicker
		java.time.LocalDate data = datePickerDataJogo.getValue();				
		
		controle.salvarJogo(sel1, sel2, estadio, tipo, data.toString());
	}
	
	
	
	//chama salvar gol em ControleGeral 
	public void botaoSalvarGol() throws Exception {				
		
		int jogo = 0;
		String jogador = "";
		String tipo = "";
		
		/*
		 * pega os valores dos ComboBox
		 */
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
	
	
	public void botaoEditarTipoJogo(ActionEvent event){
		
		//pega o texto do item selecionado na listview
		String teste = listViewTipoJogo.getSelectionModel().getSelectedItem();
		
		System.out.println(teste);
		System.out.println(listViewTipoJogo.getSelectionModel().isEmpty());

		//se tiver selecionado algum item da listview
		if (! listViewTipoJogo.getSelectionModel().isEmpty()) {
			
			//i recebe o codigo do item selecionado
			int	i = Integer.valueOf(controle.getCodigoComboBox(tipoJogo, (listViewTipoJogo.getSelectionModel().getSelectedItem())));
			
			//abre caixa de dialogo
			TextInputDialog dialog = new TextInputDialog(teste);
			dialog.setTitle("Editar Tipo de Jogo");
			//dialog.setHeaderText("Look, a Text Input Dialog");
			dialog.setContentText("Entre com novo valor:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			
			//se textfield nao tiver em branco			
			if (result.isPresent()){
								
				new Conexao().editarTipoJogo(i, result.get());
				//atualiza listview
				preencherTipoJogo();
			}			
		}
	}
	
	
	/*
	 * mesma logica de editar tipo jogo
	 */
	public void botaoEditarTipoGol(ActionEvent event){
		
		
		String teste = listViewTipoGol.getSelectionModel().getSelectedItem();
		
		if (! listViewTipoGol.getSelectionModel().isEmpty()) {
			
			int i = Integer.valueOf(controle.getCodigoComboBox(tipoGol, (listViewTipoGol.getSelectionModel().getSelectedItem())));
			
			//abre caixa de dialogo
			TextInputDialog dialog = new TextInputDialog(teste);
			dialog.setTitle("Editar Tipo de Gol");
			//dialog.setHeaderText("Look, a Text Input Dialog");
			dialog.setContentText("Entre com novo valor:");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			
			//se textfield nao tiver em branco			
			if (result.isPresent()){
								
				new Conexao().editarTipoGol(i, result.get());
				//atualiza listview
				preencherTipoGol();
			}
		}
		
		
		
	}
	
	public void botaoEditarJogo() {
		
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");

		

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);		

		TextField selecao1 = new TextField();		
		TextField selecao2 = new TextField();		
		TextField estadio = new TextField();
		TextField tipoJogo = new TextField();
		TextField data = new TextField();

		grid.add(new Label("Selecao 1:"), 0, 0);
		grid.add(selecao1, 1, 0);
		grid.add(new Label("Selecao 2:"), 0, 1);
		grid.add(selecao2, 1, 1);
		grid.add(new Label("Estadio:"), 0, 2);
		grid.add(estadio, 1, 2);
		grid.add(new Label("Tipo de jogo:"), 0, 3);
		grid.add(tipoJogo, 1, 3);
		grid.add(new Label("Tipo de jogo:"), 0, 4);
		grid.add(data, 1, 4);

		// Enable/Disable login button depending on whether a username was entered.
		//Node Button = dialog.getDialogPane().lookupButton(loginButtonType);
		//loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		//username.textProperty().addListener((observable, oldValue, newValue) -> {
		//    loginButton.setDisable(newValue.trim().isEmpty());
		//});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> selecao1.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(selecao1.getText(), selecao2.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
	}
	
	
	public void botaoDeletarTipoJogo(ActionEvent event) {
		
		//pega o codigo do item selecionado na ListView
		int i = Integer.valueOf(controle.getCodigoComboBox(tipoJogo, (listViewTipoJogo.getSelectionModel().getSelectedItem())));		
		
		new Conexao().deletarItem(1, i);
		
		//atualiza listview
		preencherTipoJogo();
	}
	
	
	public void botaoDeletarTipoGol(ActionEvent event) {
		
		//pega o codigo do item selecionado na ListView
		int i = Integer.valueOf(controle.getCodigoComboBox(tipoGol, (listViewTipoGol.getSelectionModel().getSelectedItem())));		
		
		new Conexao().deletarItem(2,i);	
		
		//atualiza listview
		preencherTipoGol();
}
	
	
	public void testarConexao(ActionEvent event) throws Exception {
		
		//txtFieldUrlConexao.setText("jdbc:mysql://localhost:3306/copa2015");		
		
		controle.testarConexao(txtFieldUrlConexao.getText(), txtFieldUsuario.getText(), txtFieldPassword.getText());
	}
	
}
