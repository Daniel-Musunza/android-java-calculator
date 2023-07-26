package com.example.javacalculator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView inputTextView;
    private TextView resultTextView;

    private String input = "";
    private String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);
        resultTextView = findViewById(R.id.resultTextView);

        // Set click listeners for buttons
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.addButton).setOnClickListener(this);
        findViewById(R.id.subtractButton).setOnClickListener(this);
        findViewById(R.id.multiplyButton).setOnClickListener(this);
        findViewById(R.id.divideButton).setOnClickListener(this);
        findViewById(R.id.decimalButton).setOnClickListener(this);
        findViewById(R.id.equalsButton).setOnClickListener(this);
        findViewById(R.id.clearButton).setOnClickListener(this);
        findViewById(R.id.deleteButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Button button = (Button) view;

        if (viewId == R.id.clearButton) {
            input = "";
            result = "";
        } else if (viewId == R.id.deleteButton) {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
            }
        } else if (viewId == R.id.addButton || viewId == R.id.subtractButton ||
                viewId == R.id.multiplyButton || viewId == R.id.divideButton) {
            input += button.getText().toString();
        } else if (viewId == R.id.equalsButton) {
            try {
                double resultValue = evaluateExpression(input);
                result = String.valueOf(resultValue);
            } catch (Exception e) {
                result = "Syntax Error";
            }
        } else {
            String buttonText = button.getText().toString();
            input += buttonText;
        }

        inputTextView.setText(input);
        resultTextView.setText(result);
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("÷", "/").replaceAll("×", "*");
        return new net.objecthunter.exp4j.ExpressionBuilder(expression).build().evaluate();
    }
}
