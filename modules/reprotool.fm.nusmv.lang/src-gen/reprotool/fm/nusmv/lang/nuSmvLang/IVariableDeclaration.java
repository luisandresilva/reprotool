/**
 * <copyright>
 * </copyright>
 *

 */
package reprotool.fm.nusmv.lang.nuSmvLang;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IVariable Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link reprotool.fm.nusmv.lang.nuSmvLang.IVariableDeclaration#getVars <em>Vars</em>}</li>
 * </ul>
 * </p>
 *
 * @see reprotool.fm.nusmv.lang.nuSmvLang.NuSmvLangPackage#getIVariableDeclaration()
 * @model
 * @generated
 */
public interface IVariableDeclaration extends ModuleElement
{
  /**
   * Returns the value of the '<em><b>Vars</b></em>' containment reference list.
   * The list contents are of type {@link reprotool.fm.nusmv.lang.nuSmvLang.VarBody}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vars</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vars</em>' containment reference list.
   * @see reprotool.fm.nusmv.lang.nuSmvLang.NuSmvLangPackage#getIVariableDeclaration_Vars()
   * @model containment="true"
   * @generated
   */
  EList<VarBody> getVars();

} // IVariableDeclaration