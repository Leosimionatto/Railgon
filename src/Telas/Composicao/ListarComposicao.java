package Telas.Composicao;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;

import Entidades.Composicao;
import Entidades.Locomotiva;
import Telas.Interface.ITelas;

import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;

public class ListarComposicao extends JFrame implements ITelas<ComposicaoTableModel>{
	
	private JPanel painelFundo;
	private JTable tabela;
	private JScrollPane barraRolagem;
	
	private ComposicaoTableModel modelo;
	private int codigoComposicao;
	
	private FactoryLayout tela = new FactoryLayout();
    private Factory f = new Factory();
    private Controller c = f.getController();
	
	List<Composicao> lista;
	private String[] colunas = new String[]{ "Código", "Descrição", "Bitola", "# Locomotiva", "# Vagões", "Comprimento", "Peso Máximo", "Peso Atual"};
	
	public ListarComposicao() {
		criaJTable();
		criaJanela();
	}

	// MÃ©todo responsÃ¡vel por criar a tela
		public void criaJanela(){
			barraRolagem = new JScrollPane(tabela);
			painelFundo = new JPanel();
			painelFundo.setLayout(new BorderLayout());
			painelFundo.add(BorderLayout.CENTER, barraRolagem);
			getContentPane().add(painelFundo);
		}
		
		// Método responsável por criar a tabela e chamar o mÃ©todo que lista os dados
		private void criaJTable() {
	        tabela = new JTable(modelo);
	        
	        tabela.addMouseListener(new MouseListener() {
	        	public void mouseClicked(MouseEvent e) {
	        		codigoComposicao = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
	        		if(e.getClickCount() == 2) {
	        			EditarComposicao(codigoComposicao);
	        		}
	        	}
	        	
	        	public void mousePressed(MouseEvent e) { /* System.out.println("Mouse pressed"); */ }
	            public void mouseReleased(MouseEvent e) { /* System.out.println("Mouse Released"); */ }
	          	public void mouseEntered(MouseEvent e) { /* System.out.println("Mouse Entered"); */ }
	            public void mouseExited(MouseEvent e) { /* System.out.println("Mouse exited"); */ }
	        });
	   
			tabela.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent e) {
					try{
						codigoComposicao = (Integer) tabela.getValueAt(tabela.getSelectedRow(), 0);
						}
						catch(Exception ex){
							tela.openAlertError("ERRO AO SELECIONAR LINHA", "Selecione a linha inteira.");
						}
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						EditarComposicao(codigoComposicao);
					}
					if(e.getKeyCode() == KeyEvent.VK_DELETE){
						ExcluirComposicao(codigoComposicao);
					}
					
				}
				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e) {}
	        	
	        });
	        
	        pesquisar();
	    }
		
		// Método responsável por listar os dados do vagão e jogar na tabela
		private void pesquisar() {
			try{
				c.connect();
		        lista = c.selectComposicoes();
		        modelo = new ComposicaoTableModel(lista, colunas);
		        tabela.setModel(modelo);
			}
	        catch(Exception e){
	        	tela.openAlertError("ERRO LISTAR COMPOSIÇÕES", "Ocorreu um erro ao listar as composições: " + e.getMessage());
	        }
			finally{
				//c.desconnect();
			}
	    }
		
		protected void EditarComposicao(int codigoComposicao) {
			try {
				int linhaSelecionada = -1;
		        linhaSelecionada = tabela.getSelectedRow();
			        if (linhaSelecionada >= 0) {
			            //tela.openAtualizarComposicao(modelo, linhaSelecionada, codigoComposicao);
			        }
			} catch (Exception e) {
				tela.openAlertError(null, e.getMessage());
			}
			
		}
		
		protected void ExcluirComposicao(int codigoComposicao) {
			try {
				int linhaSelecionada = -1;
		        linhaSelecionada = tabela.getSelectedRow();
			        if (linhaSelecionada >= 0) {
			        	int confirm = tela.openConfirm("Tem certeza que deseja excluir a composição?");
						if(confirm == 0){
							try {
								c.connect();
								//c.remove(codigoComposicao);
								tela.openAlertInfo("", "Excluído com sucesso.");
								modelo.removeComposicao(tabela.getSelectedRow());
							} catch (Exception ex) {
								tela.openAlertError("ERRO AO EXCLUIR COMPOSICAO", ex.getMessage());
							} finally{
								c.disconnect();
							}
						}
			        }
			} catch (Exception e) {
				tela.openAlertError(null, e.getMessage());
			}
			
		}
		
		public JPanel GetPanel(){
			return this.painelFundo;
		}
		
		public ComposicaoTableModel GetModelo(){
			return this.modelo;
		}
	
}