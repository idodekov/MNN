package com.nbu.cscb822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.mnist.MnistImageFileReader;
import com.nbu.cscb822.mnist.MnistList;
import com.nbu.cscb822.util.Constants;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class MNIST {
	private INeuralNetwork nn;
	private MnistList trainingList;
	private MnistList testingList;
	private MnistList validationList;
	private BufferedReader consoleReader;
	private MnistHelper helper;
	
	
	public MNIST() {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		helper = new MnistHelper();
	}
	
	public void loadExternalImageFiles() {
		MnistImageFileReader readerTrain = new MnistImageFileReader(Constants.TRAIN_LABEL_FILE, Constants.TRAIN_IMAGE_FILE);
        readerTrain.start();
        
        MnistImageFileReader readerTest = new MnistImageFileReader(Constants.TEST_LABEL_FILE, Constants.TEST_IMAGE_FILE);
        readerTest.start();
        
        MnistImageFileReader readerValidation = new MnistImageFileReader(Constants.VAL_LABEL_FILE, Constants.VAL_IMAGE_FILE);
        readerValidation.start();
        
        try {
            readerTrain.join();
            readerTest.join();
            readerValidation.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        trainingList = readerTrain.getList();
        testingList = readerTest.getList();
        validationList = readerValidation.getList();
	}
	
	public void runApplication() {
		System.out.println("Please wait. Loading files...");
		loadExternalImageFiles();
		
		while(true) {
			System.out.println();
			System.out.println("MNIST Neural Network for handwritten digit recognition. Choose the desired option:");
			System.out.println("1.  Create a new neural network.");
			System.out.println("2.  Load a neural network from a file.");
			System.out.println("3.  Save neural network to a file.");
			System.out.println("4.  Train the neural network.");
			System.out.println("5.  Test the neural network.");
			System.out.println("6.  Print average error rate.");
			System.out.println("7.  Print average mean square error history for training data.");
			System.out.println("8.  Print average mean square error history for validation data.");
			System.out.println("99. Quit.");
			System.out.print("Your choise: ");
			try {
				String choise = consoleReader.readLine();
				System.out.println();
				switch(choise) {
				case "1":
					nn = helper.createNewNN(nn);
					break;
				case "2":
					nn = helper.loadNN(nn);
					break;
				case "3":
					nn = helper.saveNN(nn);
					break;
				case "4":
					nn = helper.trainNN(nn, trainingList, testingList, validationList);
					break;
				case "5":
					nn = helper.testNN(nn, testingList);
					break;
				case "6":
					helper.printErrorRate(nn, testingList);
					break;
				case "7":
					helper.printMseHistory(nn, false);
					break;
				case "8":
					helper.printMseHistory(nn, true);
					break;
				case "99":
					System.out.println("Good bye!");
					System.exit(0);
				default:
					System.out.println("Unknown option. Try again.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
		
	}

    public static void main(String[] args) {
        new MNIST().runApplication();
    }
    
}
