import papermill as pm
import scrapbook

notebookDir = "./../notebooks/"

pm.execute_notebook(notebookDir + 'Untitled2.ipynb', './../notebooks/output/output.ipynb')

pm.inspect_notebook(notebookDir + 'output/output.ipynb')