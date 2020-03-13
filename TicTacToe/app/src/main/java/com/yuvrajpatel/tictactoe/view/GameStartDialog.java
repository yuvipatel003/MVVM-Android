package com.yuvrajpatel.tictactoe.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yuvrajpatel.tictactoe.MainActivity;
import com.yuvrajpatel.tictactoe.R;
import com.yuvrajpatel.tictactoe.utilities.StringUtilities;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class GameStartDialog extends DialogFragment {

    private static final String TAG = "TAG_GameStartDialog";
    private MainActivity mActivity;
    private View rootView;
    private EditText mEditPlayerOne;
    private EditText mEditPlayerTwo;

    public static GameStartDialog newInstance(MainActivity activity) {
        GameStartDialog dialog = new GameStartDialog();
        dialog.mActivity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.str_start, null)
                .setNegativeButton(R.string.str_cancel,null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_gamestart, null, false);

        mEditPlayerOne = rootView.findViewById(R.id.edit_PlayerOne);
        mEditPlayerTwo = rootView.findViewById(R.id.edit_PlayerTwo);
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onStartClicked();
        });

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(v -> {
            onCancelClicked();
        });
    }


    private void onStartClicked() {
        Log.d(TAG,"Start Clicked");

        String strNamePlayerOne = mEditPlayerOne.getText().toString();
        String strNamePlayerTwo = mEditPlayerTwo.getText().toString();

        if(StringUtilities.isValidName(strNamePlayerOne) &&
            StringUtilities.isValidName(strNamePlayerTwo)) {
            mActivity.onPlayersSet(strNamePlayerOne, strNamePlayerTwo);
            dismiss();
        } else {
            Log.d(TAG,"Players name is invalid");
            Toast.makeText(mActivity,"Players name is invalid",Toast.LENGTH_SHORT).show();
        }
    }

    private void onCancelClicked() {
        Log.d(TAG,"Cancel Clicked");
        dismiss();
    }
}
