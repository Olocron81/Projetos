import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    private static double usdToBrl, usdToArs, usdToBob, usdToClp, usdToCop, usdToUsd;

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Accept", "application/json")
                .GET()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Status code: " + response.statusCode());
                    System.out.println("Headers: " + response.headers().map());
                    return response.body();
                })
                .thenApply(Main::parse)
                .join();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção de conversão:");
            System.out.println("1. USD para BRL");
            System.out.println("2. BRL para USD");
            System.out.println("3. USD para ARS");
            System.out.println("4. ARS para USD");
            System.out.println("5. USD para BOB");
            System.out.println("6. BOB para USD");
            System.out.println("7. USD para CLP");
            System.out.println("8. CLP para USD");
            System.out.println("9. USD para COP");
            System.out.println("10. COP para USD");
            System.out.println("11. Sair");

            int escolha = scanner.nextInt();
            if (escolha == 11) {
                break;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor = scanner.nextDouble();

            double resultado = 0;
            switch (escolha) {
                case 1:
                    resultado = convertUsdToBrl(valor);
                    System.out.println(valor + " USD = " + resultado + " BRL");
                    break;
                case 2:
                    resultado = convertBrlToUsd(valor);
                    System.out.println(valor + " BRL = " + resultado + " USD");
                    break;
                case 3:
                    resultado = convertUsdToArs(valor);
                    System.out.println(valor + " USD = " + resultado + " ARS");
                    break;
                case 4:
                    resultado = convertArsToUsd(valor);
                    System.out.println(valor + " ARS = " + resultado + " USD");
                    break;
                case 5:
                    resultado = convertUsdToBob(valor);
                    System.out.println(valor + " USD = " + resultado + " BOB");
                    break;
                case 6:
                    resultado = convertBobToUsd(valor);
                    System.out.println(valor + " BOB = " + resultado + " USD");
                    break;
                case 7:
                    resultado = convertUsdToClp(valor);
                    System.out.println(valor + " USD = " + resultado + " CLP");
                    break;
                case 8:
                    resultado = convertClpToUsd(valor);
                    System.out.println(valor + " CLP = " + resultado + " USD");
                    break;
                case 9:
                    resultado = convertUsdToCop(valor);
                    System.out.println(valor + " USD = " + resultado + " COP");
                    break;
                case 10:
                    resultado = convertCopToUsd(valor);
                    System.out.println(valor + " COP = " + resultado + " USD");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }

    public static String parse(String responseBody) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

        JsonObject rates = jsonObject.getAsJsonObject("rates");

        usdToBrl = rates.get("BRL").getAsDouble();
        usdToArs = rates.get("ARS").getAsDouble();
        usdToBob = rates.get("BOB").getAsDouble();
        usdToClp = rates.get("CLP").getAsDouble();
        usdToCop = rates.get("COP").getAsDouble();
        usdToUsd = rates.get("USD").getAsDouble();

        return null;
    }

    public static double convertUsdToBrl(double amount) {
        return amount * usdToBrl;
    }

    public static double convertBrlToUsd(double amount) {
        return amount / usdToBrl;
    }

    public static double convertUsdToArs(double amount) {
        return amount * usdToArs;
    }

    public static double convertArsToUsd(double amount) {
        return amount / usdToArs;
    }

    public static double convertUsdToBob(double amount) {
        return amount * usdToBob;
    }

    public static double convertBobToUsd(double amount) {
        return amount / usdToBob;
    }

    public static double convertUsdToClp(double amount) {
        return amount * usdToClp;
    }

    public static double convertClpToUsd(double amount) {
        return amount / usdToClp;
    }

    public static double convertUsdToCop(double amount) {
        return amount * usdToCop;
    }

    public static double convertCopToUsd(double amount) {
        return amount / usdToCop;
    }
}