package componenteDatos;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convertir {

    public static java.sql.Date convertJavaDateTOSQLDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String convertirFechaString(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
