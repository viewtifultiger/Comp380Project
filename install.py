import subprocess
import sys

# List of required packages
required_packages = ["yfinance", "matplotlib", "scikit-learn"]

def install_and_import(packages):
	try:
		__import__(package)
	except ImportError:
		print(f"{package} is not installed. Installing now...")
		subprocess.check_call([sys.executable, "-m", "pip", "install", package])
		print(f"{package} installed successfully.")
	finally:
		globals()[package] = __import__(package)

for package in required_packages:
	install_and_import(package)