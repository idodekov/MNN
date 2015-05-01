package com.nbu.cscb822.old;

import com.nbu.cscb822.MnistHelper;
import com.nbu.cscb822.api.INetworkFactory;
import com.nbu.cscb822.api.INeuralNetwork;
import com.nbu.cscb822.exception.NeuralNetworkException;
import com.nbu.cscb822.impl.BackwardPropagationNetworkFactory;
import com.nbu.cscb822.util.NeuralNetworkInitializer;

@Deprecated
public class AppNumber {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-----START-----");
        INetworkFactory factory = new BackwardPropagationNetworkFactory();
        INeuralNetwork nn = factory.createNetwork(64, 50, 2);
        NeuralNetworkInitializer initializer = new NeuralNetworkInitializer(nn);
        MnistHelper helper = new MnistHelper();
        
        try {
//            Integer count = 1;
//            for(int i=0;i<Numbers.ALL_NUMBERS.length;i++) {
//                initializer.addTrainingData(helper.convertToArray(Numbers.ALL_NUMBERS[i]), helper.resolveOutput(count.toString()));
//                
//                if((i+1)%5 == 0) {
//                    count++;
//                }
//            }
//            initializer.addTrainingData(helper.convertToArray(Numbers.ONE1), helper.resolveOutput("1"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ONE2), helper.resolveOutput("1"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ONE3), helper.resolveOutput("1"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ONE4), helper.resolveOutput("1"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ONE5), helper.resolveOutput("1"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.TWO1), helper.resolveOutput("2"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.TWO2), helper.resolveOutput("2"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.TWO3), helper.resolveOutput("2"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.TWO4), helper.resolveOutput("2"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.TWO5), helper.resolveOutput("2"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.THREE1), helper.resolveOutput("3"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.THREE2), helper.resolveOutput("3"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.THREE3), helper.resolveOutput("3"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.THREE4), helper.resolveOutput("3"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.THREE5), helper.resolveOutput("3"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FOUR1), helper.resolveOutput("4"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FOUR2), helper.resolveOutput("4"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FOUR3), helper.resolveOutput("4"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FOUR4), helper.resolveOutput("4"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FOUR5), helper.resolveOutput("4"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FIVE1), helper.resolveOutput("5"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FIVE2), helper.resolveOutput("5"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FIVE3), helper.resolveOutput("5"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FIVE4), helper.resolveOutput("5"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.FIVE5), helper.resolveOutput("5"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SIX1), helper.resolveOutput("6"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SIX2), helper.resolveOutput("6"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SIX3), helper.resolveOutput("6"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SIX4), helper.resolveOutput("6"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SIX5), helper.resolveOutput("6"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SEVEN1), helper.resolveOutput("7"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SEVEN2), helper.resolveOutput("7"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SEVEN3), helper.resolveOutput("7"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SEVEN4), helper.resolveOutput("7"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.SEVEN5), helper.resolveOutput("7"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.EIGHT1), helper.resolveOutput("8"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.EIGHT2), helper.resolveOutput("8"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.EIGHT3), helper.resolveOutput("8"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.EIGHT4), helper.resolveOutput("8"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.EIGHT5), helper.resolveOutput("8"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.NINE1), helper.resolveOutput("9"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.NINE2), helper.resolveOutput("9"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.NINE3), helper.resolveOutput("9"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.NINE4), helper.resolveOutput("9"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.NINE5), helper.resolveOutput("9"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ZERO1), helper.resolveOutput("0"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ZERO2), helper.resolveOutput("0"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ZERO3), helper.resolveOutput("0"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ZERO4), helper.resolveOutput("0"));
//            initializer.addTrainingData(helper.convertToArray(Numbers.ZERO5), helper.resolveOutput("0"));
            initializer.train(100);
            
            System.out.println("-----RESULTS-----");
            System.out.println("Testing with fuzzed number 1:");
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.ONE1)));
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.ONE2)));
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.ONE3)));
//            System.out.println("Testing with fuzzed number 2:");
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.TWO1)));
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.TWO2)));
//            helper.translateOutput(nn.runNetowrk(helper.convertToArray(FuzzedNumbers.TWO3)));
        } catch (NeuralNetworkException e) {
            e.printStackTrace();
        }
        
        System.out.println("-----END-----");
    }

}
