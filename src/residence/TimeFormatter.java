/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package residence;

/**
 *
 * @author admin
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

public class TimeFormatter extends MaskFormatter {

    private static final long serialVersionUID = 1L;

    public TimeFormatter() { 
        try {
        setMask("##/##/####");
        setPlaceholderCharacter('0');
        setAllowsInvalid(false);
        setOverwriteMode(true);
        } catch (ParseException ex) {
            Logger.getLogger(TimeFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object stringToValue(String string) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (string == null) {
            string = "00/00/0000";
        }
        return df.parse(string);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (value == null) {
            value = new Date(0);
        }
        return df.format((Date) value);
    }
}