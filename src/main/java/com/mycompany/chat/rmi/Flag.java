package com.mycompany.chat.rmi;

public class Flag {
    private boolean flag;

    public Flag(boolean aFlag) {
        flag = aFlag;
    }

    public void setFlag(boolean newValue) {
        flag = newValue;
    }

    public boolean getFlag() {
        return flag;
    }
}

