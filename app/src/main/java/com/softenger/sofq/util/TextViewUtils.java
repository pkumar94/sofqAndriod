package com.softenger.sofq.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TextViewUtils {

    public static void setTextChangeListener(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void requestFocusNSetError(EditText editText, String error){
        editText.requestFocus();
        editText.setError(error);
    }
}
