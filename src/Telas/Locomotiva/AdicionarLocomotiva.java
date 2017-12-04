package Telas.Locomotiva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import Entidades.Locomotiva;
import Entidades.VeiculoFerroviario;

import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;

import Telas.Comum.PosicaoTela;

/** Classe responsável por criar uma janela para realizar a manutenção de uma locomotiva
 * 
 * @author RailgonTeam
 *
 */
public class AdicionarLocomotiva extends JFrame{
	
	/** Centraliza a posição da tela
	 * 
	 */
	private PosicaoTela pTela;
	
	/** declarações necessárias para atualizar a tabela
	 * 
	 */
	private LocomotivaTableModel modelo;
	private Locomotiva locomotiva = null;
	private int linha;
	
	/** declarando botões
	 * 
	 */
	private JButton JBExcluir;
	private JButton JBSalvar;
	private JButton JBNovo;
	private JButton JBCancelar;
	/** declarando campos de texto
	 * 
	 */
	
	private JTextField JTcampoClasse;
	private JTextField JTcampoDescricao;
	private JTextField JTcampoPesomaxreb;
	private JTextField JTcampoComploc;
	 
	/** declarando combobox
	 * 
	 */
	private JComboBox<VeiculoFerroviario.Bitola> JCBbitola;
	private DefaultComboBoxModel<VeiculoFerroviario.Bitola> DCBMbitola;
	
	/** declarando nome do campo
	 * 
	 */
	private JLabel JLClasse;
	private JLabel JLDescricao;
	private JLabel JLPesomaxreb;
	private JLabel JLBitola;
	private JLabel JLComploc;
	private JLabel JLimg;
	private JLabel JLlocomotiva;
	
	/** declarando seções da pagina
	 * 
	 */
	JPanel Jhead;
	JPanel Jbody;
	JPanel Jfooter;
	
	/** declarando factory para chamada de telas.
	 * 
	 */
	private FactoryLayout fLayout = new FactoryLayout();
	//JPanel Jprincipal;
	
	/** construtor read only para visualização na tela de adicionar composição
	 * 
	 */
	public AdicionarLocomotiva(Locomotiva l) {
		this();
		locomotiva = l;
		this.JTcampoClasse.setText(String.valueOf(locomotiva.getClasse()));
		this.JTcampoDescricao.setText(String.valueOf(locomotiva.getDescricao()));
		this.JTcampoPesomaxreb.setText(String.valueOf(locomotiva.getPesoMax()));
		this.JCBbitola.setSelectedItem( VeiculoFerroviario.Bitola.valueOf(String.valueOf(locomotiva.getBitola())));		
		this.JTcampoComploc.setText(String.valueOf(locomotiva.getComprimento()));
		this.JTcampoClasse.setEditable(false);
		this.JTcampoDescricao.setEditable(false);
		this.JTcampoPesomaxreb.setEditable(false);
		this.JCBbitola.setEnabled(false);
		this.JTcampoComploc.setEditable(false);
		JBExcluir.setVisible(false);
		JBSalvar.setVisible(false);
		JBNovo.setVisible(false);
		JBCancelar.setVisible(false);
		this.setTitle(l.getDescricao());
	}
	
	/** Em caso tenha locomotiva adicionada, o botão Excluir irá aparecer na tela, Setando campos da locomotiva para caso haja adicionado, irá poder excluir.
	 * 
	 */
	public AdicionarLocomotiva(LocomotivaTableModel md, int linhaSelecionada, Locomotiva l) {
		this(md);
		locomotiva = l;
		linha = linhaSelecionada;
		this.JTcampoClasse.setText(String.valueOf(locomotiva.getClasse()));
		this.JTcampoDescricao.setText(String.valueOf(locomotiva.getDescricao()));
		this.JTcampoPesomaxreb.setText(String.valueOf(locomotiva.getPesoMax()));
		this.JCBbitola.setSelectedItem( VeiculoFerroviario.Bitola.valueOf(String.valueOf(locomotiva.getBitola())));		
		this.JTcampoComploc.setText(String.valueOf(locomotiva.getComprimento()));
		JBExcluir.setEnabled(true);
		this.setTitle("Alterar Locomotiva");
	}
	
	/** colocando titulo na janela
	 * 	
	 */
	public  AdicionarLocomotiva(LocomotivaTableModel md) {
		this(); // ajusta título
		modelo = md;
		this.setTitle("Adicionar Locomotiva");
	}

