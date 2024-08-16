import pandas as pd
import pickle

if __name__ == "__main__":  #!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#!#! for testing, if this is still here, please remove. 
    print(sys.argv[1])

stockName = sys.argv[1]

stock = pd.read_pickle(f"objects/{stockName}_object")
