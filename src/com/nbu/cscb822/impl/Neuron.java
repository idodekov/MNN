package com.nbu.cscb822.impl;

import java.io.Serializable;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.api.INeuronStrategy;
import com.nbu.cscb822.util.NeuronCollection;
import com.nbu.cscb822.util.Utils;
import com.nbu.cscb822.util.WeightUpdates;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class Neuron implements INeuron, Serializable {
	private static final long serialVersionUID = -3264326299917828092L;
	private double bias = Utils.random();
    private double output;
    private double delta;
    private NeuronCollection forwardConnections = new NeuronCollection();
    private NeuronConnections inputs = new NeuronConnections();
    private INeuronStrategy strategy;
    private WeightUpdates lastInputWeightUpdates = new WeightUpdates(); // for momentum
    private WeightUpdates summedInputWeightUpdates = new WeightUpdates(); // for batch training
    private double lastBiasUpdate = 0.0; // for momentum
    private double summedBiasUpdate = 0.0; // for batch training
    
	public Neuron() {
    }
    
    public Neuron(INeuronStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double getBiasValue() {
        return bias;
    }
    
    @Override
    public void setBiasValue(double biasValue) {
        this.bias = biasValue;
        
    }

    @Override
    public double getOutputValue() {
        return output;
    }
    
    @Override
    public void setOutputValue(double outputValue) {
        this.output = outputValue;
        
    }

    @Override
    public double getDeltaValue() {
        return delta;
    }
    
    @Override
    public void setDeltaValue(double delta) {
        this.delta = delta;
        
    }
    
    @Override
    public NeuronCollection getForwardConnections() {
        return forwardConnections;
    }
    
    @Override
    public void setForwardConnections(NeuronCollection forwardConnections) {
        this.forwardConnections = forwardConnections;
        
    }
    
    @Override
    public NeuronConnections getInputs() {
        return inputs;
    }
    
    @Override
    public void setInputs(NeuronConnections inputs) {
        this.inputs = inputs;
    }
    
    @Override
    public WeightUpdates getLastInputWeightUpdates() {
		return lastInputWeightUpdates;
	}

    @Override
	public void setLastInputWeightUpdates(WeightUpdates lastInputWeightUpdates) {
		this.lastInputWeightUpdates = lastInputWeightUpdates;
	}

    @Override
	public WeightUpdates getSummedInputWeightUpdates() {
		return summedInputWeightUpdates;
	}

    @Override
	public void setSummedInputWeightUpdates(WeightUpdates summedInputWeightUpdates) {
		this.summedInputWeightUpdates = summedInputWeightUpdates;
	}
    
    @Override
    public double getLastBiasUpdate() {
		return lastBiasUpdate;
	}

    @Override
	public void setLastBiasUpdate(double lastBiasUpdate) {
		this.lastBiasUpdate = lastBiasUpdate;
	}

    @Override
	public double getSummedBiasUpdate() {
		return summedBiasUpdate;
	}

    @Override
	public void setSummedBiasUpdate(double summedBiasUpdate) {
		this.summedBiasUpdate = summedBiasUpdate;
	}

    @Override
    public INeuronStrategy getStrategy() {
        return strategy;
    }
    
    @Override
    public void setStrategy(INeuronStrategy strategy) {
        this.strategy = strategy;
        
    }

    @Override
    public void updateOutput() {
        double netValue = strategy.findNetValue(inputs, bias);
        output = strategy.activation(netValue);
    }

    @Override
    public void updateDelta(double errorFactor) {
        delta = strategy.findDelta(output, errorFactor);
    }

    @Override
    public void updateFreeParams(boolean updateWeights) {
    	bias = strategy.findNewBias(bias, lastBiasUpdate, summedBiasUpdate, delta, updateWeights);
        strategy.updateWeights(inputs, lastInputWeightUpdates, summedInputWeightUpdates, delta, updateWeights);
    }
    
    public void resetBatchParameters() {
    	summedBiasUpdate = 0.0;
    	for(INeuron neuron: summedInputWeightUpdates.keySet()) {
    		summedInputWeightUpdates.put(neuron, 0.0);
    	}
    }

}
