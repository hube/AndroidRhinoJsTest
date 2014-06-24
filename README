Android Rhino JS Test
=====================

This app demonstrates integrating the [Rhino JavaScript runtime](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino) into an Android app.

See `src/main/java/com/scubedsoft/androidrhinojs/MainActivity.java`:

```java
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
```