	/** metodo que instancia todos campos, botões e chama as seções da pagina
	 * 
	 */
	public  AdicionarLocomotiva() {
		super();
		JTcampoClasse = new JTextField();
		JTcampoDescricao = new JTextField();
		JTcampoPesomaxreb = new JTextField();
		JTcampoComploc = new JTextField();
		JTcampoClasse = new JTextField();
		JTcampoDescricao = new JTextField();
		JTcampoPesomaxreb = new JTextField();
		JTcampoComploc = new JTextField();
		JLClasse = new JLabel("Classe:");
		JLDescricao = new JLabel("Descrição:");
		JLPesomaxreb = new JLabel("Peso Maximo Rebocavel (t):");
		JLBitola = new JLabel("Bitola:");
		JLComploc = new JLabel ("Comprimento da locomotiva (m):");
		JLlocomotiva = new JLabel("Locomotiva");
		JBExcluir = new JButton("Excluir");
		JBSalvar = new JButton("Salvar");
		JBNovo = new JButton("Novo");
		JBCancelar = new JButton("Cancelar");
	
		DCBMbitola = new DefaultComboBoxModel<>(VeiculoFerroviario.Bitola.values());
		JCBbitola = new JComboBox<>();
		JCBbitola.setModel(DCBMbitola);
		JCBbitola.setSelectedItem(null);
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Jhead();
		Jbody();
		Jfooter();	
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(Jhead, BorderLayout.NORTH);
		cp.add(Jbody, BorderLayout.CENTER);
		cp.add(Jfooter, BorderLayout.SOUTH);
		
		/** Centraliza a tela
		 * 
		 */
		cp.setSize(cp.getMinimumSize().width, cp.getMinimumSize().height);
		pTela = new PosicaoTela();
		setLocation(pTela.Width(cp.getWidth()), pTela.Height(cp.getHeight()));
		setResizable(false);
		
		pack();
	}
	/** seção head da pagina onde fica o titulo e a imagem para a tela locomotiva
	 * 
	 */
	private void Jhead(){
		FormLayout layouthead = new FormLayout(
				"60dlu, pref, pref, 5dlu,pref ", // colunas
				"5dlu, pref, 5dlu"); // linha
		Jhead = new JPanel(layouthead);
		Jhead.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		JLimg    = new JLabel();
		JLimg.setBounds(0,0,50,50);
		ImageIcon imgIcon = new ImageIcon("res/locomotiva.png");
		Image img = imgIcon.getImage().getScaledInstance(JLimg.getWidth(),JLimg.getHeight(), Image.SCALE_DEFAULT);
		JLimg.setIcon(new ImageIcon(img));
		Jhead.add(JLimg,cc.xy(2,2));
		Font Font1 = new Font("Arial", Font.BOLD, 24);
		JLlocomotiva.setFont(Font1);
		Jhead.add(JLlocomotiva,cc.xy(5,2));	
	}
	/** seção body da pagina onde contém todos os campos da locomotiva
	 * 
	 */
	private void Jbody() {
		FormLayout layoutbody = new FormLayout(
				"5dlu, pref, pref, 5dlu, 50dlu, 20dlu, min,pref, 5dlu, 50dlu, 5dlu, min", // colunas
				"pref, 3dlu, pref, 3dlu, pref, pref, 3dlu, pref, 3dlu, pref"); // linha
		Jbody = new JPanel(layoutbody);
		Jbody.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		Jbody.add(JLClasse, cc.xy(3, 1));
		JTcampoClasse = new JTextField();;
		Jbody.add(JTcampoClasse, cc.xy(5, 1));
		Jbody.add(JLBitola, cc.xy(7, 1));
		Jbody.add(JCBbitola, cc.xy(10,1));
		Jbody.add(JLDescricao, cc.xy(3, 3));
		Jbody.add(JTcampoDescricao, cc.xyw(5,3,6));
		Jbody.add(JLPesomaxreb, cc.xy(3, 5));
		Jbody.add(JTcampoPesomaxreb, cc.xy(5, 5));
		Jbody.add(JLComploc, cc.xy(3,8));
		Jbody.add(JTcampoComploc, cc.xy(5,8));
	}
	/** Seção footer, onde contém os botões da aplicação
	 * 
	 */
	private void Jfooter() {
		FormLayout layoutfooter = new FormLayout(
				"5dlu, pref,5dlu, pref, 5dlu,pref, 5dlu,pref, min,pref", // colunas
				"pref, 5dlu, pref, 5dlu, pref"); // linha
		Jfooter = new JPanel(layoutfooter);
		Jfooter.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		JBSalvar = new JButton("Salvar");
		Jfooter.add(JBSalvar, cc.xy(6,3));
		JBNovo = new JButton("Novo");
		Jfooter.add(JBNovo,cc.xy(8, 3 ));
		JBExcluir = new JButton("Excluir");
		JBExcluir.setEnabled(false);
		Jfooter.add(JBExcluir,cc.xy(4,3));
		JBCancelar = new JButton("Cancelar");
		Jfooter.add(JBCancelar,cc.xy(2, 3));
		JBCancelar.addActionListener(Cancelar1);
		JBExcluir.addActionListener(Excluir1);
		JBSalvar.addActionListener(Salvar1);
		JBNovo.addActionListener(Novo1);
	}
	
