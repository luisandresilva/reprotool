/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.model.specification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import reprotool.model.structure.doc.Document;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getActors <em>Actors</em>}</li>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getName <em>Name</em>}</li>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getDescription <em>Description</em>}</li>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getRequirements <em>Requirements</em>}</li>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getUseCases <em>Use Cases</em>}</li>
 *   <li>{@link reprotool.model.specification.SoftwareProject#getSrsDocuments <em>Srs Documents</em>}</li>
 * </ul>
 * </p>
 *
 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject()
 * @model
 * @generated
 */
public interface SoftwareProject extends EObject {
	/**
	 * Returns the value of the '<em><b>Actors</b></em>' containment reference list.
	 * The list contents are of type {@link reprotool.model.specification.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actors</em>' containment reference list.
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_Actors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getActors();

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
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link reprotool.model.specification.SoftwareProject#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link reprotool.model.specification.SoftwareProject#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link reprotool.model.specification.Requirement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

	/**
	 * Returns the value of the '<em><b>Use Cases</b></em>' containment reference list.
	 * The list contents are of type {@link reprotool.model.specification.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Cases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Cases</em>' containment reference list.
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_UseCases()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCase> getUseCases();

	/**
	 * Returns the value of the '<em><b>Srs Documents</b></em>' reference list.
	 * The list contents are of type {@link reprotool.model.structure.doc.Document}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Srs Documents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Srs Documents</em>' reference list.
	 * @see reprotool.model.specification.SpecificationPackage#getSoftwareProject_SrsDocuments()
	 * @model
	 * @generated
	 */
	EList<Document> getSrsDocuments();

} // SoftwareProject