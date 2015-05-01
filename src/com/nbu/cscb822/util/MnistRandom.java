package com.nbu.cscb822.util;

import java.util.Random;

public class MnistRandom extends Random {
	private static final long serialVersionUID = 2340741756358291860L;
	private static MnistRandom instance;
	
	
	private MnistRandom() {
		super();
		if(Constants.RANDOM_SEED != null && !Constants.RANDOM_SEED.trim().isEmpty()) {
			setSeed(Long.parseLong(Constants.RANDOM_SEED));
		}
		
	}
	
	public static MnistRandom getInstance() {
		if(instance == null) {
			instance = new MnistRandom();					
		}
		
		return instance;
	}

}
