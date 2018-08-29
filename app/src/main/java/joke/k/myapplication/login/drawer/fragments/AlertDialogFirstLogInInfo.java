package joke.k.myapplication.login.drawer.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import joke.k.myapplication.R;

public class AlertDialogFirstLogInInfo extends DialogFragment {

    AlertDialog alertDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Welcome ! :) \n Try swap from right to left to save joke if u like it\n Or swap from left to right to get a u one\n Hope u will enjoy it ! ")
                .setPositiveButton("Got It !", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    alertDialog.dismiss();
                    }
                });
         alertDialog = builder.create();
        return alertDialog;
    }
}

