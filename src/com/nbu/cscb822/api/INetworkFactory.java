package com.nbu.cscb822.api;

import java.io.Serializable;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public interface INetworkFactory extends Serializable {
    INeuralNetwork createNetwork(int inputNeurons, int hiddenNeurons, int outputNeurons);
}
