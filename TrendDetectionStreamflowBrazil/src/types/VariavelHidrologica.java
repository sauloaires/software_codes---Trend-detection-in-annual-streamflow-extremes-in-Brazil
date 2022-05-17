package types;

import java.util.ArrayList;
import java.util.Map;



public class VariavelHidrologica {
	private InventarioHidrologico invhidro;
	private SerieTemporal serietemporal;
	private boolean selecionada;
	private boolean apenasInventario; 
	private String cobacia;
		
	private ArrayList<ResultEstacionaridade>resultestacionaridade;
	private boolean atendeRestricaoTamMin;
	
	private Map<Integer,Integer> anoEstacao;
	
	
	public InventarioHidrologico getInvhidro() {
		return invhidro;
	}

	public void setInvhidro(InventarioHidrologico invhidro) {
		this.invhidro = invhidro;
	}

	
	public SerieTemporal getSerietemporal() {
		return serietemporal;
	}

	public void setSerietemporal(SerieTemporal serietemporal) {
		this.serietemporal = serietemporal;
	}

	public boolean isSelecionada() {
		return selecionada;
	}

	public void setSelecionada(boolean selecionada) {
		this.selecionada = selecionada;
	}

	
	public boolean isApenasInventario() {
		return apenasInventario;
	}

	public void setApenasInventario(boolean apenasInventario) {
		this.apenasInventario = apenasInventario;
	}
	
	public String getCobacia() {
		return cobacia;
	}

	public void setCobacia(String cobacia) {
		this.cobacia = cobacia;
	}

	public ArrayList<ResultEstacionaridade> getResultestacionaridade() {
		return resultestacionaridade;
	}

	public void setResultestacionaridade(ArrayList<ResultEstacionaridade> resultestacionaridade) {
		this.resultestacionaridade = resultestacionaridade;
	}

	public boolean isAtendeRestricaoTamMin() {
		return atendeRestricaoTamMin;
	}

	public void setAtendeRestricaoTamMin(boolean atendeRestricaoTamMin) {
		this.atendeRestricaoTamMin = atendeRestricaoTamMin;
	}

	public Map<Integer,Integer> getAnoEstacao() {
		return anoEstacao;
	}

	public void setAnoEstacao(Map<Integer,Integer> anoEstacao) {
		this.anoEstacao = anoEstacao;
	}

}
