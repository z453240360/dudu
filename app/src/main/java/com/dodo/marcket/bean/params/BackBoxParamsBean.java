package com.dodo.marcket.bean.params;

import java.io.Serializable;
import java.util.List;

public class BackBoxParamsBean implements Serializable{


    private List<BackBoxParams> returnBoxParams;

    public List<BackBoxParams> getReturnBoxParams() {
        return returnBoxParams;
    }

    public void setReturnBoxParams(List<BackBoxParams> returnBoxParams) {
        this.returnBoxParams = returnBoxParams;
    }


}
