package com.nbu.cscb822.old;

import java.util.ArrayList;

import com.nbu.cscb822.util.Constants;
import com.nbu.cscb822.util.Utils;

@Deprecated
public class BinaryImageHelper {
    public ArrayList<Double> convertToArray(String input) {
        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0; i<input.length();i++) {
            result.add(Double.parseDouble(""+input.charAt(i)));
        }
        return result;
    }
    
    public ArrayList<Double> resolveOutput(String character) {
        ArrayList<Double> result = new ArrayList<Double>();
        for(int i=0; i<26;i++) {
            result.add(0.0);
        }
        
        switch(character) {
        case "A":
            result.set(0, 1.0);
            break;
        case "B":
            result.set(1, 1.0);
            break;
        case "C":
            result.set(2, 1.0);
            break;
        case "D":
            result.set(3, 1.0);
            break;
        case "E":
            result.set(4, 1.0);
            break;
        case "F":
            result.set(5, 1.0);
            break;
        case "G":
            result.set(6, 1.0);
            break;
        case "H":
            result.set(7, 1.0);
            break;
        case "I":
            result.set(8, 1.0);
            break;
        case "J":
            result.set(9, 1.0);
            break;
        case "K":
            result.set(10, 1.0);
            break;
        case "L":
            result.set(11, 1.0);
            break;
        case "M":
            result.set(12, 1.0);
            break;
        case "N":
            result.set(13, 1.0);
            break;
        case "O":
            result.set(14, 1.0);
            break;
        case "P":
            result.set(15, 1.0);
            break;
        case "Q":
            result.set(16, 1.0);
            break;
        case "R":
            result.set(17, 1.0);
            break;
        case "S":
            result.set(18, 1.0);
            break;
        case "T":
            result.set(19, 1.0);
            break;
        case "U":
            result.set(20, 1.0);
            break;
        case "V":
            result.set(21, 1.0);
            break;
        case "W":
            result.set(22, 1.0);
            break;
        case "X":
            result.set(23, 1.0);
            break;
        case "Y":
            result.set(24, 1.0);
            break;
        case "Z":
            result.set(25, 1.0);
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
            if((i+1)%5==0) {
                System.out.println();
            }
            
        }
    }
    
    public void translateOutput(ArrayList<Double> output) {
        
        String letterArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0; i<output.size();i++) {
//            if(output.get(i) > Constants.ERROR_THRESHHOLD) {
//                System.out.println("This is the letter: " + letterArray.charAt(i));
//            }
        }
        System.out.println("-------");
    }
}
