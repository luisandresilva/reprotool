/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.model.linguistic;

import reprotool.model.usecase.UseCaseStep;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goto Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link reprotool.model.linguistic.GotoTarget#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see reprotool.model.linguistic.LinguisticPackage#getGotoTarget()
 * @model
 * @generated
 */
public interface GotoTarget extends SentencePart {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(UseCaseStep)
	 * @see reprotool.model.linguistic.LinguisticPackage#getGotoTarget_Target()
	 * @model
	 * @generated
	 */
	UseCaseStep getTarget();

	/**
	 * Sets the value of the '{@link reprotool.model.linguistic.GotoTarget#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(UseCaseStep value);

} // GotoTarget
