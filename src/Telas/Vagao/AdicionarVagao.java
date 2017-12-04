package Telas.Vagao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import Entidades.Vagao;
import Entidades.VeiculoFerroviario;

import Repositorio.Factory;
import Repositorio.FactoryLayout;
import Repositorio.Controller;

import Telas.Comum.PosicaoTela;

public class AdicionarVagao extends JFrame{
	
	private PosicaoTela pTela;
	
	private VagaoTableModel modelo;
	private Vagao vagao = null;
	private int linha;
	
	//declarando botoes
	private JButton JBExcluir;
	private JButton JBSalvar;
	private JButton JBNovo;
	private JButton JBCancelar;
	
	//declarando campos de texto
	private JTextField JTBitola;
	private JTextField JTComprimento;
	private JTextField JTTipo;
	private JTextField JTSubtipo;
	private JTextField JTProprietario;
	
	//declarando as labels 
	private JLabel JLTitulo;
	private JLabel JLBitola;
	private JLabel JLComprimento;
	private JLabel JLTipo;
	private JLabel JLSubtipo;
	private JLabel JLProprietario;
	private JLabel JLimg;
	private JLabel JLlocomotiva;

	//declarando as combobox
	//bitola
	private DefaultComboBoxModel<VeiculoFerroviario.Bitola> DCBMbitola;
	private JComboBox<VeiculoFerroviario.Bitola> JCBBitola;
	
	//subtipo
	private DefaultComboBoxModel<Vagao.SubTipo> DCBMSubTipo;
	private JComboBox<Vagao.SubTipo> JCBSubTipo;
	
	//tipo
	private DefaultComboBoxModel<Vagao.Tipo> DCBMTipo; 
	private JComboBox<Vagao.Tipo> JCBTipo;
	
	//declarando seções da janela
	JPanel Jhead;
	JPanel Jbody;
	JPanel Jfooter;
	
	//declarando factory para chamada de telas.
	FactoryLayout tela;
	//JPanel Jprincipal;
	
	//declarando factory para chamada de telas.
	private FactoryLayout fLayout = new FactoryLayout();
	
	//construtor para que seja visualizado pelo usuario na tela da composição(readonly)
	public AdicionarVagao(Vagao v) {
		this();
		vagao = v;

		//textfield
		this.JTComprimento.setText(String.valueOf(vagao.getComprimento()));
		this.JTProprietario.setText(String.valueOf(vagao.getProprietario()));
		
		//comboBox		
		this.JCBBitola.setSelectedItem(VeiculoFerroviario.Bitola.valueOf(String.valueOf(vagao.getBitola())));
		this.JCBTipo.setSelectedItem(Vagao.Tipo.valueOf(String.valueOf(vagao.getTipo())));
		this.JCBSubTipo.setSelectedItem(Vagao.SubTipo.valueOf(String.valueOf(vagao.getSubTipo())));
		
		//deixando readonly
		this.JTComprimento.setEnabled(false);		
		this.JTProprietario.setEnabled(false);
		this.JCBBitola.setEnabled(false);
		this.JCBTipo.setEnabled(false);
		this.JCBSubTipo.setEnabled(false);
		JBExcluir.setVisible(false);
		JBNovo.setVisible(false);
		JBSalvar.setVisible(false);
		JBCancelar.setVisible(false);
		
		//setando o titulo
		this.setTitle(v.getIdentificacao());
	}
	
	//no caso de ter vagao adicionado, o botão excluir ira aparecer na tela
	//Setando campos do vagao para caso haja adicionado, ira poder excluir.
	public AdicionarVagao(VagaoTableModel md, int linhaSelecionada, Vagao v) {
		this(md);
		vagao = v;
		linha = linhaSelecionada;
		
		//textfield
		this.JTComprimento.setText(String.valueOf(vagao.getComprimento()));
		this.JTProprietario.setText(String.valueOf(vagao.getProprietario()));
		
		this.JTProprietario.setEnabled(false);
		
		//comboBox		
		this.JCBBitola.setSelectedItem(VeiculoFerroviario.Bitola.valueOf(String.valueOf(vagao.getBitola())));
		this.JCBTipo.setSelectedItem(Vagao.Tipo.valueOf(String.valueOf(vagao.getTipo())));
		this.JCBSubTipo.setSelectedItem(Vagao.SubTipo.valueOf(String.valueOf(vagao.getSubTipo())));
		
		this.JCBBitola.setEnabled(false);
		this.JCBTipo.setEnabled(false);
		this.JCBSubTipo.setEnabled(false);
		
		//botão
		JBExcluir.setVisible(true); 
		
		//setando o titulo da janela
		this.setTitle("Alterar Vagão");
	}
	
