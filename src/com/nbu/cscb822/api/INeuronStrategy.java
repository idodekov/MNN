package com.nbu.cscb822.api;

import java.io.Serializable;

import com.nbu.cscb822.impl.NeuronConnections;
import com.nbu.cscb822.util.WeightUpdates;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public interface INeuronStrategy extends Serializable {
    double findDelta(double output, double errorFactor);

    double activation(double value);

    double findNetValue(NeuronConnections inputs, double bias);

    double findNewBias(double bias, double lastBiasUpdate, double summedBiasUpdate, double delta, boolean updateWeights);

    void updateWeights(NeuronConnections connections, WeightUpdates lastInputWeightUpdates, WeightUpdates summedInputWeightUpdates, double delta, boolean updateWeights);
}
