package com.marangon.applab8.model;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CuadroDialogo extends DialogFragment {

    AlertDialog.Builder builder;

    public CuadroDialogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    }

    public AlertDialog.Builder getBuilder(){
        return builder;
    }

}
