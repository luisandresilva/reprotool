/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cz.cuni.mff.reprotool.model.project;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cz.cuni.mff.reprotool.model.project.UseCase#getPrimaryActor <em>Primary Actor</em>}</li>
 *   <li>{@link cz.cuni.mff.reprotool.model.project.UseCase#getUseCaseSteps <em>Use Case Steps</em>}</li>
 *   <li>{@link cz.cuni.mff.reprotool.model.project.UseCase#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see cz.cuni.mff.reprotool.model.project.ProjectPackage#getUseCase()
 * @model
 * @generated
 */
public interface UseCase extends EObject {
	/**
	 * Returns the value of the '<em><b>Primary Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Actor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Actor</em>' reference.
	 * @see #setPrimaryActor(Actor)
	 * @see cz.cuni.mff.reprotool.model.project.ProjectPackage#getUseCase_PrimaryActor()
	 * @model required="true"
	 * @generated
	 */
	Actor getPrimaryActor();

	/**
	 * Sets the value of the '{@link cz.cuni.mff.reprotool.model.project.UseCase#getPrimaryActor <em>Primary Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Actor</em>' reference.
	 * @see #getPrimaryActor()
	 * @generated
	 */
	void setPrimaryActor(Actor value);

	/**
	 * Returns the value of the '<em><b>Use Case Steps</b></em>' containment reference list.
	 * The list contents are of type {@link cz.cuni.mff.reprotool.model.project.UseCaseStep}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Case Steps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Case Steps</em>' containment reference list.
	 * @see cz.cuni.mff.reprotool.model.project.ProjectPackage#getUseCase_UseCaseSteps()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCaseStep> getUseCaseSteps();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see cz.cuni.mff.reprotool.model.project.ProjectPackage#getUseCase_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link cz.cuni.mff.reprotool.model.project.UseCase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // UseCase
