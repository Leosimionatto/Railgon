package Repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/** Realiza a conexao com o banco de dados
 * 	@author GGTRangers
 */
public class Conexao{
	
	
	/** Guarda a instancia da propria classe
	 * 
	 */
	private static Conexao instance = null;
	
	/** Guarda a conexão com o banco
	 */
	private static Connection conn = null;
	
	/** Informa o local do banco de dados, nome e o parâmetro para criar caso nao exista
	 */
	private static final String PATH = "jdbc:derby:ferrovia;create=true";
	
	/** Usuario do banco
	 */
	private static final String USER = "app";
	
	/** Senha do usuario do banco
	 */
	private static final String PASS = "app";
	
	private static FactoryLayout layout = new FactoryLayout();
	
	/** Realiza a conexao com o banco de dados
	 * @param PATH Caminho onde esta o banco de dados
	 * @param USER UsuÃ¡rio do banco de dados
	 * @param PASS Senha do usuario
	 * @see DriverManager
	 */
	
	private Conexao(){
		try{
			conn = DriverManager.getConnection(PATH, USER, PASS);
		}catch(Exception e){
			layout.openAlertError("ERRO DE CONEXÃO", ("Erro ao carregar o Driver " + e.getMessage()));
			return;
		}
	}
	
	/** Responsavel por realizar a instancia da classe ou devolver a instancia caso ja exista
	 * @return Conexao Retorna a conexao com o banco de dados
	 */
	public static Conexao getInstance(){
		try{
			if(instance == null){
				instance = new Conexao();
			}
			return instance;
		}catch(Exception e){
			layout.openAlertError("ERRO DE CONEXÃO", ("Erro ao pegar a instancia! " + e.getMessage()));
			throw new RuntimeException();
		}		
	}
	
	/** Responsavel por garantir somente uma conexao com o banco
	 * @return Connection Contem a conexao com o banco
	 */
	public Connection On(){
		if(conn == null){
			layout.openAlertError("ERRO DE CONEXÃO", ("Conexão não realizada!"));
		}
		return conn;
	} 
	
