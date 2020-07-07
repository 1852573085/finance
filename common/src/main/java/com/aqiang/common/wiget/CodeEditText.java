package com.aqiang.common.wiget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class CodeEditText extends AppCompatEditText {
    private CodeEditText previous;
    private CodeEditText next;
    public CodeEditText(Context context) {
        super(context);
    }

    public CodeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(new CodeEditTextWatcher());
        initTextStyle();
    }


    private void initTextStyle() {
        setTextAlignment(TEXT_ALIGNMENT_CENTER);
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
    }

    public void setNext(CodeEditText next) {
        this.next = next;
        next.previous = this;
    }

    public void setCodeText(String text){
        if(text != null){
            String[] split = text.split(" ");
            String textTemp = "";
            for (int i = 0; i < split.length ; i++) {
                if(TextUtils.isEmpty(split[i].trim())){
                   continue;
                }
                textTemp = split[i];
                setText(textTemp);
                break;
            }
            String substring = text.substring(text.indexOf(textTemp) + textTemp.length());
            if(next != null){
                next.setCodeText(substring);
            }
            setSelection(getText().length());
        }
    }
    private class CodeEditTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = getText().toString();
            if(text.length() >= 4){
                if(next != null){
                    clearFocus();
                    next.requestFocus();
                }
            }else if(text.length() == 0){
                if(previous != null){
                    clearFocus();
                    previous.requestFocus();
                }
            }
        }
    }
}
