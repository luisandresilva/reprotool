package reprotool.model.swproj.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import reprotool.model.lts.provider.ReprotoolEditPlugin;
import reprotool.model.swproj.SoftwareProject;
import reprotool.model.swproj.SwprojFactory;
import reprotool.model.swproj.SwprojPackage;
import reprotool.model.usecase.UsecaseFactory;

/**
 * @author jvinarek
 * 
 */
public class SoftwareProjectItemProviderExt extends SoftwareProjectItemProvider {

	private static final int ACTORS_INDEX = 0;
	private static final int USE_CASES_INDEX = 1;

	protected List children = null;

	public SoftwareProjectItemProviderExt(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection<?> getChildren(Object object) {
		if (children == null) {
			SoftwareProject softwareProject = (SoftwareProject) object;
			children = new ArrayList();
			children.add(new ActorsItemProvider(adapterFactory, softwareProject));
			children.add(new UseCasesItemProvider(adapterFactory, softwareProject));			
		}
		return children;
	}

	public Object getActors() {
		return children.get(ACTORS_INDEX);
	}

	public Object getUseCases() {
		return children.get(USE_CASES_INDEX);
	}

	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection) {
		return createWrappedCommand(super.createRemoveCommand(domain, owner, feature, collection), owner, feature);
	}

	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
			Collection<?> collection, int index) {
		return createWrappedCommand(super.createAddCommand(domain, owner, feature, collection, index), owner, feature);
	}

	protected Command createWrappedCommand(Command command, final EObject owner, final EStructuralFeature feature) {
		if (feature == SwprojPackage.eINSTANCE.getSoftwareProject_Actors()
				|| feature == SwprojPackage.eINSTANCE.getSoftwareProject_UseCases()) {
			return new CommandWrapper(command) {
				@Override
				public Collection<?> getAffectedObjects() {
					Collection<?> affected = super.getAffectedObjects();
					if (affected.contains(owner)) {
						affected = Collections
								.singleton(feature == SwprojPackage.eINSTANCE.getSoftwareProject_Actors() ? getActors()
										: getUseCases());
					}
					return affected;
				}
			};
		}
		return command;
	}

	/**
	 * Disposes the non-modeled children.
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (children != null) {
			((IDisposable) children.get(ACTORS_INDEX)).dispose();
			((IDisposable) children.get(USE_CASES_INDEX)).dispose();
		}
	}

	public class TransientSoftwareProjectItemProvider extends ItemProviderAdapter implements
			IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
			IItemPropertySource {

		public TransientSoftwareProjectItemProvider(AdapterFactory adapterFactory, SoftwareProject softwareProject) {
			super(adapterFactory);
			softwareProject.eAdapters().add(this);
		}

		@Override
		public Collection<?> getChildren(Object object) {
			return super.getChildren(target);
		}

		@Override
		public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
			return super.getNewChildDescriptors(target, editingDomain, sibling);
		}

		@Override
		public Object getParent(Object object) {
			return target;
		}

		@Override
		public ResourceLocator getResourceLocator() {
			// take resources from "edit" plugin
			return ReprotoolEditPlugin.INSTANCE;
		}

		@Override
		public Command createCommand(final Object object, final EditingDomain domain,
				Class<? extends Command> commandClass, CommandParameter commandParameter) {
			commandParameter.setOwner(target);
			return super.createCommand(target, domain, commandClass, commandParameter);
		}

		@Override
		protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
				Collection<?> collection) {
			return createWrappedCommand(super.createRemoveCommand(domain, owner, feature, collection), owner);
		}

		@Override
		protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature,
				Collection<?> collection, int index) {
			return createWrappedCommand(super.createAddCommand(domain, owner, feature, collection, index), owner);
		}

		protected Command createWrappedCommand(Command command, final EObject owner) {
			return new CommandWrapper(command) {
				public Collection<?> getAffectedObjects() {
					Collection<?> affected = super.getAffectedObjects();
					if (affected.contains(owner)) {
						affected = Collections.singleton(TransientSoftwareProjectItemProvider.this);
					}
					return affected;
				}
			};
		}

	}

	public class ActorsItemProvider extends TransientSoftwareProjectItemProvider {
		public ActorsItemProvider(AdapterFactory adapterFactory, SoftwareProject softwareProject) {
			super(adapterFactory, softwareProject);
		}

		@Override
		public Collection getChildrenFeatures(Object object) {
			if (childrenFeatures == null) {
				super.getChildrenFeatures(object);
				childrenFeatures.add(SwprojPackage.Literals.SOFTWARE_PROJECT__ACTORS);
			}
			return childrenFeatures;
		}

		@Override
		public String getText(Object object) {
			// TODO - jvinarek - move to properties file
			return "Actors";
		}

		@Override
		protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
			super.collectNewChildDescriptors(newChildDescriptors, object);
			newChildDescriptors.add(createChildParameter(SwprojPackage.Literals.SOFTWARE_PROJECT__ACTORS,
					SwprojFactory.eINSTANCE.createActor()));
		}

		@Override
		protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations,
				int operation, Collection<?> collection) {
			if (new AddCommand(domain, (EObject) owner, SwprojPackage.Literals.SOFTWARE_PROJECT__ACTORS, collection)
					.canExecute()) {
				return super.createDragAndDropCommand(domain, owner, location, operations, operation, collection);
			}
			return UnexecutableCommand.INSTANCE;
		}
	}

	public class UseCasesItemProvider extends TransientSoftwareProjectItemProvider {
		public UseCasesItemProvider(AdapterFactory adapterFactory, SoftwareProject softwareProject) {
			super(adapterFactory, softwareProject);
		}

		@Override
		public Collection getChildrenFeatures(Object object) {
			if (childrenFeatures == null) {
				super.getChildrenFeatures(object);
				childrenFeatures.add(SwprojPackage.Literals.SOFTWARE_PROJECT__USE_CASES);
			}
			return childrenFeatures;
		}

		@Override
		public String getText(Object object) {
			// TODO
			return "Use cases";
		}

		@Override
		protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
			super.collectNewChildDescriptors(newChildDescriptors, object);
			newChildDescriptors.add(createChildParameter(SwprojPackage.Literals.SOFTWARE_PROJECT__USE_CASES,
					UsecaseFactory.eINSTANCE.createUseCase()));
		}

		@Override
		protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations,
				int operation, Collection<?> collection) {
			if (new AddCommand(domain, (EObject) owner, SwprojPackage.Literals.SOFTWARE_PROJECT__USE_CASES, collection)
					.canExecute()) {
				return super.createDragAndDropCommand(domain, owner, location, operations, operation, collection);
			}
			return UnexecutableCommand.INSTANCE;
		}
	}

}