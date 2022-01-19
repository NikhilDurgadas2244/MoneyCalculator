package ui;

import Controller.MainFrame;
import Model.Currency;
import Model.Money;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogSwing extends JPanel implements Dialog{
    private final List<Currency> currencies;
    private double amount = 0;
    private Currency currencyFrom;
    private Currency currencyTo;
    private JTextField insert;
    private MainFrame mainframe;
    

    public DialogSwing(List<Currency> currencies, MainFrame mainframe) {
        this.mainframe = mainframe;
        this.currencies = currencies;
        this.add(insertAmount());
        this.add(getComboCurrencyFrom());
        this.add(getComboCurrencyTo());
    }
    
    @Override
    public Money getMoney(){
        return new Money(amount, currencyFrom);
    }
    
    private JTextField insertAmount(){
        JTextField field = new JTextField("");
        field.setColumns(5);
        this.insert = field;
        this.insert.addKeyListener(getFinalAmount());
        
        return this.insert;
    }
    
    @Override
    public Currency getCurrencyTo() {
        return currencyTo;
    }
    
    @Override
    public Currency getCurrencyFrom() {
        return currencyFrom;
    }
    
    private JComboBox getComboCurrencyFrom(){
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencies) {
            comboBox.addItem(currency);
        }
        comboBox.addItemListener(getSelectedCurrencyFrom());
        currencyFrom = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }
    
    
    private JComboBox getComboCurrencyTo(){
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencies) {
            comboBox.addItem(currency);
        }
        comboBox.addItemListener(getSelectedCurrencyTo());
        currencyTo = (Currency) comboBox.getSelectedItem();
        return comboBox;
    }

    
    private ItemListener getSelectedCurrencyFrom() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if(item.getStateChange() == ItemEvent.DESELECTED){}
                currencyFrom = (Currency) item.getItem();
                mainframe.refresh();
            }
        };
    }
    private ItemListener getSelectedCurrencyTo() {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent item) {
                if(item.getStateChange() == ItemEvent.DESELECTED){}
                currencyTo = (Currency) item.getItem();
                mainframe.refresh();
            }
        };
    }
    
    
    private KeyListener getFinalAmount(){
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
                String text = insert.getText();
                if (text.equals("")){text = "0";}
                try{
                    amount = Double.parseDouble(text);
                    if(amount >= 0.){
                        mainframe.refresh();
                    }
                }catch(NumberFormatException e){}
                
            }
        };
    }
    
    

}
