import os
import sys
import yfinance as yf
import pickleTools as pt

objDir = pt.dir.objDir
csvDir = pt.dir.csvDir
ticker = sys.argv[1]

# Ensure the CSV directory exists
os.makedirs(csvDir, exist_ok=True)

stock = yf.Ticker(f"{ticker}") # Initialize ticker class, S&P 500 ticker symbol
stockTable = stock.history(period="max")

if stockTable.empty:
    raise Exception(f"{ticker} does not exist")

pt.save_object(stock.info, f"{objDir}{ticker}_info")  # yfinance class
pt.save_object(stockTable, f"{objDir}{ticker}_panda")  # pandas class
stockTable.to_csv(f"{csvDir}{ticker}_CSV", index=True)