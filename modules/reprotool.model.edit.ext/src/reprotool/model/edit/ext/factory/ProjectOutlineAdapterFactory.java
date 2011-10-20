package reprotool.model.edit.ext.factory;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import reprotool.model.edit.ext.annotation.ActorItemProviderAnnotation;
import reprotool.model.edit.ext.annotation.SoftwareProjectItemProviderAnnotation;
import reprotool.model.edit.ext.annotation.UseCaseItemProviderAnnotation;
import reprotool.model.edit.ext.annotation.UseCaseStepItemProviderAnnotation;
import reprotool.model.swproj.provider.ActorItemProvider;
import reprotool.model.swproj.provider.ActorItemProviderExt;
import reprotool.model.swproj.provider.SoftwareProjectItemProvider;
import reprotool.model.swproj.provider.SoftwareProjectItemProviderExt;
import reprotool.model.swproj.provider.SwprojItemProviderAdapterFactory;
import reprotool.model.swproj.provider.SwprojItemProviderAdapterFactoryExt;
import reprotool.model.swproj.provider.SwprojItemProviderAdapterFactoryExt.FactorySoftwareProject;
import reprotool.model.usecase.provider.UseCaseItemProvider;
import reprotool.model.usecase.provider.UseCaseItemProviderExt;
import reprotool.model.usecase.provider.UseCaseStepItemProvider;
import reprotool.model.usecase.provider.UseCaseStepItemProviderExt;
import reprotool.model.usecase.provider.UsecaseItemProviderAdapterFactory;
import reprotool.model.usecase.provider.UsecaseItemProviderAdapterFactoryExt;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;

/**
 * Provides item providers for outline view in project editor.
 * 
 * @author jvinarek
 *
 */
public class ProjectOutlineAdapterFactory extends ComposedAdapterFactory {

	public ProjectOutlineAdapterFactory() {
		Injector injector = Guice.createInjector(new ProjectOutlineModule());

		SwprojItemProviderAdapterFactory swprojItemProviderAdapterFactory = injector.getInstance(SwprojItemProviderAdapterFactory.class);
		UsecaseItemProviderAdapterFactory usecaseItemProviderAdapterFactory = injector.getInstance(UsecaseItemProviderAdapterFactory.class);
		
		addAdapterFactory(new ResourceItemProviderAdapterFactory());
		addAdapterFactory(swprojItemProviderAdapterFactory);
		addAdapterFactory(usecaseItemProviderAdapterFactory);
		addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	private static class ProjectOutlineModule extends AbstractModule {

		@Override
		protected void configure() {
			// ProjectOutlineAdapterFactory dependencies
			bind(SwprojItemProviderAdapterFactory.class).to(SwprojItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
			bind(UsecaseItemProviderAdapterFactory.class).to(UsecaseItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
			
			// SwprojItemProviderAdapterFactoryExt dependencies
			bind(ActorItemProvider.class).to(ActorItemProviderExt.class).in(Scopes.SINGLETON);
			// factory, becouse SoftwareProjectItemProviderExt is stateful
			bind(FactorySoftwareProject.class).to(FactorySoftwareProjectImpl.class).in(Scopes.SINGLETON);
			
			// UsecaseItemProviderAdapterFactoryExt dependencies
			bind(UseCaseItemProvider.class).to(UseCaseItemProviderExt.class).in(Scopes.SINGLETON);
			bind(UseCaseStepItemProvider.class).to(UseCaseStepItemProviderExt.class).in(Scopes.SINGLETON);
			
			bind(AdapterFactory.class).annotatedWith(ActorItemProviderAnnotation.class).to(SwprojItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
			bind(AdapterFactory.class).annotatedWith(SoftwareProjectItemProviderAnnotation.class).to(SwprojItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
			
			// use case item provider needs SwprojItemProviderAdapterFactoryExt as parent factory 
			bind(AdapterFactory.class).annotatedWith(UseCaseItemProviderAnnotation.class).to(SwprojItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
			bind(AdapterFactory.class).annotatedWith(UseCaseStepItemProviderAnnotation.class).to(UsecaseItemProviderAdapterFactoryExt.class).in(Scopes.SINGLETON);
		}

	}
	
	static class FactorySoftwareProjectImpl implements FactorySoftwareProject {

		@Override
		public SoftwareProjectItemProvider create(SwprojItemProviderAdapterFactory adapterFactory) {
			return new SoftwareProjectItemProviderExt(adapterFactory);
		}
		
	}
	
}
