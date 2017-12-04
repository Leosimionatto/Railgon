package Entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/** Representacao de uma Composicao
 * @author GGTRangers
 */
public class Composicao implements Comparable<Composicao> {
	
	/** Identificador unico da Composicao 
	 */
	protected int codigo;
	
	/** Descricao da composicao
	 */
	protected String descricao;
	
	/** Lista com todas as locomotivas que pertencem a Composicao 
	 */
	protected ArrayList<Locomotiva> locomotivas = new ArrayList<>();
	
	/** Lista com todos os vagoes que pertencem a Composicao
	 */
	protected ArrayList<Vagao> vagoes = new ArrayList<>();
	
	/** A soma dos comprimentos dos elementos da composicao
	 */
	protected double comprimento = 0.0;
	
	/** A bitola da primeira locomotiva inserida na composicao
	 */
	private char bitola;
	
	/** A soma do peso máximo das locomotivas
	 * 
	 */
	private double pesoMax = 0.0;
	
	/** Peso atual da composicao
	 */
	private double pesoAtual = 0.0;

	/** Maximo de locomotivas na composicao 
	 */
	private static final int MAXLOCOMOTIVA  = 3;
	
	/** Comprimento maximo de uma composicao
	 */
	private static final double MAXCOMPRIMENTO = 2000;
	
	/** Construtor Default da Composicao
	 */
	public Composicao(){
		
	}
	/** Construtor da Composicao passando uma locomotiva e uma descricao
	 */
	public Composicao(Locomotiva l, String descricao ){
		add(l);
		this.descricao = descricao;
	}
	
	/** Construtor da Composicao com todas as locomotivas e vagoes
	 */
	public Composicao(ArrayList<Locomotiva> l, ArrayList<Vagao> v, String descricao ){
		addLocomotivas(l);
		addVagoes(v);
		setDescricao(descricao);
	}
	
	/** Responsavel por obter o codigo da Composicao
	 * @return String Descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Responsavel por inserir a descricao na Composicao
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		if(descricao.length() > 200){
			throw new RuntimeException("A Descrição é muito grande!");
		}
		this.descricao = descricao;
	}
	
	/** Responsavel por obter a bitola da Composicao
	 * @return char com a bitola da Composicao 
	 */
	public char getBitola() {
		return bitola;
	}

	/** Responsavel por obter o peso maximo da Composicao
	 * @return double com o peso maximo da composicao
	 */
	public double getPesoMax() {
		return pesoMax;
	}

	/** Responsavel por obter o peso atual da Composicao
	 * @return double com o peso atual da Composicao
	 */
	public double getPesoAtual() {
		return pesoAtual;
	}

	/** Responsavel por obter o maximo de locomotivas que pode ser inserido em uma Composicao
	 * @return int com o maximo de Locomotivas permitidas em uma Composicao
	 */
	public static int getMaxLocomotiva() {
		return MAXLOCOMOTIVA;
	}
	
	/** Responsavel por obter o comprimento maximo de uma composicao
	 * @return double com o comprimento maximo permitido em uma composicao
	 */	
	public static double getMaxComprimento() {
		return MAXCOMPRIMENTO;
	}
	
	/** Responsavel por obter o codigo da Composicao
	 * @return String com o codigo da Composicao
	 */
	public int getCodigo() {
		return codigo;
	}
	
	/** Responsavel por inserir o codigo da composicao
	 * @param codigo 
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	/** Responsavel por obter a lista de Locomotivas da Composicao
	 * @return ArrayList com as Locomotivas da Composicao
	 */
	public ArrayList<Locomotiva> getLocomotivas() {
		return locomotivas;
	}
	
	/** Responsavel por inserir as Locomotivas na Composicao
	 * @param locomotivas 
	 */
	public void setLocomotivas(ArrayList<Locomotiva> locomotivas) {
		this.locomotivas = locomotivas;
	}
	
	/** Responsavel por Obter a lista de Vagoes da Composicao
	 * @return ArrayList com os Vagoes da composicao
	 */
	public ArrayList<Vagao> getVagoes() {
		return vagoes;
	}
	
	/** Responsavel por inserir os vagoes na composicao
	 * @param vagoes
	 */
	public void setVagoes(ArrayList<Vagao> vagoes) {
		this.vagoes = vagoes;
	}
	
	/** Responsavel por informar a quantidade de vagoes na composicao
	 * @return int
	 */
	public int getQtdVagao(){
		return this.vagoes.size();
	}
	
	/** Responsavel por informar a quantidade de locomotivas na composicao
	 * @return int
	 */
	public int getQtdLocomotiva(){
		return this.locomotivas.size();
	}

	/** Reponsavel por obter o comprimento da composicao
	 * @return double
	 */
	public double getComprimento() {
		return comprimento;
	}
	
