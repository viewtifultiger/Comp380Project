import sys
import pickleTools as pt

objDir = pt.dir.objDir
ticker = sys.argv[1]

stock = pt.load_object(f"{objDir}{ticker}_info")

print(stock["shortName"])