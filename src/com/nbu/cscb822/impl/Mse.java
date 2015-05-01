package com.nbu.cscb822.impl;

import java.io.Serializable;

public class Mse implements Serializable {
	private static final long serialVersionUID = 6010276792891345324L;
	private Double mseValue;
	
	public Mse(Double mseValue) {
		super();
		this.mseValue = mseValue;
	}
	
	public Double getMseValue() {
		return mseValue;
	}
	public void setMseValue(Double mseValue) {
		this.mseValue = mseValue;
	}
}
