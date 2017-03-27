
package LogicaNegocio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Horario implements Serializable{
    private String dias;
    private Calendar horaInicial;
    private Calendar horaFinal;

    public Horario() {
    }

    public Horario(String dias, Calendar horaInicial, Calendar horaFinal) {
        this.dias = dias;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }
    
    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public Calendar getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Calendar horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Calendar getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Calendar horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        return " Dias: " + dias + ", Hora inicial: " + sdf.format(horaInicial.getTime())  + ", Hora final: " + sdf.format(horaFinal.getTime());
    }
    
}
