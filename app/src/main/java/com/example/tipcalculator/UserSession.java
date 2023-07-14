package com.example.tipcalculator;

import com.chaquo.python.PyObject;

public class UserSession {
    private PyObject currentSession;

    public UserSession(PyObject py){
        this.currentSession = py;
    }


}
