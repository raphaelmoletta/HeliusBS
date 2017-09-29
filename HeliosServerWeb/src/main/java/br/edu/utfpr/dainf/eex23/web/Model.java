package br.edu.utfpr.dainf.eex23.web;

import br.edu.utfpr.dainf.eex23.web.bean.Data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rapha
 */
public class Model {
    private static Model model;
    private final List<Data> data;
    private Model() {
        data = new ArrayList<>();
    }
    
    public synchronized Data getLast() {
        if (data.isEmpty()) {
            return null;
        }
        return data.get(data.size() - 1);
    }
    
    public synchronized void add(Data d) {
        System.out.println(d.toString());
        this.data.add(d);
    }
    
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
}
