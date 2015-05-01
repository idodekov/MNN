package com.nbu.cscb822.impl;

import java.io.Serializable;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.api.INeuronStrategy;
import com.nbu.cscb822.util.Constants;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class BackwardPropagationNeuronStrategy implements INeuronStrategy, Serializable {
	private static final long serialVersionUID = 495757837356630311L;

	@Override
    public double findDelta(double output, double errorFactor) {
        return output * (1 - output) * errorFactor;
    }

    @Override
    public double activation(double value) {
        return (1 / (1 + Math.exp(value * -1)));
    }

    @Override
    public double findNetValue(NeuronConnections inputs, double bias) {
        double sum = bias;
        for(INeuron neuron: inputs.keySet()) {
            sum += inputs.get(neuron) * neuron.getOutputValue();
        }
        return sum;
    }

    @Override
    public double findNewBias(double bias, double delta) {
        return bias + Constants.LEARNING_RATE * 1 * delta;
    }

    @Override
    public void updateWeights(NeuronConnections connections, double delta) {
        for(INeuron neuron: connections.keySet()) {
            Double newWeight = connections.get(neuron) + Constants.LEARNING_RATE * neuron.getOutputValue() * delta;
            connections.put(neuron, newWeight);
        }
    }

}

