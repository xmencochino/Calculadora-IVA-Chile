package com.example.calculadora;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.widget.*;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends Activity {
 EditText costo; SeekBar barra; TextView pct, salida;
 String pesos(double n){ NumberFormat f=NumberFormat.getCurrencyInstance(new Locale("es","CL")); f.setMaximumFractionDigits(0); return f.format(Math.round(n)); }
 void calcular(){ String s=costo.getText().toString().replace(".","").replace("$","").trim(); if(s.isEmpty()){salida.setText("");return;} try{ double c=Double.parseDouble(s.replace(",","")); int p=barra.getProgress()+10; double venta=c*(1+p/100.0), iva=venta*.19, total=venta+iva; salida.setText("Precio costo     "+pesos(c)+"\nGanancia "+p+"%     "+pesos(venta-c)+"\n------------------------\nVenta sin IVA    "+pesos(venta)+"\nIVA 19%          "+pesos(iva)+"\n------------------------\nPRECIO FINAL     "+pesos(total)); }catch(Exception e){salida.setText("Ingresa un número válido.");}}
 @Override public void onCreate(Bundle b){super.onCreate(b); LinearLayout l=new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(35,35,35,35);
  TextView t=new TextView(this); t.setText("Calculadora de IVA Chile"); t.setTextSize(26); t.setGravity(Gravity.CENTER); l.addView(t);
  costo=new EditText(this); costo.setHint("Precio de costo"); costo.setInputType(InputType.TYPE_CLASS_NUMBER); l.addView(costo);
  pct=new TextView(this); pct.setText("Ganancia: 10%"); pct.setTextSize(20); l.addView(pct);
  barra=new SeekBar(this); barra.setMax(190); barra.setProgress(0); l.addView(barra);
  TextView r=new TextView(this); r.setText("10%                                      200%"); l.addView(r);
  Button calc=new Button(this); calc.setText("CALCULAR"); l.addView(calc);
  salida=new TextView(this); salida.setTextSize(19); salida.setPadding(0,30,0,0); l.addView(salida);
  barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){public void onProgressChanged(SeekBar s,int p,boolean f){pct.setText("Ganancia: "+(p+10)+"%"); calcular();} public void onStartTrackingTouch(SeekBar s){} public void onStopTrackingTouch(SeekBar s){}});
  calc.setOnClickListener(v->calcular()); setContentView(l);
 }
}
