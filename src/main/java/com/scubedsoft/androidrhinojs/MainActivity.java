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

    /**
     * Executes the JavaScript entered into the EditText view.
     *
     * Implementation instructions adapted from:
     *   https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino/Embedding_tutorial
    */
    public void executeJs(View v) {
        // Retrieve text from the EditView, then clear it out
        String cmd = mTextInput.getText().toString();
        mTextOutput.setText(String.format("%s%s\n", mTextOutput.getText(), cmd));
        mTextInput.setText(null);

        try {
            // Create and enter a JavaScript Context
            // Note that this is an instance of org.mozilla.javascript.Context NOT android.content.Context
            mJsContext = Context.enter();

            // Disable optimizations. This is necessary or else we get this exception:
            //   java.lang.UnsupportedOperationException: can't load this type of class file
            mJsContext.setOptimizationLevel(-1);

            // This invocation of initStandardObjects allows us to re-use the existing mScope object
            mScope = mJsContext.initStandardObjects(mScope, false);

            // Evaluate the JavaScript and save the result
            Object result = mJsContext.evaluateString(mScope, cmd, "MainActivity.java", 52, null);

            // Output the result to the TextView
            mTextOutput.setText(String.format("%s%s\n", mTextOutput.getText(), result.toString()));
        } finally {
            // Ensure that we release the resources consumed by the Context
            Context.exit();
        }
    }
}
