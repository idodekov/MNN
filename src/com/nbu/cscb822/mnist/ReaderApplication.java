package com.nbu.cscb822.mnist;

import com.nbu.cscb822.util.Constants;

/**
 * NOT USED
 * 
 * @author Iliyan
 *
 */
@Deprecated
public class ReaderApplication {

    public static void main(String[] args) {
        MnistImageFileReader readerTrain = new MnistImageFileReader(Constants.TRAIN_LABEL_FILE, Constants.TRAIN_IMAGE_FILE);
        readerTrain.start();
        
        MnistImageFileReader readerTest = new MnistImageFileReader(Constants.TEST_LABEL_FILE, Constants.TEST_IMAGE_FILE);
        readerTest.start();
        
        try {
            readerTrain.join();
            readerTest.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        readerTrain.getList();
        readerTest.getList();
    }

}
