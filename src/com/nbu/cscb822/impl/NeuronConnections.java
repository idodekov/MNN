package com.nbu.cscb822.impl;

import java.io.Serializable;
import java.util.HashMap;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.util.Utils;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class NeuronConnections extends HashMap<INeuron, Double> implements Serializable {
    private static final long serialVersionUID = 534781415424028133L;

    public Double put(INeuron neuron) {
        return super.put(neuron, Utils.random());
    }

}
