package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import types.VariavelHidrologica;

//import org.snirh.HidroReferenciamento.monitorSecas.HID_PanelTabelaIDadosImportados.ModeloTabelaDadosMonitor;
//import org.snirh.hydroelementsBeans.VariavelHidrologica;

public class PanelTabelaIDadosImportados extends JPanel{

		//private SimulationDataGlobal simulationData;
		private JTable tabela;
		private DefaultTableModel tmodel;
		private ArrayList<VariavelHidrologica> variaveisHidrologicas;
		private int tamx;
		private int tamy;
		private int posx;
		private int posy;
		
		public PanelTabelaIDadosImportados(ArrayList<VariavelHidrologica> variaveisHidrologicas){
			//this.simulationData=simulationData;
			this.variaveisHidrologicas= variaveisHidrologicas;
			this.formatPanelTable();
		}
		
		
		public PanelTabelaIDadosImportados(ArrayList<VariavelHidrologica> variaveisHidrologicas,int tamx,int tamy, int posx,int posy){
			//this.simulationData=simulationData;
			this.variaveisHidrologicas= variaveisHidrologicas;
			this.formatPanelTable();
			this.tamx=tamx;
			this.tamy=tamy;
			this.posx=posx;
			this.posy=posy;
			this.formatPanelTable();
		}
		
		
		public PanelTabelaIDadosImportados(ArrayList<VariavelHidrologica> variaveisHidrologicas,int tamx,int tamy){
			//this.simulationData=simulationData;
			this.variaveisHidrologicas= variaveisHidrologicas;
			this.formatPanelTable();
			this.tamx=tamx;
			this.tamy=tamy;
			this.posx=0;
			this.posy=15;
			this.formatPanelTable();
		}
		
		
		public JTable getTabela() {
			return tabela;
		}

		public void setTabela(JTable tabela) {
			this.tabela = tabela;
		}

		private void formatPanelTable(){
			
			this.setBackground(Color.LIGHT_GRAY);
	    	this.setBorder(new EtchedBorder());
	    	this.setLayout(null);
			this.criarTabela();
		}
		
		
		private void criarTabela() {
	    	int ntamx=150;
	    	int ncols=9;
	    	String[] colunas = new String[ncols];
	    	this.tmodel = new DefaultTableModel();
	    	
	    	colunas [0]="Select";
	    	colunas [1]="Gauge Code";
	    	colunas [2]="Latitude";
	    	colunas [3]="Longitude";
	    	colunas [4]="Temporary Discretization";
	        colunas [5]="Data Type";
	        colunas [6]="Variable Unit";
	        colunas [7]="Initial date";
	        colunas [8]="Final date";
	    	//colunas [2]="Nome da estação";
	    	//colunas [3]="Área de Drenagem";
	    	//colunas [4]="Codigo da Bacia";
	    	//colunas [5]="Codigo da SubBacia";
	     	//colunas [8]="Altitude";
	    	//colunas [9]="Origem da Série";
	       // colunas [14]="NANOS SF";
	             
	        Object [][] conteudo = new Object [5000][ncols];
	       
	       for (int i=0;i<5000;i++){
	        	for (int j=0;j<ncols;j++){
	        		if(j==0){
	        			conteudo[i][j]=new Boolean(false);	
	        		}else{
	        			conteudo[i][j]="";
	        	   }
	          }
	       }
	        
	        
	    	this.tabela = new JTable(new ModeloTabelaDadosMonitor(colunas,conteudo));
	        this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    	for (int i = 0; i <= ncols-1; i++) {
	    	TableColumn col =this.tabela.getColumnModel().getColumn(i);
	    	   if(i==0){
	        	col.setPreferredWidth(80);	
	        	}else{
	        		col.setPreferredWidth(120);	
	        	}
	    	}
	    	
	    	
	    	
	    	JScrollPane scrollPane = new JScrollPane(this.tabela);
			scrollPane.setBounds(this.posx,this.posy, this.tamx-10, this.tamy-25);
			scrollPane.setPreferredSize(new Dimension(this.tamx-10, this.tamy-25));
			JPanel panel = new JPanel();
			panel.add(scrollPane);
			panel.setBounds(this.posx+5, this.posy+1,  this.tamx-10, this.tamy-25);
			this.add(panel);
				
			 DefaultTableCellRenderer dtcr =  
		                new DefaultTableCellRenderer() {  
		                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {    
		                    	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		                    	//Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);    
		                         boolean result = (Boolean)table.getValueAt(row, 0);  
		                         setBackground(table.getBackground());
		     		             setForeground(table.getForeground());
		                         // this.setBackground(Color.RED);
		     		              if (!result) {  
		                        	  setBackground(Color.WHITE);
		                        	  setForeground(Color.BLACK);
		                        	  //table.setForeground(Color.RED);
		                           } else {  
		                                setBackground(Color.RED);  
		                                setForeground(Color.BLACK); 
		                             
		                            }  
		     		            table.setBackground(Color.GRAY);
		     		            table.setForeground(Color.BLACK);
		     		            //table.set
		     		           table.repaint();
		     		            
		     		            return this;    
		                    }    
		              
		                 };  
		                  
		        //setando o render para qualquer objeto da jTable  
		          this.tabela.setDefaultRenderer(Object.class, dtcr); 
		          this.tabela.repaint();
	    }
		
		
		
		
		
		
		class ModeloTabelaDadosMonitor extends AbstractTableModel {
		
		
			private String[] colunas;
			  private Object[][] conteudo;

