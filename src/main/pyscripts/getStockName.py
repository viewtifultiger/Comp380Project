import pandas as pd
import pickle
import sys

stockName = sys.argv[1]
mainDir = "./../pyscripts/"

print("reading pickle")
stock = pd.read_pickle(f"{mainDir}objects/{stockName}_object.pkl")

print(stock.values)