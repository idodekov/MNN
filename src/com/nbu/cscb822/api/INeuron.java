package com.nbu.cscb822.api;

import java.io.Serializable;

import com.nbu.cscb822.impl.NeuronConnections;
import com.nbu.cscb822.util.NeuronCollection;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public interface INeuron extends Serializable {
    double getBiasValue();
    
    void setBiasValue(double biasValue);
    
    double getOutputValue();
    
    void setOutputValue(double outputValue);
    
    double getDeltaValue();
    
    void setDeltaValue(double delta);
    
    NeuronConnections getInputs();
    
    void setInputs(NeuronConnections inputs);
    
    NeuronCollection getForwardConnections();
    
    void setForwardConnections(NeuronCollection forwardConnections);
    
    INeuronStrategy getStrategy();
    
    void setStrategy(INeuronStrategy strategy);
    
    void updateOutput();
    
    void updateDelta(double errorFactor);
    
    void updateFreeParams();
}
