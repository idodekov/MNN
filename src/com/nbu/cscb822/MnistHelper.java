package com.nbu.cscb822;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.impl.BackwardPropagationNetworkFactory;
import com.nbu.cscb822.mnist.MnistImage;
import com.nbu.cscb822.mnist.MnistList;
import com.nbu.cscb822.util.Constants;
import com.nbu.cscb822.util.MseHistory;
import com.nbu.cscb822.util.NeuralNetworkInitializer;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class MnistHelper {
	private BufferedReader consoleReader;
	
	public MnistHelper() {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public INeuralNetwork createNewNN(INeuralNetwork nn) {
		INetworkFactory factory = new BackwardPropagationNetworkFactory();
        nn = factory.createNetwork(Constants.NEURONS_INPUT, 
        							Constants.NEURONS_HIDDEN, 
        							Constants.NEURONS_OUTPUT);
        System.out.println("Neural Network successfully initialized!");
        return nn;
	}
	
	public INeuralNetwork trainNN(INeuralNetwork nn, MnistList trainingList, MnistList testingList, MnistList validationList) {
		if(nn == null) {
			System.out.println("Neural Network is not initialized!");
			return null;
		}
		
		long timestamp = System.currentTimeMillis();
        
        NeuralNetworkInitializer initializer = new NeuralNetworkInitializer(nn);
        
        try {
            Iterator<MnistImage> it = trainingList.iterator();
            while(it.hasNext()) {
                MnistImage image = it.next();
                initializer.addTrainingData(image.getData(), resolveOutput("" + image.getLabel()), false);
            }
            
            System.out.println("Finished adding training data.");
            
            if(Constants.OVERFITTING_CHECK_ENABLED) {
	            Iterator<MnistImage> vit = validationList.iterator();
	            while(vit.hasNext()) {
	                MnistImage image = vit.next();
	                initializer.addTrainingData(image.getData(), resolveOutput("" + image.getLabel()), true);
	            }
	            
	            System.out.println("Finished adding validation data.");
            }
            
            System.out.print("Enter the number of epochs: ");
        	String stringLine = consoleReader.readLine();
        	Integer line = Integer.parseInt(stringLine);
        	System.out.println();
        	System.out.println("Training is in process. Please wait.");
        	
        	initializer.train(line);
            
            double time = (System.currentTimeMillis() - timestamp)/60000.0 ;
            System.out.println("Training done in " + time + " minutes.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return nn;
	}
	
	public void printMseHistory(INeuralNetwork nn, boolean validation) {
		if(nn == null) {
			System.out.println("Neural Network is not initialized!");
			return;
		}
		
		MseHistory history;
		if(validation) {
			history = nn.getValidationMseHistory();
		} else {
			history = nn.getTrainingMseHistory();
		}
		for (int i = 0; i < history.size(); i++) {
			System.out.println("Training epoch [" + (i+1) + "] has finished with MSE value of [" + history.get(i).getMseValue() + "].");
		}
	}
	
	public double printErrorRate(INeuralNetwork nn, MnistList list, String datasetName) {
		if(nn == null) {
			System.out.println("Neural Network is not initialized!");
			return 100.0;
		}
		
        try {
        	System.out.println("-----");
        	System.out.println("Calculating average error rate on " + datasetName + " data...");
	        Iterator<MnistImage> it = list.iterator();
	        int errorCount = 0;
	        int errorCount_0 = 0;
	        int errorCount_1 = 0;
	        int errorCount_2 = 0;
	        int errorCount_3 = 0;
	        int errorCount_4 = 0;
	        int errorCount_5 = 0;
	        int errorCount_6 = 0;
	        int errorCount_7 = 0;
	        int errorCount_8 = 0;
	        int errorCount_9 = 0;
	        while(it.hasNext()) {
	            MnistImage image = it.next();
	            int result = translateOutput(nn.runNetowrk(image.getData()), false);
	            if(result != image.getLabel()) {
	            	errorCount++;
	            	
	            	switch(image.getLabel()) {
	                case 0:
	                	errorCount_0++;
	                    break;
	                case 1:
	                	errorCount_1++;
	                    break;
	                case 2:
	                	errorCount_2++;
	                    break;
	                case 3:
	                	errorCount_3++;
	                    break;
	                case 4:
	                	errorCount_4++;
	                    break;
	                case 5:
	                	errorCount_5++;
	                    break;
	                case 6:
	                	errorCount_6++;
	                    break;
	                case 7:
	                	errorCount_7++;
	                    break;
	                case 8:
	                	errorCount_8++;
	                    break;
	                case 9:
	                	errorCount_9++;
	                    break;
	                }
	            }
	        }
	        
	        System.out.println("Finished testing the data.");
	        double errorRate = (errorCount/(double)list.size())*100;
	        System.out.println("Error count is [" + errorCount + "] from [" + list.size() + 
	        		"]. That's [" + errorRate + "%].");
	        System.out.println("Error count for number [0] is " + errorCount_0);
	        System.out.println("Error count for number [1] is " + errorCount_1);
	        System.out.println("Error count for number [2] is " + errorCount_2);
	        System.out.println("Error count for number [3] is " + errorCount_3);
	        System.out.println("Error count for number [4] is " + errorCount_4);
	        System.out.println("Error count for number [5] is " + errorCount_5);
	        System.out.println("Error count for number [6] is " + errorCount_6);
	        System.out.println("Error count for number [7] is " + errorCount_7);
	        System.out.println("Error count for number [8] is " + errorCount_8);
	        System.out.println("Error count for number [9] is " + errorCount_9);
	        System.out.println("-----");
	        
	        return errorRate;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return 100.0;
	}
	
	public INeuralNetwork testNN(INeuralNetwork nn, MnistList testingList) {
		if(nn == null) {
			System.out.println("Neural Network is not initialized!");
			return null;
		}
		
		while(true) {
        	try {
	        	System.out.print("Enter the desired letter number from the testing set or [b] to return: ");
	        	String stringLine = consoleReader.readLine();
	        	if("b".equalsIgnoreCase(stringLine)) {
	        		break;
	        	}
	        	
	        	Integer line = Integer.parseInt(stringLine);
	        	
	        	MnistImage image = testingList.get(line);
	        	image.drawToOutputStream(System.out);
	        	System.out.println("Testing with number: " + image.getLabel());
	        	translateOutput(nn.runNetowrk(image.getData()), true);
	        	//Double mse = nn.calculateMse(new TrainingData(image.getData(), resolveOutput("" + image.getLabel())));
	        	//System.out.println("Square error for this run is: " + mse);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        	continue;
	        }
        }
		
		return nn;
	}
	
	public INeuralNetwork saveNN(INeuralNetwork nn) {
		if(nn == null) {
			System.out.println("Neural Network is not initialized!");
			return null;
		}
		
    	try {
    		System.out.print("Enter file to save the network to: ");
			String fileString = consoleReader.readLine();
			File file = new File(fileString);
			
			if(file.exists()) {
				throw new IOException("File already exists");
			}
			
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(nn);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return nn;
	}
	
	public INeuralNetwork loadNN(INeuralNetwork nn) {
		try {
    		System.out.print("Enter file to load the network from: ");
			String fileString = consoleReader.readLine();
			File file = new File(fileString);
			
			if(!file.exists() || file.isDirectory()) {
				throw new IOException("File doesn't exist or is a directory.");
			}
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			nn = (INeuralNetwork) ois.readObject();
			ois.close();
			System.out.println("Neural Network successfully loaded!");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nn;
	}
	
    public ArrayList<Double> resolveOutput(String number) {
        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0; i<Constants.NEURONS_OUTPUT;i++) {
            result.add(0.0);
        }
        
        switch(number) {
        case "0":
            result.set(0, 1.0);
            break;
        case "1":
            result.set(1, 1.0);
            break;
        case "2":
            result.set(2, 1.0);
            break;
        case "3":
            result.set(3, 1.0);
            break;
        case "4":
            result.set(4, 1.0);
            break;
        case "5":
            result.set(5, 1.0);
            break;
        case "6":
            result.set(6, 1.0);
            break;
        case "7":
            result.set(7, 1.0);
            break;
        case "8":
            result.set(8, 1.0);
            break;
        case "9":
            result.set(9, 1.0);
            break;
        }
        
        return result;
    }
    
    public int translateOutput(ArrayList<Double> output, boolean print) {
    	NumberFormat formatter = new DecimalFormat("#0.0000");
        
        double max = output.get(0);
        int result = 0;
        String dump = "Neural Network output - [0:" + formatter.format(max);
        
        for(int i=1; i<output.size();i++) {
        	dump += " ," + i +":" + formatter.format(output.get(i));
        	
        	if(max < output.get(i)) {
        		max = output.get(i);
        		result = i;
        	}
        }
        
        dump += "]";
        
        if(print) {
	        System.out.println("This looks like the number: " + result);
	        System.out.println(dump);
        }
        
        return result;
    }
}
