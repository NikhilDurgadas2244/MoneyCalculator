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
        //PARA HACER UNA PRUEBA DE FUNCIONAMIENTO DE LOS EXCHANGE RATES CREADOS DESCOMENTAR LAS DOS LÍNEAS DE CODIGO A CONTINUACIÓN 
        //Y COMENTAR LAS OTRAS DOS QUE LE SIGUEN
        
        //CurrencyLoader loader = new CurrencyLoaderArchive("currencies_prueba_local.txt");
        //ExchangeRateLoader ex = new ExchangeRateArchive("ExchangeRates.txt");

        CurrencyLoader loader = new CurrencyLoaderArchive("currencies.txt");
        ExchangeRateLoader ex = new ExchangeRateWebService();

        List<Currency> currencies = loader.loadAllCurrencies();
        
        MainFrame mainframe = new MainFrame(currencies, ex);
        
        Dialog dialog = new DialogSwing(currencies, mainframe);
        Display display = new DisplaySwing();
        mainframe.addDialog(dialog);
        mainframe.addDisplay(display);
        mainframe.init();
        
        
        
        
    }
    
}
