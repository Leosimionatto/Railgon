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
	// Quando passamos nulo, na hora em que dever√≠amos passar as novas informa√ß√µes de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vag„o", "res/newVagao.gif", null, null, "Vagıes", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova ComposiÁ„o", "res/newComposicao.gif", null, null, "ComposiÁıes", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	private ListarVagao panelListarVagao = telas.openListarVagao();
	private ListarLocomotiva panelListarLocomotiva = telas.openListarLocomotiva();
	private ListarComposicao panelListarComposicao = telas.openListarComposicao();
	private Sobre panelSobre = telas.openSobre();
	
	public Menu() {
		
		// Construtor passando t√≠tulo
		super("Railgon");
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Instanciando respons√°vel para olhar o objeto quando esse for clicado.
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as op√ßoes no Menu, sendo Nome da op√ß√£o, letra sublinhada, conjunto de itens que conter√° nela e 
		mb.add(MenuBuilder.newMenu("Vag„o", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("ComposiÁ„o", 'C', sComposicao, mh));
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		
		// Setando o menu
		setJMenuBar(mb);

		//panelListarVagao = telas.openListarVagao();
		
		// Definindo que ao clicar no X para fechar a aplica√ß√£o, o programa se encerra de fato.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setando o tamanho m√°ximo da tela
		setExtendedState(this.MAXIMIZED_BOTH);
		
		//Chama a tela da aplica√ß√£o
		getContentPane().add(panelSobre.GetPanel());
		
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
			case "Novo Vag„o":
				telas.openAdicionarVagao(panelListarVagao.GetModelo());
			break;
			case "Vagıes":
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
			case "Nova ComposiÁ„o":
				telas.openAdicionarComposicao(panelListarComposicao.GetModelo());	
			break;
			case "ComposiÁıes":
				getContentPane().removeAll();
				panelBody = panelListarComposicao.GetPanel();	
			break;
			case "Sobre":
				getContentPane().removeAll();
				panelBody = panelSobre.GetPanel();
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
