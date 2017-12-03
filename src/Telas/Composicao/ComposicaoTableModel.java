package Telas.Composicao;

import java.util.ArrayList;
import java.util.List;

import Entidades.Composicao;
import Entidades.Locomotiva;
import Telas.Comum.AbstractTable;

public class ComposicaoTableModel extends AbstractTable<Composicao>{
	
	/** Model da tabela de composi��o.
	 * 
	 */
	
	// Vari�veis dos indices das colunas. Utilizada para facilitar legibilidade do c�digo.
	private static final int COL_Codigo = 0;
	private static final int COL_Descricao = 1;
    private static final int COL_Locomotiva = 2;
    private static final int COL_Vagoes = 3;
    private static final int COL_Comprimento = 4;
    private static final int COL_PesoMax = 5;
    private static final int COL_PesoAtual = 6;
    
    /** Construtor
     * 
     * @param Lista das composi��es
     * @param Colunas da tabela
     */
    public ComposicaoTableModel(List<Composicao> composicao, String[] colunasComposicao ){
        super(new ArrayList<>(composicao), colunasComposicao);
    }
    
    /** M�todo que pega o tipo de classe de cada coluna. Necess�rio para o componente
     *  retorna a classe espec�fica dacoluna
     */
    public Class getColumnClass(int columnIndex) {
       if(columnIndex == COL_Codigo || columnIndex == COL_Descricao){
           return String.class;
       }
       if(columnIndex == COL_Locomotiva || columnIndex == COL_Vagoes){
           return Integer.class;
       }
       return Double.class;
   }
    
    /** M�todo respons�vel por pegar o conte�do de cada coluna. Necess�rio para o componente
     *  retorna um Objeto
     */
    public Object getValueAt(int row, int column) {
        Composicao composicao = linhas.get(row);
        		
    	if(column == COL_Codigo) { return composicao.getCodigo(); } else
    	if(column == COL_Descricao) { return composicao.getDescricao(); } else
    	if(column == COL_Locomotiva) { return (Object) composicao.getLocomotivas().size(); } else
    	if(column == COL_Vagoes) { return (Object) composicao.getVagoes().size(); } else
    	if(column == COL_Comprimento) { return (Object) composicao.getComprimento(); } else
    	if(column == COL_PesoMax) { return (Object) composicao.getPesoMax(); } else
    	if(column == COL_PesoAtual) { return (Object) composicao.getPesoAtual(); } return "";
    }
    
    /** M�todo que adiciona uma nova linha na tabela. Chamado ap�s a inser��o.
     * 
     * @param Objeto do tipo composi��o
     */
    public void addComposicao(Composicao c) {
        linhas.add(c);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    /** M�todo que altera uma linha na tabela. Chamado ap�s a altera��o.
     * 
     * @param indice da linha alterada
     */
    public void updateComposicao(int indiceLinha, Composicao composicao) {
        linhas.set(indiceLinha, composicao);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }
    
    /** M�todo que remove uma linha na tabela. Chamado ap�s a exclus�o.
     * 
     * @param indice da linha exclu�da
     */
    public void removeComposicao(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
    
}