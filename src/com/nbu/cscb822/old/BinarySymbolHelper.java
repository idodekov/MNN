package com.nbu.cscb822.old;

import java.util.ArrayList;

import com.nbu.cscb822.util.Constants;
import com.nbu.cscb822.util.Utils;

@Deprecated
public class BinarySymbolHelper {
    public ArrayList<Double> convertToArray(String input) {
        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0; i<input.length();i++) {
            result.add(Double.parseDouble(""+input.charAt(i)));
        }
        return result;
    }
    
    public ArrayList<Double> resolveOutput(String symbol) {
        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0; i<2;i++) {
            result.add(0.0);
        }
        
        switch(symbol) {
        case "+":
            result.set(0, 1.0);
            break;
        case "-":
            result.set(1, 1.0);
            break;
        }
        
        return result;
    }
    
    public String generateRandomLetter() {
        StringBuilder result = new StringBuilder("");
        for(int i=0; i<25; i++) {
            long bit = Math.round(Utils.random());
            if(bit > 0) {
                result.append(bit);
            } else {
                result.append(bit * -1);
            }
        }
        return result.toString();
    }
    
    public void printLetter(ArrayList<Double> letter) {
        System.out.println("Testing with:");
        
        for(int i=0; i<letter.size();i++) {
            System.out.print(letter.get(i).intValue());
            if((i+1)%8==0) {
                System.out.println();
            }
            
        }
    }
    
    public void translateOutput(ArrayList<Double> output) {
//        if((output.get(0) > output.get(1)) && (output.get(0) > Constants.ERROR_THRESHHOLD)) {
//            System.out.println("This looks like [+].");
//        } else if((output.get(1) > output.get(0)) && (output.get(1) > Constants.ERROR_THRESHHOLD)) {
//            System.out.println("This looks like [-].");
//        }
    }
}
