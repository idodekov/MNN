package com.nbu.cscb822.mnist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author Iliyan Dodekov
 *
 */
public class MnistList extends ArrayList<MnistImage> implements Serializable {
    private static final long serialVersionUID = 2481020459647150511L;

    public MnistList() {
        super();
    }

    public MnistList(Collection<? extends MnistImage> arg0) {
        super(arg0);
    }

    public MnistList(int arg0) {
        super(arg0);
    }
    
}
