package com.nbu.cscb822.impl;

import java.io.Serializable;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.api.INeuronStrategy;
import com.nbu.cscb822.util.BiasUpdate;
import com.nbu.cscb822.util.Constants;
import com.nbu.cscb822.util.WeightUpdates;

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
    public double findNewBias(double bias, BiasUpdate lastBiasUpdate, BiasUpdate summedBiasUpdate, double delta, boolean updateWeights) {
    	double biasUpdate = Constants.LEARNING_RATE * 1 * delta;
    	
    	if(Constants.MOMENTUM_ENABLED) {
    		biasUpdate = biasUpdate + lastBiasUpdate.getUpdateValue() * Constants.MOMENTUM_RATE;
        }
    	
    	lastBiasUpdate.setUpdateValue(biasUpdate);
    	summedBiasUpdate.setUpdateValue(summedBiasUpdate.getUpdateValue() + biasUpdate);
    	
    	if(updateWeights) {
    		bias = bias + biasUpdate;
    	}
        return bias;
    }

    @Override
    public void updateWeights(NeuronConnections connections, WeightUpdates lastInputWeightUpdates, WeightUpdates summedInputWeightUpdates, double delta, boolean updateWeights) {
        for(INeuron neuron: connections.keySet()) {
        	double weightUpdate = Constants.LEARNING_RATE * neuron.getOutputValue() * delta;
            
            if(Constants.MOMENTUM_ENABLED) {
            	weightUpdate = weightUpdate + lastInputWeightUpdates.get(neuron) * Constants.MOMENTUM_RATE;
            }
            
            lastInputWeightUpdates.put(neuron, weightUpdate);
            double weightSum = summedInputWeightUpdates.get(neuron) + weightUpdate;
            summedInputWeightUpdates.put(neuron, weightSum);
            
            Double newWeight = connections.get(neuron) + weightUpdate;
            
            if(updateWeights) {
            	connections.put(neuron, newWeight);
            }
        }
    }

}

