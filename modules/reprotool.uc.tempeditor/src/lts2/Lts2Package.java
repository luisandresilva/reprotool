/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lts2;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see lts2.Lts2Factory
 * @model kind="package"
 * @generated
 */
public interface Lts2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "lts2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://d3s.mff.cuni.cz/reprotool/model/lts2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "lts2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Lts2Package eINSTANCE = lts2.impl.Lts2PackageImpl.init();

	/**
	 * The meta object id for the '{@link lts2.impl.StateMachineImpl <em>State Machine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.StateMachineImpl
	 * @see lts2.impl.Lts2PackageImpl#getStateMachine()
	 * @generated
	 */
	int STATE_MACHINE = 0;

	/**
	 * The feature id for the '<em><b>Initial State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__INITIAL_STATE = 0;

	/**
	 * The feature id for the '<em><b>Transitional States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__TRANSITIONAL_STATES = 1;

	/**
	 * The feature id for the '<em><b>Final State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__FINAL_STATE = 2;

	/**
	 * The feature id for the '<em><b>Abort State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE__ABORT_STATE = 3;

	/**
	 * The number of structural features of the '<em>State Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_MACHINE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link lts2.State <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.State
	 * @see lts2.impl.Lts2PackageImpl#getState()
	 * @generated
	 */
	int STATE = 3;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link lts2.impl.TransitionalStateImpl <em>Transitional State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.TransitionalStateImpl
	 * @see lts2.impl.Lts2PackageImpl#getTransitionalState()
	 * @generated
	 */
	int TRANSITIONAL_STATE = 4;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONAL_STATE__TRANSITIONS = STATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Transitional State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITIONAL_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link lts2.impl.InitialStateImpl <em>Initial State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.InitialStateImpl
	 * @see lts2.impl.Lts2PackageImpl#getInitialState()
	 * @generated
	 */
	int INITIAL_STATE = 1;

	/**
	 * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE__TRANSITIONS = TRANSITIONAL_STATE__TRANSITIONS;

	/**
	 * The number of structural features of the '<em>Initial State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_STATE_FEATURE_COUNT = TRANSITIONAL_STATE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link lts2.impl.FinalStateImpl <em>Final State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.FinalStateImpl
	 * @see lts2.impl.Lts2PackageImpl#getFinalState()
	 * @generated
	 */
	int FINAL_STATE = 2;

	/**
	 * The number of structural features of the '<em>Final State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link lts2.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.TransitionImpl
	 * @see lts2.impl.Lts2PackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 5;

	/**
	 * The feature id for the '<em><b>Target State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET_STATE = 0;

	/**
	 * The feature id for the '<em><b>Related Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__RELATED_STEP = 1;

	/**
	 * The feature id for the '<em><b>Source State</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__SOURCE_STATE = 2;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link lts2.impl.AbortStateImpl <em>Abort State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.AbortStateImpl
	 * @see lts2.impl.Lts2PackageImpl#getAbortState()
	 * @generated
	 */
	int ABORT_STATE = 6;

	/**
	 * The number of structural features of the '<em>Abort State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABORT_STATE_FEATURE_COUNT = STATE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link lts2.impl.LTSGeneratorImpl <em>LTS Generator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lts2.impl.LTSGeneratorImpl
	 * @see lts2.impl.Lts2PackageImpl#getLTSGenerator()
	 * @generated
	 */
	int LTS_GENERATOR = 7;

	/**
	 * The feature id for the '<em><b>Label Transition System</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTS_GENERATOR__LABEL_TRANSITION_SYSTEM = 0;

	/**
	 * The number of structural features of the '<em>LTS Generator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTS_GENERATOR_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link lts2.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine</em>'.
	 * @see lts2.StateMachine
	 * @generated
	 */
	EClass getStateMachine();

	/**
	 * Returns the meta object for the containment reference '{@link lts2.StateMachine#getInitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initial State</em>'.
	 * @see lts2.StateMachine#getInitialState()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_InitialState();

	/**
	 * Returns the meta object for the containment reference list '{@link lts2.StateMachine#getTransitionalStates <em>Transitional States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitional States</em>'.
	 * @see lts2.StateMachine#getTransitionalStates()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_TransitionalStates();

	/**
	 * Returns the meta object for the containment reference '{@link lts2.StateMachine#getFinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Final State</em>'.
	 * @see lts2.StateMachine#getFinalState()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_FinalState();

	/**
	 * Returns the meta object for the containment reference '{@link lts2.StateMachine#getAbortState <em>Abort State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Abort State</em>'.
	 * @see lts2.StateMachine#getAbortState()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_AbortState();

	/**
	 * Returns the meta object for class '{@link lts2.InitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initial State</em>'.
	 * @see lts2.InitialState
	 * @generated
	 */
	EClass getInitialState();

	/**
	 * Returns the meta object for class '{@link lts2.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final State</em>'.
	 * @see lts2.FinalState
	 * @generated
	 */
	EClass getFinalState();

	/**
	 * Returns the meta object for class '{@link lts2.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see lts2.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for class '{@link lts2.TransitionalState <em>Transitional State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transitional State</em>'.
	 * @see lts2.TransitionalState
	 * @generated
	 */
	EClass getTransitionalState();

	/**
	 * Returns the meta object for the containment reference list '{@link lts2.TransitionalState#getTransitions <em>Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transitions</em>'.
	 * @see lts2.TransitionalState#getTransitions()
	 * @see #getTransitionalState()
	 * @generated
	 */
	EReference getTransitionalState_Transitions();

	/**
	 * Returns the meta object for class '{@link lts2.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see lts2.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference '{@link lts2.Transition#getTargetState <em>Target State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target State</em>'.
	 * @see lts2.Transition#getTargetState()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_TargetState();

	/**
	 * Returns the meta object for the reference '{@link lts2.Transition#getRelatedStep <em>Related Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Related Step</em>'.
	 * @see lts2.Transition#getRelatedStep()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_RelatedStep();

	/**
	 * Returns the meta object for the container reference '{@link lts2.Transition#getSourceState <em>Source State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source State</em>'.
	 * @see lts2.Transition#getSourceState()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_SourceState();

	/**
	 * Returns the meta object for class '{@link lts2.AbortState <em>Abort State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abort State</em>'.
	 * @see lts2.AbortState
	 * @generated
	 */
	EClass getAbortState();

	/**
	 * Returns the meta object for class '{@link lts2.LTSGenerator <em>LTS Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>LTS Generator</em>'.
	 * @see lts2.LTSGenerator
	 * @generated
	 */
	EClass getLTSGenerator();

	/**
	 * Returns the meta object for the containment reference '{@link lts2.LTSGenerator#getLabelTransitionSystem <em>Label Transition System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label Transition System</em>'.
	 * @see lts2.LTSGenerator#getLabelTransitionSystem()
	 * @see #getLTSGenerator()
	 * @generated
	 */
	EReference getLTSGenerator_LabelTransitionSystem();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Lts2Factory getLts2Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link lts2.impl.StateMachineImpl <em>State Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.StateMachineImpl
		 * @see lts2.impl.Lts2PackageImpl#getStateMachine()
		 * @generated
		 */
		EClass STATE_MACHINE = eINSTANCE.getStateMachine();

		/**
		 * The meta object literal for the '<em><b>Initial State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__INITIAL_STATE = eINSTANCE.getStateMachine_InitialState();

		/**
		 * The meta object literal for the '<em><b>Transitional States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__TRANSITIONAL_STATES = eINSTANCE.getStateMachine_TransitionalStates();

		/**
		 * The meta object literal for the '<em><b>Final State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__FINAL_STATE = eINSTANCE.getStateMachine_FinalState();

		/**
		 * The meta object literal for the '<em><b>Abort State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__ABORT_STATE = eINSTANCE.getStateMachine_AbortState();

		/**
		 * The meta object literal for the '{@link lts2.impl.InitialStateImpl <em>Initial State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.InitialStateImpl
		 * @see lts2.impl.Lts2PackageImpl#getInitialState()
		 * @generated
		 */
		EClass INITIAL_STATE = eINSTANCE.getInitialState();

		/**
		 * The meta object literal for the '{@link lts2.impl.FinalStateImpl <em>Final State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.FinalStateImpl
		 * @see lts2.impl.Lts2PackageImpl#getFinalState()
		 * @generated
		 */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
		 * The meta object literal for the '{@link lts2.State <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.State
		 * @see lts2.impl.Lts2PackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '{@link lts2.impl.TransitionalStateImpl <em>Transitional State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.TransitionalStateImpl
		 * @see lts2.impl.Lts2PackageImpl#getTransitionalState()
		 * @generated
		 */
		EClass TRANSITIONAL_STATE = eINSTANCE.getTransitionalState();

		/**
		 * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITIONAL_STATE__TRANSITIONS = eINSTANCE.getTransitionalState_Transitions();

		/**
		 * The meta object literal for the '{@link lts2.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.TransitionImpl
		 * @see lts2.impl.Lts2PackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Target State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET_STATE = eINSTANCE.getTransition_TargetState();

		/**
		 * The meta object literal for the '<em><b>Related Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__RELATED_STEP = eINSTANCE.getTransition_RelatedStep();

		/**
		 * The meta object literal for the '<em><b>Source State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__SOURCE_STATE = eINSTANCE.getTransition_SourceState();

		/**
		 * The meta object literal for the '{@link lts2.impl.AbortStateImpl <em>Abort State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.AbortStateImpl
		 * @see lts2.impl.Lts2PackageImpl#getAbortState()
		 * @generated
		 */
		EClass ABORT_STATE = eINSTANCE.getAbortState();

		/**
		 * The meta object literal for the '{@link lts2.impl.LTSGeneratorImpl <em>LTS Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lts2.impl.LTSGeneratorImpl
		 * @see lts2.impl.Lts2PackageImpl#getLTSGenerator()
		 * @generated
		 */
		EClass LTS_GENERATOR = eINSTANCE.getLTSGenerator();

		/**
		 * The meta object literal for the '<em><b>Label Transition System</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LTS_GENERATOR__LABEL_TRANSITION_SYSTEM = eINSTANCE.getLTSGenerator_LabelTransitionSystem();

	}

} //Lts2Package
