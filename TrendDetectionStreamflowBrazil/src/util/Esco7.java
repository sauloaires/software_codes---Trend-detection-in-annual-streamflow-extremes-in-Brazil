package util;

import java.math.BigInteger;
import java.util.ArrayList;

public class Esco7 {

public static ArrayList<Integer> ordenarInteiro(ArrayList<Integer> valuesArray){
		
		double[] values=new double[valuesArray.size()];
		for(int i=0;i<valuesArray.size();i++){
			values[i]=valuesArray.get(i);
		}
		
		int nvalues=valuesArray.size();
		ArrayList<Integer> indArrayNovo=new ArrayList<Integer>();
		
		int [] ind = new int[nvalues+1];
		int [] ind2 = new int[nvalues];
		double [] valueord=new double[nvalues+1];
		int M;
		int IR;
		int J = 0;
		int I = 0;
		int NL;
		double XK,A1,A2,A3 = 0;
		int L;
		double XAVE=1.0;
		int JESCO,IESCO;
		
		valueord[0]=0;
		for (int i = 1; i <= nvalues; i++){
		ind[i]=i;	
		valueord[i]=values[i-1];
		}
		
		L=(nvalues/2)+1;
		M=nvalues;
		NL=nvalues-nvalues;

	while(M != NL || M!=1){	
	   	if(L == 1){
	   	IR  = ind[M];
	   	XK=valueord[IR]*XAVE;
	   	ind[M]=ind[1];
	   	M=M-1;
	   	JESCO=IR;
	   	if(M==1)ind[1]=IR;
	   	if(M==NL)JESCO=ind[M+1];
	   	if(M==NL)IESCO=ind[M+2];
	   	if(M==NL || M==1){
	   		for (int i = 1; i <= nvalues; i++){
	   		ind2[i-1]=ind[i]-1;	
	   		indArrayNovo.add(ind2[i-1]);
	   		}
	   		return indArrayNovo;	
	   	}
	   	J=L;
	   	}else{
	   	 L=L-1;
	     IR=ind[L];
	     XK=valueord[IR]*XAVE;
	     J=L;
	   	}
	   	
	   	   	
	   	  while(true)	{

			I=J;
			J=2*J;	
		    if(J-M < 0){
	   	    A1 =valueord[ind[J]]*XAVE;
	   	    A2 =valueord[ind[J+1]]*XAVE;
	   	    if(A1<A2)J++;
	   		A3 =valueord[ind[J]]*XAVE;
	   		
	  		//break cinco;    
	   		if(XK >= A3){
	   		break;	
	   		}
	   		ind[I]=ind[J];
	   	    }else if(J-M == 0){
	   	    A3 =valueord[ind[J]]*XAVE;
	   	        if(XK >= A3){
	    		break;	
	    		}
	   	    ind[I]=ind[J];
	   	    }else{
	   	    break;	
	   	    }
	   	 
	    } 	
		    
			
	    ind[I]=IR;
	   
	} 
	        for (int i = 1; i <= nvalues; i++){
			ind2[i-1]=ind[i]-1;	
			indArrayNovo.add(ind2[i-1]);
			}
	return indArrayNovo;
	}
	
public static ArrayList<Integer> ordenar(ArrayList<Double> valuesArray){
		
		double[] values=new double[valuesArray.size()];
		for(int i=0;i<valuesArray.size();i++){
			values[i]=valuesArray.get(i);
		}
		
		int nvalues=valuesArray.size();
		ArrayList<Integer> indArrayNovo=new ArrayList<Integer>();
		
		int [] ind = new int[nvalues+1];
		int [] ind2 = new int[nvalues];
		double [] valueord=new double[nvalues+1];
		int M;
		int IR;
		int J = 0;
		int I = 0;
		int NL;
		double XK,A1,A2,A3 = 0;
		int L;
		double XAVE=1.0;
		int JESCO,IESCO;
		
		valueord[0]=0;
		for (int i = 1; i <= nvalues; i++){
		ind[i]=i;	
		valueord[i]=values[i-1];
		}
		
		L=(nvalues/2)+1;
		M=nvalues;
		NL=nvalues-nvalues;

	while(M != NL || M!=1){	
	   	if(L == 1){
	   	IR  = ind[M];
	   	XK=valueord[IR]*XAVE;
	   	ind[M]=ind[1];
	   	M=M-1;
	   	JESCO=IR;
	   	if(M==1)ind[1]=IR;
	   	if(M==NL)JESCO=ind[M+1];
	   	if(M==NL)IESCO=ind[M+2];
	   	if(M==NL || M==1){
	   		for (int i = 1; i <= nvalues; i++){
	   		ind2[i-1]=ind[i]-1;	
	   		indArrayNovo.add(ind2[i-1]);
	   		}
	   		return indArrayNovo;	
	   	}
	   	J=L;
	   	}else{
	   	 L=L-1;
	     IR=ind[L];
	     XK=valueord[IR]*XAVE;
	     J=L;
	   	}
	   	
	   	   	
	   	  while(true)	{

			I=J;
			J=2*J;	
		    if(J-M < 0){
	   	    A1 =valueord[ind[J]]*XAVE;
	   	    A2 =valueord[ind[J+1]]*XAVE;
	   	    if(A1<A2)J++;
	   		A3 =valueord[ind[J]]*XAVE;
	   		
	  		//break cinco;    
	   		if(XK >= A3){
	   		break;	
	   		}
	   		ind[I]=ind[J];
	   	    }else if(J-M == 0){
	   	    A3 =valueord[ind[J]]*XAVE;
	   	        if(XK >= A3){
	    		break;	
	    		}
	   	    ind[I]=ind[J];
	   	    }else{
	   	    break;	
	   	    }
	   	 
	    } 	
		    
			
	    ind[I]=IR;
	   
	} 
	        for (int i = 1; i <= nvalues; i++){
			ind2[i-1]=ind[i]-1;	
			indArrayNovo.add(ind2[i-1]);
			}
	return indArrayNovo;
	}
	
	
	

