package tests;

import java.util.ArrayList;
import java.util.Map;

import tests.autocorrelationApproaches.BS;
import tests.autocorrelationApproaches.PW;
import tests.autocorrelationApproaches.PWSW;
import tests.autocorrelationApproaches.PWcunb;
import tests.autocorrelationApproaches.TFPW;
import tests.autocorrelationApproaches.TFPWcunb;
import tests.autocorrelationApproaches.TFPWcunbPW;
import tests.autocorrelationApproaches.VC;
import tests.autocorrelationApproaches.VCPW;
import types.DadoTemporal;

/*import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.BS;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.PW;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.PWSW;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.PWcunb;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.TFPW;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.TFPWcunb;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.TFPWcunbPW;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.VC;
import org.snirh.extremos_unb.geracao.modelos.tendenciaCorrel.VCPW;
import org.snirh.extremos_unb.tipos.DadoTemporal;*/

public class SelecionarAbordagemCorrelacaoSerial {
	private ArrayList<Double> amostraFinal;
	private double CF;
	private double [] vcriticoBS;
	
	
	
	private Map<String,DadoTemporal> serieMapaFinal;
	
	public void executarSerieMapa(String nomeAbordagem,Map<String,DadoTemporal> serieMapa) {
		this.amostraFinal=new ArrayList<Double>();
		this.CF=1;
		this.vcriticoBS=new double [2];
		this.vcriticoBS[0]=-99999.;
		this.vcriticoBS[1]=-99999.;
		
		
		
		
		if(nomeAbordagem.equals("PW")) {
			PW pw=new PW();
			this.setSerieMapaFinal(pw.executar(serieMapa));
		}else if (nomeAbordagem.equals("TFPW")) {
			TFPW tfpw=new TFPW();
			this.setSerieMapaFinal(tfpw.executar(serieMapa));
		}else if (nomeAbordagem.equals("PWcunb")) {
			PWcunb pwcunb=new PWcunb();
			this.setSerieMapaFinal(pwcunb.executar(serieMapa));
		}else if (nomeAbordagem.equals("TFPWcunb")) {
			TFPWcunb tfpwcunb=new TFPWcunb();
			this.setSerieMapaFinal(tfpwcunb.executar(serieMapa));
		}else if (nomeAbordagem.equals("TFPWcunbPW")) {
			TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			this.setSerieMapaFinal(tfpwcunbpw.executar(serieMapa));
		}else if (nomeAbordagem.equals("TFPWcunbPW_V2")) {
			//TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			//this.serieMapaFinal=tfpwcunbpw.executar_V2(serieMapa);
		}else if (nomeAbordagem.equals("TFPWcunbPW_V3")) {
			//TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			//this.serieMapaFinal=tfpwcunbpw.executar_V3(serieMapa);
		}else if (nomeAbordagem.equals("VCPW")) {
			VCPW vcpw=new VCPW();
			this.setSerieMapaFinal(vcpw.executar(serieMapa));
		}else if (nomeAbordagem.equals("VCPW_V2")) {
			//VCPW vcpw=new VCPW();
			//this.serieMapaFinal=vcpw.executar_V2(serieMapa);
		}else if (nomeAbordagem.equals("VCPW_V3")) {
			//VCPW vcpw=new VCPW();
			//this.serieMapaFinal=vcpw.executar_V3(serieMapa);
		}else if (nomeAbordagem.equals("PWSW")) {
			//PWSW pwsw=new PWSW();
			//this.serieMapaFinal=pwsw.executar(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF1")) {
			VC vccf1=new VC();
			this.CF=vccf1.executar_CF1(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF2")) {
			VC vccf2=new VC();
			this.CF=vccf2.executar_CF2(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF3")) {
			VC vccf3=new VC();
			this.CF=vccf3.executar_CF3(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF4")) {
			//VC vccf4=new VC();
			//this.CF=vccf4.executar_CF4(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF5")) {
			//VC vccf5=new VC();
			//this.CF=vccf5.executar_CF5(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("VC_CF6")) {
			//VC vccf6=new VC();
			//this.CF=vccf6.executar_CF6(serieMapa);
			this.setSerieMapaFinal(serieMapa);
		}else if (nomeAbordagem.equals("BS_BL2")) {
			//BS bs=new BS();
			//this.vcriticoBS=bs.executar_bloco2(amostra);
			//this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("BS_BL3")) {
			//BS bs=new BS();
			//this.vcriticoBS=bs.executar_bloco3(amostra);
			//this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("BS_BL4")) {
			//BS bs=new BS();
			//this.vcriticoBS=bs.executar_bloco4(amostra);
			//this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("MK")) {
			this.setSerieMapaFinal(serieMapa);
		}
		
		
		
		
	}
	
	
	
	public void executar(String nomeAbordagem,ArrayList<Double> amostra) {
		this.amostraFinal=new ArrayList<Double>();
		this.CF=1;
		this.vcriticoBS=new double [2];
		this.vcriticoBS[0]=-99999.;
		this.vcriticoBS[1]=-99999.;
		
		
		
		
		if(nomeAbordagem.equals("PW")) {
			PW pw=new PW();
			this.amostraFinal=pw.executar(amostra);
		}else if (nomeAbordagem.equals("TFPW")) {
			TFPW tfpw=new TFPW();
			this.amostraFinal=tfpw.executar(amostra);
		}else if (nomeAbordagem.equals("PWcunb")) {
			PWcunb pwcunb=new PWcunb();
			this.amostraFinal=pwcunb.executar(amostra);
		}else if (nomeAbordagem.equals("TFPWcunb")) {
			TFPWcunb tfpwcunb=new TFPWcunb();
			this.amostraFinal=tfpwcunb.executar(amostra);
		}else if (nomeAbordagem.equals("TFPWcunbPW")) {
			TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			this.amostraFinal=tfpwcunbpw.executar(amostra);
		}else if (nomeAbordagem.equals("TFPWcunbPW_V2")) {
			TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			this.amostraFinal=tfpwcunbpw.executar_V2(amostra);
		}else if (nomeAbordagem.equals("TFPWcunbPW_V3")) {
			TFPWcunbPW tfpwcunbpw=new TFPWcunbPW();
			this.amostraFinal=tfpwcunbpw.executar_V3(amostra);
		}else if (nomeAbordagem.equals("VCPW")) {
			VCPW vcpw=new VCPW();
			this.amostraFinal=vcpw.executar(amostra);
		}else if (nomeAbordagem.equals("VCPW_V2")) {
			VCPW vcpw=new VCPW();
			this.amostraFinal=vcpw.executar_V2(amostra);
		}else if (nomeAbordagem.equals("VCPW_V3")) {
			VCPW vcpw=new VCPW();
			this.amostraFinal=vcpw.executar_V3(amostra);
		}else if (nomeAbordagem.equals("PWSW")) {
			PWSW pwsw=new PWSW();
			this.amostraFinal=pwsw.executar(amostra);
		}else if (nomeAbordagem.equals("VC_CF1")) {
			VC vccf1=new VC();
			this.CF=vccf1.executar_CF1(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("VC_CF2")) {
			VC vccf2=new VC();
			this.CF=vccf2.executar_CF2(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("VC_CF3")) {
			VC vccf3=new VC();
			this.CF=vccf3.executar_CF3(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("VC_CF4")) {
			VC vccf4=new VC();
			this.CF=vccf4.executar_CF4(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("VC_CF5")) {
			VC vccf5=new VC();
			this.CF=vccf5.executar_CF5(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("VC_CF6")) {
			VC vccf6=new VC();
			this.CF=vccf6.executar_CF6(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("BS_BL2")) {
			BS bs=new BS();
			this.vcriticoBS=bs.executar_bloco2(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("BS_BL3")) {
			BS bs=new BS();
			this.vcriticoBS=bs.executar_bloco3(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("BS_BL4")) {
			BS bs=new BS();
			this.vcriticoBS=bs.executar_bloco4(amostra);
			this.amostraFinal=amostra;
		}else if (nomeAbordagem.equals("MK")) {
			this.amostraFinal=amostra;
		}
		
		
		
		
	}

	public ArrayList<Double> getAmostraFinal() {
		return amostraFinal;
	}

	public void setAmostraFinal(ArrayList<Double> amostraFinal) {
		this.amostraFinal = amostraFinal;
	}

	public double getCF() {
		return CF;
	}

	public void setCF(double cF) {
		CF = cF;
	}

	public double[] getVcriticoBS() {
		return vcriticoBS;
	}

	public void setVcriticoBS(double[] vcriticoBS) {
		this.vcriticoBS = vcriticoBS;
	}



	public Map<String,DadoTemporal> getSerieMapaFinal() {
		return serieMapaFinal;
	}



	public void setSerieMapaFinal(Map<String,DadoTemporal> serieMapaFinal) {
		this.serieMapaFinal = serieMapaFinal;
	}
	
}
