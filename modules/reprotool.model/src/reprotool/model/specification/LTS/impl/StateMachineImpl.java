/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package reprotool.model.specification.LTS.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import reprotool.model.specification.LTS.LTSPackage;
import reprotool.model.specification.LTS.State;
import reprotool.model.specification.LTS.StateMachine;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Machine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link reprotool.model.specification.LTS.impl.StateMachineImpl#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link reprotool.model.specification.LTS.impl.StateMachineImpl#getAbortState <em>Abort State</em>}</li>
 *   <li>{@link reprotool.model.specification.LTS.impl.StateMachineImpl#getSuccessState <em>Success State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateMachineImpl extends EObjectImpl implements StateMachine {
	/**
	 * The cached value of the '{@link #getInitialState() <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialState()
	 * @generated
	 * @ordered
	 */
	protected State initialState;

	/**
	 * The cached value of the '{@link #getAbortState() <em>Abort State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbortState()
	 * @generated
	 * @ordered
	 */
	protected State abortState;

	/**
	 * The cached value of the '{@link #getSuccessState() <em>Success State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuccessState()
	 * @generated
	 * @ordered
	 */
	protected State successState;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateMachineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LTSPackage.Literals.STATE_MACHINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getInitialState() {
		if (initialState != null && initialState.eIsProxy()) {
			InternalEObject oldInitialState = (InternalEObject)initialState;
			initialState = (State)eResolveProxy(oldInitialState);
			if (initialState != oldInitialState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LTSPackage.STATE_MACHINE__INITIAL_STATE, oldInitialState, initialState));
			}
		}
		return initialState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetInitialState() {
		return initialState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialState(State newInitialState) {
		State oldInitialState = initialState;
		initialState = newInitialState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LTSPackage.STATE_MACHINE__INITIAL_STATE, oldInitialState, initialState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getAbortState() {
		if (abortState != null && abortState.eIsProxy()) {
			InternalEObject oldAbortState = (InternalEObject)abortState;
			abortState = (State)eResolveProxy(oldAbortState);
			if (abortState != oldAbortState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LTSPackage.STATE_MACHINE__ABORT_STATE, oldAbortState, abortState));
			}
		}
		return abortState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetAbortState() {
		return abortState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbortState(State newAbortState) {
		State oldAbortState = abortState;
		abortState = newAbortState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LTSPackage.STATE_MACHINE__ABORT_STATE, oldAbortState, abortState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getSuccessState() {
		if (successState != null && successState.eIsProxy()) {
			InternalEObject oldSuccessState = (InternalEObject)successState;
			successState = (State)eResolveProxy(oldSuccessState);
			if (successState != oldSuccessState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LTSPackage.STATE_MACHINE__SUCCESS_STATE, oldSuccessState, successState));
			}
		}
		return successState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetSuccessState() {
		return successState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuccessState(State newSuccessState) {
		State oldSuccessState = successState;
		successState = newSuccessState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LTSPackage.STATE_MACHINE__SUCCESS_STATE, oldSuccessState, successState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case LTSPackage.STATE_MACHINE__INITIAL_STATE:
				if (resolve) return getInitialState();
				return basicGetInitialState();
			case LTSPackage.STATE_MACHINE__ABORT_STATE:
				if (resolve) return getAbortState();
				return basicGetAbortState();
			case LTSPackage.STATE_MACHINE__SUCCESS_STATE:
				if (resolve) return getSuccessState();
				return basicGetSuccessState();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case LTSPackage.STATE_MACHINE__INITIAL_STATE:
				setInitialState((State)newValue);
				return;
			case LTSPackage.STATE_MACHINE__ABORT_STATE:
				setAbortState((State)newValue);
				return;
			case LTSPackage.STATE_MACHINE__SUCCESS_STATE:
				setSuccessState((State)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case LTSPackage.STATE_MACHINE__INITIAL_STATE:
				setInitialState((State)null);
				return;
			case LTSPackage.STATE_MACHINE__ABORT_STATE:
				setAbortState((State)null);
				return;
			case LTSPackage.STATE_MACHINE__SUCCESS_STATE:
				setSuccessState((State)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case LTSPackage.STATE_MACHINE__INITIAL_STATE:
				return initialState != null;
			case LTSPackage.STATE_MACHINE__ABORT_STATE:
				return abortState != null;
			case LTSPackage.STATE_MACHINE__SUCCESS_STATE:
				return successState != null;
		}
		return super.eIsSet(featureID);
	}

} //StateMachineImpl