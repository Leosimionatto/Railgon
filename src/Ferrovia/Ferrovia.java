package Ferrovia;

import java.util.ArrayList;

import Entidades.*;
import Repositorio.*;
import Telas.Composicao.ComposicaoTableModel;
public class Ferrovia {
	
	public static void main (String args[]){
		
		Factory f = new Factory();
		FactoryLayout fLayout = new FactoryLayout();
		
		
		Controller control = f.getController();
		control.connect();

		char[] prop = {'1','2','3','4','5'};
		char[] prop1 = {'1','4','7','4','5'};
		char[] prop2 = {'1','5','9','4','5'};
		
		try{
			
			Vagao v1 = f.getVagao(Vagao.Bitola.G, Vagao.Tipo.A, Vagao.SubTipo.C, prop, 80.8);
			Vagao v2 = f.getVagao(Vagao.Bitola.G, Vagao.Tipo.A, Vagao.SubTipo.D, prop, 80.8);
			Vagao v3 = f.getVagao(Vagao.Bitola.G, Vagao.Tipo.C, Vagao.SubTipo.C, prop, 80.8);

			control.create(v1);
			control.create(v2);
			control.create(v3);
			
			System.out.println("foi");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		/*try{
			
			ArrayList<Composicao> c = control.selectComposicoes();
			
			for(int j=0; j < c.size(); j++){
				ArrayList<Locomotiva> l = c.get(0).getLocomotivas();
				ArrayList<Vagao> v = c.get(0).getVagoes();
				
				System.out.println(c.get(j).getCodigo());
				System.out.println(c.get(j).getDescricao());
				
				for(int i=0; i < l.size(); i++){
					System.out.println(l);
				}
				
				for(int i=0; i < v.size(); i++){
					System.out.println(v);
				}
			}					
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		*/
	}
}	