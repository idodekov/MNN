package com.nbu.cscb822.util;

import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.impl.Neuron;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class NeuronCollection extends ArrayList<INeuron> implements Serializable {
    private static final long serialVersionUID = 2102045278385654256L;

    public boolean add() {
        INeuron neuron = new Neuron();
        return super.add(neuron);
    }
}
