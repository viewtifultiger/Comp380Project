import sys
import pickleTools as pt

mainDir = "./../pyscripts/objects/"
ticker = sys.argv[1]

table = pt.load_object(f"{mainDir}{ticker}_table")

print(table)