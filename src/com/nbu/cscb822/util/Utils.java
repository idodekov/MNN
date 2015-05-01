package com.nbu.cscb822.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class Utils {
    public static double random() {
        Random rn = MnistRandom.getInstance();
        double result =  (((double)rn.nextInt()/(double)Integer.MAX_VALUE) * 1);
        return result;
    }
    
    public static double randomRadians() {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            double result =  (((double)random.nextInt()/(double)Integer.MAX_VALUE) * Math.PI*2);
            if(result < 0) {
                return result*(-1);
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    
    public static void main(String args[]) {
        System.out.println(random());
    }
}
