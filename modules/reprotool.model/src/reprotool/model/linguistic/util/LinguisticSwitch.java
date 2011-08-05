/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.model.linguistic.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import reprotool.model.linguistic.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see reprotool.model.linguistic.LinguisticPackage
 * @generated
 */
public class LinguisticSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LinguisticPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinguisticSwitch() {
		if (modelPackage == null) {
			modelPackage = LinguisticPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case LinguisticPackage.SENTENCE_NODE: {
				SentenceNode sentenceNode = (SentenceNode)theEObject;
				T result = caseSentenceNode(sentenceNode);
				if (result == null) result = caseInnerParseNode(sentenceNode);
				if (result == null) result = caseParseNode(sentenceNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.WORD: {
				Word word = (Word)theEObject;
				T result = caseWord(word);
				if (result == null) result = caseParseNode(word);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.PARSE_NODE: {
				ParseNode parseNode = (ParseNode)theEObject;
				T result = caseParseNode(parseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.NOUN_PHRASE_NODE: {
				NounPhraseNode nounPhraseNode = (NounPhraseNode)theEObject;
				T result = caseNounPhraseNode(nounPhraseNode);
				if (result == null) result = caseInnerParseNode(nounPhraseNode);
				if (result == null) result = caseParseNode(nounPhraseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.VERB_PHRASE_NODE: {
				VerbPhraseNode verbPhraseNode = (VerbPhraseNode)theEObject;
				T result = caseVerbPhraseNode(verbPhraseNode);
				if (result == null) result = caseInnerParseNode(verbPhraseNode);
				if (result == null) result = caseParseNode(verbPhraseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.PREPOSITIONAL_PHRASE_NODE: {
				PrepositionalPhraseNode prepositionalPhraseNode = (PrepositionalPhraseNode)theEObject;
				T result = casePrepositionalPhraseNode(prepositionalPhraseNode);
				if (result == null) result = caseInnerParseNode(prepositionalPhraseNode);
				if (result == null) result = caseParseNode(prepositionalPhraseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.INNER_PARSE_NODE: {
				InnerParseNode innerParseNode = (InnerParseNode)theEObject;
				T result = caseInnerParseNode(innerParseNode);
				if (result == null) result = caseParseNode(innerParseNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.SENTENCE_PART: {
				SentencePart sentencePart = (SentencePart)theEObject;
				T result = caseSentencePart(sentencePart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.TEXT: {
				Text text = (Text)theEObject;
				T result = caseText(text);
				if (result == null) result = caseSentencePart(text);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.SUBJECT: {
				Subject subject = (Subject)theEObject;
				T result = caseSubject(subject);
				if (result == null) result = caseSentencePart(subject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.GOTO_TARGET: {
				GotoTarget gotoTarget = (GotoTarget)theEObject;
				T result = caseGotoTarget(gotoTarget);
				if (result == null) result = caseSentencePart(gotoTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.SENTENCE_OBJECT: {
				SentenceObject sentenceObject = (SentenceObject)theEObject;
				T result = caseSentenceObject(sentenceObject);
				if (result == null) result = caseSentencePart(sentenceObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.REPRESENTATIVE_OBJECT: {
				RepresentativeObject representativeObject = (RepresentativeObject)theEObject;
				T result = caseRepresentativeObject(representativeObject);
				if (result == null) result = caseSentenceObject(representativeObject);
				if (result == null) result = caseSentencePart(representativeObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.INDIRECT_OBJECT: {
				IndirectObject indirectObject = (IndirectObject)theEObject;
				T result = caseIndirectObject(indirectObject);
				if (result == null) result = caseSentenceObject(indirectObject);
				if (result == null) result = caseSentencePart(indirectObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LinguisticPackage.VERB: {
				Verb verb = (Verb)theEObject;
				T result = caseVerb(verb);
				if (result == null) result = caseSentencePart(verb);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sentence Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sentence Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSentenceNode(SentenceNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Word</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Word</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWord(Word object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parse Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parse Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParseNode(ParseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Noun Phrase Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Noun Phrase Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNounPhraseNode(NounPhraseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Verb Phrase Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Verb Phrase Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerbPhraseNode(VerbPhraseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prepositional Phrase Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prepositional Phrase Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrepositionalPhraseNode(PrepositionalPhraseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inner Parse Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inner Parse Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInnerParseNode(InnerParseNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sentence Part</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sentence Part</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSentencePart(SentencePart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseText(Text object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubject(Subject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goto Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goto Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGotoTarget(GotoTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sentence Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sentence Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSentenceObject(SentenceObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Representative Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Representative Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepresentativeObject(RepresentativeObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Indirect Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Indirect Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIndirectObject(IndirectObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Verb</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Verb</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerb(Verb object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //LinguisticSwitch
