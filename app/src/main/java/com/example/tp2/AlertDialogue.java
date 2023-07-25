package com.example.tp2;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class AlertDialogue extends Dialog {
    private SeekBar seekBar;
    private TextView textTailleCrayon;
    private int taille = 4;//taille par defaut
    private Button boutonSortir;
    private MainActivity mainActivity;//pour recuperer la taille du crayon se trouvant dans MainActivity

    public AlertDialogue(@NonNull Context context) {
        super(context);
        mainActivity = (MainActivity) context;
        setContentView(R.layout.boite_dialogue);
        boutonSortir = findViewById(R.id.boutonSortir);
        seekBar = findViewById(R.id.seekBar);
        textTailleCrayon = findViewById(R.id.textTailleCrayon);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(30);
                textTailleCrayon.setText("taille crayon : " + String.valueOf(progress));
                mainActivity.setTailleSpecial(progress);
                taille = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        boutonSortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.setTailleSpecial(taille);
                dismiss();
            }
        });
    }

    public int getTaille() {
        return taille;
    }

}
