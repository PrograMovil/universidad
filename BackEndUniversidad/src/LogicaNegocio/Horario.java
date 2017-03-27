
package LogicaNegocio;

import java.io.Serializable;
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
        return "Horario{" + " Dias: " + dias + ", hora inicial: " + horaInicial.getTime().getHours() + ":"+ horaInicial.getTime().getMinutes() + ", hora final: " + horaFinal.getTime().getHours() + ":"+ horaFinal.getTime().getMinutes() + " }";
    }
    
}
