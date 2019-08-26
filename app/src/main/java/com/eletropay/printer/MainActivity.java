package com.eletropay.printer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    EletroPayPrinter eletroPayPrinter;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eletroPayPrinter = new EletroPayPrinter(this);
        eletroPayPrinter.init();
    }




    private void printer() {
        handler = new Handler();
        handler.postDelayed(() -> {

            if (eletroPayPrinter.getPrinterStatus() == eletroPayPrinter.PRINTER_NORMAL) {
                eletroPayPrinter.AddCommand(new PrinterCommand("msg 04", 16,0,0));
                eletroPayPrinter.AddCommand(new PrinterCommand("msg 01", 24,0,1));
                eletroPayPrinter.AddCommand(new PrinterCommand("msg 02", 24,0,0));
                eletroPayPrinter.AddCommand(new PrinterCommand("msg 03", 24,2,0));


                eletroPayPrinter.printer();
            } else {
                printer();
            }
        }, 1000);
    }

    public void onClickPrinter(View v){
        printer();
    }
}
