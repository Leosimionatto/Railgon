package Entidades;

/** Representacao de uma Locomotiva
 * @author GGTRangers
 * @see VeiculoFerroviario
 */
public class Locomotiva extends VeiculoFerroviario{
	
	/** Responsavel por guardar a classe da Locomotiva
	 */
	protected int classe;
	
	/** Responsavel por guardar a descricao da Locomotiva
	 */
	protected String descricao;
	
	/** Responsavel por guardar o peso maximo admissivel pela Locomotiva
	 */
	protected double pesoMax;	
	
	/** Responsavel por instanciar uma Locomotiva
	 * @param bitola
	 * @param classe
	 * @param descricao
	 * @param comprimento
	 * @param pesoMax
	 */
	public Locomotiva(Bitola bitola, int classe, String descricao, double comprimento, double pesoMax){
		setBitola(bitola);
		setClasse(classe);
		setDescricao(descricao);
		setComprimento(comprimento);
		setPesoMax(pesoMax);
	}
	
	public Locomotiva(String bitola, int classe, String descricao, double comprimento, double pesoMax){
		setBitola(bitola);
		setClasse(classe);
		setDescricao(descricao);
		setComprimento(comprimento);
		setPesoMax(pesoMax);
	}
	
	/** Responsavel por obter a classe da Locomotiva
	 * @return int classe da Locomotivaa
	 */
	public int getClasse() {
		return classe;
	}
	
	/** Responsavel por inserir o valores da classe na Locomotiva
	 * @param classe Inteiro com a classe da Locomotiva
	 */
	public void setClasse(int classe) {
		this.classe = classe;
	}
	
	/** Responsavel por obter a descricao da Locomotiva
	 * @return String com a descricao da Locomotiva
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/** Responsavel por inserir o valors da descricao na Locomotiva
	 * @param descricao String com a descricao da Locomotiva
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/** Responsavel por obter o peso maximo admissivel pela Locomotiva
	 * @return char com o peso maximo admissivel pela Locomotiva
	 */
	public double getPesoMax() {
		return pesoMax;
	}
	
	/** Responsavel por inserir o peso maximo inserido pela Locomotiva
	 * @param pesoMax
	 */
	public void setPesoMax(double pesoMax){
		this.pesoMax = pesoMax;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("L | ");
		sb.append(getClasse());				
		return sb.toString();
	}

	public int compareToTeste(Locomotiva l) {
		if(l.getClasse() > this.classe){
			return 1;
		}else if(l.getClasse() < this.classe){
			return -1;
		}else{
			return 0;
		}
	}

	@Override
	public int compareTo(VeiculoFerroviario vf){
		Locomotiva l = (Locomotiva) vf;
		if(l.getClasse() > this.classe){
			return 1;
		}else if(l.getClasse() < this.classe){
			return -1;
		}else{
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object obj){
		Locomotiva l = (Locomotiva) obj;
		
		if(this.classe			== l.getClasse()	  &
		   this.pesoMax			== l.getPesoMax()	  &
		   this.comprimento		== l.getComprimento() &
		   this.id 				== l.getId()		  &
		   this.ordemComposicao == l.getOrdemComposicao() &
		   this.getBitola() 	== l.getBitola()	  &
		   this.descricao.equals(l.getDescricao())){
			return true;
		}
		return false;
	}
}
