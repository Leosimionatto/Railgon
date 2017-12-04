package Telas.Comum;

import java.awt.Dimension;
import java.awt.Toolkit;

/** Classe responsável por calulcar as medidas para centralizar a tela.
 *
 * @author Traldi
 *
 */
public class PosicaoTela {
	
	/**
	 * variáveis utilizadas por obter a dimensão do monitor do usuário.
	 */
	private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension d = tk.getScreenSize();
	
    /** Método responsável por calcular a distância do eixo X
     * 
     * @param largura da Panel
     * @return distância da posição lateral
     */
	public int Width(int widthPanel){
		int x = (d.width - widthPanel)/2;
		return x;
	}
	
	/** Método responsável por calcular a distância do eixo Y
     * 
     * @param altura da Panel
     * @return distância da posição superior
     */
	public int Height(int heightPanel){	
		int y =  ((d.height - heightPanel)/2) - 30;
		return y;
	}
	
}
