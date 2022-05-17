package util;

public class PegarNomesTestes {
public static String [] nomeCompleto(){
		
		int ntestes=14;
		
		String [] nometesteExtenso=new String [ntestes];
		nometesteExtenso[0]="Mann-Kendall";
		nometesteExtenso[1]="Spearman’s Rho";
		nometesteExtenso[2]="Linear Regression";
		nometesteExtenso[3]="Teste T";
		nometesteExtenso[4]="Distribution CUSUM";
		nometesteExtenso[5]="Cumulative Deviation";
		nometesteExtenso[6]="Worsley Lik. Ratio";
		nometesteExtenso[7]="Rank-Sum (Mann-Whitney)";
		nometesteExtenso[8]="Teste F";
		nometesteExtenso[9]="Median Crossing";
		nometesteExtenso[10]="Turning Points";
		nometesteExtenso[11]="Rank Difference";
		nometesteExtenso[12]="Autocorrelation";
		nometesteExtenso[13]="Wald-Wolfowitz";
							
		return nometesteExtenso;
	}
	
	
public static String [] siglaTeste(){
		
		int ntestes=14;
									
		String [] nometeste=new String [ntestes];
		nometeste[0]="MK";
		nometeste[1]="SR";
		nometeste[2]="LR";
		nometeste[3]="TT";
		nometeste[4]="DC";
		nometeste[5]="CD";
		nometeste[6]="WR";
		nometeste[7]="MW";
		nometeste[8]="TF";
		nometeste[9]="MC";
		nometeste[10]="TP";
		nometeste[11]="RD";
		nometeste[12]="AC";
		nometeste[13]="WW";
		return nometeste;
	}
}
