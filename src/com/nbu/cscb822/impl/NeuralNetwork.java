package com.nbu.cscb822.impl;

import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.api.INeuron;
import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.util.MseHistory;
import com.nbu.cscb822.util.NeuronLayerCollection;
import com.nbu.cscb822.util.Utils;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class NeuralNetwork implements INeuralNetwork, Serializable {
	private static final long serialVersionUID = -5641718730177075073L;
	private NeuronLayerCollection layers = new NeuronLayerCollection();
	private MseHistory trainingMseHistory = new MseHistory();
	private MseHistory validationMseHistory = new MseHistory();

    @Override
    public void trainNetwork(TrainingData t, boolean updateWeights) throws NeuralNetworkException {
        if(layers.size() < 2) {
            throw new NeuralNetworkException("Not enough layers!");
        }
        
        if(this.getInputLayer().size() != t.getInputs().size()) {
            throw new NeuralNetworkException("Mismatch of inputs!");
        }
        
        if(this.getOutputLayer().size() != t.getOutputs().size()) {
            throw new NeuralNetworkException("Mismatch of outputs!");
        }
        
        try {
            int i = 0;
            
            for(INeuron neuron : this.getInputLayer()) {
                neuron.setOutputValue(t.getInputs().get(i));
                i++;
            }
            
            NeuronLayer nl;
            
            for(int count = 1; count < layers.size(); count++) {
                nl = layers.get(count);
                for(INeuron neuron : nl) {
                    neuron.updateOutput();
                }
            }

            i = 0;
            for(INeuron neuron : this.getOutputLayer()) {
                neuron.updateDelta(t.getOutputs().get(i) - neuron.getOutputValue());
                i++;
            }
            
            for(i = layers.size() - 2; i>=1; i--) {
                NeuronLayer currentLayer = layers.get(i);
                
                for(INeuron neuron: currentLayer) {
                    double errorFactor = 0;
                    for(INeuron connectedNeuron : neuron.getForwardConnections()) {
                        errorFactor += connectedNeuron.getDeltaValue() * connectedNeuron.getInputs().get(neuron);
                    }
                    neuron.updateDelta(errorFactor);
                }
            }
            
	        for(i = 1; i<layers.size(); i++) {
	            for(INeuron neuron : layers.get(i)) {
	                neuron.updateFreeParams(updateWeights);
	            }
	        }
        } catch (Exception e) {
            throw new NeuralNetworkException("Error occurred while training network", e);
        }
    }
    
    @Override
    public Double calculateMse(TrainingData t) throws NeuralNetworkException {
    	ArrayList<Double> outputs = runNetowrk(t.getInputs());
    	
    	Double sum = 0.0;
    	for(int i = 0; i<outputs.size(); i++) {
    		sum += (t.getOutputs().get(i) - outputs.get(i)) * (t.getOutputs().get(i) - outputs.get(i));
    	}
    	
    	return sum/outputs.size();
    }
    
    @Override
    public void batchUpdate() throws NeuralNetworkException {
    	for(int i=1; i<layers.size(); i++) {
            for(INeuron neuron: layers.get(i)) {
                neuron.setBiasValue(neuron.getBiasValue() + neuron.getSummedBiasUpdate().getUpdateValue());
                
                for(INeuron inputNeuron: neuron.getInputs().keySet()) {
                	double oldWeight = neuron.getInputs().get(inputNeuron);
                	neuron.getInputs().put(inputNeuron, oldWeight + neuron.getSummedInputWeightUpdates().get(inputNeuron));
                }
                
                neuron.resetBatchParameters();
            }
        }
    }
    
    @Override
    public void resetBatchParameters() {
    	for(int i=1; i<layers.size(); i++) {
            for(INeuron neuron: layers.get(i)) {
                neuron.resetBatchParameters();
            }
        }
    }
    
    @Override
    public void connectNeurons(INeuron source, INeuron destination) throws NeuralNetworkException {
        connectNeurons(source, destination, Utils.random());
    }

    @Override
    public void connectNeurons(INeuron source, INeuron destination, double weight) throws NeuralNetworkException {
        if(layers.size() < 2) {
            throw new NeuralNetworkException("Not enough layers!");
        }

        //TODO
        source.getForwardConnections().add(destination);
        destination.getInputs().put(source, weight);
        destination.getLastInputWeightUpdates().put(source, 0.0);
        destination.getSummedInputWeightUpdates().put(source, 0.0);
    }

    @Override
    public void connectLayers(NeuronLayer layer1, NeuronLayer layer2) throws NeuralNetworkException {
        if(layers.size() < 2) {
            throw new NeuralNetworkException("Not enough layers!");
        }
        
        for(INeuron inputNeuron : layer1) {
            for(INeuron targetNeuron : layer2) {
                this.connectNeurons(inputNeuron, targetNeuron);
            }
        }
    }

    @Override
    public void connectLayers() throws NeuralNetworkException {
        for(int i = 1; i<layers.size(); i++) {
            this.connectLayers(layers.get(i - 1), layers.get(i));
        }
    }

    @Override
    public ArrayList<Double> runNetowrk(ArrayList<Double> inputs) throws NeuralNetworkException {
        if(inputs.size() != this.getInputLayer().size()) {
            throw new NeuralNetworkException("Number of inputs doesn't match number of neurons in input layer!");
        }
        
        int i = 0;
        for(INeuron neuron: this.getInputLayer()) {
            neuron.setOutputValue(inputs.get(i));
            i++;
        }
        
        for(i=1; i<layers.size(); i++) {
            for(INeuron neuron: layers.get(i)) {
                neuron.updateOutput();
            }
        }
        
        return this.getOutput();
    }

    @Override
    public ArrayList<Double> getOutput() {
        ArrayList<Double> output = new ArrayList<Double>();
        
        for(INeuron neuron : this.getOutputLayer()) {
            output.add(neuron.getOutputValue());
        }
        
        return output;
    }

    @Override
    public void setOutput(ArrayList<Double> output) {
        // does nothing
    }

    @Override
    public NeuronLayerCollection getNeuronLayers() {
        return layers;
    }

    @Override
    public void setNeuronLayers(NeuronLayerCollection layers) {
        this.layers = layers;
    }

    @Override
    public NeuronLayer getInputLayer() {
        return layers.get(0);
    }

    @Override
    public void setInputLayer(NeuronLayer layer) {
        // does nothing

    }

    @Override
    public NeuronLayer getOutputLayer() {
        return layers.get(layers.size() - 1);
    }

    @Override
    public void setOutputLayer(NeuronLayer layer) {
        // does nothing
    }

	public MseHistory getTrainingMseHistory() {
		return trainingMseHistory;
	}

	public MseHistory getValidationMseHistory() {
		return validationMseHistory;
	}
}
