import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Currency;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String APIKEY = "f896915e4f2e4765f254632549b41209";
    static List<String> currenciesList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        currenciesList.add("USD");
        currenciesList.add("EUR");
        currenciesList.add("CNY");
        System.out.println("Choose currency one of them => ");
        for (int i = 0; i < currenciesList.size(); i++) {
            System.out.println((i+1)+ currenciesList.get(i));
        }
        String currenc = scanner.nextLine();
        LocalDate date = LocalDate.now();
        URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/" + currenc + "/" + date + " ?=");
        URLConnection connection = url.openConnection();
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ArrayList<Currency> currencies = gson.fromJson(reader,new TypeToken<ArrayList<Currency>>(){}.getType());
            boolean run = true;
            while (run){
                System.out.println(" 1=> " + currenc + " ning so'mga nisbati ");
                System.out.println(" 2=> So'mni " + currenc + " ga nisbati ");
                System.out.println(" 0=> tizimdan chiqish");
                scanner = new Scanner(System.in);
                int choose = scanner.nextInt();
                switch (choose){
                    case 1:
                        System.out.println(currenc + " miqdorini kiriting");
                        int money = scanner.nextInt();
                        for (Currency currency : currencies) {
                            if ((currency.getCcy().equalsIgnoreCase(currenc)|| currency.getCcy().equalsIgnoreCase(currenc)|| currency.getCcy().equalsIgnoreCase(currenc))){
                                double sum = Double.parseDouble(currency.getRate());
                                System.out.println(money*sum + " So'm ");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("So'm  miqdorini kiriting");
                        int money2 = scanner.nextInt();
                        for (Currency currency : currencies) {
                            if ((currency.getCcy().equalsIgnoreCase(currenc)||currency.getCcy().equalsIgnoreCase(currenc)||currency.getCcy().equalsIgnoreCase(currenc))){
                                double sum2 = Double.parseDouble(currency.getRate());
                                System.out.println(money2/sum2 + currenc);
                            }
                        }
                        break;
                    case 0:
                        run=false;
                        System.out.println("Thanks");
                        break;
                    default:
                        System.out.println("Wrong action brother");
                }
            }
        }




}
