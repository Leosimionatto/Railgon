package Telas.Composicao;

import java.util.ArrayList;
import java.util.List;

import Entidades.Composicao;
import Entidades.Locomotiva;
import Entidades.Vagao;
import Telas.Comum.AbstractTable;

public class ComposicaoTableModel extends AbstractTable<Composicao>{
	
	/** Model da tabela de composição.
	 * 
	 */
	
	// Variáveis dos indices das colunas. Utilizada para facilitar legibilidade do código.
	private static final int COL_Codigo = 0;
	private static final int COL_Descricao = 1;
	private static final int COL_Bitola = 2;
    private static final int COL_Locomotiva = 3;
    private static final int COL_Vagoes = 4;
    private static final int COL_Comprimento = 5;
    private static final int COL_PesoMax = 6;
    private static final int COL_PesoAtual = 7;
    
    /** Construtor
     * 
     * @param Lista das composições
     * @param Colunas da tabela
     */
    public ComposicaoTableModel(List<Composicao> composicao, String[] colunasComposicao ){
        super(new ArrayList<>(composicao), colunasComposicao);
    }
    
    /** Método que pega o tipo de classe de cada coluna. Necessário para o componente
     *  retorna a classe específica dacoluna
     */
    public Class getColumnClass(int columnIndex) {
       if(columnIndex == COL_Codigo || columnIndex == COL_Descricao){
           return String.class;
       }
       if(columnIndex == COL_Locomotiva || columnIndex == COL_Vagoes){
           return Integer.class;
       }
       if(columnIndex == COL_Bitola)
    	   return Character.class;
       return Double.class;
   }
    
    /** Método responsável por pegar o conteúdo de cada coluna. Necessário para o componente
     *  retorna um Objeto
     */
    public Object getValueAt(int row, int column) {
        Composicao composicao = linhas.get(row);
        		
    	if(column == COL_Codigo) { return composicao.getCodigo(); } else
    	if(column == COL_Descricao) { return composicao.getDescricao(); } else
    	if(column == COL_Bitola) { return composicao.getBitola(); } else
    	if(column == COL_Locomotiva) { return (Object) composicao.getLocomotivas().size(); } else
    	if(column == COL_Vagoes) { return (Object) composicao.getVagoes().size(); } else
    	if(column == COL_Comprimento) { return (Object) composicao.getComprimento(); } else
    	if(column == COL_PesoMax) { return (Object) composicao.getPesoMax(); } else
    	if(column == COL_PesoAtual) { return (Object) composicao.getPesoAtual(); } return "";
    }
    
    /** Método que adiciona uma nova linha na tabela. Chamado após a inserção.
     * 
     * @param Objeto do tipo composição
     */
    public void addComposicao(Composicao c) {
        linhas.add(c);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    
    /** Método que altera uma linha na tabela. Chamado após a alteração.
     * 
     * @param indice da linha alterada
     */
    public void updateComposicao(int indiceLinha, Composicao composicao) {
        linhas.set(indiceLinha, composicao);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    }
    
    /** Método que remove uma linha na tabela. Chamado após a exclusão.
     * 
     * @param indice da linha excluída
     */
    public void removeComposicao(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha);

    }
    
}