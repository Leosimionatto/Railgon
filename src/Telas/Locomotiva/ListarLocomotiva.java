package Telas.Locomotiva;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import Entidades.Locomotiva;
import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Telas.Interface.ITelas;
import Telas.Vagao.VagaoTableModel;

public class ListarLocomotiva extends JFrame implements ITelas{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private LocomotivaTableModel modelo;
	
	List<Locomotiva> lista;
	private String[] colunas = new String[]{"Id", "Bitola", "Comprimento", "Classe", "Descricao"};
	
	public ListarLocomotiva() {
		criaJTable();
		criaJanela();
	}
	
	// M�todo respons�vel por criar a tela
	public void criaJanela(){
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		getContentPane().add(painelFundo);
	}
	
	// M�todo respons�vel por criar a tabela e chamar o m�todo que lista os dados
	private void criaJTable() {
        tabela = new JTable(modelo);
        pesquisar();
    }
	
	// M�todo respons�vel por listar os dados do vag�o e jogar na tabela
	private void pesquisar() {
        Factory f = new Factory();
        Controller c = f.getController();
        c.connect();
        lista = c.selectLocomotivas();
        modelo = new LocomotivaTableModel(lista, colunas);
        tabela.setModel(modelo);
    }
	
	public JPanel GetPanel(){
		return this.painelFundo;
	}

}