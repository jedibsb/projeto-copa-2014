package connection;

import java.sql.Connection;

import controller.Jogo;
import controller.Selecao;
import javafx.scene.control.Alert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Conexao {		
		
		
		//parametros para conexao com BD
		private	Connection connection = null;
		static private	String url = null; //"jdbc:mysql://localhost:3306/copa2015";
		static private	String username = null; //"root";
		static private	String password = null; //"root"; 		
		
		
		public void conectar(final String url, final String user, final String password) throws Exception {
			
			this.url = url;
			this.username = user;
			this.password = password;
			
 			System.out.println("Conectando com o Banco de Dados..."); 			 			 			

 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, user, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Conectou com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			} 
 			catch (SQLException e) {
 				System.out.println("Erro na conexão "+e);

 				Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
 				dialogoInfo.setTitle("Diálogo de informação");
 				dialogoInfo.setHeaderText("Erro na conexão "+e); 		       
 				dialogoInfo.showAndWait();
 			} finally {
 					 if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 					 System.out.println("Finalizou");
 			} 			 			
 		}
		
		
		
		
		//salvar tipo de jogo
		public void salvarTipoJogo(String descricao) throws Exception {
			
			System.out.println("Conectando com o Banco de Dados..."); 			 			

 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 	
 				
 				//objeto para executar query no BD 				
 				PreparedStatement ps = connection.prepareStatement("INSERT INTO TIPOJOGO (descricao) values (?)");
 				ps.setString(1, descricao); 				 				
 				
 				ps.executeUpdate();
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Tipo de Jogo Cadastrado com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			} 
 			catch (SQLException e) {
 					 System.out.println("Erro na conexão "+e);
 			} finally {
 					 if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 					 System.out.println("Finalizou");
 			} 
		}
		
		
		
		//salvar tipo de gol
		public void salvarTipoGol(String descricao) throws Exception {
			
			System.out.println("Conectando com o Banco de Dados..."); 						

 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 	
 				
 				//objeto para executar query no BD
 				//Statement stmt = connection.createStatement();
 				PreparedStatement ps = connection.prepareStatement("INSERT INTO TIPOGOL (tipo) values (?)");
 				ps.setString(1, descricao);
 				
 				//String sql = "INSERT INTO TIPOGOL (tipo) values ('" + descricao + "')";
 				
 				ps.executeUpdate();
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Tipo de Gol Cadastrado com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			} 
 			catch (SQLException e) {
 					 System.out.println("Erro na conexão "+e);
 			} finally {
 					 if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 					 System.out.println("Finalizou");
 			} 
		}
		
		
		
		
		public Map<Integer, String> montarComboJogadores(int codigoJogo) {
			
			System.out.println("Conectando com o Banco de Dados...      " + codigoJogo);

			Map<Integer, String> mapa = new HashMap<>();
			
			int cod1 = 0, cod2 = 0;
			
			
			PreparedStatement ps = null;
			
			try {
				//tenta conectar
				Class.forName("com.mysql.jdbc.Driver");   				
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexao Realizada com Sucesso");
				
				ps = connection.prepareStatement("select codigo_S1, codigo_s2 from JOGO where codigo = ?");
				ps.setString(1, codigoJogo+"");
				
				ResultSet rs = ps.executeQuery();			

				while (rs.next()) {					
					cod1 = rs.getInt(1);
					cod2 = rs.getInt(2);
					System.out.println(rs.getInt(1));
					System.out.println(rs.getInt(2));
				}
				
				ps = connection.prepareStatement("select codigo, nome from JOGADOR where codigo_selecao = ? or codigo_selecao = ? order by codigo");
				ps.setInt(1, cod1);
				ps.setInt(2, cod2);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getString(1));
					
					mapa.put(rs.getInt(1), rs.getString(2));
				}
				System.out.println(mapa);
				
			} 
			catch (SQLException | ClassNotFoundException e) {
				System.out.println("Erro na conexão "+e);
			} finally {
				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
				System.out.println("Finalizou");
			}
			
			
			return mapa;
		}
		
		
		
		
		//retorna uma lista com dados do BD para preencher combobox no front end
		public Map<Integer, String> getLista(final int codigo) {						

			Map<Integer, String> mapa = new HashMap<>();								

			System.out.println("Conectando com o Banco de Dados...");

			PreparedStatement ps = null;

			try {
				//tenta conectar
				Class.forName("com.mysql.jdbc.Driver");   				
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexao Realizada com Sucesso"); 	

				switch(codigo) {
					case 1: ps = connection.prepareStatement("SELECT codigo, pais FROM SELECAO");
							break;
					case 2: ps = connection.prepareStatement("SELECT codigo, nome FROM ESTADIO");
							break;
					case 3: ps = connection.prepareStatement("SELECT codigo, descricao FROM TIPOJOGO");
							break;
					case 4: ps = connection.prepareStatement("SELECT codigo, tipo FROM TIPOGOL");
							break;									
				}											

				ResultSet rs = ps.executeQuery();			

				while (rs.next()) {
					System.out.println(rs.getInt(1) + "   " + rs.getString(2));
					mapa.put(rs.getInt(1), rs.getString(2)); 					
				}
			} 
			catch (SQLException | ClassNotFoundException e) {
				System.out.println("Erro na conexão "+e);
			} finally {
				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
				System.out.println("Finalizou");
			}

			System.out.println(mapa);
			return mapa; 
		}	
		
		
		
		public List<Jogo> getListaJogos() {						

			List<Jogo> lista = new ArrayList<>();
			Jogo jogo = new Jogo();

			System.out.println("Conectando com o Banco de Dados...");

			PreparedStatement ps = null;

			try {
				//tenta conectar
				Class.forName("com.mysql.jdbc.Driver");   				
				connection = DriverManager.getConnection(url, username, password);
				System.out.println("Conexao Realizada com Sucesso"); 	

				ps = connection.prepareStatement("select j.codigo, j.codigo_s1, j.codigo_s2  from JOGO j "
						+ "inner join SELECAO s on j.codigo_s1 = s.codigo");

				ResultSet rs = ps.executeQuery();			

				while (rs.next()) {
					//System.out.println(rs.getInt(1) + "   " + rs.getString(2));
					jogo.setCodigo(rs.getInt(1));
					jogo.setCodigos1(rs.getInt(2));
					jogo.setCodigos2(rs.getInt(3));
					lista.add(jogo);
					jogo = new Jogo();
				}
			} 
			catch (SQLException | ClassNotFoundException e) {
				System.out.println("Erro na conexão "+e);
			} finally {
				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
				System.out.println("Finalizou");
			}

			System.out.println(lista);
			return lista; 
		} 		
 		
 		
 		
 		public void salvarEstadios(List<Selecao> lista) {
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 	
 				
 				//objeto para executar query no BD 				
 				PreparedStatement ps = connection.prepareStatement("INSERT INTO ESTADIO (nome, cidade_estado) values (?, ?)");
 				
 				for(int i = 0; i < lista.size(); i++) {

 					ps.setString(1, lista.get(i).getPais());
 					ps.setString(2, lista.get(i).getTecnico()); 

 					ps.executeUpdate(); 					
 				}
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	 		    dialogoInfo.setTitle("Diálogo de informação");
	 		    dialogoInfo.setHeaderText("Estadios Cadastrados com Sucesso"); 		       
	 		    dialogoInfo.showAndWait();
 			} 
 			catch (SQLException | ClassNotFoundException e) {
 					 System.out.println("Erro na conexão "+e);
 			} finally {
 					 if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 					 System.out.println("Finalizou");
 			}
 			
 		}
 		
 		
 		
 		public void salvarSelecoes (List<Selecao> lista) {
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = connection.prepareStatement("insert into SELECAO (codigo, pais, tecnico) values (?, ?, ?)");
 				
 				
 				
 				//percorre todas selecoes
 				for (int i = 0; i < lista.size(); i++) {
 				
 					//String sql = "insert into SELECAO (codigo, pais, tecnico) values (" + (i+1) + ", '" + lista.get(i).pais + "', '" + lista.get(i).tecnico + "')";
 					ps.setString(1, ((i+1)+""));
 					ps.setString(2, lista.get(i).getPais());
 					ps.setString(3, lista.get(i).getTecnico());
 					
 					//executa query
 					ps.executeUpdate(); 					
 				}
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
	 		    dialogoInfo.setTitle("Diálogo de informação");
	 		    dialogoInfo.setHeaderText("Selecoes Cadastradas com Sucesso"); 		       
	 		    dialogoInfo.showAndWait();
 			} 
 			catch (SQLException | ClassNotFoundException e) {
 					 System.out.println("Erro na conexão "+e);
 			} finally {
 					 if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 					 System.out.println("Finalizou");
 			}
 			
 		}
 		
 		
 		
 		
 		public void salvarJogadores(List<String> lista) {
 			
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = connection.prepareStatement
 						("insert into JOGADOR (codigo, nome, camisa, codigo_selecao) values (?, ?, ?, ?)"); 				
 				
 				//percorre todos os jogadores
 				//i = contador geral
 				//j = num camisa jogadores
 				//s = codigo selecao
 				for(int i = 0, j = 1, s= 1; i < lista.size(); i++, j++) {
 					
 					ps.setString(1, ((i+1)+""));
 					ps.setString(2, (lista.get(i)));
 					ps.setString(3, (j+""));
 					ps.setString(4, (s+"")); 					
 					
 					//executa query
 					ps.executeUpdate();

 					//j é zerado a cada 23 insercoes
 					//s é acrescentado em um a cada 23 insercoes
 					if (j == 23) {
 						j = 0;
 						s++;
 					}
 				}
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Jogadores Cadastrados com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			} 			 			
 		}
 		
 		
 		
 		
 		public void salvarJogo(int codSel1, int codSel2, int codEstadio, int codTipoJogo, String data) {
 			
 			System.out.println("cod s1  " + codSel1);
 			System.out.println("cod s2  " + codSel2);
 			System.out.println("estadio  " + codEstadio);
 			System.out.println("tipojogo  " + codTipoJogo);
 			
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = connection.prepareStatement
 						("insert into JOGO (codigo_s1, codigo_s2, codigo_estadio, tipo_jogo, data) values (?, ?, ?, ?, ?)"); 				
 				
 				ps.setInt(1, codSel1);
 				ps.setInt(2, codSel2);
 				ps.setInt(3, codEstadio);
 				ps.setInt(4, codTipoJogo);
 				ps.setString(5, data);
 				
 				ps.executeUpdate();
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Jogo Cadastrado com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			} 
 		}
 		
 		
 		
 		
 		public void salvarGol(int codJogo, int codJogador, int codTipo, String tempo) {
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = connection.prepareStatement
 						("insert into GOL (codigo_jogo, codigo_jogador, codigo_tipo, tempo) values (?, ?, ?, ?)"); 				
 				
 				ps.setInt(1, codJogo);
 				ps.setInt(2, codJogador);
 				ps.setInt(3, codTipo);
 				ps.setString(4, tempo);
 				
 				ps.executeUpdate();
 				
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Gol Cadastrado com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			} 
 		}
 		
 		
 		
 		/*
 		 * recebe um codigo para alterar campo no banco de dados
 		 */
 		
 		public void editarTipoJogo(int codigoRegistro, String alteracao){
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = null;
 				ps = connection.prepareStatement("update TIPOJOGO set descricao = ? where codigo = ? ");
 				ps.setString(1, alteracao);
 				ps.setInt(2, codigoRegistro);
 				ps.executeUpdate();
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Tipo de Jogo ALTERADO com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			}
 			
 		}
 		
 		public void editarTipoGol(int codigoRegistro, String alteracao){
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = null;
 				ps = connection.prepareStatement("update TIPOGOL set tipo = ? where codigo = ? ");
 				ps.setString(1, alteracao);
 				ps.setInt(2, codigoRegistro);
 				ps.executeUpdate();
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Tipo de Gol ALTERADO com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			}
 			
 		}
 		
 		
 		
 		
 		/*
 		 * recebe um codigo para deletar "tipo de jogo" no banco de dados
 		 */
 		public void deletarItem(int codigo) {
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = connection.prepareStatement
 						("delete from TIPOJOGO where codigo = ?"); 				
 								
 				//ps.setString(1, "TIPOJOGO");
 				ps.setInt(1, codigo); 				 				
 				
 				ps.executeUpdate();
 				
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Tipo de Jogo EXCLUIDO com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			}
 		}
 		
 		/*
 		 * recebe um codigo para deletar registro  no banco de dados
 		 */
 		public void deletarItem (int codigoOperacao, int codigoRegistro) {
 			
 			try {
 				//tenta conectar
 				Class.forName("com.mysql.jdbc.Driver");   				
 				connection = DriverManager.getConnection(url, username, password);
 				System.out.println("Conexao Realizada com Sucesso"); 
 				
 				PreparedStatement ps = null;
 				/* 
 				 * codigoOperacao representa em qual tabela sera realizado a exclusao
 				 * 1 para  TIPOJOGO
 				 * 2 para  TIPOGOL
 				 */
 				switch (codigoOperacao) {
 					case 1: 
 						ps = connection.prepareStatement
 						("delete from TIPOJOGO where codigo = ?");
 					break;	
 					case 2:
 						ps = connection.prepareStatement
 						("delete from TIPOGOL where codigo = ?");
 					break;	
 				
 				}
 									
 				//ps.setString(1, "TIPOJOGO");
 				ps.setInt(1, codigoRegistro); 				 				
 				
 				ps.executeUpdate();
 				
 				
 				Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
 		        dialogoInfo.setTitle("Diálogo de informação");
 		        dialogoInfo.setHeaderText("Registro EXCLUIDO com Sucesso"); 		       
 		        dialogoInfo.showAndWait();
 			}
 			catch (SQLException | ClassNotFoundException e) {
 				System.out.println("Erro na conexão "+e);
 			} finally {
 				if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
 				System.out.println("Finalizou");
 			}
 		}
 				
}  