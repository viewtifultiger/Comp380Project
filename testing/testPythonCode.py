import papermill as pm
import scrapbook as sb

notebookDir = "./../notebooks/Untitled2.ipynb"
outputDir = "./../notebooks/output/output.ipynb"

pm.execute_notebook(notebookDir, outputDir)

nb = sb.read_notebook(outputDir)
scraps = nb.scraps

nb.reglue('sharable_plot')
scraps['sharable_plot'].display['data']