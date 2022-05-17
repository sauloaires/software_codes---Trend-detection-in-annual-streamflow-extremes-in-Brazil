package types;

public class SNIRHPlugInSettings {
	/**
     * Default key to store a workbench context in a blackboard.
     */
    public final static String KEY_WORKBENCHCONTEXT_IN_BLACKBOARD = "workbenchContext";

    
    public static String getModelsMenuExtremosUNB(){
		return "EXTREMOS";
	}
    
    public static String getNameSNIRHMenu(){
      //return "FERAH";
      return "EXTREMOS_UNB";
    }
    
	public static String getNameSNIRHMenuFERAH(){
		//return "ANA";
		return "FERAH";
	}
	
	
	public static String getModelsMenu(){
		return "Modelos";
	}

	public static String resultLayerCategory(){
		return "Result";
	}
	

	public static String getModelsMenuPNSH(){
    return "PNSH";
  }

	public static String getModelsMenuSigaFERAH(){
    return "SIGA";
  }
}
