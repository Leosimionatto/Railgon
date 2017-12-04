
package Entidades;

/** Repesentacao de um Vagao
 * @author GGTRangers
 * @see VeiculoFerroviario
 */
public class Vagao extends VeiculoFerroviario {
	
	public static enum Tipo {G,P,T,F,I,H,A,C};
	public static enum SubTipo {
		D,P,F,M,T,S,H,C,B,N,Q,E,R,G,A,L,V;
		
		protected boolean valida( Tipo t){
			
			String tipo = t.name();
			boolean ok = false;
			switch (tipo) {
				case "G":
					if("DPFMTSHCBNQ".contains(this.name())){
						ok = true;
					}
				break;
				case "P":
					if("MEDCRTGPBANQ".contains(this.name())){
						ok = true;
					}
				break;
				case "T":
					if("CSPFAGNQ".contains(this.name())){
						ok = true;
					}
				break;
				case "F":
					if("RSMEHLPVNQ".contains(this.name())){
						ok = true;
					}
				break;
				case "I":
					if("CFNQ".contains(this.name())){
						ok = true;
					}
				break;
				case "H":
					if("FPETANQ".contains(this.name())){
						ok = true;
					}
				break;
				case "A":
					if("CMRVDNQ".contains(this.name())){
						ok = true;
					}
				break;
				case "C":
					if("CBNQ".contains(this.name())){
						ok = true;
					}
				break;
			}
			return ok;
		}
	};

	/** Tipo	
	 */
	protected Tipo tipo;
	
	/** SubTipo
	 */
	protected SubTipo subTipo;
	
	/** Proprietario 
	 */
	protected char[] proprietario;
	
	/** Concatenacao de Tipo,SubTipo e o proprietario
	 */
	protected String identificacao;
	
	/** Construtor do Vagao
	 * @param bitola
	 * @param tipo
	 * @param subtipo
	 * @param proprietario
	 * @param comprimento
	 */
	public Vagao(Bitola bitola, Tipo tipo, SubTipo subtipo, char[] proprietario, double comprimento){
		setBitola(bitola);
		setTipo(tipo);
		setSubTipo(subtipo);
		setProprietario(proprietario);
		setComprimento(comprimento);
		setIdentificacao(tipo, subtipo, bitola, proprietario);
	}
	
	/** Responsavel por costruir um vagao com a bitola, a identificacao e o comprimento
	 * @param bitola Bitola que o vagao esta usando
	 * @param identificacao String com o Tipo,Subtipo e o proprietario concatenados 
	 * @param comprimento Comprimento do vagao
	 */
	public Vagao(String identificacao, double comprimento){
		setIdentificacao(identificacao);
		setComprimento(comprimento); 
	}	
	
	/** Responsavel por obter a identificacao
	 * @return char[]
	 */
	public String getIdentificacao() {
		return identificacao;
	}

	/** Responsavel por inserir a identificacao
	 * @param identificacao
	 */
	protected void setIdentificacao(String identificacao) {
		setTipo(Tipo.valueOf(identificacao.substring(0,1)));
		setSubTipo(SubTipo.valueOf(identificacao.substring(1,2)));
		setBitola(Bitola.valueOf(identificacao.substring(2,3)));
		setProprietario(identificacao.substring(3).toCharArray());
		
		this.identificacao = identificacao;
	}
	
	/** Responsavel por inserir a identificadao a partir do tipo, subtipo, bitola e proprietario
	 * @param identificacao
	 */
	protected void setIdentificacao(Tipo tipo, SubTipo subTipo, Bitola bitola, char[] proprietario) {
		StringBuilder sb = new StringBuilder();
		sb.append(tipo.name());
		sb.append(subTipo.name());
		sb.append(bitola.name());
		sb.append(proprietario);
		this.identificacao = sb.toString();
	}
	
	/** Responsavel por obter o tipo do Vagao
	 * @return char com o tipo do vagaor
	 */
	public char getTipo() {
		return tipo.name().charAt(0);
	}
	
	/** Responsavel por inserir o tipo do Vagao
	 * @param tipo
	 */
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	/** Responsavel por obter o subTipo do Vagao
	 * @return char com o subTipo do vagao
	 */
	public char getSubTipo() {
		return subTipo.name().charAt(0);
	}
	
	/** Responsavel por inserir o subTipo do Vagao
	 * @param subTipo
	 */
	public void setSubTipo(SubTipo subTipo) {
		if(!subTipo.valida(this.tipo)){
			throw new RuntimeException("Subtipo inválido para o tipo escolhido!");
		}
		this.subTipo = subTipo;
	}
	
	/** Responsavel por obter o proprietario do Vagao 
	 * @return char com o proprietario do vagao
	 */
	public char[] getProprietario() {
		return proprietario;
	}
	
	/** Responsavel por inserie o proprietario do Vagao
	 * @param proprietario
	 */
	public void setProprietario(char[] proprietario) {
		this.proprietario = proprietario;
	}
	
	public void teste(String teste){
		/*Bitola t;
		t = Bitola.valueOf(teste);*/
		System.out.println(teste);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("V | ");
		sb.append(getIdentificacao());
		return sb.toString();
	}

	@Override
	public int compareTo(VeiculoFerroviario vf) {
		Vagao v = (Vagao) vf;
		return v.getIdentificacao().compareTo(this.identificacao);
	}
	
	@Override
	public boolean equals(Object obj){
		Vagao v = (Vagao) obj;
		
		if(this.identificacao.equals(v.getIdentificacao()) &
		   this.id				== v.getId() &
		   this.ordemComposicao == v.getOrdemComposicao() &
		   this.comprimento		== v.getComprimento()){
			return true;
		}
		return false;
	}
}
