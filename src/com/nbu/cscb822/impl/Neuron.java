package com.nbu.cscb822.impl;

import java.io.Serializable;

import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.api.INeuronStrategy;
import com.nbu.cscb822.util.NeuronCollection;
import com.nbu.cscb822.util.Utils;

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
    public void updateFreeParams() {
        strategy.findNewBias(bias, delta);
        strategy.updateWeights(inputs, delta);
    }
}
