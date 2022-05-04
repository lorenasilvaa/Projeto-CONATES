package conates.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData {
    
    public static Date formatar(String data, String formato) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(formato);
        Date date = formatter.parse(data);
        return date;
    }
    
    public static String formatar(Date data, String formato) {
        DateFormat formatter = new SimpleDateFormat(formato);
        String dataStr = formatter.format(data); 
        return dataStr;
    }
}
