package cmd;
import consolehandler.TableController;
import productdata.Product;
import productdata.ReaderProductBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * replace new product with old one if price of new product is higher
 *
 *
 */

public class Commandreplace_if_greater implements Command{

    private static final long serialVersionUID = 1337000014L;

    @Override
    public String execute(String[] args) {
        int c = 0;
        for(String key : TableController.getCurrentTable().getKey()){
            if(key.equals(args[0])){
                c++;
            }
        }
        if(c==0){
            return ("No such key\nAvailable keys: " + TableController.getCurrentTable().getKey());
        }else{
            for (Map.Entry<String, Product> map : TableController.getCurrentTable().getSet()) {
                if (map.getKey().compareTo(args[0]) == 0) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    Product product = ReaderProductBuilder.buildProduct(reader);
                    if (product != null && product.getPrice() > map.getValue().getPrice()) {
                        TableController.getCurrentTable().replace(map.getKey(), product);
                    }
                }
            }
            return ("Element has been replaced");
        }
    }

    /**
     * get name of command
     *
     * @return String
     */

    @Override
    public String toString() {
        return "replace_if_greater";
    }
}
