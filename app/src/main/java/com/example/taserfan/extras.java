package com.example.taserfan;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.taserfan.MainActivity;

public class extras {
    public static void crearDialogos(Context context, String titulo, String mensaje){
        AlertDialog builder = new AlertDialog.Builder(context).create();
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
