package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot, btnEqual, btnPlus, btnMinus, btnSqrt,
            btnDivide, btnMultiply, btnBraceOpen, btnBraceClose, btnPower, btnE, btnClear, btnDel, btnSin, btnCos, btnTan, btnLog10;
    TextView editText, resultText;
    private CalculatorViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        assignNamesToIds();
        model = ViewModelProviders.of(this).get(CalculatorViewModel.class);
        model.getExpression().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) { editText.setText(s);
            }
        });
    }
    protected void assignNamesToIds() {

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnDot = findViewById(R.id.btnDot);

        btnEqual = findViewById(R.id.btnEqual);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMultiply = findViewById(R.id.btnMultiply);

        btnBraceOpen = findViewById(R.id.btnBraceOpen);
        btnBraceClose = findViewById(R.id.btnBraceClose);
        btnPower = findViewById(R.id.btnPower);

        btnSqrt = findViewById(R.id.btnSqrt);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnLog10 = findViewById(R.id.btnLog10);
        btnSin = findViewById(R.id.btnSin);
        btnE = findViewById(R.id.btnE);
        btnClear = findViewById(R.id.btnClear);
        btnDel = findViewById(R.id.btnDel);

        resultText = findViewById(R.id.resultText);
        editText = findViewById(R.id.editText);
    }
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.btnPi:
                    model.changeExpression(String.valueOf(Math.PI));
                    break;

                case R.id.btnE:
                    model.changeExpression(String.valueOf(Math.E));
                    break;

                case R.id.btnClear:
                    model.clearExpression();
                    resultText.setText("");
                    break;

                case R.id.btnDel:
                    model.backspaceExpression();
                    resultText.setText("");
                    break;



                case R.id.btnSin:
                    model.changeExpression("sin(");
                    break;

                case R.id.btnCos:
                    model.changeExpression("cos(");
                    break;

                case R.id.btnTan:
                    model.changeExpression("tan(");
                    break;

                case R.id.btnLog10:
                    model.changeExpression("log10(");
                    break;



                case R.id.btnBraceOpen:
                    model.changeExpression("(");
                    break;

                case R.id.btnBraceClose:
                    model.changeExpression(")");
                    break;

                case R.id.btnSqrt:
                    model.changeExpression("sqrt(");
                    break;

                case R.id.btnPower:
                    model.changeExpression("^");
                    break;



                case R.id.btn7:
                    model.changeExpression("7");
                    break;

                case R.id.btn8:
                    model.changeExpression("8");
                    break;

                case R.id.btn9:
                    model.changeExpression("9");
                    break;

                case R.id.btnDivide:
                    model.changeExpression("/");
                    break;



                case R.id.btn4:
                    model.changeExpression("4");
                    break;

                case R.id.btn5:
                    model.changeExpression("5");
                    break;

                case R.id.btn6:
                    model.changeExpression("6");
                    break;

                case R.id.btnMultiply:
                    model.changeExpression("*");
                    break;



                case R.id.btn1:
                    model.changeExpression("1");
                    break;

                case R.id.btn2:
                    model.changeExpression("2");
                    break;

                case R.id.btn3:
                    model.changeExpression("3");
                    break;

                case R.id.btnMinus:
                    model.changeExpression("-");
                    break;



                case R.id.btnDot:
                    model.changeExpression(".");
                    break;

                case R.id.btn0:
                    model.changeExpression("0");
                    break;

                case R.id.btnEqual:
                    double result = calculateExpression(editText.getText().toString());
                    String resultString = String.valueOf(result);
                    if (resultString.equals("NaN")) {
                        throw new Exception("Mathematic error");
                    } else {
                        resultText.setText(String.valueOf(result));
                    }
                    break;

                case R.id.btnPlus:
                    model.changeExpression("+");
                    break;
            }
        } catch (Exception e) {
            model.clearExpression();
            resultText.setText("");
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
    private double calculateExpression(String expression) {
        return new ExpressionBuilder(expression)
                .build()
                .evaluate();
    }
}
