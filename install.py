import subprocess
import sys

# List of required packages
required_packages = ["yfinance", "matplotlib", "scikit-learn"] # list of all python packages required...
# to run install.py, enter "python install.py" into the command line. Python needs to be installed prior.

def install_and_import(packages):
	try:
		__import__(package)	# check if package is installed
	except ImportError:
		print(f"{package} is not installed. Installing now...")
		subprocess.check_call([sys.executable, "-m", "pip", "install", package]) # e.g. of check_call: python3 -m pip install <package_name>
		print(f"{package} installed successfully.")
	finally:
		globals()[package] = __import__(package)

for package in required_packages:
	install_and_import(package)