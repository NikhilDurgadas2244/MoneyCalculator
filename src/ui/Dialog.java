package ui;

import Model.Currency;
import Model.Money;
import javax.swing.JComboBox;

public interface Dialog {
    public Money getMoney();
    public Currency getCurrencyTo();
    public Currency getCurrencyFrom();
}
