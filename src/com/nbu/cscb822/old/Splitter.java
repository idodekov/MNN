package com.nbu.cscb822.old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.nbu.cscb822.util.Constants;

public class Splitter {

	public static void main(String[] args) {
		File input = new File("F:\\workspaces\\work\\CSCB822NN\\resources\\temp\\t10k-images.idx3-ubyte");
		File o1 = new File("F:\\workspaces\\work\\CSCB822NN\\resources\\temp\\i2");

		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(o1));
			dos.writeInt(Constants.IMAGE_MAGIC_NUMBER);
			dos.writeInt(5000);
			dos.writeInt(28);
			dos.writeInt(28);
			
			
			
			
			
			DataInputStream in = null;
	        int[] dataArray = null;
	        
	        try {
	            in = new DataInputStream(new FileInputStream(input));
	            int fileMagicNumber = in.readInt();
	            
	            int globalDataSetSize = in.readInt();
	            
	            int dataSetSize = globalDataSetSize;
	            
	            int xSize;
	            int ySize;
	            if(Constants.IMAGE_MAGIC_NUMBER == fileMagicNumber) {
	                xSize = in.readInt();
	                ySize = in.readInt();
	                
	                dataSetSize = dataSetSize * xSize * ySize;
	            }
	            
	            byte[] byteArray = new byte[dataSetSize];
	            dataArray = new int[dataSetSize];
	            
	            in.read(byteArray, 0 , dataSetSize);
	            
	            for(int i=28*28*5000; i<28*28*10000 ; i++) {
	                //byte b = byteArray[i];
	                //dataArray[i] = b & 0xFF;
	                dos.writeByte(byteArray[i]);
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
			
			
			
			
			
			dos.flush();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
