import java.util.HashMap;
import java.util.Map;

public class StockPrediction 
{

    public enum Stock 
    {
        SAMSUNG,
        GOOGLE,
        APPLE,
        NVIDIA,
        TESLA
    }

    public static double predictStockLevel(Stock stock, int daysAhead) 
    {
        double currentPrice = getCurrentStockPrice(stock);
        double predictedPrice = currentPrice + (daysAhead * getTrendFactor(stock));
        return predictedPrice;
    }

    private static double getCurrentStockPrice(Stock stock)
     {
        Map<Stock, Double> currentPrices = new HashMap<>();
        currentPrices.put(Stock.SAMSUNG, 100.0);
        currentPrices.put(Stock.GOOGLE, 1500.0);
        currentPrices.put(Stock.APPLE, 200.0);
        currentPrices.put(Stock.NVIDIA, 500.0);
        currentPrices.put(Stock.TESLA, 800.0);
        return currentPrices.get(stock);
    }

    private static double getTrendFactor(Stock stock)
     {
        Map<Stock, Double> trendFactors = new HashMap<>();
        trendFactors.put(Stock.SAMSUNG, 1.5);
        trendFactors.put(Stock.GOOGLE, 10.0);
        trendFactors.put(Stock.APPLE, 2.0);
        trendFactors.put(Stock.NVIDIA, 5.0);
        trendFactors.put(Stock.TESLA, 8.0);
        return trendFactors.get(stock);
    }

    public static void main(String[] args) 
    {
        int daysAhead = 30;
        for (Stock stock : Stock.values()) 
        {
            double predictedPrice = predictStockLevel(stock, daysAhead);
            System.out.println("Predicted price of " + stock + " in " + daysAhead + " days: " + predictedPrice);
        }
    }
}