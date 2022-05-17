package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class ST_verificarNdiasFevereiro {
	public static Date verificarNdiasFevereiro(Date utilDateIniMesFim, int mesFim){
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fev =formatter.format(utilDateIniMesFim);
		String[] fevstr = fev.split("/");
		Date utilDateNovoIniMesFim = null;
		int anoFinal=Integer.parseInt(fevstr[2]);
		int ndiasFinal=ST_pegarMaximoDiaMes.ndias(anoFinal,mesFim);
		int diasAtual=Integer.parseInt(fevstr[0]);
		if(diasAtual != ndiasFinal){
				String novaDataStr=ndiasFinal+"/"+mesFim+"/"+fevstr[2];
				try {
				utilDateNovoIniMesFim=formatter.parse(novaDataStr);
				} catch (ParseException e) {
				e.printStackTrace();
				}
		}else{
			utilDateNovoIniMesFim=utilDateIniMesFim;	
		}
		return utilDateNovoIniMesFim;
		
	}
}
