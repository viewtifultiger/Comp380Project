import sys
import yfinance as yf
import pickleTools as pt

mainDir = "./../pyscripts/objects/"
ticker = sys.argv[1]

stock = yf.Ticker(f"^{ticker}") # initialize ticker class, S&P 500 ticker symbol
stockTable = stock.history(period = "max")

pt.save_object(stock.info, f"{mainDir}{ticker}_info")	# yfinance class
pt.save_object(stockTable, f"{mainDir}{ticker}_table")	# pandas class