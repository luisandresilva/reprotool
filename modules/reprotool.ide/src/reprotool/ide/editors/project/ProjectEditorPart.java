package reprotool.ide.editors.project;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.TextActionHandler;
import org.eclipse.ui.part.EditorPart;

import reprotool.ide.editors.project.action.AbstractAddAction;
import reprotool.ide.editors.project.action.AddActorAction;
import reprotool.ide.editors.project.action.AddConceptualObjectAction;
import reprotool.ide.editors.project.action.EditDescribedElementAction;
import reprotool.ide.utils.SelectionProviderIntermediate;
import reprotool.ide.utils.Utils;
import reprotool.model.swproj.SoftwareProject;
import reprotool.model.swproj.SwprojPackage;
import reprotool.model.usecase.UseCase;
import reprotool.model.usecase.UsecaseFactory;
import reprotool.model.usecase.presentation.ReprotoolEditorPlugin;

public class ProjectEditorPart extends EditorPart implements IMenuListener, IEditingDomainProvider {

	private ProjectEditorComposite composite;
	protected ProjectEditor parentEditor;
	private boolean useCaseDoubleClickListener = false;
	private boolean actorsDoubleClickListener = false;
	private boolean conceptualDoubleClickListener = false;

	// used by binding framework
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;

	public ProjectEditorPart(ProjectEditor parent) {
		super();
		this.parentEditor = parent;
	}

	protected static String getString(String key) {
		return ReprotoolEditorPlugin.INSTANCE.getString(key);
	}

	public EditingDomain getEditingDomain() {		
		return parentEditor.getEditingDomain();
	}

	protected BasicCommandStack getCommandStack() {
		return ((BasicCommandStack) getEditingDomain().getCommandStack());
	}

