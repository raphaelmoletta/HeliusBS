package br.edu.utfpr.dainf.eex23.heliusclient;

import br.edu.utfpr.dainf.eex23.heliusbeans.Instant;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author Raphael Zagonel Moletta <raphael.moletta@gmail.com>
 */
public class HeliusClient {
    
    private final String url;
    public enum TYPE {Efficiency, Current, Voltage, Economy}
    public HeliusClient(String url) {
        this.url = url;
    }
    
    //Temperatura Ambiente, Eficiência, Incidência Solar, Economia desde o início
    public Instant getInstant(){
        return null;
    }
    
    //Gráfico de Eficiência, Gráfico de Corrente, Gráfico de Tensão e Gráfico de Economia
    public Map<LocalDateTime, Double> getGraphicData(LocalDateTime begin, LocalDateTime end, TYPE type) {
        return null;
    }
    
    
}