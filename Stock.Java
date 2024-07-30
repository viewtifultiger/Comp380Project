public class StockPrediction
{

    static String[] stocks = { "Samsung", "Google", "Apple", "Nvidia", "Tesla" };
    static double[] currentPrices = { 100.0, 1500.0, 200.0, 500.0, 800.0 };
    static double[] trendFactors = { 1.5, 10.0, 2.0, 5.0, 8.0 };

    public static String predictStockChange(int stockIndex, int daysAhead)
    {
        double currentPrice = currentPrices[stockIndex];
        double trendFactor = trendFactors[stockIndex];
        double futurePrice = currentPrice + (daysAhead * trendFactor);

        if (futurePrice > currentPrice)
        {
            return "Increase";
        }
        else
        {
            return "Decrease";
        }
    }

    public static void updateStockData(int stockIndex, double newPrice, double newTrendFactor)
    {
        currentPrices[stockIndex] = newPrice;
        trendFactors[stockIndex] = newTrendFactor;
    }

    public static void main(String[] args)
    {
        int daysAhead = 30;
        for (int i = 0; i < stocks.length; i++)
        {
            String change = predictStockChange(i, daysAhead);
            System.out.println("Stock " + stocks[i] + " is expected to " + change + " in " + daysAhead + " days.");
        }
    }
}