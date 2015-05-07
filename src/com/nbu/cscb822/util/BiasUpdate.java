package com.nbu.cscb822.util;

import java.io.Serializable;

public class BiasUpdate implements Serializable {
	private static final long serialVersionUID = 402063097640223212L;
	private Double updateValue;
	
	public BiasUpdate() {
		this.updateValue = 0.0;
	}
	
	public Double getUpdateValue() {
		return updateValue;
	}
	
	public void setUpdateValue(Double updateValue) {
		this.updateValue = updateValue;
	}
}