	/** Responsavel por desligar a conexao com o banco e encerrar a instancia da classe 
	 */
	public void Off(){
		try{
			//encerra a conexão com o banco
			conn.close();
			conn = null;
			instance = null;
		}catch(Exception e){
			layout.openAlertError("ERRO DE CONEXÃO", ("Erro ao encerrar conexão" + e.getMessage()));
			return;
		}
	}
	/** Responsavel por criar o banco na primeira execucao
	 */
	private void script(){
		String DDLBitola = "CREATE TABLE BITOLA (NOME CHAR NOT NULL PRIMARY KEY, PESO DOUBLE NOT NULL, DISTANCIA DOUBLE NOT NULL)";
		
		String DDLVagao = "CREATE TABLE VAGAO (ID INT GENERATED ALWAYS AS IDENTITY, TIPO CHAR NOT NULL, SUBTIPO CHAR NOT NULL, IDENTIFICACAO VARCHAR(9), PROPRIETARIO VARCHAR(6) NOT NULL, BITOLA CHAR NOT NULL, COMPRIMENTO DOUBLE NOT NULL, PRIMARY KEY(IDENTIFICACAO), FOREIGN KEY (BITOLA) REFERENCES BITOLA(NOME))";
		
		String DDLLocomotiva = "CREATE TABLE LOCOMOTIVA (ID INT GENERATED ALWAYS AS IDENTITY, BITOLA CHAR NOT NULL, CLASSE INT NOT NULL, DESCRICAO VARCHAR(200) NOT NULL, COMPRIMENTO DOUBLE NOT NULL, PESOMAXIMO DOUBLE NOT NULL, PRIMARY KEY(CLASSE), FOREIGN KEY (BITOLA) REFERENCES BITOLA(NOME))";
				
		String DDLComposicao = "CREATE TABLE COMPOSICAO (ID INT GENERATED ALWAYS AS IDENTITY, DESCRICAO VARCHAR(200) NOT NULL, BITOLA CHAR NOT NULL, QTDLOCOMOTIVA INT NOT NULL, QTDVAGAO INT DEFAULT 0, COMPRIMENTO DOUBLE NOT NULL, PRIMARY KEY(ID), FOREIGN KEY (BITOLA) REFERENCES BITOLA(NOME))";
		
		String DDLComposicaoVagao = "CREATE TABLE COMPOSICAO_VAGAO (CODCOMPOSICAO INT NOT NULL, CODVAGAO VARCHAR(9) NOT NULL, ORDEM INT NOT NULL, PRIMARY KEY(CODCOMPOSICAO, CODVAGAO), FOREIGN KEY (CODVAGAO) REFERENCES VAGAO(IDENTIFICACAO), FOREIGN KEY (CODCOMPOSICAO) REFERENCES COMPOSICAO(ID))";
		
		String DDLComposicaoLocomotiva = "CREATE TABLE COMPOSICAO_LOCOMOTIVA (CODCOMPOSICAO INT NOT NULL, CODLOCOMOTIVA INT NOT NULL, ORDEM INT NOT NULL, PRIMARY KEY(CODCOMPOSICAO, CODLOCOMOTIVA), FOREIGN KEY (CODLOCOMOTIVA) REFERENCES LOCOMOTIVA(CLASSE), FOREIGN KEY (CODCOMPOSICAO) REFERENCES COMPOSICAO(ID))";
		
		String DMLBitola = "INSERT INTO BITOLA (NOME, PESO, DISTANCIA) VALUES (?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?)";
		
		try{
			//prepara a query para executar um comando fixo
			Statement stmt = conn.createStatement();
			//recebe definicao da tabela Bitola e cria
			stmt.executeUpdate(DDLBitola);
			//recebe definicao da tabela Vagao e cria
			stmt.executeUpdate(DDLVagao);
			//receve a definicao da tabela locomotiva e cria
			stmt.executeUpdate(DDLLocomotiva);	
			stmt.executeUpdate(DDLComposicao);
			stmt.executeUpdate(DDLComposicaoVagao);
			stmt.executeUpdate(DDLComposicaoLocomotiva);
			
			//prepara a query para ser executada
			PreparedStatement pstmt = conn.prepareStatement(DMLBitola);
			//troca os valores passados pelos pontos de interrogoação, evitando SQLINJECT 
			pstmt.setString(1, "A");  pstmt.setDouble(2,  30.0); pstmt.setDouble(3, 1.0);
			pstmt.setString(4, "B");  pstmt.setDouble(5,  47.0); pstmt.setDouble(6, 1.0);
			pstmt.setString(7, "C");  pstmt.setDouble(8,  64.0); pstmt.setDouble(9, 1.0);
			pstmt.setString(10,"D");  pstmt.setDouble(11, 80.0); pstmt.setDouble(12,1.0);
			pstmt.setString(13,"E");  pstmt.setDouble(14,100.0); pstmt.setDouble(15,1.0);
			pstmt.setString(16,"F");  pstmt.setDouble(17,119.0); pstmt.setDouble(18,1.0);
			pstmt.setString(19,"G");  pstmt.setDouble(20,143.0); pstmt.setDouble(21,1.0);
			pstmt.setString(22,"P");  pstmt.setDouble(23, 47.0); pstmt.setDouble(24,1.6);
			pstmt.setString(25,"Q");  pstmt.setDouble(26, 64.0); pstmt.setDouble(27,1.6);
			pstmt.setString(28,"R");  pstmt.setDouble(29, 80.0); pstmt.setDouble(30,1.6);
			pstmt.setString(31,"S");  pstmt.setDouble(32,100.0); pstmt.setDouble(33,1.6);
			pstmt.setString(34,"T");  pstmt.setDouble(35,119.0); pstmt.setDouble(36,1.6);
			pstmt.setString(37,"U");  pstmt.setDouble(38,143.0); pstmt.setDouble(39,1.6);
			
			//insere as bitolas no banco e retorna o numero de linhas afetadas
			pstmt.executeUpdate();
		}catch(Exception e){
			layout.openAlertError("ERRO AO CRIAR TELAS", ("Não foi possivel criar as tabelas!" + e.getMessage()));
			return;
		}				
	}
}
