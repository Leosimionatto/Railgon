package Telas.Menu;

import java.awt.event.*;

import javax.swing.*;

import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Composicao.ListarComposicao;
import Telas.Locomotiva.ListarLocomotiva;
import Telas.Outras.Sobre;
import Telas.Vagao.ListarVagao;


public class Menu extends JFrame {
	
	// Ordem: Nome do item - Icone do Item - Letra sublinhada
	// Quando passamos nulo, na hora em que deveríamos passar as novas informações de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vag�o", "res/newVagao.gif", null, null, "Vag�es", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova Composi��o", "res/newComposicao.gif", null, null, "Composi��es", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	private ListarVagao panelListarVagao = telas.openListarVagao();
	private ListarLocomotiva panelListarLocomotiva = telas.openListarLocomotiva();
	private ListarComposicao panelListarComposicao = telas.openListarComposicao();
	
	public Menu() {
		
		// Construtor passando título
		super("Railgon");
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Instanciando responsável para olhar o objeto quando esse for clicado.
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as opçoes no Menu, sendo Nome da opção, letra sublinhada, conjunto de itens que conterá nela e 
		mb.add(MenuBuilder.newMenu("Vag�o", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("Composi��o", 'C', sComposicao, mh));
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		
		// Setando o menu
		setJMenuBar(mb);

		//panelListarVagao = telas.openListarVagao();
		
		// Definindo que ao clicar no X para fechar a aplicação, o programa se encerra de fato.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setando o tamanho máximo da tela
		setExtendedState(this.MAXIMIZED_BOTH);
		
		//Chama a tela da aplicação
		//getContentPane().add(panelSobre.GetPanel());
		
		//panelListarVagao.add(telas.openListarVagao());
	}
	
	private class MenuHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel panelBody = null;
			String acao = ((JMenuItem) e.getSource()).getText();
			
			switch(acao){
			case "Sair":
				System.exit(0);
			break;
			case "Novo Vag�o":
				//telas.openAdicionarVagao(panelListarVagao.GetModelo());
			break;
			case "Vag�es":
				getContentPane().removeAll();
				panelBody = panelListarVagao.GetPanel();	
			break;
			case "Nova Locomotiva":
				telas.openAdicionarLocomotiva(panelListarLocomotiva.GetModelo());
			break;
			case "Locomotivas":
				getContentPane().removeAll();
				panelBody = panelListarLocomotiva.GetPanel();	
			break;
			case "Nova Composi��o":
				telas.openAdicionarComposicao(panelListarComposicao.GetModelo());	
			break;
			case "Composi��es":
				getContentPane().removeAll();
				panelBody = panelListarComposicao.GetPanel();	
			break;
			case "Sobre":
				getContentPane().removeAll();
				//panelBody = panelSobre.GetPanel();
			break;
			default:
				telas.openAlertError("Osh", "ERRO");
			break;
			}
			
			try{
				if(panelBody != null){
					getContentPane().add(panelBody);
				}
				revalidate();
				repaint();
				
			}
			catch(Exception ex){
				telas.openAlertError("ERRO - Corpo do painel", "Erro: " + ex.getMessage());
			}
			
		}
	}

	
}
