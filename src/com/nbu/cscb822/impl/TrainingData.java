package com.nbu.cscb822.impl;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class TrainingData implements Serializable {
	private static final long serialVersionUID = -267343121974460736L;
	private ArrayList<Double> inputs;
    private ArrayList<Double> outputs;
    
    public TrainingData(ArrayList<Double> inputs, ArrayList<Double> outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }
    public TrainingData() {
        this.inputs = new ArrayList<Double>();
        this.outputs = new ArrayList<Double>();
    }
    
    public ArrayList<Double> getInputs() {
        return inputs;
    }
    public void setInputs(ArrayList<Double> inputs) {
        this.inputs = inputs;
    }
    public ArrayList<Double> getOutputs() {
        return outputs;
    }
    public void setOutputs(ArrayList<Double> outputs) {
        this.outputs = outputs;
    }
}
