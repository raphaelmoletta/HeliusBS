package br.edu.utfpr.dainf.eex23.web;

import br.edu.utfpr.dainf.eex23.heliusbeans.DataBean;
import br.edu.utfpr.dainf.eex23.heliusbeans.InstantBean;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rapha
 */
public class Model {
    private static Model model;
    private final List<DataBean> data;

    private Model() {
        data = new ArrayList<>();
    }
    
    public synchronized InstantBean getInstant() {
        if (data.isEmpty()) {
            return null;
        }
        //TODO
        InstantBean instant = new InstantBean();
        instant.setTemperature(10);
        return instant;
    }
    
    public synchronized void add(DataBean d) {
        System.out.println(d.toString());
        this.data.add(d);
    }
    
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public Map<LocalDateTime, Double> getGraphic() {
        return null;
    }
}
