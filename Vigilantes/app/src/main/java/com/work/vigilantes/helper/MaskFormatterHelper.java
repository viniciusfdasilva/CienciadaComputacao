package com.work.vigilantes.helper;

import android.view.View;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MaskFormatterHelper{
    private SimpleMaskFormatter smf;
    private MaskTextWatcher mtw;

    public MaskFormatterHelper(String mask, EditText view){
        this.smf = new SimpleMaskFormatter(mask);
        this.mtw = new MaskTextWatcher(view,smf);
    }// End MaskFormatterHelper

    public MaskTextWatcher getMtw(){
        return mtw;
    }// End getMtw()
}// End MaskFormatterHelper