	/** Ação para o botão Cancelar
	 * 
	 */
		ActionListener Cancelar1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
	
	/** Ação para o botão excluir 
	 * 
	 */
	ActionListener Excluir1 = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Factory f = new Factory();
			Controller c = f.getController();
			int confirm = fLayout.openConfirm("Tem certeza que deseja excluir o locomotiva?");
			if(confirm == 0){
				try {
					c.connect();
					c.remove(locomotiva);
					fLayout.openAlertInfo(null,"A locomotiva foi removida com sucesso!");
					modelo.removeLocomotiva(linha);
				}catch(Exception err){
					fLayout.openAlertError(null, err.getMessage());
					return;
				} finally{
					c.disconnect();
				}
				
				dispose();
			}
		}
	};
	/**Ação para o botão salvar
	 * 
	 */
	ActionListener Salvar1 = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int classe = 0;
			double pesomaxreb = 0;
			double comploc = 0;
			/**tratamento de erro de campo vazio e  preenchimento do usuario no campo classe
			 * 
			 */
			try {
				classe  = Integer.parseInt(JTcampoClasse.getText().trim());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo classe necessita ser preenchido corretamente!");
					return;
			}
			/**tratamento de erro de campo vazio e preenchimento do usuario no campo peso maximo rebocavel
			 * 
			 */
			try {
				pesomaxreb = Double.parseDouble(JTcampoPesomaxreb.getText());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo peso necessita ser preenchido corretamente!");
					return;
			}
			/**tratamento de erro de campo vazio e preenchimento do usuario no campo comprimento da locomotiva
			 * 
			 */
			try {
				 comploc = Double.parseDouble(JTcampoComploc.getText());
			}catch(NumberFormatException err ){
				JOptionPane.showMessageDialog(null,"O campo Comprimento da locomotiva necessita ser preenchido corretamente!");
					return;
			}
			VeiculoFerroviario.Bitola bitola = (Entidades.VeiculoFerroviario.Bitola) JCBbitola.getSelectedItem();
			String descricao = JTcampoDescricao.getText();;
			/** exceção para caso o campo Descrição esteja vazio
			 * 
			 */
			if(descricao.isEmpty()) {
				JOptionPane.showMessageDialog(null,"Preencha a Descrição, a locomotiva nÃ£o pode ser salva sem preencher!");
					return;
			/** exceção para caso o campo esteja vazio
			 * 
			 */
			}else if( JCBbitola.getSelectedIndex() == -1) {
				fLayout.openAlertWarning("Bitola","Bitola necessita ser preenchido corretamente!");
				return;
			}
			/** acão para caso clique no botao salvar e todos os campos estejam certos ,conexão com o banco de dados, salvar no banco de dados e desconectar do banco
			 * 
			 */
			Factory f = new Factory();
			Controller c = f.getController();
			try {
				Locomotiva l = f.getLocomotiva(bitola, classe, descricao, comploc, pesomaxreb);
				c.connect();
				if(locomotiva == null){
					c.create(l);//salvar no banco
					JOptionPane.showMessageDialog(null,"A locomotiva foi salva com sucesso!");			
					modelo.addLocomotiva(l);
				}
				else
				{
					c.update(l);
					JOptionPane.showMessageDialog(null,"A locomotiva foi alterada com sucesso!");
					modelo.updateLocomotiva(linha, l);
				}
				
			}catch(Exception err){
				fLayout.openAlertError("Erro",err.getMessage());
			}finally{
				c.disconnect();//desconectar do banco
			}
		
			dispose();
		}	
		
	};
	/**ação para o botão novo, limpa os campos
	 * 
	 */
	ActionListener Novo1 = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  	JTcampoClasse.setText(" ");
			  	JTcampoDescricao.setText(" ");
			  	JTcampoPesomaxreb.setText(" ");
				JCBbitola.setSelectedItem(null);
				JTcampoComploc.setText(" ");
		}
	};
}