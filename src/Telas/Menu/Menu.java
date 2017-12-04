package Telas.Menu;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

import Repositorio.*;
import Telas.Composicao.ListarComposicao;
import Telas.Locomotiva.ListarLocomotiva;
import Telas.Outras.Sobre;


public class Menu extends JFrame {
	
	// Ordem: Nome do item - Icone do Item - Letra sublinhada
	// Quando passamos nulo, na hora em que dever�amos passar as novas informa��es de item, ele coloca uma linha separadora
	private static String sVagao[] = {"Novo Vagão", "res/newVagao.gif", null, null, "Vagões", "res/listaVagao.gif", null};
	private static String sLocomotiva[] = {"Nova Locomotiva", "res/newLocomotiva.gif", null, null, "Locomotivas", "res/listaLocomotiva.gif", null};
	private static String sComposicao[] = {"Nova Composição", "res/newComposicao.gif", null, null, "Composições", "res/listaComposicao.gif", null};
	private static String sSistema[] = {"Sobre", "res/sobre.gif", null, null, "Sair", "res/listaComposicao.gif", null};
	
	FactoryLayout telas = new FactoryLayout();
	
	private JPanel panelAdicionarVagao;
	private JPanel panelListarVagao = telas.openListarVagao();
	
	private Sobre panelSobre = telas.openSobre();
	private ListarLocomotiva panelListarLocomotiva = telas.openListarLocomotiva();
	private ListarComposicao panelListarComposicao = telas.openListarComposicao();
	
	public Menu() {
		
		// Construtor passando t�tulo
		super("Railgon");
		
		ImageIcon imgIcon = new ImageIcon("res/LogoRailgon.png");
		this.setIconImage(imgIcon.getImage());
		
		// Instanciando o menu
		JMenuBar mb = new JMenuBar();
		
		// Instanciando respons�vel para olhar o objeto quando esse for clicado.
		MenuHandler mh = new MenuHandler();
		
		// Adicionando as op�oes no Menu, sendo Nome da op��o, letra sublinhada, conjunto de itens que conter� nela e 
		mb.add(MenuBuilder.newMenu("Sistema", 'S', sSistema, mh));
		mb.add(MenuBuilder.newMenu("Vagão", 'V', sVagao, mh));
		mb.add(MenuBuilder.newMenu("Locomotiva", 'L', sLocomotiva, mh));
		mb.add(MenuBuilder.newMenu("Composição", 'C', sComposicao, mh));		
		
		// Setando o menu
		setJMenuBar(mb);

		//panelListarVagao = telas.openListarVagao();
		
		// Definindo que ao clicar no X para fechar a aplica��o, o programa se encerra de fato.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Setando o tamanho m�ximo da tela
		setExtendedState(this.MAXIMIZED_BOTH);
		
		//Chama a tela da aplicação
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
			case "Novo Vagão":
				panelBody = panelAdicionarVagao;
			break;
			case "Vagões":
				getContentPane().removeAll();
				panelBody = panelListarVagao;	
			break;
			case "Nova Locomotiva":
				telas.openAdicionarLocomotiva(panelListarLocomotiva.GetModelo());
			break;
			case "Locomotivas":
				getContentPane().removeAll();
				panelBody = panelListarLocomotiva.GetPanel();	
			break;
			case "Nova Composição":
				telas.openAdicionarComposicao(panelListarComposicao.GetModelo());	
			break;
			case "Composições":
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
