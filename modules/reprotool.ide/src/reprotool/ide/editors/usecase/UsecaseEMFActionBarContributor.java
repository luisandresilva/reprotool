/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.ide.editors.usecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

import reprotool.ide.editors.usecase.sentenceanalysis.action.ActivityAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.AutomaticAnalysisAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.AutomaticAnalysisAllAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.EraseAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.GotoAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.IncludeUseCaseAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.ParamAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.ReceiverAction;
import reprotool.ide.editors.usecase.sentenceanalysis.action.SenderAction;
import reprotool.model.linguistic.action.Communication;
import reprotool.model.linguistic.action.FromSystem;
import reprotool.model.linguistic.action.Goto;
import reprotool.model.linguistic.action.ToSystem;
import reprotool.model.linguistic.action.UseCaseInclude;
import reprotool.model.usecase.Scenario;
import reprotool.model.usecase.UseCaseStep;
import reprotool.model.usecase.UsecasePackage;
import reprotool.model.usecase.presentation.ReprotoolEditorPlugin;

/**
 * This is the action bar contributor for the Usecase model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UsecaseEMFActionBarContributor
	extends EditingDomainActionBarContributor
	implements ISelectionChangedListener {
	
	/**
	 * This keeps track of the active editor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IAction showPropertiesViewAction =
		new Action(ReprotoolEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) {
			@Override
			public void run() {
				try {
					getPage().showView("org.eclipse.ui.views.PropertySheet");
				}
				catch (PartInitException exception) {
					ReprotoolEditorPlugin.INSTANCE.log(exception);
				}
			}
		};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IAction refreshViewerAction =
		new Action(ReprotoolEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")) {
			@Override
			public boolean isEnabled() {
				return activeEditorPart instanceof IViewerProvider;
			}

			@Override
			public void run() {
				if (activeEditorPart instanceof IViewerProvider) {
					Viewer viewer = ((IViewerProvider)activeEditorPart).getViewer();
					if (viewer != null) {
						viewer.refresh();
					}
				}
			}
		};

	protected IAction refreshEditorAction = new Action("refresh-editor") {

		@Override
		public void run() {
			if (activeEditorPart instanceof UsecaseEMFEditor) {
				UsecaseEMFEditor editor = (UsecaseEMFEditor) activeEditorPart;
				editor.refreshEMFEditorPart();
			}
		}
	};
		
	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateChild actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMenuManager createChildMenuManager;

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMenuManager createSiblingMenuManager;

	private SenderAction senderAction;
	private ActivityAction activityAction;
	private ReceiverAction receiverAction;
	private ParamAction paramAction;
	private GotoAction gotoAction;
	private IncludeUseCaseAction includeUseCaseAction;
	private EraseAction eraseAction;
	
	private AutomaticAnalysisAllAction automaticAnalysisAllAction;
	private AutomaticAnalysisAction automaticAnalysisAction;
	

	/**
	 * This creates an instance of the contributor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsecaseEMFActionBarContributor() {
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		controlAction = new ControlAction();
	}

	/**
	 * This adds Separators for editor additions to the tool bar.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(new Separator("usecase-settings"));
		
		senderAction = new SenderAction("sender-action", refreshEditorAction);
		toolBarManager.add(senderAction);
		senderAction.setEnabled(false);
		activityAction = new ActivityAction("activity-action", refreshEditorAction);
		toolBarManager.add(activityAction);
		receiverAction = new ReceiverAction("receiver-action", refreshEditorAction);
		toolBarManager.add(receiverAction);
		paramAction = new ParamAction("action-param-action", refreshEditorAction);
		toolBarManager.add(paramAction);
		gotoAction = new GotoAction("goto-action", refreshEditorAction);
		toolBarManager.add(gotoAction);
		includeUseCaseAction = new IncludeUseCaseAction("include-use-case-action", refreshEditorAction);
		toolBarManager.add(includeUseCaseAction);
		eraseAction = new EraseAction("plain-text-action", refreshEditorAction);
		toolBarManager.add(eraseAction);
		
		toolBarManager.add(new Separator("usecase-automatic-tools"));
		
		automaticAnalysisAllAction = new AutomaticAnalysisAllAction("usecase-automatic-all", "Analyze linguistically Use Case", refreshEditorAction);
		toolBarManager.add(automaticAnalysisAllAction);
		
		automaticAnalysisAction = new AutomaticAnalysisAction("usecase-automatic", "Analyze linguistically selected Use Case Step", refreshEditorAction);
		toolBarManager.add(automaticAnalysisAction);
		
		toolBarManager.add(new Separator("usecase-additions"));
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor additions,
	 * as well as the sub-menus for object creation items.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(ReprotoolEditorPlugin.INSTANCE.getString("_UI_UsecaseEditor_menu"), "reprotool.model.usecaseMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(ReprotoolEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(ReprotoolEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		submenuManager.insertBefore("additions", createSiblingMenuManager);

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener
			(new IMenuListener() {
				 public void menuAboutToShow(IMenuManager menuManager) {
					 menuManager.updateAll(true);
				 }
			 });

		addGlobalActions(submenuManager);
	}

	/**
	 * When the active editor changes, this remembers the change and registers with it as a selection provider.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		activeEditorPart = part;

		// Switch to the new selection provider.
		//
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		}
		else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// add linguistic tools analysis
			selectionProvider.addSelectionChangedListener(automaticAnalysisAllAction);
			selectionProvider.addSelectionChangedListener(automaticAnalysisAction);
			
			// Fake a selection changed event to update the menus.
			//
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
			}
		}
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
	 * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings
	 * that can be added to the selected object and updating the menus accordingly.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		//
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		//
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			Object object = ((IStructuredSelection)selection).getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		//
		createChildActions = generateCreateChildActions(newChildDescriptors, selection);
		createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions, null);
			createSiblingMenuManager.update(true);
		}
		
		// change from generated method
		changeMarkingActionsEnablement(selection);
	}

	private void changeMarkingActionsEnablement(ISelection selection) {
		boolean isUseCaseStep = (selection instanceof IStructuredSelection) 
				&& (((IStructuredSelection)selection).size() == 1)
				&& (((IStructuredSelection)selection).getFirstElement() instanceof UseCaseStep);
		
		if (isUseCaseStep) {
			UseCaseStep useCaseStep = (UseCaseStep)((IStructuredSelection)selection).getFirstElement();
			reprotool.model.linguistic.action.Action action = useCaseStep.getAction();
			
			senderAction.setEnabled(action instanceof ToSystem);
			activityAction.setEnabled(action instanceof Communication);
			receiverAction.setEnabled(action instanceof FromSystem);
			paramAction.setEnabled(action instanceof Communication);
			gotoAction.setEnabled(action instanceof Goto);
			includeUseCaseAction.setEnabled(action instanceof UseCaseInclude);
			eraseAction.setEnabled(true);
			automaticAnalysisAllAction.setEnabled(true);
			automaticAnalysisAction.setEnabled(true);
		} else {
			// disable all
			senderAction.setEnabled(false);
			activityAction.setEnabled(false);
			receiverAction.setEnabled(false);
			paramAction.setEnabled(false);
			gotoAction.setEnabled(false);
			includeUseCaseAction.setEnabled(false);
			eraseAction.setEnabled(false);
			automaticAnalysisAllAction.setEnabled(false);
			automaticAnalysisAction.setEnabled(false);
		}
	}

	private static List<EReference> useCaseStepReferencesToRetain = new ArrayList<EReference>();
	private static List<EReference> scenarioReferencesToRetain = new ArrayList<EReference>();
	static {
		useCaseStepReferencesToRetain.add(UsecasePackage.eINSTANCE.getUseCaseStep_Extensions());
		useCaseStepReferencesToRetain.add(UsecasePackage.eINSTANCE.getUseCaseStep_Variations());
		useCaseStepReferencesToRetain.add(UsecasePackage.eINSTANCE.getParseableElement_Annotations());
		
		scenarioReferencesToRetain.add(UsecasePackage.eINSTANCE.getScenario_Steps());
	}
	
	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in <code>descriptors</code>,
	 * and returns the collection of these actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			
			// change from generated code - get rid of descriptors that should not be showed in popup menu
			if (selectionHasType(UseCaseStep.class, selection)) {
				filterDescriptors(descriptors, useCaseStepReferencesToRetain);
			} else if (selectionHasType(Scenario.class, selection)) {
				filterDescriptors(descriptors, scenarioReferencesToRetain);
			} 
			
			for (Object descriptor : descriptors) {
				actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}
	
	private void filterDescriptors(Collection<?> descriptors, Collection<EReference> referencesToRetain) {
		List<Object> toRetain = new ArrayList<Object>();
		
		for (Object descriptor : descriptors) {
			if (descriptor instanceof CommandParameter 
					&& referencesToRetain.contains(((CommandParameter)descriptor).getEReference())) {
				toRetain.add(descriptor);
			}
		}
		
		descriptors.retainAll(toRetain);
		
	}

	private boolean selectionHasType(Class<?> clazz, ISelection selection) {
		return (selection instanceof TreeSelection) && clazz.isAssignableFrom(((TreeSelection)selection).getFirstElement().getClass());
	}
	
	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in <code>descriptors</code>,
	 * and returns the collection of these actions.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			// change from generated code - get rid of descriptors that should not be showed in popup menu
			if (selectionHasType(UseCaseStep.class, selection)) {
				filterDescriptors(descriptors, scenarioReferencesToRetain);
			} else if (selectionHasType(Scenario.class, selection)) {
				filterDescriptors(descriptors, useCaseStepReferencesToRetain);
			} 
			
			for (Object descriptor : descriptors) {
				actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
	 * by inserting them before the specified contribution item <code>contributionID</code>.
	 * If <code>contributionID</code> is <code>null</code>, they are simply added.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void populateManager(IContributionManager manager, Collection<? extends IAction> actions, String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				}
				else {
					manager.add(action);
				}
			}
		}
	}
		
	/**
	 * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem)contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This populates the pop-up menu before it appears.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(ReprotoolEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(ReprotoolEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	/**
	 * This inserts global actions before the "additions-end" separator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void addGlobalActions(IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", showPropertiesViewAction);

//		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());		
//		menuManager.insertAfter("ui-actions", refreshViewerAction);

//		toolBarManager.add(new Separator("usecase-automatic-tools"));
//		menuManager.add(new AutomaticAnalysisAction("automatic-analysis-action"));
		
		super.addGlobalActions(menuManager);
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted objects.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean removeAllReferencesOnDelete() {
		return true;
	}

}