
package LogicaNegocio;

import java.util.Date;

public class Horario {
    private String dias;
    private Date horaInicial;
    private Date horaFinal;

    public Horario() {
    }

    public Horario(String dias, Date horaInicial, Date horaFinal) {
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

    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    
}
