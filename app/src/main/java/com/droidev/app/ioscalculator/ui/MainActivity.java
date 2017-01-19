package com.droidev.app.ioscalculator.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.droidev.app.ioscalculator.R;
import com.droidev.app.ioscalculator.util.CalculationHelper;
import com.droidev.app.ioscalculator.util.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAX_ENTRY_LIMIT = 10;
    private TextView mMainTextField;
    private int mOperand = -1;
    private String mFirstValue;
    private String mSecondValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMainTextField = (TextView) findViewById(R.id.main_text_field);
        findViewById(R.id.number_0).setOnClickListener(this);
        findViewById(R.id.decimal_point).setOnClickListener(this);
        findViewById(R.id.equals).setOnClickListener(this);
        findViewById(R.id.number_1).setOnClickListener(this);
        findViewById(R.id.number_2).setOnClickListener(this);
        findViewById(R.id.number_3).setOnClickListener(this);
        findViewById(R.id.number_4).setOnClickListener(this);
        findViewById(R.id.number_5).setOnClickListener(this);
        findViewById(R.id.number_6).setOnClickListener(this);
        findViewById(R.id.number_7).setOnClickListener(this);
        findViewById(R.id.number_8).setOnClickListener(this);
        findViewById(R.id.number_9).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.division).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.sign).setOnClickListener(this);
        findViewById(R.id.percentage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.number_0:
                handleNumberClick(Constants.Numbers.NUMBER_0);
                break;
            case R.id.number_1:
                handleNumberClick(Constants.Numbers.NUMBER_1);
                break;
            case R.id.number_2:
                handleNumberClick(Constants.Numbers.NUMBER_2);
                break;
            case R.id.number_3:
                handleNumberClick(Constants.Numbers.NUMBER_3);
                break;
            case R.id.number_4:
                handleNumberClick(Constants.Numbers.NUMBER_4);
                break;
            case R.id.number_5:
                handleNumberClick(Constants.Numbers.NUMBER_5);
                break;
            case R.id.number_6:
                handleNumberClick(Constants.Numbers.NUMBER_6);
                break;
            case R.id.number_7:
                handleNumberClick(Constants.Numbers.NUMBER_7);
                break;
            case R.id.number_8:
                handleNumberClick(Constants.Numbers.NUMBER_8);
                break;
            case R.id.number_9:
                handleNumberClick(Constants.Numbers.NUMBER_9);
                break;
            case R.id.decimal_point:
                handleNumberClick(Constants.Numbers.DECIMAL);
                break;
            case R.id.equals:
                handleActionClick(Constants.Operands.EQUAL);
                break;
            case R.id.plus:
                handleActionClick(Constants.Operands.PLUS);
                break;
            case R.id.minus:
                handleActionClick(Constants.Operands.MINUS);
                break;
            case R.id.multiply:
                handleActionClick(Constants.Operands.MULTIPLY);
                break;
            case R.id.division:
                handleActionClick(Constants.Operands.DIVISION);
                break;
            case R.id.percentage:
                handleActionClick(Constants.Operands.PERCENTAGE);
                break;
            case R.id.sign:
                handleSign();
                break;
            case R.id.clear:
                handleClear();
                break;
        }
    }

    private void handleClear() {
        mMainTextField.setText("");
        mOperand = -1;
        mFirstValue = "";
        mSecondValue = "";
    }

    private void handleSign() {
        String currentEntry = mMainTextField.getText().toString();

        /*if (!TextUtils.isEmpty(currentEntry)) {
            String sign = currentEntry.substring(currentEntry.length() - 1, currentEntry.length());

            if (sign.equals("-")) {
                currentEntry = sign.replace("-", "");
            } else {
                currentEntry += "-";
            }
            mMainTextField.setText(currentEntry);
        }*/
    }

    private void handleActionClick(int operation) {

        if (operation != Constants.Operands.EQUAL && TextUtils.isEmpty(mFirstValue)) {
            mFirstValue = mMainTextField.getText().toString().replaceAll(",", "");
            mMainTextField.setText("");
            mOperand = operation;
        } else if (operation != Constants.Operands.EQUAL && !TextUtils.isEmpty(mFirstValue) && TextUtils.isEmpty(mSecondValue)) {
            mSecondValue = mMainTextField.getText().toString().replaceAll(",", "");
            performOperation(operation);
            mMainTextField.setText("");
            mOperand = operation;
        } else if (operation != Constants.Operands.EQUAL && TextUtils.isEmpty(mSecondValue)) {
            mMainTextField.setText("");
        } else {
            mSecondValue = mMainTextField.getText().toString().replaceAll(",", "");
            performOperation(operation);
            mOperand = -1;
        }
    }

    private void performOperation(int operation) {

        if (operation != -1) {
            if (!TextUtils.isEmpty(mFirstValue)) {
                double firstValue;

                try {
                    firstValue = Double.parseDouble(mFirstValue);
                } catch (NumberFormatException nfe) {
                    firstValue = 0;
                }

                if (!TextUtils.isEmpty(mSecondValue)) {
                    double secondValue = Double.parseDouble(mSecondValue);
                    doCalculation(mOperand, firstValue, secondValue);
                } else {
                    doCalculation(mOperand, firstValue, firstValue);
                }
            }
        }
    }

    private void doCalculation(int operation, double value1, double value2) {
        String result = "";

        switch (operation) {
            case Constants.Operands.PLUS:
                result = CalculationHelper.sumTwoNumbers(value1, value2);
                mMainTextField.setText(result);
                break;

            case Constants.Operands.MINUS:
                result = CalculationHelper.substractTwoNumbers(value1, value2);
                mMainTextField.setText(result);
                break;

            case Constants.Operands.MULTIPLY:
                result = CalculationHelper.multiplyTwoNumbers(value1, value2);
                mMainTextField.setText(result);
                break;

            case Constants.Operands.DIVISION:
                result = CalculationHelper.divideTwoNumbers(value1, value2);
                mMainTextField.setText(result);
                break;

            case Constants.Operands.PERCENTAGE:
                result = CalculationHelper.percentageTwoNumbers(value1, value2);
                mMainTextField.setText(result);
                break;
        }
        mFirstValue = result;
        mSecondValue = "";
    }

    private void handleNumberClick(String number) {
        String currentEntry = mMainTextField.getText().toString().trim();

        if (currentEntry.length() < MAX_ENTRY_LIMIT) {
            mMainTextField.setText(currentEntry + number);
        }
    }
}
