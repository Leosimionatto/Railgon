package Repositorio;

import Telas.Vagao.*;
import Telas.Locomotiva.*;
import Entidades.*;

import javax.swing.JPanel;

import Telas.Composicao.*;
import Telas.Comum.Alert;
import Telas.Comum.Confirm;
import Telas.Outras.*;

/** Representação de uma Fabrica para chamada de telas
 * @author GGTRangers
 */
public class FactoryLayout {
	
	/** Responsavel por exibir a tela de adicionar vagão
	 * @return void
	 */
	public void openAdicionarVagao(VagaoTableModel modelo){
		new AdicionarVagao(modelo).setVisible(true);
	}
	
	/** Responsavel por exibir a tela com os dados para atualizar o vagao
	 * @return void
	 */
	public void openAtualizarVagao(VagaoTableModel modelo, int linhas, Vagao vagao){
		new AdicionarVagao(modelo, linhas, vagao).setVisible(true);
	}
	
	/** Responsavel por exibir a tela com os dados para visualizar a locomotiva
	 * @return void
	 */
	public void openVisualizarVagao(Vagao vagao){
		new AdicionarVagao(vagao).setVisible(true);
	}
	
	/** Responsavel por exibir a tela de listar vagão
	 * @return void
	 */
	public ListarVagao openListarVagao(){
		return new ListarVagao();
	}
	
	/** Responsavel por exibir a tela de adicionar locomotiva
	 * @return void
	 */
	public void openAdicionarLocomotiva(LocomotivaTableModel modelo){
		new AdicionarLocomotiva(modelo).setVisible(true);
	}
	
	/** Responsavel por exibir a tela com os dados para atualizar a locomotiva
	 * @return void
	 */
	public void openAtualizarLocomotiva(LocomotivaTableModel modelo, int linhas, Locomotiva locomotiva){
		new AdicionarLocomotiva(modelo, linhas, locomotiva).setVisible(true);
	}
	
	/** Responsavel por exibir a tela com os dados para visualizar a locomotiva
	 * @return void
	 */
	public void openVisualizarLocomotiva(Locomotiva locomotiva){
		new AdicionarLocomotiva(locomotiva).setVisible(true);
	}
	
	/** Responsavel por exibir a tela de listar locomotiva
	 * @return void
	 */
	public ListarLocomotiva openListarLocomotiva(){
		return new ListarLocomotiva();
	}
	
	/** Responsavel por exibir a tela de adicionar composição
	 * @return void
	 */
	public void openAdicionarComposicao(ComposicaoTableModel modelo){
		new AdicionarComposicao(modelo).setVisible(true);
	}
	
	/** Responsavel por exibir a tela de adicionar composição
	 * @return void
	 */
	public void openAtualizarComposicao(ComposicaoTableModel modelo, int linhas, Composicao composicao){
		new AdicionarComposicao(modelo, linhas, composicao).setVisible(true);
	}
	
	/** Responsavel por abrir a tela da composicao com os dados já informados
	 * @param modelo
	 * @param linhas
	 * @param c
	 */
	public void openAtualizarLocomotiva(ComposicaoTableModel modelo, int linhas, Composicao c){
		new AdicionarComposicao(modelo, linhas, c).setVisible(true);;
	}

	/** Responsavel por exibir a tela de listar composição
	 * @return void
	 */
	public ListarComposicao openListarComposicao(){
		return new ListarComposicao();
	}

	/** Responsavel por exibir a tela de sobre
	 * @return void
	 */
	public Sobre openSobre(){
		return new Sobre();
	}
	

	/** Responsavel por exibir o alerta de erro
	 * @return void
	 */
	public void openAlertError(String title, String message){
		new Alert(0, title, message);
	}
	
	/** Responsavel por exibir o alerta de informa��o
	 * @return void
	 */
	public void openAlertInfo(String title, String message){
		new Alert(1, title, message);
	}
	
	/** Responsavel por exibir o alerta de warning
	 * @return void
	 */
	public void openAlertWarning(String title, String message){
		new Alert(2, title, message);
	}
	
	/** Responsavel por exibir o alerta de quest�o
	 * @return void
	 */
	public void openAlertQuestion(String title, String message){
		new Alert(3, title, message);
	}
	
	/** Responsavel por exibir um "modal" de confirma��o
	 * @return void
	 */
	public int openConfirm(String message){
		return new Confirm(message).getRetorno();
	}
	
}
