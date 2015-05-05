package com.nbu.cscb822.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class Constants {
	static {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("nn.properties"));
			
			LEARNING_RATE = Double.parseDouble(props.getProperty("learning.rate"));
			NEURONS_INPUT = Integer.parseInt(props.getProperty("neurons.input.layer"));
			NEURONS_HIDDEN = Integer.parseInt(props.getProperty("neurons.hidden.layer"));
			NEURONS_OUTPUT = Integer.parseInt(props.getProperty("neurons.output.layer"));
			TRAIN_LABEL_FILE = props.getProperty("file.training.label");
			TRAIN_IMAGE_FILE = props.getProperty("file.training.image");
			TEST_LABEL_FILE = props.getProperty("file.testing.label");
			TEST_IMAGE_FILE = props.getProperty("file.testing.image");
			VAL_LABEL_FILE = props.getProperty("file.validation.label");
			VAL_IMAGE_FILE = props.getProperty("file.validation.image");
			IMAGE_X_AXIS_SIZE = Integer.parseInt(props.getProperty("image.pixels.x"));
			IMAGE_Y_AXIS_SIZE = Integer.parseInt(props.getProperty("image.pixels.y"));
			OVERFITTING_CHECK_ENABLED = Boolean.parseBoolean(props.getProperty("overfitting.check.enabled"));
			TRAINING_MODE = props.getProperty("training.mode");
			RANDOM_SEED = props.getProperty("random.seed");
			MOMENTUM_ENABLED = Boolean.parseBoolean(props.getProperty("momentum.enable"));
			MOMENTUM_RATE = Double.parseDouble(props.getProperty("momentum.rate"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
    public static double LEARNING_RATE;
    public static int NEURONS_INPUT;
    public static int NEURONS_HIDDEN;
    public static int NEURONS_OUTPUT;
    
    public static String TRAIN_LABEL_FILE;
    public static String TRAIN_IMAGE_FILE;
    public static String TEST_LABEL_FILE;
    public static String TEST_IMAGE_FILE;
    public static String VAL_LABEL_FILE;
    public static String VAL_IMAGE_FILE;
    
    public static int LABEL_MAGIC_NUMBER = 0x00000801;
    public static int IMAGE_MAGIC_NUMBER = 0x00000803;
    
    public static int IMAGE_X_AXIS_SIZE;
    public static int IMAGE_Y_AXIS_SIZE;
    
    public static boolean OVERFITTING_CHECK_ENABLED;
    public static String TRAINING_MODE;
    public static String RANDOM_SEED;
    
    public static boolean MOMENTUM_ENABLED;
    public static double MOMENTUM_RATE;
}
