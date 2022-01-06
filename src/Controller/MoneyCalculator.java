package Controller;

import Model.Currency;
import Persistence.Archive.CurrencyLoaderArchive;
import Persistence.Archive.ExchangeRateArchive;
import Persistence.CurrencyLoader;
import Persistence.ExchangeRateLoader;
import Persistence.WebService.ExchangeRateWebService;
import java.util.List;
import ui.Dialog;
import ui.DialogSwing;
import ui.Display;
import ui.DisplaySwing;

public class MoneyCalculator {
    

    public static void main(String[] args) {
        CurrencyLoader loader = new CurrencyLoaderArchive("currencies.txt");
        ExchangeRateLoader ex = new ExchangeRateArchive("ExchangeRates.txt");
        //ExchangeRateLoader ex = new ExchangeRateWebService();
        List<Currency> currencies = loader.loadAllCurrencies();
        
        MainFrame mainframe = new MainFrame(currencies, ex);
        
        Dialog dialog = new DialogSwing(currencies, mainframe);
        Display display = new DisplaySwing();
        mainframe.addDialog(dialog);
        mainframe.addDisplay(display);
        mainframe.init();
        
        
        
        
    }
    
}