			  ModeloTabelaDadosMonitor (String[] nomecoluna, Object[][] dadoscoluna){
				  this.colunas=nomecoluna;
				  this.conteudo=dadoscoluna;
			  }
			  
			  public int getColumnCount(){
		  	    return colunas.length;
		  	  }

		  	  public int getRowCount(){
		  	    return conteudo.length;
		  	  }
		  	  
		  	  public void setNumRow(){
		    	    //return conteudo.length;
		    	  }

		  	  public String getColumnName(int col){
		  	     return colunas[col];
		  	  }

		  	  public Object getValueAt(int row, int col){
		  	    return conteudo[row][col];
		  	  }
		  	  
		  	  public Object[][] getConteudo(){
		    	    return conteudo;
		    	  }
		  	 
		  	  public Class getColumnClass(int c){
		  	    return getValueAt(0, c).getClass();
		  	  }

		  	  public boolean isCellEditable(int row, int col) {
		  	    return true;
		  	  }
		  	  
		  	  public void limpaTabela() {  
			    	
			    	for (int i=0;i<5000;i++){
			        	for (int j=0;j<getColumnCount();j++){
			        		if(j==0){
			        			conteudo[i][j]=new Boolean(false);	
			        		}else{
			        			conteudo[i][j]="";
			        	   }
			          }
			       }  
			         fireTableDataChanged();  
			    }

		  	  public void setValueAt(Object value, int row, int col) {
		  	    conteudo[row][col] = value;
		  	    String cod = String.valueOf(conteudo[row][1]);
		  	    //this.
		  	    
		  	    if(col == 0 && value.equals(true)){
		      	    
		  	    	for(int i=0;i<variaveisHidrologicas.size();i++){
		  	    		if(cod.equals(String.valueOf(variaveisHidrologicas.get(i).getInvhidro().getEstacao_codigo()))){
		  	    			variaveisHidrologicas.get(i).setSelecionada(true);	
		  	    		}
		  	       	}
		  	    	   	    
		  	    }else if(col == 0 && value.equals(false)){
		  	    	for(int i=0;i<variaveisHidrologicas.size();i++){
		  	    		if(cod.equals(String.valueOf(variaveisHidrologicas.get(i).getInvhidro().getEstacao_codigo()))){
		  	    			variaveisHidrologicas.get(i).setSelecionada(false);	
		  	    		}
		  	       	}
		  	  
		  	    	
		  	    	//checkSelecionarTodas.setSelected(false);	
		  	    }
		  	    
		  	  	    fireTableCellUpdated(row, col);	
		  	    
		  	  }

		  	    
		    
		    }
		
		
		
		public void addDischargeGaugesToTable(ArrayList<VariavelHidrologica> variaveisHidrologicas){
			this.variaveisHidrologicas=variaveisHidrologicas;
			int nrows = this.variaveisHidrologicas.size();		
		    limpaTabela(this.tabela);
			for (int r = 0; r < nrows; r++){
				this.variaveisHidrologicas.get(r).getInvhidro().setTipodeDado("Vazoes");
				this.tabela.setValueAt(true, r, 0);
				this.variaveisHidrologicas.get(r).setSelecionada(true);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getEstacao_codigo()), r, 1);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getLatitude()), r, 2);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getLongitude()), r, 3);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getDiscretizaçãoTemporária()), r, 4);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getTipodeDado()), r, 5);
				String unidadeTemp="mm";
				if(this.variaveisHidrologicas.get(r).getInvhidro().getTipodeDado().equals("Vazoes")){
					unidadeTemp="m³/s";
				}else if(this.variaveisHidrologicas.get(r).getInvhidro().getTipodeDado().equals("Cotas")){
					unidadeTemp="cm";
				}
				
			    this.tabela.setValueAt(String.valueOf(unidadeTemp), r, 6);
			    this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getDataInicialstr()), r, 7);
				this.tabela.setValueAt(String.valueOf(this.variaveisHidrologicas.get(r).getInvhidro().getDataFinalstr()), r, 8);
				
				
			}
			
			//this.checkSelecionarTodas.setSelected(true);
			
		}

		public void limpaTabela(JTable tab) {  
		    	
		    	for (int i=0;i<1000;i++){
		        	for (int j=0;j<tab.getColumnCount();j++){
		        		if(j==0){
		        			tab.setValueAt(false, i, j);
		        				
		        		}else{
		        			tab.setValueAt("", i, j);
		        	   }
		          }
		       }  
		    
		    }
}
