package com.nbu.cscb822.old;

import java.util.ArrayList;

import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.impl.BackwardPropagationNetworkFactory;
import com.nbu.cscb822.util.NeuralNetworkInitializer;
import com.nbu.cscb822.util.Utils;

@Deprecated
public class SineApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-----START-----");
        INetworkFactory factory = new BackwardPropagationNetworkFactory();
        INeuralNetwork nn = factory.createNetwork(1, 4, 1);
        NeuralNetworkInitializer initializer = new NeuralNetworkInitializer(nn);
        
        try {
            System.out.println("-----Start training-----");
            for(int i=0; i<50000; i++) {
                ArrayList<Double> input = new ArrayList<Double>();
                ArrayList<Double> output = new ArrayList<Double>();
                double randomRadian = Utils.randomRadians();
                double result = (Math.sin(randomRadian) + 1)/2;
                input.add(randomRadian);
                output.add(result);
//                initializer.addTrainingData(input, output);
            }
            initializer.train(100);
            System.out.println("-----Finish training-----");
            
            double allDeltas = 0.0;
            int testIterations = 1000;
            for(int i=0; i<testIterations;i++) {
                double randomRadian = Utils.randomRadians();
                System.out.println("Testing with X="+randomRadian);
                Double expectedResult = Math.sin(randomRadian);
                System.out.println("Expecting result sin(X)=" + expectedResult);
                ArrayList<Double> input = new ArrayList<Double>();
                input.add(randomRadian);
                ArrayList<Double> output = nn.runNetowrk(input);
                Double result = output.get(0);
                result = result*2 - 1;
                System.out.println("Result sin(X)=" + result);
                System.out.println("-----");
                
                
                double delta = expectedResult - result;
                
                if(delta < 0) {
                    delta = delta * (-1);
                }
                
                allDeltas+=delta;
            }
            System.out.println("Error is " + allDeltas/testIterations);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
