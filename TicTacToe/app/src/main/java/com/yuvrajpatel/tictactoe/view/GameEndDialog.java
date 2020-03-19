package com.yuvrajpatel.tictactoe.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuvrajpatel.tictactoe.MainActivity;
import com.yuvrajpatel.tictactoe.R;
import com.yuvrajpatel.tictactoe.utilities.StringUtilities;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class GameEndDialog extends DialogFragment {

    private static final String TAG = "TAG_GameEndDialog";
    private MainActivity mActivity;
    private View rootView;
    private String mStrWinner;


    public static GameEndDialog newInstance(MainActivity activity, String winner) {
        GameEndDialog dialog = new GameEndDialog();
        dialog.mActivity = activity;
        dialog.mStrWinner = winner;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.str_new_game, null)
                .setNegativeButton(R.string.str_exit,null)
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
                .inflate(R.layout.dialog_gameend, null, false);

        TextView mTextGameResult = rootView.findViewById(R.id.txt_GameResult);
        mTextGameResult.setText(mActivity.getString(R.string.str_winner_is) +
                mActivity.getString(R.string.str_space) + mStrWinner);
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onStartNewGameClicked();
        });

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(v -> {
            onExitClicked();
        });
    }


    private void onStartNewGameClicked() {
        Log.d(TAG,"Start New Game Clicked");
        mActivity.playAgain();
        dismiss();
    }

    private void onExitClicked() {
        Log.d(TAG,"Exit Clicked");
        dismiss();
    }
}
