package Persistence.WebService;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.ExchangeRateLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateWebService implements ExchangeRateLoader{

    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        double value = 0;
        String codefrom = from.getCode();
        String codeto = to.getCode();
        
        try {
            URL url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + codefrom.toLowerCase() + "/" + codeto.toLowerCase() + ".json");
            URLConnection connection = url.openConnection();
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                int i = 0;
                while( (line = reader.readLine()) != null){
                    i++;
                    if(i == 3)break;
                }
                line = line.substring(line.indexOf(":") + 2, line.length());
                value = Double.parseDouble(line);
                return new ExchangeRate(value,from,to);
                
                
            } catch (IOException ex) {
                Logger.getLogger(ExchangeRateWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (IOException ex) {
            Logger.getLogger(ExchangeRateWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
