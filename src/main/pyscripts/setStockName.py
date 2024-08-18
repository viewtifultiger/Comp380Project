import sys
import pickleTools as pt

mainDir = "./../pyscripts/objects/"
ticker = sys.argv[1]

stock = pt.load_object(f"{mainDir}{ticker}_info")

print(stock["shortName"])