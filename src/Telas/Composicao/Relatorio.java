package Telas.Composicao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import Repositorio.FactoryLayout;

public class Relatorio {
	protected FactoryLayout FLayout = new FactoryLayout();
		public void GerarRelatorio(){
			   try
			    {
			    	String data = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(new Date());
			    	String sFileName = "RelatorioComposicao-" + data + ".txt";
			    	sFileName = sFileName.replace('/', '-');
			        FileWriter arquivo = new FileWriter(sFileName);
			        PrintWriter gravarArq = new PrintWriter(arquivo);

			        gravarArq.printf("============================================================%n");
			        gravarArq.printf("Age");
			        gravarArq.printf("%n============================================================%n");
			        //generate whatever data you want

			        arquivo.flush();
			        arquivo.close();
			        FLayout.openAlertInfo(null, "Relatório Gerado.");
			    }
			    catch(IOException e)
			    {
			         e.printStackTrace();
			    } 
		}
	 
}
