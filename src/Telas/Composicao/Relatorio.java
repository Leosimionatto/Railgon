package Telas.Composicao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Entidades.Composicao;
import Entidades.Locomotiva;
import Entidades.Vagao;
import Repositorio.Controller;
import Repositorio.Factory;
import Repositorio.FactoryLayout;

public class Relatorio {
	
	private FactoryLayout fLayout = new FactoryLayout();
	private Factory f = new Factory();
	private Controller c = f.getController();
	
	private List<Composicao> lComposicoes;
	private Composicao composicao;
	private List<Locomotiva> lLocomotivas;
	private Locomotiva locomotiva;
	private List<Vagao> lVagoes;
	private Vagao vagao;
	
		public void GerarRelatorio(){
			try{
				c.connect();
				lComposicoes = c.selectComposicoes();
				 try
				    {
					 	String formatoData = "ddMMyyyy";
						String formatoHora = "hmmss";
						String data, hora;
						
						java.util.Date horaAtual = new java.util.Date();;
						SimpleDateFormat formata = new SimpleDateFormat(formatoData);
						data = formata.format(horaAtual);
						formata = new SimpleDateFormat(formatoHora);
						hora = formata.format(horaAtual);
						
				    	String sFileName = "RelatorioComposicao-" + data + "-" + hora + ".txt";
				    	sFileName = sFileName.replace('/', '-');
				        FileWriter arquivo = new FileWriter(sFileName);
				        PrintWriter gravarArq = new PrintWriter(arquivo);
				        
				        int i = 0;
				        int j = 0;
				        while(i < lComposicoes.size()){
				        	composicao = lComposicoes.get(i);
				        	
				        	gravarArq.printf("============================================================%n");
					        gravarArq.printf("Composição:    " + composicao.getDescricao() + "%n");
					        gravarArq.printf("Bitola:        " + composicao.getBitola()    + "%n");
					        gravarArq.printf("Comprimento:   " + composicao.getComprimento() + "%n");
					        gravarArq.printf("Peso Atual:    " + composicao.getPesoAtual() + "%n");
					        gravarArq.printf("Peso Máximo:   " + composicao.getPesoMax() + "%n");
					        gravarArq.printf("# Locomotivas: " + composicao.getQtdLocomotiva() + "%n");
					        gravarArq.printf("# Vagoes:      " + composicao.getQtdVagao() + "%n");
					        
					        lLocomotivas = composicao.getLocomotivas();
					        lVagoes = composicao.getVagoes();
					        
					        gravarArq.printf("----------------------------------------------------%n");
					        
							while(j < lLocomotivas.size()){
								locomotiva = lLocomotivas.get(j);
								gravarArq.printf("Locomotiva | " + locomotiva.getDescricao() + "%n");
								j++;
					        }
							
							j = 0;
							
					        while(j < lVagoes.size()){
					        	vagao = lVagoes.get(j);
								gravarArq.printf("Vagão      | " + vagao.getIdentificacao() + "%n");
					        	j++;
					        }
					        
					        gravarArq.printf("%n----------------------------------------------------");
					        gravarArq.printf("%n============================================================%n%n");
					        j = 0;
					        i++;
				        }
				        

				        arquivo.flush();
				        arquivo.close();
				        fLayout.openAlertInfo(null, "Relatório Gerado.");
				    }
				    catch(IOException e)
				    {
				         e.printStackTrace();
				    } 
			}
			catch(Exception e){
				fLayout.openAlertError("ERRO DE CONEXÃO", e.getMessage());
			}
			finally{
				c.disconnect();
			}
		   
		}
	 
}