	public AdicionarVagao(VagaoTableModel md) {
		this(); // ajusta título
		modelo = md;
		this.setTitle("Adicionar Vagao");
	}
	
	//metodo para intanciar meus componentes e seta as secoes na janela
	public AdicionarVagao() {
		super();
		
		//campos de texto
		JTComprimento = new JTextField();
		JTProprietario = new JTextField();
		
		//comboBox
		//bitola
		DCBMbitola = new DefaultComboBoxModel<>(VeiculoFerroviario.Bitola.values());
		JCBBitola = new JComboBox<>();
		JCBBitola.setModel(DCBMbitola);
		
		//subtipo
		DCBMSubTipo = new DefaultComboBoxModel<>(Vagao.SubTipo.values());
		JCBSubTipo = new JComboBox<>();
		JCBSubTipo.setModel(DCBMSubTipo);
		
		//tipo
		DCBMTipo = new DefaultComboBoxModel<>(Vagao.Tipo.values());
		JCBTipo = new JComboBox<Vagao.Tipo>();
		JCBTipo.setModel(DCBMTipo);
		
		//labels
		JLTitulo = new JLabel("Cadastrar Vagão");
		JLBitola = new JLabel("Bitola:");
		JLComprimento = new JLabel("Comprimento(m):");
		JLProprietario = new JLabel("Proprietário:");
		JLSubtipo = new JLabel("Subtipo:");
		JLTipo = new JLabel("Tipo:");
		JLlocomotiva = new JLabel("Vagão");
		
		//botões
		JBExcluir = new JButton("Excluir");
		JBNovo = new JButton("Novo");
		JBSalvar = new JButton("Salvar");
		JBCancelar = new JButton("Cancelar");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Jhead();
		Jbody();
		Jfooter();	
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(Jhead, BorderLayout.NORTH);
		cp.add(Jbody, BorderLayout.CENTER);
		cp.add(Jfooter, BorderLayout.SOUTH);
		
		cp.setSize(cp.getMinimumSize().width, cp.getMinimumSize().height);
		pTela = new PosicaoTela();
		setLocation(pTela.Width(cp.getWidth()), pTela.Height(cp.getHeight()));
		setResizable(false);
		
		pack();
		tela = new FactoryLayout();
	}
	
	private void Jhead() {
		FormLayout layouthead = new FormLayout(
				"60dlu, pref, pref, 5dlu,pref ", // colunas
				"5dlu, pref, 5dlu"); // linha
		Jhead = new JPanel(layouthead);
		Jhead.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		JLimg    = new JLabel();
		JLimg.setBounds(0,0,50,50);
		ImageIcon imgIcon = new ImageIcon("res/vagao.png");
		Image img = imgIcon.getImage().getScaledInstance(JLimg.getWidth(),JLimg.getHeight(), Image.SCALE_DEFAULT);
		JLimg.setIcon(new ImageIcon(img));
		Jhead.add(JLimg,cc.xy(2,2));
		Font Font1 = new Font("Arial", Font.BOLD, 24);
		JLlocomotiva.setFont(Font1);
		Jhead.add(JLlocomotiva,cc.xy(5,2));	
	}

	//conteudo do formulário
	private void Jbody() {
		FormLayout layoutbody = new FormLayout(
				"5dlu, pref, pref, 5dlu, 50dlu, 20dlu, min,pref, 5dlu, 50dlu, 5dlu, min", // colunas
				"pref, 3dlu, pref, 3dlu, pref, pref, 3dlu, pref, 3dlu, pref"); // linha
		//layoutbody.setRowGroups(new int[][] { { 1, 3, 5 } });
		Jbody = new JPanel(layoutbody);
		Jbody.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		//proprietario
		Jbody.add(JLProprietario, cc.xy(3, 1));
		JTProprietario= new JTextField();
		Jbody.add(JTProprietario, cc.xy(5, 1));
		
		//bitola
		Jbody.add(JLBitola, cc.xy(7, 1)); 
		Jbody.add(JCBBitola, cc.xy(10, 1));
		
		//comprimento
		Jbody.add(JLComprimento, cc.xy(3, 3));
		JTComprimento = new JTextField();
		Jbody.add(JTComprimento, cc.xyw(5,3,6));
		
		//Subtipo
		Jbody.add(JLSubtipo, cc.xy(3, 5));
		Jbody.add(JCBSubTipo, cc.xy(5,5));
		
		//Tipo
		Jbody.add(JLTipo, cc.xy(3, 8)); 
		Jbody.add(JCBTipo, cc.xy(5, 8));
	}
	
