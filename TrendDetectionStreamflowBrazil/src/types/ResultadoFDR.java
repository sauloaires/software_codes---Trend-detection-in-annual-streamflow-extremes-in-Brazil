package types;


	public class ResultadoFDR {

		private String codigo;
		private int ordemi;
		private double pvalue;
		private double q;
		private String resultFDR;
		private boolean falsoPositivo;
		
		
		private ResultadoBsen resbsen;
		
		
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
		public int getOrdemi() {
			return ordemi;
		}
		public void setOrdemi(int ordemi) {
			this.ordemi = ordemi;
		}
		public double getPvalue() {
			return pvalue;
		}
		public void setPvalue(double pvalue) {
			this.pvalue = pvalue;
		}
		public double getQ() {
			return q;
		}
		public void setQ(double q) {
			this.q = q;
		}
		public String getResultFDR() {
			return resultFDR;
		}
		public void setResultFDR(String resultFDR) {
			this.resultFDR = resultFDR;
		}
		public boolean isFalsoPositivo() {
			return falsoPositivo;
		}
		public void setFalsoPositivo(boolean falsoPositivo) {
			this.falsoPositivo = falsoPositivo;
		}
		public ResultadoBsen getResbsen() {
			return resbsen;
		}
		public void setResbsen(ResultadoBsen resbsen) {
			this.resbsen = resbsen;
		}
		
		
		
		
		
	
}
