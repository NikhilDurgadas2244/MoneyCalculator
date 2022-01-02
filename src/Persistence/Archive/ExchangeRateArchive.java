package Persistence.Archive;

import Model.Currency;
import Model.ExchangeRate;
import Persistence.ExchangeRateLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExchangeRateArchive implements ExchangeRateLoader {
    private final String filePathName;

    public ExchangeRateArchive(String FilePathName) {
        this.filePathName = FilePathName;
    }
    
    @Override
    public ExchangeRate load(Currency from, Currency to) {
        double value = 0;
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filePathName)));
            while(true){
                String lineText = reader.readLine();
                if(lineText == null)break;
                String[] splitLine = lineText.split(",");
                if((splitLine[0].equals(from.getCode())) && (splitLine[1].equals(to.getCode()))){
                    value = Double.parseDouble(splitLine[2]);
                    return new ExchangeRate(value,from,to);
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("ERROR CurrencyLoaderArchive; loadAllCurrencies FIleNotFoundException, " + e);
            
        } catch (IOException ex) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies IOException, " + ex);
        }
        return null;
    }
    
}
