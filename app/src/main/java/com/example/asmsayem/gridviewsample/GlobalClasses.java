package com.example.asmsayem.gridviewsample;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by A.S.M Sayem on 12/12/2017.
 */

public class GlobalClasses {

    public GlobalClasses() {
    }

    public void Tostyfy(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