	//botoes do formulario
	private void Jfooter() {
		FormLayout layoutfooter = new FormLayout(
				"5dlu, pref,5dlu, pref, 5dlu,pref, 5dlu,pref, min,pref", // colunas
				"pref, 5dlu, pref, 5dlu, pref"); // linha
		//layoutbody.setRowGroups(new int[][] { { 1, 3, 5 } });
		Jfooter = new JPanel(layoutfooter);
		Jfooter.setBorder(new TitledBorder(" "));
		CellConstraints cc = new CellConstraints();
		//botão salvar
		JBSalvar = new JButton("Salvar");
		Jfooter.add(JBSalvar, cc.xy(6,3));
		
		//botão novo
		JBNovo = new JButton("Novo");
		Jfooter.add(JBNovo,cc.xy(8, 3 ));
		
		//botão excluir
		JBExcluir = new JButton("Excluir");
		JBExcluir.setEnabled(false);
		Jfooter.add(JBExcluir,cc.xy(4,3));
		
		//botão cancelar
		JBCancelar = new JButton("Cancelar");
		Jfooter.add(JBCancelar,cc.xy(2, 3));
		
		//ações dos botões
		JBSalvar.addActionListener(jbSalvarActLt);
		JBNovo.addActionListener(jbNovoActLt);
		JBExcluir.addActionListener(jbExCluirActLt);
		JBCancelar.addActionListener(jbCancelarActLt);
	}
	
	//ação botão Salvar
	ActionListener jbSalvarActLt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//textField
			//comprimento
			double valorComprimento = 0;
			try {
				valorComprimento = Double.parseDouble(JTComprimento.getText());
			}catch(NumberFormatException err ){
				fLayout.openAlertError(null,"O campo comprimento necessita ser preenchido corretamente!");
				return;
			}
			String valorProprietario = JTProprietario.getText();
			
			//comboBox
			VeiculoFerroviario.Bitola valorBitola = (Entidades.VeiculoFerroviario.Bitola) JCBBitola.getSelectedItem();
			Vagao.SubTipo valorSubtipo = (Entidades.Vagao.SubTipo) JCBSubTipo.getSelectedItem();
			Vagao.Tipo valorTipo = (Entidades.Vagao.Tipo) JCBTipo.getSelectedItem();
			
			if(valorProprietario.isEmpty() || valorProprietario.length() != 6) {
				fLayout.openAlertError(null,"Preencha a propritário corretamente!");
				return;
			} else if(JCBBitola.getSelectedIndex() == -1) {
				fLayout.openAlertError("Bitola","A Bitola necessita ser preenchido corretamente.");
				return;
			} else if(JCBSubTipo.getSelectedIndex() == -1) {
				fLayout.openAlertError("Subtipo","O Subtipo necessita ser preenchido corretamente.");
				return;
			} else if(JCBTipo.getSelectedIndex() == -1) {
				fLayout.openAlertError("Tipo","O Tipo necessita ser preenchudo corretamente.");
				return;
			}
			
			//acao para caso clique no botao salvar e todos os campos estejam certos
			//conexao com o banco de dados, salvar no banco de dados e desconectar do banco
			Factory f = new Factory();
			Controller c = f.getController();
			try {
				Vagao v = f.getVagao(valorBitola, valorTipo, valorSubtipo, valorProprietario.toCharArray(), valorComprimento);
				c.connect();
				if(vagao == null){
					c.create(v);//salvar no banco
					fLayout.openAlertWarning(null,"Vagão salvo com sucesso!");
					modelo.addVagao(v);
				}
				else {
					c.update(v);
					fLayout.openAlertWarning(null,"Vagãos alterado com sucesso!");
					modelo.updateVagao(linha, v);
				}
				
			}catch(Exception err){
				fLayout.openAlertWarning(null,err.getMessage());	
			}finally{
				c.disconnect();//desconectar do banco
			}
			dispose();
		}	
		
	};
	
	//ação botao Novo
	ActionListener jbNovoActLt = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				JTComprimento.setText("");
				if(vagao == null){
					JTProprietario.setText("");
					JCBBitola.setSelectedItem(null);
					JCBSubTipo.setSelectedItem(null);
					JCBTipo.setSelectedItem(null);
				}
		}
	};
	
	//ação botão excluir
	ActionListener jbExCluirActLt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Factory f = new Factory();
			Controller c = f.getController();
			int confirm = fLayout.openConfirm("Tem certeza que deseja excluir o vagão?");
			if(confirm == 0) {
				try {
					c.connect();
					c.remove(vagao);
					fLayout.openAlertInfo(null,"O vagão foi removido com sucesso!");
					modelo.removeVagao(linha);
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
	
	//ação botão Cancelar
	ActionListener jbCancelarActLt = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}; 
	
	public JPanel GetPanel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}