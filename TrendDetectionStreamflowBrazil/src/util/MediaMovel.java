package util;

import java.util.ArrayList;

public class MediaMovel {
	private double [] valor;
	private String [] data;
	private int movel;
	private double [] media;
	private ArrayList<Double> valor2;
	private ArrayList<String> datamovel=new ArrayList<String>();
	private int [] mes;
	private int [] ano;
	private int [] mesfalha=new int[12];
	private int [] valfalha;
	
	public MediaMovel(double [] valor,int movel) {
	 this.valor = new double [valor.length];
	 this.valor =valor;
	 this.movel=movel;
	 this.data = new String [valor.length];
	     for (int i=0;i<valor.length;i++){
		 this.data[i]="0";
		 }
	 //this.media=new double [valor.length-this.movel];
	// this.setValfalha(new int [this.valor.length-this.movel+1]);
	 
	 }
	
     
	  
	 public MediaMovel(ArrayList<Double> valor,int movel) {
		 this.valor = new double [valor.size()];
		 for (int i=0;i<valor.size();i++){
		 this.valor[i]=	 valor.get(i);
		 }
		 
		this.movel=movel; 
		//this.media=new double [this.valor.length-this.movel];
		//this.setValfalha(new int [this.valor.length-this.movel+1]);
	 }
	 public MediaMovel(double [] valor,int movel,String [] data) {
	     //this.mes = new int [mes.length];
	     //this.mes =mes;
	     this.valor = new double [valor.length];
		 this.valor =valor;
		 this.movel=movel;
		 this.data= new String [data.length];
		 this.data =data;
		 //this.media=new double [valor.length-this.movel];
		 //this.setValfalha(new int [this.valor.length-this.movel+1]);
		 }
	
	    public MediaMovel(double [] valor,int movel,int []mes,String [] data) {
	     this.mes = new int [mes.length];
	     this.mes =mes;
	     this.valor = new double [valor.length];
		 this.valor =valor;
		 this.movel=movel;
		 this.data= new String [data.length];
		 this.data =data;
		 //this.media=new double [valor.length-this.movel];
		 //this.setValfalha(new int [this.valor.length-this.movel+1]);
		 }
		
	     
		  
		 public MediaMovel(ArrayList<Double> valor,int movel,int []mes,String [] data) {
			 this.mes = new int [mes.length];
			 this.mes =mes;
			 this.valor = new double [valor.size()];
			 for (int i=0;i<valor.size();i++){
			 this.valor[i]=	 valor.get(i);
			 }
			 
			this.movel=movel; 
			this.data= new String [data.length];
			 this.data =data;
			//this.media=new double [this.valor.length-this.movel];
			//this.valfalha(new int [this.valor.length-this.movel+1]);
		 } 
	 
	
		 
		 
		 
   public double[] calcmedia(){
	   
	   
	   if(this.valor.length > this.movel){
	     int [] valfalha2=new int [this.valor.length-this.movel+1];
	     int [] mesfalha = new int [12];
		 for(int k=0;k<12;k++){
		 mesfalha[k]=0;	 
		 }
		 int ival=0;
		 ArrayList<Double> media=new ArrayList<Double>();
		 ArrayList<String> dataminini=new ArrayList<String>();
		 for (int i=0;i<(this.valor.length-this.movel+1);i++){	
				 
			     double soma=0;
				 int n=0;
				 int nfalha=0;
				 
				 	 for (int j=0;j<this.movel;j++){
					 
						if(this.valor[i+j] != -99999.0){
						soma=soma+this.valor[i+j];
						n++;	 
						}else{
							
							//if(this.mes[i+j] != 0){
							//mesfalha[this.mes[i+j]-1]++;
							//}
						nfalha++;	
						}
				    
				 	 }
		
				 			 	 
		 //if(nfalha != this.movel){
		 if(nfalha == 0){
		 double med = (soma/(n*1.0));
		 media.add(med);
		 dataminini.add(this.data[i]);
		 //this.media[ival]=(soma/(n*1.0));
		 valfalha2[ival]=nfalha;
		 ival++;	 
		 }
		 
			 
		 }
	
		 
    this.media=new double [media.size()];	 
    for (int j=0;j<media.size();j++){
    this.media[j]=media.get(j);	
    }
    this.setMesfalha(mesfalha);
	this.setValfalha(valfalha2);
	this.setDatamovel(dataminini);
	
	   }
	
	
	return this.media;
		
	}

   public ArrayList<Double> calcmedia2(){
	     int [] valfalha2=new int [this.valor.length-this.movel+1];
	     int [] mesfalha = new int [12];
		 for(int k=0;k<12;k++){
		 mesfalha[k]=0;	 
		 }
		 int ival=0;
		 ArrayList<Double> media=new ArrayList<Double>();
		 ArrayList<String> dataminini=new ArrayList<String>();
		 for (int i=0;i<(this.valor.length-this.movel+1);i++){	
				 
			     double soma=0;
				 int n=0;
				 int nfalha=0;
				 
				 	 for (int j=0;j<this.movel;j++){
					 
						if(this.valor[i+j] != -99999.0){
						soma=soma+this.valor[i+j];
						n++;	 
						}else{
							
							/*if(this.mes[i+j] != 0){
							mesfalha[this.mes[i+j]-1]++;
							}*/
						nfalha++;	
						}
				    
				 	 }
		
				 			 	 
		 if(nfalha != this.movel){
		 double med = (soma/(n*1.0));
		 media.add(med);
		 //dataminini.add(this.data[i]);
		 //this.media[ival]=(soma/(n*1.0));
		 valfalha2[ival]=nfalha;
		 ival++;	 
		 }
		 
			 
		 }
	
		 
 // this.media=new double [media.size()];	 
 // for (int j=0;j<media.size();j++){
 // this.media[j]=media.get(j);	
//  }
//    this.setMesfalha(mesfalha);
//	this.setValfalha(valfalha2);
//	this.setDatamovel(dataminini);
	return media;
		
	}

public int [] getMesfalha() {
	return mesfalha;
}

public void setMesfalha(int [] mesfalha) {
	this.mesfalha = mesfalha;
}

public int [] getValfalha() {
	return valfalha;
}

public void setValfalha(int [] valfalha) {
	this.valfalha = valfalha;
}



public ArrayList<String> getDatamovel() {
	return datamovel;
}



public void setDatamovel(ArrayList<String> datamovel) {
	this.datamovel = datamovel;
}
	
}