	public static int[] ordenar(int nvalues,ArrayList<Double> valuesArray){
		
		double[] values=new double[valuesArray.size()];
		for(int i=0;i<valuesArray.size();i++){
			values[i]=valuesArray.get(i);
		}
		
		int [] ind = new int[nvalues+1];
		int [] ind2 = new int[nvalues];
		double [] valueord=new double[nvalues+1];
		int M;
		int IR;
		int J = 0;
		int I = 0;
		int NL;
		double XK,A1,A2,A3 = 0;
		int L;
		double XAVE=1.0;
		int JESCO,IESCO;
		
		valueord[0]=0;
		for (int i = 1; i <= nvalues; i++){
		ind[i]=i;	
		valueord[i]=values[i-1];
		}
		
		L=(nvalues/2)+1;
		M=nvalues;
		NL=nvalues-nvalues;

	while(M != NL || M!=1){	
	   	if(L == 1){
	   	IR  = ind[M];
	   	XK=valueord[IR]*XAVE;
	   	ind[M]=ind[1];
	   	M=M-1;
	   	JESCO=IR;
	   	if(M==1)ind[1]=IR;
	   	if(M==NL)JESCO=ind[M+1];
	   	if(M==NL)IESCO=ind[M+2];
	   	if(M==NL || M==1){
	   		for (int i = 1; i <= nvalues; i++){
	   		ind2[i-1]=ind[i]-1;	
	   		}
	   	return ind2;	
	   	}
	   	J=L;
	   	}else{
	   	 L=L-1;
	     IR=ind[L];
	     XK=valueord[IR]*XAVE;
	     J=L;
	   	}
	   	
	   	   	
	   	  while(true)	{

			I=J;
			J=2*J;	
		    if(J-M < 0){
	   	    A1 =valueord[ind[J]]*XAVE;
	   	    A2 =valueord[ind[J+1]]*XAVE;
	   	    if(A1<A2)J++;
	   		A3 =valueord[ind[J]]*XAVE;
	   		
	  		//break cinco;    
	   		if(XK >= A3){
	   		break;	
	   		}
	   		ind[I]=ind[J];
	   	    }else if(J-M == 0){
	   	    A3 =valueord[ind[J]]*XAVE;
	   	        if(XK >= A3){
	    		break;	
	    		}
	   	    ind[I]=ind[J];
	   	    }else{
	   	    break;	
	   	    }
	   	 
	    } 	
		    
			
	    ind[I]=IR;
	   
	} 
	        for (int i = 1; i <= nvalues; i++){
			ind2[i-1]=ind[i]-1;	
			}
	return ind2;
	}
	
