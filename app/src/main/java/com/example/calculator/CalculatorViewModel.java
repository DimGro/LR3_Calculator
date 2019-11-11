package com.example.calculator;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalculatorViewModel extends ViewModel {
    private MutableLiveData<String> liveDataExpression;
    public CalculatorViewModel() {
        liveDataExpression = new MutableLiveData<>("");
    }
    public void changeExpression(String symbol) {
        liveDataExpression.setValue(liveDataExpression.getValue() + symbol);
    }
    public void backspaceExpression() {
        final int strLength =  liveDataExpression.getValue().length();
        if (strLength > 0) {
            liveDataExpression.setValue(liveDataExpression.getValue().substring(0, strLength - 1));
        }
    }
    public void clearExpression() {
        liveDataExpression.setValue("");
    }
    public MutableLiveData<String> getExpression() {
        return liveDataExpression;
    }
}
