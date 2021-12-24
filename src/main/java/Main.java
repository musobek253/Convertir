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
    static List<String> currenciesList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        currenciesList.add("USD");
        currenciesList.add("EUR");
        currenciesList.add("CNY");
        System.out.println("Choose currency one of them => ");
        for (int i = 0; i < currenciesList.size(); i++) {
            System.out.println( (i+1) + " -> "+  currenciesList.get(i));

        }
        int currenc = scanner.nextInt();


        LocalDate date = LocalDate.now();
        URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/" + currenciesList.get(currenc -1) + "/" + date + " ?=");
        URLConnection connection = url.openConnection();
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ArrayList<Currency> currencies = gson.fromJson(reader,new TypeToken<ArrayList<Currency>>(){}.getType());
            boolean run = true;
            while (run){
                System.out.println(" 1=> " + currenciesList.get(currenc-1) + " ning so'mga nisbati ");
                System.out.println(" 2=> So'mni " + currenciesList.get(currenc-1) + " ga nisbati ");
                System.out.println(" 0=> tizimdan chiqish");
                scanner = new Scanner(System.in);
                int choose = scanner.nextInt();
                switch (choose){
                    case 1:
                        System.out.println(currenciesList.get(currenc-1) +  " miqdorini kiriting");
                        int money = scanner.nextInt();
                        for (Currency currency : currencies) {
                            if ((currency.getCcy().equalsIgnoreCase(currenciesList.get(currenc-1)))){
                                double sum = Double.parseDouble(currency.getRate());
                                System.out.println(money*sum + " So'm " + money + " ta " +  currenciesList.get(currenc-1));
                            }
                        }
                        break;
                    case 2:
                        System.out.println("So'm  miqdorini kiriting");
                        int money2 = scanner.nextInt();
                        for (Currency currency : currencies) {
                            if ((currency.getCcy().equalsIgnoreCase(currenciesList.get(currenc-1)))){
                                double sum2 = Double.parseDouble(currency.getRate());
                                System.out.println(money2/sum2 +  " " + currenciesList.get(currenc-1));
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