	public static int[] ordenar(int nvalues,double[] values){
	int [] ind = new int[nvalues+1];
	int [] ind2 = new int[nvalues];
	double [] valueord=new double[nvalues+1];
	int M;
	int IR;
	int J = 0;
	int I = 0;
	int NL;
	double XK,A1,A2,A3 = 0;
	int L;
	double XAVE=1.0;
	int JESCO,IESCO;
	
	valueord[0]=0;
	for (int i = 1; i <= nvalues; i++){
	ind[i]=i;	
	valueord[i]=values[i-1];
	}
	
	L=(nvalues/2)+1;
	M=nvalues;
	NL=nvalues-nvalues;

while(M != NL || M!=1){	
   	if(L == 1){
   	IR  = ind[M];
   	XK=valueord[IR]*XAVE;
   	ind[M]=ind[1];
   	M=M-1;
   	JESCO=IR;
   	if(M==1)ind[1]=IR;
   	if(M==NL)JESCO=ind[M+1];
   	if(M==NL)IESCO=ind[M+2];
   	if(M==NL || M==1){
   		for (int i = 1; i <= nvalues; i++){
   		ind2[i-1]=ind[i]-1;	
   		}
   	return ind2;	
   	}
   	J=L;
   	}else{
   	 L=L-1;
     IR=ind[L];
     XK=valueord[IR]*XAVE;
     J=L;
   	}
   	
   	   	
   	  while(true)	{

		I=J;
		J=2*J;	
	    if(J-M < 0){
   	    A1 =valueord[ind[J]]*XAVE;
   	    A2 =valueord[ind[J+1]]*XAVE;
   	    if(A1<A2)J++;
   		A3 =valueord[ind[J]]*XAVE;
   		
  		//break cinco;    
   		if(XK >= A3){
   		break;	
   		}
   		ind[I]=ind[J];
   	    }else if(J-M == 0){
   	    A3 =valueord[ind[J]]*XAVE;
   	        if(XK >= A3){
    		break;	
    		}
   	    ind[I]=ind[J];
   	    }else{
   	    break;	
   	    }
   	 
    } 	
	    
		
    ind[I]=IR;
   
} 
        for (int i = 1; i <= nvalues; i++){
		ind2[i-1]=ind[i]-1;	
		}
return ind2;
}
	
	
	    public static int[] ordenar(int nvalues, Integer[] values){
		int [] ind = new int[nvalues+1];
		int [] ind2 = new int[nvalues];
		int [] valueord=new int[nvalues+1];
		int M;
		int IR;
		int J = 0;
		int I = 0;
		int NL;
		double XK,A1,A2,A3 = 0;
		int L;
		double XAVE=1.0;
		int JESCO,IESCO;
		
		valueord[0]=0;
		for (int i = 1; i <= nvalues; i++){
		ind[i]=i;	
		valueord[i]=values[i-1];
		}
		
		L=(nvalues/2)+1;
		M=nvalues;
		NL=nvalues-nvalues;

	while(M != NL || M!=1){	
	   	if(L == 1){
	   	IR  = ind[M];
	   	XK=valueord[IR]*XAVE;
	   	ind[M]=ind[1];
	   	M=M-1;
	   	JESCO=IR;
	   	if(M==1)ind[1]=IR;
	   	if(M==NL)JESCO=ind[M+1];
	   	if(M==NL)IESCO=ind[M+2];
	   	if(M==NL || M==1){
	   		for (int i = 1; i <= nvalues; i++){
	   		ind2[i-1]=ind[i]-1;	
	   		}
	   	return ind2;	
	   	}
	   	J=L;
	   	}else{
	   	 L=L-1;
	     IR=ind[L];
	     XK=valueord[IR]*XAVE;
	     J=L;
	   	}
	   	
	   	   	
	   	  while(true)	{

			I=J;
			J=2*J;	
		    if(J-M < 0){
	   	    A1 =valueord[ind[J]]*XAVE;
	   	    A2 =valueord[ind[J+1]]*XAVE;
	   	    if(A1<A2)J++;
	   		A3 =valueord[ind[J]]*XAVE;
	   		
	  		//break cinco;    
	   		if(XK >= A3){
	   		break;	
	   		}
	   		ind[I]=ind[J];
	   	    }else if(J-M == 0){
	   	    A3 =valueord[ind[J]]*XAVE;
	   	        if(XK >= A3){
	    		break;	
	    		}
	   	    ind[I]=ind[J];
	   	    }else{
	   	    break;	
	   	    }
	   	 
	    } 	
		    
			
	    ind[I]=IR;
	   
	} 
	        for (int i = 1; i <= nvalues; i++){
			ind2[i-1]=ind[i]-1;	
			}
	return ind2;
	}	

