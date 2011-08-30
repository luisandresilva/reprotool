/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.model.linguistic.sentenceparts;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see reprotool.model.linguistic.sentenceparts.SentencepartsPackage
 * @generated
 */
public interface SentencepartsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SentencepartsFactory eINSTANCE = reprotool.model.linguistic.sentenceparts.impl.SentencepartsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text</em>'.
	 * @generated
	 */
	Text createText();

	/**
	 * Returns a new object of class '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject</em>'.
	 * @generated
	 */
	Subject createSubject();

	/**
	 * Returns a new object of class '<em>Goto Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Goto Target</em>'.
	 * @generated
	 */
	GotoTarget createGotoTarget();

	/**
	 * Returns a new object of class '<em>Verb</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Verb</em>'.
	 * @generated
	 */
	Verb createVerb();

	/**
	 * Returns a new object of class '<em>Representative Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Representative Object</em>'.
	 * @generated
	 */
	RepresentativeObject createRepresentativeObject();

	/**
	 * Returns a new object of class '<em>Indirect Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indirect Object</em>'.
	 * @generated
	 */
	IndirectObject createIndirectObject();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SentencepartsPackage getSentencepartsPackage();

} //SentencepartsFactory
