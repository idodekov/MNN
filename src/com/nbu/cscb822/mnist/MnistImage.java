package com.nbu.cscb822.mnist;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.util.Constants;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class MnistImage implements Serializable {
	private static final long serialVersionUID = 5565636639319509329L;
	private int label;
    private ArrayList<Double> data;
    
    private static final int xSize = Constants.IMAGE_X_AXIS_SIZE;
    private static final int ySize = Constants.IMAGE_Y_AXIS_SIZE;
    
    public MnistImage() {}
    
    public MnistImage(int label, ArrayList<Double> data) {
        this.label = label;
        this.data = data;
    }
    
    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public ArrayList<Double> getData() {
        return data;
    }

    public void setData(ArrayList<Double> data) {
        this.data = data;
    }
    
    public void drawToOutputStream(OutputStream out) {
        PrintWriter writer = new PrintWriter(out, true);
        
        int count = 0;
        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                if(data.get(count) > 0) {
                    writer.print("+");
                } else {
                    writer.print(" ");
                }
                count++;
            }
            
            writer.println();
        }
    }
}
