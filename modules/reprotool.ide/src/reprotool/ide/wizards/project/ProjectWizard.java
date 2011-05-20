package reprotool.ide.wizards.project;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import reprotool.ide.service.Service;

public class ProjectWizard extends Wizard implements INewWizard {

	private static final String PROJECT_FILE_NAME = "project.ucproj";
	private static final String WIZARD_PAGE_TITLE = "New Reprotool Project";
	private static final String WIZARD_PAGE_DESCRIPTION = "Create a new Reprotool Project.";
	private static final String WIZARD_PAGE_NAME = "NewReprotoolProject";

	private WizardNewProjectCreationPage wizardPage;
	private IWorkbench workbench;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		setWindowTitle("New Reprotool project");
	}

	@Override
	public boolean performFinish() {
		try {
			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor progressMonitor) {
					try {
						// Create project
						//
						IProject projectHandle = wizardPage.getProjectHandle();
						java.net.URI projectURI = wizardPage.useDefaults() ? null
								: wizardPage.getLocationURI();

						IWorkspace workspace = ResourcesPlugin.getWorkspace();

						// TODO - jvinarek - add project description, not only
						// project name
						IProjectDescription desc = workspace
								.newProjectDescription(projectHandle.getName());
						desc.setLocationURI(projectURI);

						projectHandle.create(desc, progressMonitor);

						// Create a resource set
						//
						ResourceSet resourceSet = new ResourceSetImpl();

						// Get the URI of the model file.
						//
						URI fileURI = URI.createFileURI(projectHandle
								.getLocation().append(PROJECT_FILE_NAME)
								.toString());

						// Create a resource for this file.
						//
						Resource resource = resourceSet.createResource(fileURI);

						// Add the initial model object to the contents.
						//
						EObject rootObject = createInitialModel();
						if (rootObject != null) {
							resource.getContents().add(rootObject);
						}

						// Save the contents of the resource to the file system.
						//
						Map<Object, Object> options = new HashMap<Object, Object>();
						resource.save(options);

						// Open project
						//
						projectHandle.open(progressMonitor);
					} catch (Exception exception) {
						// TODO - jvinarek - log
						// ReprotoolEditorPlugin.INSTANCE.log(exception);
						throw new RuntimeException(exception);
					} finally {
						progressMonitor.done();
					}
				}
			};

			getContainer().run(false, false, operation);

			IFile modelFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(getModelFilePath());

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench
					.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(
						modelFile);
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						((ISetSelectionTarget) activePart)
								.selectReveal(targetSelection);
					}
				});
			}

			// Open an editor on the new file.
			//
			try {
				page.openEditor(
						new FileEditorInput(modelFile),
						workbench
								.getEditorRegistry()
								.getDefaultEditor(
										modelFile.getFullPath().toString())
								.getId());
			} catch (PartInitException exception) {
				// TODO - jvinarek - messagebox
				// MessageDialog.openError(workbenchWindow.getShell(),
				// ReprotoolModelEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"),
				// exception.getMessage());
				exception.printStackTrace();
				return false;
			}

			return true;
		} catch (Exception exception) {
			// TODO - jvinarek - log
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public void addPages() {
		wizardPage = new WizardNewProjectCreationPage(WIZARD_PAGE_NAME);
		wizardPage.setDescription(WIZARD_PAGE_DESCRIPTION);
		wizardPage.setTitle(WIZARD_PAGE_TITLE);
		addPage(wizardPage);
	}

	private EObject createInitialModel() {
		return Service.INSTANCE.createSoftwareProject();
	}

	private IPath getModelFilePath() {
		IProject projectHandle = wizardPage.getProjectHandle();
		return projectHandle.getProjectRelativePath()
				.append(projectHandle.getName()).append(PROJECT_FILE_NAME);
	}

}