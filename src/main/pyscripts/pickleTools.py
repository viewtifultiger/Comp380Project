import pickle

class dir:
	objDir = "./../pyscripts/objects/"
	pyDir = "./../pyscripts/"
	csvDir = "./../csv/"

def save_object(obj, filename):
	with open(filename, 'wb') as outp:	#Overwrites any existing file
		pickle.dump(obj, outp, pickle.HIGHEST_PROTOCOL)

def load_object(filename):
	with open(filename, 'rb') as file:	# Load pickle object
		return pickle.load(file)



# sample usage
# save_object(sp500, 'sp500.pkl')