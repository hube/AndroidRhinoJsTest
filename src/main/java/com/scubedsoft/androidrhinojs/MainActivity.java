package com.scubedsoft.androidrhinojs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private EditText mTextInput;
    private TextView mTextOutput;
    private Button mSubmitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextInput = (EditText)findViewById(R.id.text_input);
        mTextOutput = (TextView)findViewById(R.id.text_output);
        mSubmitButton = (Button)findViewById(R.id.submit_button);
    }

    public void executeJs(View v) {
        mTextOutput.setText(String.format("%s%s\n",
                mTextOutput.getText(), mTextInput.getText()));
        mTextInput.setText(null);
    }
}
