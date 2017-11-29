package Telas.Comum;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author GGTRangers
 *
 * @param <T>
 * 
 * Classe gen�rica para cria��o de tabelas
 */

public abstract class AbstractTable<T> extends AbstractTableModel {
	
	// Lista de uma entidade que ser� as linhas
	protected List<T> linhas;
	
	// Cabe�alho da tabela
	protected String[] colunas;
	
	// Construtor
	public AbstractTable(List<T> entidade, String[] colunas){
		// Define as linhas
		this.linhas = new ArrayList<>(entidade);
		// Define o cabe�alho
		this.colunas = colunas;
	}
	
	// M�todo implementado para AbstractTableModel
	/**
	 * @return n�mero de linhas da tabela
	 */
	public int getRowCount(){
		return linhas.size();
	}
	
	// M�todo implementado para AbstractTableModel
	/**
	 * @return n�mero de colunas da tabela
	 */
	public int getColumnCount(){
		return colunas.length;
	}
	
	// M�todo implementado para AbstractTableModel
	/**
	 * @return nome da coluna referente ao inde especificado 
	 */
	public String getColumnName(int indexColuna){
		return colunas[indexColuna];
	}

}
