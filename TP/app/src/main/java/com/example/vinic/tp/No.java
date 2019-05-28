package com.example.vinic.tp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vinicius Francisco da Silva
 */
public class No{
    Object object;
    Map<Object,No> filho;

    /**
     *
     */
    public No(Object object){
        this.object = object;
        filho = new HashMap<>();
    }// End No()

    /**
     *
     */
    public No(){
        this.object = null;
        filho = new HashMap<>();
    }// End No()
}// End class No

