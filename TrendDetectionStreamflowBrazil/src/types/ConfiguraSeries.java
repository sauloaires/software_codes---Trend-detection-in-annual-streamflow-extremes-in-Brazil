package types;

public class ConfiguraSeries {
	 private int mesIni;
	    private int mesFim;
	   
	    
	    
	   /**
	    *  tipoSerie == 0 - CF
	    *  tipoSerie == 1 - SF
	    *  tipoSerie == 2 - CFT
	    */
	    private int TipoSerieFalha;
	    
	    /**
	     * VALIDO PARA TipoSerieFalha = CFT
	     */
	    private Double tolFalhaMax;
	    
	    private int anoIniSubConjunto;
	    private int anoFimSubConjunto;
	    private int tamanhoMinimo;
	  
	    private String TipoSerieFalhaHidro;
	    
	    
	    /**
	    * A ideia do codigo é no futuro saber em qual modulo essa configuração se refere
	    */
	    
	    private String codConfiguracao;
	    
	    
	    
	    /**
	     * codEstatistica = 0 - SOMA
	     * codEstatistica = 1 - MEDIA
	     * codEstatistica = 2 - DESVPAD
	     * codEstatistica = 3 - ASSIMETRIA
	     * codEstatistica = 4 - CURTOSE
	     * codEstatistica = 5 - MAXIMOS
	     * codEstatistica = 6 - MINIMOS
	     */
	    private int codEstatistica;
	    
	    
	    
	    
	    
	    
	    public ConfiguraSeries(){
	    	
	    }
	    
	    public ConfiguraSeries(String codConfiguracao){
	    	
	    this.codConfiguracao=codConfiguracao;
	    	
	    }
	    
	   
	    
	    public int getMesIni() {
			return mesIni;
		}
		public void setMesIni(int mesIni) {
			this.mesIni = mesIni;
		}
		public int getMesFim() {
			return mesFim;
		}
		public void setMesFim(int mesFim) {
			this.mesFim = mesFim;
		}
		public Double getTolFalhaMax() {
			return tolFalhaMax;
		}
		public void setTolFalhaMax(Double tolFalhaMax) {
			this.tolFalhaMax = tolFalhaMax;
		}
		public int getTipoSerieFalha() {
			return TipoSerieFalha;
		}
		public void setTipoSerieFalha(int tipoSerieFalha) {
			TipoSerieFalha = tipoSerieFalha;
		}
		public int getAnoIniSubConjunto() {
			return anoIniSubConjunto;
		}
		public void setAnoIniSubConjunto(int anoIniSubConjunto) {
			this.anoIniSubConjunto = anoIniSubConjunto;
		}
		public int getAnoFimSubConjunto() {
			return anoFimSubConjunto;
		}
		public void setAnoFimSubConjunto(int anoFimSubConjunto) {
			this.anoFimSubConjunto = anoFimSubConjunto;
		}
		public int getTamanhoMinimo() {
			return tamanhoMinimo;
		}
		public void setTamanhoMinimo(int tamanhoMinimo) {
			this.tamanhoMinimo = tamanhoMinimo;
		}
		public String getCodConfiguracao() {
			return codConfiguracao;
		}

		public void setCodConfiguracao(String codConfiguracao) {
			this.codConfiguracao = codConfiguracao;
		}

		public String getTipoSerieFalhaHidro() {
			return TipoSerieFalhaHidro;
		}

		public void setTipoSerieFalhaHidro(String tipoSerieFalhaHidro) {
			TipoSerieFalhaHidro = tipoSerieFalhaHidro;
		}

		public int getCodEstatistica() {
			return codEstatistica;
		}

		public void setCodEstatistica(int codEstatistica) {
			this.codEstatistica = codEstatistica;
		}

}
