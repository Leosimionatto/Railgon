package Telas.Vagao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Telas.Comum.Confirm;
import Telas.Interface.ITelas;

public class ListarVagao extends JFrame implements ITelas<VagaoTableModel>{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	
	private VagaoTableModel modelo;
	private Vagao vagaoEditar;
	
	private FactoryLayout tela = new FactoryLayout();
	private Factory f = new Factory();
	private Controller c = f.getController();
	
	List<Vagao> lista;
	String[] colunasVagao = new String[]{"Identificação", "Bitola", "Comprimento", "Tipo", "Subtipo", "Proprietário"};
	
	public ListarVagao() {
		criaJTable();
		criaJanela();
	}
	
	// Método responsável por criar a tela
	public void criaJanela(){
		barraRolagem = new JScrollPane(tabela);
		painelFundo = new JPanel();
		painelFundo.setLayout(new BorderLayout());
		painelFundo.add(BorderLayout.CENTER, barraRolagem);
		
		getContentPane().add(painelFundo);
	}
	
	
	// Método responsável por criar a tabela e chamar o método que lista os dados
	private void criaJTable() {
        tabela = new JTable(modelo);
 
        tabela.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                	vagaoEditar = new Vagao((String) tabela.getValueAt(tabela.getSelectedRow(), 0), (Double) tabela.getValueAt(tabela.getSelectedRow(), 2));
                	editarVagao(vagaoEditar);
                }
            }
            public void mousePressed(MouseEvent e) { /* System.out.println("Mouse pressed"); */ }
            public void mouseReleased(MouseEvent e) { /* System.out.println("Mouse Released"); */ }
            public void mouseEntered(MouseEvent e) { /* System.out.println("Mouse Entered"); */ }
            public void mouseExited(MouseEvent e) { /* System.out.println("Mouse exited"); */ }
        });
        
        tabela.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				vagaoEditar = f.getVagao((String) tabela.getValueAt(tabela.getSelectedRow(), 0), (Double) tabela.getValueAt(tabela.getSelectedRow(), 2));
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
                	editarVagao(vagaoEditar);
				}
				if(e.getKeyCode() == KeyEvent.VK_DELETE){
					excluirVagao(vagaoEditar);
				}
				
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
        	
        });
        
        pesquisar();
    }
	
	protected void editarVagao(Vagao vagaoEditar) {
		try {
			int linhaSelecionada = -1;
	        linhaSelecionada = tabela.getSelectedRow();
		        if (linhaSelecionada >= 0) {
		            tela.openAtualizarVagao(modelo, linhaSelecionada, vagaoEditar);
		        }
		} catch (Exception e) {
			tela.openAlertError(null, e.getMessage());
		}
	}
	
	protected void excluirVagao(Vagao vagaoExcluir){
		int confirm = tela.openConfirm("Tem certeza que deseja excluir o vagão?");
		if(confirm == 0){
			try{
			    c.connect();
			    c.remove(vagaoEditar);
			    tela.openAlertInfo("", "Excluído com sucesso.");
			    c.disconnect();
				modelo.removeVagao(tabela.getSelectedRow());
			}
			catch(Exception ex){
				tela.openAlertError("ERRO AO EXCLUIR VAGÃO", ex.getMessage());
			}
		}
	}

	// Método responsável por listar os dados do vagão e jogar na tabela
	private void pesquisar() {
		try{
			c.connect();
	        lista = c.selectVagoes();
	        modelo = new VagaoTableModel(lista, colunasVagao);
	        tabela.setModel(modelo);
		}
		catch(Exception e){
			tela.openAlertError("ERRO LISTAR VAGÕES", "Ocorreu um erro ao listar os vagões: " + e.getMessage());
		}	
		finally{			
			c.disconnect();
		}
    }
	
	public JPanel GetPanel(){
		return this.painelFundo;
	}

	@Override
	public VagaoTableModel GetModelo() {
		return this.modelo;
	}
	
}
