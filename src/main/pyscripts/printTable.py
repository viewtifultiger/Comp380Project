import sys
import pickleTools as pt

objDir = pt.dir.objDir
ticker = sys.argv[1]

table = pt.load_object(f"{pt.dir.objDir}{ticker}_panda")

print(table)