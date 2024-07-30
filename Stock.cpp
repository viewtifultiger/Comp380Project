#include <iostream>
#include <string>

std::string stocks[] = { "Samsung", "Google", "Apple", "Nvidia", "Tesla" };
double currentPrices[] = { 100.0, 1500.0, 200.0, 500.0, 800.0 };
double trendFactors[] = { 1.5, 10.0, 2.0, 5.0, 8.0 };
const int numStocks = sizeof(stocks) / sizeof(stocks[0]);

std::string predictStockChange(int stockIndex, int daysAhead) 
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

void updateStockData(int stockIndex, double newPrice, double newTrendFactor)
{
    currentPrices[stockIndex] = newPrice;
    trendFactors[stockIndex] = newTrendFactor;
}

int main()
{
    int daysAhead = 30;
    for (int i = 0; i < numStocks; i++) 
    {
        std::string change = predictStockChange(i, daysAhead);
        std::cout << "Stock " << stocks[i] << " is expected to " << change << " in " << daysAhead << " days." << std::endl;
    }

    return 0;
}