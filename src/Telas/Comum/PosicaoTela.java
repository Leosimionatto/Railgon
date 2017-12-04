package Telas.Comum;

import java.awt.Dimension;
import java.awt.Toolkit;

/** Classe respons�vel por calulcar as medidas para centralizar a tela.
 *
 * @author Traldi
 *
 */
public class PosicaoTela {
	
	/**
	 * vari�veis utilizadas por obter a dimens�o do monitor do usu�rio.
	 */
	private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension d = tk.getScreenSize();
	
    /** M�todo respons�vel por calcular a dist�ncia do eixo X
     * 
     * @param largura da Panel
     * @return dist�ncia da posi��o lateral
     */
	public int Width(int widthPanel){
		int x = (d.width - widthPanel)/2;
		return x;
	}
	
	/** M�todo respons�vel por calcular a dist�ncia do eixo Y
     * 
     * @param altura da Panel
     * @return dist�ncia da posi��o superior
     */
	public int Height(int heightPanel){	
		int y =  ((d.height - heightPanel)/2) - 30;
		return y;
	}
	
}
