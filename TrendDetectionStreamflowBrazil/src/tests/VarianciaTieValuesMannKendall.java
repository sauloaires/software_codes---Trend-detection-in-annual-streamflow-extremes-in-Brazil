package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class VarianciaTieValuesMannKendall {
	
	 public static double executar(double [] valuetot){
		    
		    /*ArrayList<Double> valores=new ArrayList<Double>();
		    for (int i=0;i<valuetot.length;i++ ){
		      valores.add(valuetot[i]);
		    }
		    Collections.sort(valores);*/
		    
		    
		    Map<String,Integer> gruposRepetidosQuantidade=new HashMap<String,Integer>();
		    
		    ArrayList<String> gruposRepetidos=new ArrayList<String>();
		    for (int i=0;i<valuetot.length;i++ ){
		      
		      String valstr=String.valueOf(valuetot[i]);
		      if(!gruposRepetidosQuantidade.containsKey(valstr)){
		        gruposRepetidosQuantidade.put(valstr, 1);
		      }else{
		        if(!gruposRepetidos.contains(valstr)){
		          gruposRepetidos.add(valstr);
		        }
		        
		        Integer val=gruposRepetidosQuantidade.get(valstr);
		        val++;
		        gruposRepetidosQuantidade.put(valstr, val);
		        int valNovoConfirma=gruposRepetidosQuantidade.get(valstr);
		        System.out.println(valNovoConfirma);
		      }
		      
		    }
		    
		    double somaValorTp=0.0;
		    for (int igroup=0;igroup<gruposRepetidos.size();igroup++ ){
		      double tp=gruposRepetidosQuantidade.get(gruposRepetidos.get(igroup));
		      double valtp=tp*(tp-1)*((2*tp)+5);
		      somaValorTp=somaValorTp+valtp;
		      
		    }
		    
		    System.out.println("Finalizou");
		    
		    return somaValorTp;
		    
		    
		  }
		  
		  public static void main(String[] arg){
		    
		    double [] valuetot=new double [9];
		    valuetot[0]=23.0;
		    valuetot[1]=24.0;
		    valuetot[2]=29.0;
		    valuetot[3]=6.0;
		    valuetot[4]=29.0;
		    valuetot[5]=24.0;
		    valuetot[6]=24.0;
		    valuetot[7]=29.0;
		    valuetot[8]=23.0;
		    
		    double valor1=VarianciaTieValuesMannKendall.executar(valuetot);
		    //23  24  29  6 29  24  24  29  23

		    
		    double [] valuetotRenato=new double [8];
		    valuetotRenato[0]=1.0;
		    valuetotRenato[1]=1.0;
		    valuetotRenato[2]=2.0;
		    valuetotRenato[3]=2.0;
		    valuetotRenato[4]=3.0;
		    valuetotRenato[5]=5.0;
		    valuetotRenato[6]=3.0;
		    valuetotRenato[7]=3.0;
		    double valor2=VarianciaTieValuesMannKendall.executar(valuetotRenato);
		    
		    System.out.println("valor  = "+valor1);
		    
		  }
		  
}
