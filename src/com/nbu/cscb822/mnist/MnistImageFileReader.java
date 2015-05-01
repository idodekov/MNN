package com.nbu.cscb822.mnist;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.nbu.cscb822.util.Constants;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class MnistImageFileReader extends Thread implements Serializable{
	private static final long serialVersionUID = 2188983259730159759L;
	private String labelFileName;
    private String imageFileName;
    private int globalDataSetSize;
    private int xSize;
    private int ySize;
    
    private MnistList list;
    
    public MnistImageFileReader(String labelFileName, String imageFileName) {
        this.labelFileName = labelFileName;
        this.imageFileName = imageFileName;
        list = new MnistList();
    }
    
    public MnistList getList() {
        return list;
    }
    
    @Override
    public void run() {
        int[] labelData = getFileData(labelFileName, Constants.LABEL_MAGIC_NUMBER);
        int[] imageData = getFileData(imageFileName, Constants.IMAGE_MAGIC_NUMBER);
        int dataSize = xSize * ySize;
        
        for(int i = 0; i < globalDataSetSize; i++) {
            MnistImage image = new MnistImage();
            image.setLabel(labelData[i]);
            ArrayList<Double> imageDataArray = new ArrayList<Double>();
            
            int lowerBowndry = dataSize * i;
            int upperBowndry = dataSize * (i + 1);
            for(int j = lowerBowndry; j < upperBowndry; j++) {
                imageDataArray.add(imageData[j]/255.0);
            }
            
            image.setData(imageDataArray);
            list.add(image);
        }
        
        labelData = null;
        imageData = null;
    }
    
    private int[] getFileData(String fileName, int magicNumber) {
        DataInputStream in = null;
        int[] dataArray = null;
        
        try {
            in = new DataInputStream(new FileInputStream(fileName));
            int fileMagicNumber = in.readInt();
            System.out.println("Magic number for file " + fileName + " is " + fileMagicNumber);
            
            if(fileMagicNumber != magicNumber) {
                throw new IOException("Expected a file with magic number [" + magicNumber + 
                        "], but got one with magic number [" + fileMagicNumber + "].");
            }
            
            globalDataSetSize = in.readInt();
            
            int dataSetSize = globalDataSetSize;
            
            if(Constants.IMAGE_MAGIC_NUMBER == fileMagicNumber) {
                xSize = in.readInt();
                ySize = in.readInt();
                
                dataSetSize = dataSetSize * xSize * ySize;
            }
            
            System.out.println("Entry size for file " + fileName + " is " + dataSetSize);
            
            byte[] byteArray = new byte[dataSetSize];
            dataArray = new int[dataSetSize];
            
            in.read(byteArray, 0 , dataSetSize);
            
            for(int i=0; i<byteArray.length ; i++) {
                byte b = byteArray[i];
                dataArray[i] = b & 0xFF; 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return dataArray;
    }
}
