package com.nbu.cscb822.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.impl.Mse;
import com.nbu.cscb822.impl.TrainingData;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class NeuralNetworkInitializer {
    private INeuralNetwork network;
    private TrainingDataCollection trainingQueue = new TrainingDataCollection();
    private TrainingDataCollection validationQueue = new TrainingDataCollection();
    
    public NeuralNetworkInitializer(INeuralNetwork network) {
        this.network = network;
    }
    
    public void initialize(INeuralNetwork network) {
        this.network = network;
        trainingQueue.clear();
        validationQueue.clear();
    }
    
    public void addTrainingData(ArrayList<Double> input, ArrayList<Double> output, boolean validation) throws NeuralNetworkException {
        if(input.size() != network.getInputLayer().size()) {
            throw new NeuralNetworkException("Inconistency in number of layers");
        }
        
        if(output.size() != network.getOutputLayer().size()) {
            throw new NeuralNetworkException("Inconistency in number of layers");
        }
        
        TrainingData data = new TrainingData(input, output);
        
        if(validation) {
        	validationQueue.add(data);
        } else {
        	trainingQueue.add(data);        	
        }
    }
    
    public void train(int rounds) throws NeuralNetworkException {
    	System.out.println("Training mode is set to: " + Constants.TRAINING_MODE);
    	network.resetBatchParameters();
    	
        for(int roundCount = 1; roundCount <= rounds; roundCount++) {
        	long timestamp = System.currentTimeMillis();
        	
//        	if("shuffle".equalsIgnoreCase(Constants.TRAINING_MODE)) {
//        		System.out.println("Shuffling training data...");
//        		Collections.shuffle(trainingQueue, new Random(System.currentTimeMillis()));
//        		System.out.println("Traing data succesfully shuffled.");
//        	}
        	
            Iterator<TrainingData> it = trainingQueue.iterator();
            double mseSum = 0.0;
            
            if("batch".equalsIgnoreCase(Constants.TRAINING_MODE)) {
            	while(it.hasNext()) {
                    TrainingData next = it.next();
                    network.trainNetwork(next, false);
                }
            	
            	network.batchUpdate();
            } else {
            	while(it.hasNext()) {
                    TrainingData next = it.next();
                    network.trainNetwork(next, true);
                }
            }
            
            it = trainingQueue.iterator();
            while(it.hasNext()) {
                TrainingData next = it.next();
                mseSum += network.calculateMse(next);
            }
            
            double mseAverage = mseSum / trainingQueue.size();
            network.getTrainingMseHistory().add(new Mse(mseAverage));
            System.out.println("Training data MSE for round [" + roundCount + "] is [" + mseAverage + "].");
            
            if(Constants.OVERFITTING_CHECK_ENABLED) {
            	int tSize = network.getTrainingMseHistory().size();
                if(tSize >= 2) {
    	            Double diff = network.getTrainingMseHistory().get(tSize - 2).getMseValue() - network.getTrainingMseHistory().get(tSize - 1).getMseValue();
    	            if(diff < 0) {
    	            	System.out.println("Average MSE of training data is bigger than in previous iteration. The network is overfitting - training will stop now.");
    	            	return;
    	            }
                }
                
            	Iterator<TrainingData> vit = validationQueue.iterator();
                double validationMseSum = 0.0;
                while(vit.hasNext()) {
                    TrainingData next = vit.next();
                    validationMseSum += network.calculateMse(next);
                }
                
                double validationMseAverage = validationMseSum / validationQueue.size();
                network.getValidationMseHistory().add(new Mse(validationMseAverage));
                System.out.println("Validation data MSE for round [" + roundCount + "] is [" + validationMseAverage + "].");
                
                int vSize = network.getValidationMseHistory().size();
                if(vSize >= 2) {
	                Double diff = network.getValidationMseHistory().get(vSize - 2).getMseValue() - network.getValidationMseHistory().get(vSize - 1).getMseValue();
	                if(diff < 0) {
	                	System.out.println("Average MSE of validation data is bigger than in previous iteration. The network is overfitting - training will stop now.");
	                	return;
	                }
                }
            }
            
            double time = (System.currentTimeMillis() - timestamp)/60000.0 ;
            System.out.println("Epoch [" + roundCount + "] is done in " + time + " minutes.");
        }
    }
}
