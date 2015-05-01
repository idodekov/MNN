package com.nbu.cscb822.api;

import java.io.Serializable;

import com.nbu.cscb822.impl.NeuronConnections;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public interface INeuronStrategy extends Serializable {
    double findDelta(double output, double errorFactor);

    double activation(double value);

    double findNetValue(NeuronConnections inputs, double bias);

    double findNewBias(double bias, double delta);

    void updateWeights(NeuronConnections connections, double delta);
}
