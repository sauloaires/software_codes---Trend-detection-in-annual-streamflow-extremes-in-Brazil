package gui.tableResultTrendTeste;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;

public class RenderizaCelulaTableResultEstac extends JLabel implements TableCellRenderer{

    /**
* 
*/
private static final long serialVersionUID = 1L;
	private String columnName;
    public RenderizaCelulaTableResultEstac(String column)
        {
        this.columnName = column;
        setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
	         Object columnValue=table.getValueAt(row,table.getColumnModel().getColumnIndex(columnName));
	        
	         if (value != null) { 
	        	 setText(value.toString());
	         }else{
	        	 setText("");
	         }
	         
	            setHorizontalAlignment(JLabel.CENTER);
	            setBackground(table.getBackground());
	            setForeground(table.getForeground());
	             
	           if(column == 6){  
	             if(columnValue != null){
		             /*if (columnValue.equals("S(0,01)")) {
		            	 setBackground(java.awt.Color.red);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }else if(columnValue.equals("S(0,05)")) {
		            	 setBackground(java.awt.Color.orange);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }else if(columnValue.equals("S(0,10)")) {
		            	 setBackground(java.awt.Color.yellow);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }else if(columnValue.equals("NS")) {
		            	 setBackground(java.awt.Color.cyan);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }*/
		             			            
		             if(columnValue.equals("NS")) {
		            	 setBackground(java.awt.Color.cyan);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }else if(columnValue.equals("-99999")) {
		            	 setBackground(java.awt.Color.LIGHT_GRAY);
				         setForeground(Color.BLACK); 
				         setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 
		             }else{
		            	 setBackground(java.awt.Color.red);
		            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
		             }
		           
		             
	             }
	          }else if(column == 0 || column == 1){
	        	  //setBackground(Color.getHSBColor(1, 0, 0));
	        	  //setBackground(new Color(1, 0, 0));
	        	  //new Color(255, 0, 255)
	        	 setBackground(java.awt.Color.LIGHT_GRAY);
	        	// setFont(new Font("arial", Font.BOLD, 12));    
	             setForeground(Color.BLACK); 
	             setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
	             		             
	          } else if(column == 2 || column == 3){
		        	 setBackground(java.awt.Color.LIGHT_GRAY);
		             setForeground(Color.BLACK); 
		             setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		             		             
		       } else if(column == 8 || column == 10){
		    	  // if(columnValue.equals("") || columnValue.equals(null)){
		    	   if(columnValue== null){
		    		   setBackground(java.awt.Color.LIGHT_GRAY);
	        	         setForeground(Color.BLACK); 
	             setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
		    	   }else{
		    		   if(columnValue.equals("Increase") || columnValue.equals("Maior") ) {
			    		   setBackground(java.awt.Color.BLUE);
			            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.BLUE) );
			            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
			    		   
			    	   }else if(columnValue.equals("Decrease") || columnValue.equals("Menor")){
			    		   
			    		     setBackground(java.awt.Color.ORANGE);
			            	 setBorder(new MatteBorder(0, 2, 1, 0, Color.ORANGE) );
			            	 setFont(new Font("Times New Roman", Font.BOLD, 12));
			    	   }else{
			    		   setBackground(java.awt.Color.LIGHT_GRAY);
				        	         setForeground(Color.BLACK); 
				             setBorder(new MatteBorder(0, 2, 1, 0, Color.RED) );
			    	   }
		    	   }
		    	   
		    	   
		       }

        return this;

        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        }


}