	protected AdapterFactory getAdapterFactory() {
		return parentEditor.getAdapterFactory();
	}

	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(getEditingDomain(), viewer));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// nothing to do here - this is handled by the parent editor
	}

	@Override
	public void doSaveAs() {
		// nothing to do here - this is handled by the parent editor
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);

	}

	@Override
	public boolean isDirty() {
		return getCommandStack().isSaveNeeded();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	public void menuAboutToShow(IMenuManager manager) {
		// pass the request to show the context menu on to the parent editor
		((IMenuListener) parentEditor.getEditorSite().getActionBarContributor()).menuAboutToShow(manager);
	}
	
	public void modelChanged() {
		SoftwareProject softwareProject = getSoftwareProject();
		m_bindingContext = initDataBindings(softwareProject);
		
		// add actions to the section toolbars
		addActorsActions(softwareProject);
		addConceptualObjectsActions(softwareProject);
		addUseCasesActions(softwareProject);
	}

	@Override
	public void createPartControl(final Composite parent) {
		composite = new ProjectEditorComposite(parent, SWT.NONE);
		
		addSelectionListeners();
		
		// get project and use it in bindings
		SoftwareProject softwareProject = getSoftwareProject();
		m_bindingContext = initDataBindings(softwareProject);
		
		// add actions to the section toolbars
		addActorsActions(softwareProject);
		addConceptualObjectsActions(softwareProject);
		addUseCasesActions(softwareProject);
		
		// add context menus + drag & drop
		addContextMenus();
		
		addCopyPasteSupport();
	}
	
	private void addCopyPasteSupport() {
		IActionBars actionBars = getEditorSite().getActionBars();
		TextActionHandler textActionHandler = new TextActionHandler(actionBars);
		 
		// hook in the text control
		textActionHandler.addText(composite.getNameDescriptionComposite().getTxtName());
		textActionHandler.addText(composite.getNameDescriptionComposite().getTxtDescription());
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
	}

	private void addSelectionListeners() {
		SelectionProviderIntermediate selectionProviderIntermediate = new SelectionProviderIntermediate();
		
		TableViewer actorsViewer = composite.getActorsComposite().getTableViewer();
		TableViewer conceptualObjectsViewer = composite.getConceptualObjectsComposite().getTableViewer();
		TableViewer useCasesViewer = composite.getUseCasesComposite().getTableViewer();
		
		Utils.addSelectionFocusListener(selectionProviderIntermediate, actorsViewer.getTable(), actorsViewer);
		Utils.addSelectionFocusListener(selectionProviderIntermediate, conceptualObjectsViewer.getTable(), conceptualObjectsViewer);
		Utils.addSelectionFocusListener(selectionProviderIntermediate, useCasesViewer.getTable(), useCasesViewer);
		
		// set default
		selectionProviderIntermediate.setSelectionProviderDelegate(useCasesViewer);
		getEditorSite().setSelectionProvider(selectionProviderIntermediate);
	}

	private void addContextMenus() {
		createContextMenuFor(composite.getActorsComposite().getTableViewer());
		createContextMenuFor(composite.getConceptualObjectsComposite().getTableViewer());
		createContextMenuFor(composite.getUseCasesComposite().getTableViewer());
	}

	private void addActorsActions(SoftwareProject softwareProject) {
		ToolBarManager toolBarManager = composite.getActorsComposite().getToolBarManager();
		toolBarManager.removeAll();
		
		Action createAction = new AddActorAction(getEditingDomain(), softwareProject);
		toolBarManager.add(createAction);
		
		final EditDescribedElementAction editAction = new EditDescribedElementAction(getEditingDomain(), "Edit actor");
		getEditorSite().getSelectionProvider().addSelectionChangedListener(editAction);
		if (!actorsDoubleClickListener) {
			composite.getActorsComposite().getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
				
				@Override
				public void doubleClick(DoubleClickEvent event) {
					if (!event.getSelection().isEmpty()) {
						editAction.run();
					}				
				}
			});
			actorsDoubleClickListener = true;
		}
		
		toolBarManager.update(true);
	}
	
	private void addConceptualObjectsActions(SoftwareProject softwareProject) {
		ToolBarManager toolBarManager = composite.getConceptualObjectsComposite().getToolBarManager();
		toolBarManager.removeAll();
		
		Action createAction = new AddConceptualObjectAction(getEditingDomain(), softwareProject);
		toolBarManager.add(createAction);
		
		final EditDescribedElementAction editAction = new EditDescribedElementAction(getEditingDomain(), "Edit conceptual object");
		getEditorSite().getSelectionProvider().addSelectionChangedListener(editAction);
		if (!conceptualDoubleClickListener) {
			composite.getConceptualObjectsComposite().getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
				
				@Override
				public void doubleClick(DoubleClickEvent event) {
					if (!event.getSelection().isEmpty()) {
						editAction.run();
					}
				}
			});
			conceptualDoubleClickListener = true;
		}
		
		toolBarManager.update(true);
	}
	
	private void addUseCasesActions(final SoftwareProject softwareProject) {
		ToolBarManager toolBarManager = composite.getUseCasesComposite().getToolBarManager();
		toolBarManager.removeAll();
		
		Action createAction = new AbstractAddAction("Add use case") {
			@Override
			public void run() {
				// add use case
				UseCase useCase = UsecaseFactory.eINSTANCE.createUseCase();
				Command addCommand = new AddCommand(getEditingDomain(), softwareProject, SwprojPackage.Literals.SOFTWARE_PROJECT__USE_CASES, useCase);
				getEditingDomain().getCommandStack().execute(addCommand);
				UseCaseEditorInput useCaseEditorInput = new UseCaseEditorInput(EcoreUtil.getURI(useCase), getEditorInput(), getEditingDomain());
				openUseCaseEmfEditorPage(useCaseEditorInput);
			}
		};
		
		toolBarManager.add(createAction);
		
		if (!useCaseDoubleClickListener) {
			composite.getUseCasesComposite().getTableViewer().addDoubleClickListener(new IDoubleClickListener() {
				
				@Override
				public void doubleClick(DoubleClickEvent event) {
					ISelection selection = event.getSelection();
					if (!selection.isEmpty()) {
						IStructuredSelection structuredSelection = (IStructuredSelection)selection;
						UseCase useCase = (UseCase)structuredSelection.getFirstElement();
						
						UseCaseEditorInput useCaseEditorInput = new UseCaseEditorInput(EcoreUtil.getURI(useCase), getEditorInput(),
								getEditingDomain());
	
						openUseCaseEmfEditorPage(useCaseEditorInput);
					}
				}
				
			});
			useCaseDoubleClickListener = true;
		}
		
		toolBarManager.update(true);
	}

	private void openUseCaseEmfEditorPage(UseCaseEditorInput useCaseEditorInput) {
		IWorkbenchPage page = getSite().getPage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry()
				.findEditor("org.eclipselabs.reprotool.ide.UseCaseEmfEditor");
		
		try {
			page.openEditor(useCaseEditorInput, desc.getId());
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SoftwareProject getSoftwareProject() {
		// TODO jvinarek - add checks
		Resource resource = parentEditor.getEditingDomain().getResourceSet().getResources().get(0);
		SoftwareProject softwareProject = (SoftwareProject)resource.getContents().get(0);
		return softwareProject;
	}

	private DataBindingContext initDataBindings(SoftwareProject softwareProject) {
		DataBindingContext bindingContext = new DataBindingContext();

		bindName(bindingContext, softwareProject);
		bindDescription(bindingContext, softwareProject);
		bindTitle(bindingContext, softwareProject);
		bindActors(bindingContext, softwareProject);
		bindConceptualObjects(bindingContext, softwareProject);
		bindUseCases(bindingContext, softwareProject);
		
		return bindingContext;
	}

	private void bindName(DataBindingContext bindingContext, SoftwareProject project) {
		IObservableValue emfValue = EMFEditProperties.value(getEditingDomain(),
				SwprojPackage.Literals.DESCRIBED_ELEMENT__NAME).observe(project);
		IObservableValue textValue = WidgetProperties.text(SWT.Modify).observe(composite.getNameDescriptionComposite().getTxtName());
		bindingContext.bindValue(textValue, emfValue);
	}
	
	private void bindDescription(DataBindingContext bindingContext, SoftwareProject project) {
		IObservableValue emfValue = EMFEditProperties.value(getEditingDomain(),
				SwprojPackage.Literals.DESCRIBED_ELEMENT__DESCRIPTION).observe(project);
		IObservableValue textValue = WidgetProperties.text(SWT.Modify).observe(composite.getNameDescriptionComposite().getTxtDescription());
		bindingContext.bindValue(textValue, emfValue);
	}

	private void bindTitle(DataBindingContext bindingContext, SoftwareProject project) {
		IObservableValue emfValue = EMFEditProperties.value(getEditingDomain(),
				SwprojPackage.Literals.DESCRIBED_ELEMENT__NAME).observe(project);
		IObservableValue titleValue = PojoObservables.observeValue(composite.getForm(), "text");
		bindingContext.bindValue(titleValue, emfValue);
	}
	
	private void bindUseCases(DataBindingContext bindingContext, SoftwareProject softwareProject) {
		TableViewer tableViewer = composite.getUseCasesComposite().getTableViewer();
		
		IObservableList emfList = EMFEditProperties.list(getEditingDomain(), SwprojPackage.Literals.SOFTWARE_PROJECT__USE_CASES).observe(softwareProject);
		IValueProperty labelProperty = EMFEditProperties.value(getEditingDomain(), SwprojPackage.Literals.DESCRIBED_ELEMENT__NAME);
	
		ViewerSupport.bind(tableViewer, emfList, labelProperty);
	}

	private void bindActors(DataBindingContext bindingContext, SoftwareProject softwareProject) {
		TableViewer tableViewer = composite.getActorsComposite().getTableViewer();
		
		IObservableList emfList = EMFEditProperties.list(getEditingDomain(), SwprojPackage.Literals.SOFTWARE_PROJECT__ACTORS).observe(softwareProject);
		IValueProperty labelProperty = EMFEditProperties.value(getEditingDomain(), SwprojPackage.Literals.DESCRIBED_ELEMENT__NAME);
	
		ViewerSupport.bind(tableViewer, emfList, labelProperty);
	}

	private void bindConceptualObjects(DataBindingContext bindingContext, SoftwareProject softwareProject) {
		TableViewer tableViewer = composite.getConceptualObjectsComposite().getTableViewer();
		
		IObservableList emfList = EMFEditProperties.list(getEditingDomain(), SwprojPackage.Literals.SOFTWARE_PROJECT__CONCEPTUAL_OBJECTS).observe(softwareProject);
		IValueProperty labelProperty = EMFEditProperties.value(getEditingDomain(), SwprojPackage.Literals.DESCRIBED_ELEMENT__NAME);
	
		ViewerSupport.bind(tableViewer, emfList, labelProperty);
	}
	
}
