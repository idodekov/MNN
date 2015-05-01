package com.nbu.cscb822.old;

import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.impl.BackwardPropagationNetworkFactory;
import com.nbu.cscb822.util.NeuralNetworkInitializer;

@Deprecated
public class AppSymbols {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-----START-----");
        INetworkFactory factory = new BackwardPropagationNetworkFactory();
        INeuralNetwork nn = factory.createNetwork(64, 34, 2);
        NeuralNetworkInitializer initializer = new NeuralNetworkInitializer(nn);
        BinarySymbolHelper helper = new BinarySymbolHelper();

        try {
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS1), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS2), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS3), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS4), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS5), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS6), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS7), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS8), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS9), helper.resolveOutput("+"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.PLUS10), helper.resolveOutput("+"));
//            
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS1), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS2), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS3), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS4), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS5), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS6), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS7), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS8), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS9), helper.resolveOutput("-"));
//            initializer.addTrainingData(helper.convertToArray(Symbols.MINUS10), helper.resolveOutput("-"));
            
            initializer.train(200);
            
            System.out.println("-----RESULTS-----");
            System.out.println("Testing with fuzzed symbol +:");
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.PLUS1)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.PLUS2)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.PLUS3)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.PLUS4)));
            System.out.println("Testing with fuzzed symbol -:");
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.MINUS1)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.MINUS2)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.MINUS3)));
            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedSymbols.MINUS4)));
        } catch (NeuralNetworkException e) {
            e.printStackTrace();
        }
        
        System.out.println("-----END-----");
    }

}