	    public static int[] ordenar(ArrayList<BigInteger> valuesBig, boolean teste){
	    	int nvalues=valuesBig.size();
	    	
	    	BigInteger[] values=new BigInteger[valuesBig.size()];
			for(int i=0;i<valuesBig.size();i++){
				values[i]=valuesBig.get(i);
			}
	    	
	    	
			int [] ind = new int[nvalues+1];
			int [] ind2 = new int[nvalues];
			BigInteger [] valueord=new BigInteger[nvalues+1];
			int M;
			int IR;
			int J = 0;
			int I = 0;
			int NL;
			BigInteger XK,A1,A2,A3 = BigInteger.valueOf(0);
			int L;
			BigInteger XAVE=BigInteger.valueOf(1);
			int JESCO,IESCO;
			
			valueord[0]=BigInteger.valueOf(0);
			for (int i = 1; i <= nvalues; i++){
			ind[i]=i;	
			valueord[i]=values[i-1];
			}
			
			L=(nvalues/2)+1;
			M=nvalues;
			NL=nvalues-nvalues;

		while(M != NL || M!=1){	
		   	if(L == 1){
		   	IR  = ind[M];
		   	XK=valueord[IR].multiply(XAVE);
		   	ind[M]=ind[1];
		   	M=M-1;
		   	JESCO=IR;
		   	if(M==1)ind[1]=IR;
		   	if(M==NL)JESCO=ind[M+1];
		   	if(M==NL)IESCO=ind[M+2];
		   	if(M==NL || M==1){
		   		for (int i = 1; i <= nvalues; i++){
		   		ind2[i-1]=ind[i]-1;	
		   		}
		   	return ind2;	
		   	}
		   	J=L;
		   	}else{
		   	 L=L-1;
		     IR=ind[L];
		     XK=valueord[IR].multiply(XAVE);
		     J=L;
		   	}
		   	
		   	   	
		   	  while(true)	{

				I=J;
				J=2*J;	
			    if(J-M < 0){
		   	    A1 =valueord[ind[J]].multiply(XAVE);
		   	    A2 =valueord[ind[J+1]].multiply(XAVE);
		   	    if(A1.compareTo(A2)== -1)J++;
		   		A3 =valueord[ind[J]].multiply(XAVE);
		   		
		  		//break cinco;    
		   		//if(XK >= A3){
		   		if(XK.compareTo(A3) > 0){
		   		break;	
		   		}
		   		ind[I]=ind[J];
		   	    }else if(J-M == 0){
		   	    A3 =valueord[ind[J]].multiply(XAVE);
		   	       // if(XK >= A3){
		   	       if(XK.compareTo(A3) > 0){
		   	         break;	
		    		}
		   	    ind[I]=ind[J];
		   	    }else{
		   	    break;	
		   	    }
		   	 
		    } 	
			    
				
		    ind[I]=IR;
		   
		} 
		        for (int i = 1; i <= nvalues; i++){
				ind2[i-1]=ind[i]-1;	
				}
		return ind2;
		}
	    
	    
	    /*public static int[] ordenar(ArrayList<Integer> valuesBig){
	    	int nvalues=valuesBig.size();
	    	
	    	Integer[] values=new Integer[valuesBig.size()];
			for(int i=0;i<valuesBig.size();i++){
				values[i]=valuesBig.get(i);
			}
	    	
	    	
			int [] ind = new int[nvalues+1];
			int [] ind2 = new int[nvalues];
			Integer [] valueord=new Integer[nvalues+1];
			int M;
			int IR;
			int J = 0;
			int I = 0;
			int NL;
			BigInteger XK,A1,A2,A3 = BigInteger.valueOf(0);
			int L;
			BigInteger XAVE=BigInteger.valueOf(1);
			int JESCO,IESCO;
			
			valueord[0]=Integer.valueOf(0);
			for (int i = 1; i <= nvalues; i++){
			ind[i]=i;	
			valueord[i]=values[i-1];
			}
			
			L=(nvalues/2)+1;
			M=nvalues;
			NL=nvalues-nvalues;

		while(M != NL || M!=1){	
		   	if(L == 1){
		   	IR  = ind[M];
		   	XK=valueord[IR].multiply(XAVE);
		   	ind[M]=ind[1];
		   	M=M-1;
		   	JESCO=IR;
		   	if(M==1)ind[1]=IR;
		   	if(M==NL)JESCO=ind[M+1];
		   	if(M==NL)IESCO=ind[M+2];
		   	if(M==NL || M==1){
		   		for (int i = 1; i <= nvalues; i++){
		   		ind2[i-1]=ind[i]-1;	
		   		}
		   	return ind2;	
		   	}
		   	J=L;
		   	}else{
		   	 L=L-1;
		     IR=ind[L];
		     XK=valueord[IR].multiply(XAVE);
		     J=L;
		   	}
		   	
		   	   	
		   	  while(true)	{

				I=J;
				J=2*J;	
			    if(J-M < 0){
		   	    A1 =valueord[ind[J]].multiply(XAVE);
		   	    A2 =valueord[ind[J+1]].multiply(XAVE);
		   	    if(A1.compareTo(A2)== -1)J++;
		   		A3 =valueord[ind[J]].multiply(XAVE);
		   		
		  		//break cinco;    
		   		//if(XK >= A3){
		   		if(XK.compareTo(A3) > 0){
		   		break;	
		   		}
		   		ind[I]=ind[J];
		   	    }else if(J-M == 0){
		   	    A3 =valueord[ind[J]].multiply(XAVE);
		   	       // if(XK >= A3){
		   	       if(XK.compareTo(A3) > 0){
		   	         break;	
		    		}
		   	    ind[I]=ind[J];
		   	    }else{
		   	    break;	
		   	    }
		   	 
		    } 	
			    
				
		    ind[I]=IR;
		   
		} 
		        for (int i = 1; i <= nvalues; i++){
				ind2[i-1]=ind[i]-1;	
				}
		return ind2;
		}*/
	    
	    
	
}