	/** Responsavel por inserir um Vagao possa ser inserido na composicao
	 * @param v
	 */
	public void add(Vagao v){
		if(this.locomotivas.isEmpty()){
			throw new RuntimeException("Nenhuma Locomotiva na composição!");
		}
		//verifica se o peso do vagão vai exceder o maximo permitido
		double pesoAux = this.pesoAtual + v.getPesoMaxBitola();
		if (pesoAux > this.pesoMax){
			throw new RuntimeException("O vagão excede o peso máximo permitido da composição");
		}
		
		//verifica se o vagao ja esta na composicao
		if(vagoes.contains(v)){
			throw new RuntimeException("O vagao ja esta na composicao!");
		}
		valida(v);
		
		this.pesoAtual = pesoAux;
		this.vagoes.add(v);		
	}
	
	/** Responsavel por inserir a lista de vagoes na Composicao
	 * @param v
	 */
	public void addVagoes(ArrayList<Vagao> v){
		for(int i=0;i<v.size();i++){
			this.add(v.get(i));
		}
	}
	
	/** Responsavel por inserir uma Locomotiva dentro da composicao
	 * @param l
	 */
	public void add(Locomotiva l){
		//se a lista de locomotiva estiver vazia, pega a bitola da primeira, caso contrario, verifica se a bitola é do mesmo tipo da primeira
		if(this.locomotivas.isEmpty()){
			this.bitola = l.getBitola();
		}
		
		//limita a tres o numero de locomotivas em uma composicao
		if(locomotivas.size() >= Composicao.MAXLOCOMOTIVA){
			throw new RuntimeException("Numero de locomotivas excedente!");
		}
		
		//verifica se a locomotiva ja esta na composicao
		if(locomotivas.contains(l)){
			throw new RuntimeException("A Locomotiva ja esta na composicao!");
		}
		
		valida(l);		
		
		this.pesoMax     += l.getPesoMax();
		this.locomotivas.add(l);		
	}
	
	/** Responsavel por inserir a lista de locomotivas na Composicao
	 * @param l
	 */
	public void addLocomotivas(ArrayList<Locomotiva> l){
		for(int i=0;i<l.size();i++){
			this.add(l.get(i));
		}
	}
	
	/** Responsavel por validar caracteristicas da composicao em relacao aos componentes
	 * @param vf
	 */
	protected void valida(VeiculoFerroviario vf){
		//valida se o elemento a ser inserido possui a mesma bitola que a Composição
		if(!(this.bitola == vf.getBitola())){
			throw new RuntimeException("O elemento deve conter a mesma bitola da Composição!");
		}
		//verifica se o tamanho da composicao excede o maximo permitido
		double comprimentoAux = this.comprimento + vf.getComprimento();
		if(comprimentoAux > Composicao.MAXCOMPRIMENTO){
			throw new RuntimeException("Comprimento da composicao excede o maximo permitido!");
		}
		this.comprimento = comprimentoAux;
	}
	
	public int teste(Locomotiva l){
		return locomotivas.indexOf(l);
	}
	/** Responsavel por remover o objeto da composicao
	 * @param l
	 */
	public void remove(Locomotiva l){
		//se existe vagão dentro da composição
		if(vagoes.size() > 0){
			//se só existe uma locomotiva na composição
			if(locomotivas.size() == 1){
				throw new RuntimeException("A Composição não pode ficar sem Locomotiva!");
			}
			double pesoAux = this.pesoMax - l.getPesoMax();
			if(pesoAux < this.pesoAtual){
				throw new RuntimeException("A Locomotiva não pode ser removida: O peso dos vagoes ultrapassariam o limite!");
			}
		}
		//a locomotiva não existe na composicao
		if(!locomotivas.contains(l)){
			throw new RuntimeException("A Locomotiva não pertence a Composição");
		}
		
		locomotivas.remove(l);		
		
		if(locomotivas.size() == 0){
			this.bitola = ' ';
		}		
		
		this.comprimento-= l.getComprimento();
		this.pesoMax -= l.getPesoMax();
	}
	
	
	/** Responsavel por remover o objeto da composicao
	 * @param v
	 */
	public void remove(Vagao v){
		int pos = vagoes.indexOf(v);
		if(pos != -1){
			vagoes.remove(pos);
		}else{
			throw new RuntimeException("O Vagão não pertence a Composicão!");
		}
		this.comprimento-= v.getComprimento();
		this.pesoAtual-= v.getPesoMaxBitola();
	}

	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("C| ");
		sb.append(getDescricao());
		return sb.toString();
	}

	@Override
	public int compareTo(Composicao c) {
		if(c.getCodigo() > this.codigo){
			return 1;
		}else if(c.getCodigo() < this.codigo){
			return -1;
		}else{
			return 0;
		}
	}
}
 