package com.nbu.cscb822.impl;

import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.api.INeuronStrategy;
import com.nbu.cscb822.exception.NeuralNetworkException;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class BackwardPropagationNetworkFactory implements INetworkFactory, Serializable {
	private static final long serialVersionUID = -8696487981544278823L;

	@Override
    public INeuralNetwork createNetwork(int inputNeurons, int hiddenNeurons, int outputNeurons) {
        ArrayList<Integer> neuronsInLayers = new ArrayList<Integer>();
        
        neuronsInLayers.add(inputNeurons);
        neuronsInLayers.add(hiddenNeurons);
        neuronsInLayers.add(outputNeurons);
        
        return createNetwork(neuronsInLayers);
    }
    
    public INeuralNetwork createNetwork(ArrayList<Integer> neuronsInLayers) {
        INeuralNetwork nn = new NeuralNetwork();
        INeuronStrategy strategy = new BackwardPropagationNeuronStrategy();
        
        for(int neurons : neuronsInLayers) {
            NeuronLayer layer = new NeuronLayer();
            
            for(int i = 0; i<neurons; i++) {
                layer.add(new Neuron(strategy));
            }
            
            nn.getNeuronLayers().add(layer);
        }
        
        try {
            nn.connectLayers();
        } catch (NeuralNetworkException e) {
            e.printStackTrace();
        }
        
        return nn;
    }

}
