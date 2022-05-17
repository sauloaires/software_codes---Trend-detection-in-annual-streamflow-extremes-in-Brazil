package types;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;





	public class DadoTemporal implements Comparable<DadoTemporal> {

		
		private Date data;
		private Double valor;
		private String valorString;
		private boolean resultado;
		private String codigo;
		private String nivelConsistencia;
		private String dataStr;
		
		/**
		 * status pode ser: "Observado", "Preenchido", "Extendido", "Falha";
		 */
		private String status;
		
		private boolean numerico;
		
		
		private boolean excluirOutlier;
		/**
		 * Indicar que existe pelo menos um tipo de outlier
		 */
		private boolean existeOutlier;
		
		
		/**
		 * Saulo 03/12/2014 - Para Ottobacias
		 */
		private Double valorIncremental;
		
		private boolean consistido;
		private int codCombinaPlu;
		private double percConsistida;
		
		public int getCodCombinaPlu() {
	    return codCombinaPlu;
	  }


	  public void setCodCombinaPlu(int codCombinaPlu) {
	    this.codCombinaPlu = codCombinaPlu;
	  }


	  public double getPercConsistida() {
	    return percConsistida;
	  }


	  public void setPercConsistida(double percConsistida) {
	    this.percConsistida = percConsistida;
	  }


	  public Date getData() {
			return data;
		}

		
		public void setData(Date data) {
			this.data = data;
		}

		public void setData(String datastr) {
			
			/**
			 * Saulo - Alteração para evitar problema com hora em java - 19/11/2015
			 */
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String[] str = datastr.split("/");
			int ano=Integer.parseInt(str[2]);
		    int mes=Integer.parseInt(str[1]);
		    int dia=Integer.parseInt(str[0]);
			Date utilDate = new Date();
			Calendar clStart =Calendar.getInstance();
			clStart.set(ano,mes-1,dia);
			
			
			int hora = utilDate.getHours();
			//utilDate.s
		   /*	try {
		   		//clStart.setTime(formatter.parse(datastr));
		   		
			//utilDate = formatter.parse(datastr);
			} catch (ParseException e) {
			e.printStackTrace();
			}*/
			this.data = clStart.getTime();
			//System.out.println(this.data);
		}
		
		
		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}
		
		public void setValor(double valor) {
			this.valor = valor;
		}
		
		public boolean existeValor(){
			
			if(this.valor == null){
			this.resultado=true;	
				
				
			}else{
			this.resultado=false;	
			}
			return this.resultado;
			
			
			
		}
		
		@Override
		public int compareTo(DadoTemporal outroDadoTemporal) {
			// TODO Auto-generated method stub
			
			ArrayList<Date> data = new  ArrayList<Date>();
			data.add(this.data);
			data.add(outroDadoTemporal.data);
			
			Collections.sort(data);
			if (this.data.equals(data.get(0))) {
			return -1;
			}
			if (this.data.equals(data.get(1))) {
			return 1;
			}
			//if (this.data < outroDadoTemporal.data) {
	       //     return -1;
	        //}
	        //if (this.data >  outroDadoTemporal.data) {
	        //    return 1;
	        //}
			
			return 0;
		}


		public String getCodigo() {
			return codigo;
		}


		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}


		public String getNivelConsistencia() {
			return nivelConsistencia;
		}


		public void setNivelConsistencia(String nivelConsistencia) {
			this.nivelConsistencia = nivelConsistencia;
		}


		public String getDataStr() {
			return this.transDataStr();
		}


		
		public String transDataStr (Date data){
			String datastr = null;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			datastr =formatter.format(data);
			return datastr;
			
		}
		
		public String transDataStr (){
			String datastr = null;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			datastr =formatter.format(this.data);
			return datastr;
			
		}	
		
		public int pegarAno (){
			int ano = 0;
			String[] str=this.transDataStr().split("/");
			ano=Integer.parseInt(str[2]);	
			return ano;
	    }
		
		public boolean eDoAno(int anoVerifica){
			boolean mesmoAno=false;
			int ano = 0;
			String[] str=this.transDataStr().split("/");
			ano=Integer.parseInt(str[2]);
			if(ano == anoVerifica)mesmoAno=true;
			
			return mesmoAno;
		}
		
		
		public int pegarAno (Date data){
			int ano = 0;
			String[] str=this.transDataStr(data).split("/");
			ano=Integer.parseInt(str[2]);	
			return ano;
	    }
		
		public int pegarMes (){
			int mes = 0;
			String[] str=this.transDataStr().split("/");
			mes=Integer.parseInt(str[1]);	
			return mes;
	    }
		
		public int pegarDia (){
			int dia = 0;
			String[] str=this.transDataStr().split("/");
			dia=Integer.parseInt(str[0]);	
			return dia;
	    }


		public String getValorString() {
			return valorString;
		}


		public void setValorString(String valorString) {
			this.valorString = valorString;
		}


		public boolean isNumerico() {
			return numerico;
		}


		public void setNumerico(boolean numerico) {
			this.numerico = numerico;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public void setDataStr(String dataStr) {
			this.dataStr = dataStr;
		}


		

		public boolean isExcluirOutlier() {
			return excluirOutlier;
		}


		public void setExcluirOutlier(boolean excluirOutlier) {
			this.excluirOutlier = excluirOutlier;
		}


		public boolean isExisteOutlier() {
			return existeOutlier;
		}


		public void setExisteOutlier(boolean existeOutlier) {
			this.existeOutlier = existeOutlier;
		}


		public Double getValorIncremental() {
			return valorIncremental;
		}


		public void setValorIncremental(Double valorIncremental) {
			this.valorIncremental = valorIncremental;
		}


	  public boolean isConsistido() {
	    return consistido;
	  }


	  public void setConsistido(boolean consistido) {
	    this.consistido = consistido;
	  }
	
	
}
