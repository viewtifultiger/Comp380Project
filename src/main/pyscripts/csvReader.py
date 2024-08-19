import pandas as pd
import pickleTools as pt
import sys

csvDir = pt.dir.csvDir
ticker = sys.argv[1]

df = pd.read_csv(f"{csvDir}{ticker}_CSV")

pd.set_option('display.max_columns', None)
df = df.tail(10)

print(df)