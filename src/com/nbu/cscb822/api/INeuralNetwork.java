package com.nbu.cscb822.api;

import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.impl.NeuronLayer;
import com.nbu.cscb822.impl.TrainingData;
import com.nbu.cscb822.util.MseHistory;
import com.nbu.cscb822.util.NeuronLayerCollection;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public interface INeuralNetwork extends Serializable {
    void trainNetwork(TrainingData t) throws NeuralNetworkException;
    
    void connectNeurons(INeuron source, INeuron destination) throws NeuralNetworkException;
    
    void connectNeurons(INeuron source, INeuron destination, double weight) throws NeuralNetworkException;
    
    void connectLayers(NeuronLayer layer1, NeuronLayer layer2) throws NeuralNetworkException;
    
    void connectLayers() throws NeuralNetworkException;
    
    ArrayList<Double> runNetowrk(ArrayList<Double> inputs) throws NeuralNetworkException;
    
    ArrayList<Double> getOutput();
    
    void setOutput(ArrayList<Double> output);
    
    NeuronLayerCollection getNeuronLayers();
    
    void setNeuronLayers(NeuronLayerCollection layers);
    
    NeuronLayer getInputLayer();
    
    void setInputLayer(NeuronLayer layer);
    
    NeuronLayer getOutputLayer();
    
    void setOutputLayer(NeuronLayer layer);
    
    Double calculateMse(TrainingData t) throws NeuralNetworkException;
    
	MseHistory getTrainingMseHistory();

	MseHistory getValidationMseHistory();
}
