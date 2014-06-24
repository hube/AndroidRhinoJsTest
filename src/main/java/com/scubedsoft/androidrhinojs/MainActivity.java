package com.scubedsoft.androidrhinojs;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

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
    private Context mJsContext;
    private ScriptableObject mScope;

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
        String cmd = mTextInput.getText().toString();
        mTextOutput.setText(String.format("%s%s\n", mTextOutput.getText(), cmd));
        mTextInput.setText(null);

        try {
            mJsContext = Context.enter();
            mJsContext.setOptimizationLevel(-1);
            mScope = mJsContext.initStandardObjects(mScope, false);

            Object result = mJsContext.evaluateString(mScope, cmd, "MainActivity.java", 45, null);
            mTextOutput.setText(String.format("%s%s\n", mTextOutput.getText(), result.toString()));
        } finally {
            Context.exit();
        }
    }
}
