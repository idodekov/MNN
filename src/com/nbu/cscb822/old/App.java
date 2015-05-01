package com.nbu.cscb822.old;

import java.util.ArrayList;

import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.impl.BackwardPropagationNetworkFactory;
import com.nbu.cscb822.util.NeuralNetworkInitializer;

@Deprecated
public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-----START-----");
        INetworkFactory factory = new BackwardPropagationNetworkFactory();
        INeuralNetwork nn = factory.createNetwork(25, 40, 26);
        NeuralNetworkInitializer initializer = new NeuralNetworkInitializer(nn);
        BinaryImageHelper helper = new BinaryImageHelper();
        
        try {
//            initializer.addTrainingData(helper.convertToArray(Letters.A), helper.resolveOutput("A"));
//            initializer.addTrainingData(helper.convertToArray(Letters.B), helper.resolveOutput("B"));
//            initializer.addTrainingData(helper.convertToArray(Letters.C), helper.resolveOutput("C"));
//            initializer.addTrainingData(helper.convertToArray(Letters.D), helper.resolveOutput("D"));
//            initializer.addTrainingData(helper.convertToArray(Letters.E), helper.resolveOutput("E"));
//            initializer.addTrainingData(helper.convertToArray(Letters.F), helper.resolveOutput("F"));
//            initializer.addTrainingData(helper.convertToArray(Letters.G), helper.resolveOutput("G"));
//            initializer.addTrainingData(helper.convertToArray(Letters.H), helper.resolveOutput("H"));
//            initializer.addTrainingData(helper.convertToArray(Letters.I), helper.resolveOutput("I"));
//            initializer.addTrainingData(helper.convertToArray(Letters.J), helper.resolveOutput("J"));
//            initializer.addTrainingData(helper.convertToArray(Letters.K), helper.resolveOutput("K"));
//            initializer.addTrainingData(helper.convertToArray(Letters.L), helper.resolveOutput("L"));
//            initializer.addTrainingData(helper.convertToArray(Letters.M), helper.resolveOutput("M"));
//            initializer.addTrainingData(helper.convertToArray(Letters.N), helper.resolveOutput("N"));
//            initializer.addTrainingData(helper.convertToArray(Letters.O), helper.resolveOutput("O"));
//            initializer.addTrainingData(helper.convertToArray(Letters.P), helper.resolveOutput("P"));
//            initializer.addTrainingData(helper.convertToArray(Letters.Q), helper.resolveOutput("Q"));
//            initializer.addTrainingData(helper.convertToArray(Letters.R), helper.resolveOutput("R"));
//            initializer.addTrainingData(helper.convertToArray(Letters.S), helper.resolveOutput("S"));
//            initializer.addTrainingData(helper.convertToArray(Letters.T), helper.resolveOutput("T"));
//            initializer.addTrainingData(helper.convertToArray(Letters.U), helper.resolveOutput("U"));
//            initializer.addTrainingData(helper.convertToArray(Letters.V), helper.resolveOutput("V"));
//            initializer.addTrainingData(helper.convertToArray(Letters.W), helper.resolveOutput("W"));
//            initializer.addTrainingData(helper.convertToArray(Letters.X), helper.resolveOutput("X"));
//            initializer.addTrainingData(helper.convertToArray(Letters.Y), helper.resolveOutput("Y"));
//            initializer.addTrainingData(helper.convertToArray(Letters.Z), helper.resolveOutput("Z"));
            initializer.train(1000);
            
            System.out.println("-----RESULTS-----");
            ArrayList<Double> letter = helper.convertToArray(FuzzedLetters.A1);
            helper.printLetter(letter);
            helper.translateOutput(nn.runNetowrk(letter));
        } catch (NeuralNetworkException e) {
            e.printStackTrace();
        }
        
        System.out.println("-----END-----");
    }

}
