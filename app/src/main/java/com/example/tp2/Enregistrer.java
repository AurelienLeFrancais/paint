package com.example.tp2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.provider.MediaStore;

import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Enregistrer extends Dialog {
    private AlertDialog.Builder saveDialog;
    private ContentResolver contentResolver;

    public Enregistrer(@NonNull Context context, MainActivity.Surface s, ContentResolver contentResolver) {
        super(context);
        this.contentResolver = contentResolver;
        this.saveDialog = new AlertDialog.Builder(context);
        this.saveDialog.setTitle("Enregistrer le dessin");
        this.saveDialog.setMessage("Voulez-vous enregistrer?");

        if (s.getBackground() == null) {//si je nai pas change la couleur du background par defaut je le met blanc sinon il passera au noir
            s.setBackgroundColor(Color.WHITE);
        }

        saveDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //on active le cache
                s.setDrawingCacheEnabled(true);
                //on essaie d'ecrire l'image dans un fichier. La methode insertImage() permt d'essayer d'ecrire l'image dans la gallerie d'image du telephone
                String imgSaved = MediaStore.Images.Media.insertImage(contentResolver, s.getDrawingCache(), UUID.randomUUID().toString() + ".png", "drawing");
                //Nous passons le résolveur de contenu, dessinant le cache pour la vue affichée, une chaîne UUID générée aléatoirement pour le nom de fichier avec l'extension PNG et une courte description.

                //Si ca a fonctionne
                if (imgSaved != null) {
                    Toast savedToast = Toast.makeText(context.getApplicationContext(),
                            "Fichier enregistre!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                //Si ca na pas fonctionne
                else {
                    Toast unsavedToast = Toast.makeText(context.getApplicationContext(),
                            "Le fichier n'a pu etre enregistre", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                s.destroyDrawingCache();

            }

        });
        saveDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        saveDialog.show();

    }


}
