package Controller;

import Model.Currency;
import Model.Money;
import Persistence.ExchangeRateLoader;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ui.Dialog;
import ui.DialogSwing;
import ui.Display;
import ui.DisplaySwing;

public class MainFrame extends JFrame {

    private Dialog dialog;
    private Display display;
    private final List<Currency> currencies;
    private final ExchangeRateLoader loader;
    
    public MainFrame(List<Currency> currencies, ExchangeRateLoader loader) {
        this.loader = loader;
        this.currencies = currencies;
        this.setTitle("MONEY CALCULATOR");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        JLabel title = new JLabel("                    MONEY CALCULATOR                    ");
        title.setFont(new Font("Times New Roman", Font.BOLD, 14));
        title.setOpaque(true);
        title.setBackground(Color.black);
        title.setForeground(Color.yellow);
        
        JLabel from = new JLabel("From: ");
        JLabel to = new JLabel("To: ");
        
        this.add(title, new FlowLayout(FlowLayout.TRAILING));

        
        this.setLocationRelativeTo(null);
        this.setSize(300,150);
        
        
    }

    public void addDialog(Dialog dialogSwing) {
        this.add((DialogSwing) dialogSwing, new FlowLayout(FlowLayout.CENTER));
        this.dialog = dialogSwing;
    }
    
    public void addDisplay(Display displaySwing) {
        this.add((DisplaySwing) displaySwing,FlowLayout.RIGHT);
        this.display = displaySwing;
    }
    
    public void init(){
        this.setVisible(true);
    }
    
    
    
    private double rateLoader(Currency from, Currency to){
        return loader.load(from, to).getRate();
    }
    
    private Money calculateExchangeRate(Money money, Currency to){
        double rate = rateLoader(money.getCurrency(), to);
        return new Money(money.getAmount() * rate, to);
    }
    
    public void refresh(){
        Money money = (calculateExchangeRate(dialog.getMoney(), dialog.getCurrencyTo()));
        display.display(money);
    }
    
}



