package reprotool.ide.editors.counterExample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lts2.AbortState;
import lts2.State;
import lts2.StateMachine;
import lts2.Transition;
import lts2.TransitionalState;
import lts2.impl.LTSGeneratorImpl;

import org.apache.commons.lang.WordUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.SWTEventDispatcher;
import org.eclipse.draw2d.Shape;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.CGraphNode;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphItem;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutAlgorithm;

import reprotool.model.linguistic.action.UseCaseInclude;
import reprotool.model.swproj.CounterExample;
import reprotool.model.swproj.Step;
import reprotool.model.swproj.UseCaseTransition;
import reprotool.model.usecase.Condition;
import reprotool.model.usecase.Scenario;
import reprotool.model.usecase.UseCase;
import reprotool.model.usecase.UseCaseStep;
import reprotool.model.usecase.annotate.StepAnnotation;
import reprotool.uc.tempeditor.FigureProvider;


public class LTSContentOutlinePage extends Page implements IContentOutlinePage {
	private GraphViewer viewer;
	private Composite graphParent;
	private FigureProvider figureProvider;
	private UseCase useCase;
	private StateMachine machine;
	private EList<UseCaseTransition> transitions;
	
	HashMap<State, GraphNode> state2Node = new HashMap<State, GraphNode>();
	
	/*
	 * List of machines corresponding to the included use cases.
	 */
	private List<StateMachine> includedMachines = new ArrayList<StateMachine>();
	private List<UseCase> includedUseCases = new ArrayList<UseCase>();
	private HashMap<StateMachine, UseCase> machine2UseCase = new HashMap<StateMachine, UseCase>();
	private HashMap<UseCase, StateMachine> useCase2Machine = new HashMap<UseCase, StateMachine>();
	private HashMap<GraphNode, GraphConnection> strayConnections =
			new HashMap<GraphNode, GraphConnection>();
	
	private HashMap<UseCaseStep, Transition> ucStep2TransLayout = null;
	private HashMap<UseCaseStep, Transition> ucStep2TransOriginal = null;
	private HashMap<Transition, GraphNode> trans2Node = new HashMap<Transition, GraphNode>();
	private List<Transition> gotoTransitions = null;	
	private HashMap<Transition, GraphConnection> trans2Edge = new HashMap<Transition, GraphConnection>();
	
	private HashMap<UseCaseTransition, HashMap<UseCaseStep, GraphConnection>> connectionTracker =
			new HashMap<UseCaseTransition, HashMap<UseCaseStep, GraphConnection>>();
	
	public LTSContentOutlinePage(CounterExample cexmp) {
		super();
		transitions = cexmp.getUseCaseTransitions();
		figureProvider = new FigureProvider();
	}
	
	private void generateIncludedMachines(List<UseCase> roots) {
		Queue<UseCase> ucQueue = new LinkedList<UseCase>(roots);
		includedUseCases.clear();
		includedMachines.clear();
		
		machine2UseCase.put(machine, useCase);
		useCase2Machine.put(useCase, machine);
		
		while (!ucQueue.isEmpty()) {
			UseCase u = ucQueue.poll();
			if ((u == null) || (u == useCase)) {
				continue;
			}
			if (!includedUseCases.contains(u)) {
				includedUseCases.add(u);
				LTSGeneratorImpl g = new LTSGeneratorImpl();
				g.processUseCase(u);
				includedMachines.add(g.getLabelTransitionSystem());
				ucQueue.addAll(g.getLtsCache().getIncludedUseCases());
				ucStep2TransLayout.putAll(g.getLtsCache().getUCStep2TransLayout());
				ucStep2TransOriginal.putAll(g.getLtsCache().getUCStep2Trans());
				gotoTransitions.addAll(g.getLtsCache().getGotoTransitions());
				machine2UseCase.put(g.getLabelTransitionSystem(), u);
				useCase2Machine.put(u, g.getLabelTransitionSystem());
			}
		}
	}
	
	private void regenerateLTS(UseCaseTransition ucTrans) {
		useCase = ucTrans.getUseCase();
		LTSGeneratorImpl generator = new LTSGeneratorImpl();
		generator.processUseCase(useCase);
		machine = generator.getLabelTransitionSystem();
		
		ucStep2TransOriginal = generator.getLtsCache().getUCStep2Trans();
		ucStep2TransLayout = generator.getLtsCache().getUCStep2TransLayout();
		gotoTransitions = generator.getLtsCache().getGotoTransitions();

		generateIncludedMachines(generator.getLtsCache().getIncludedUseCases());
	}
	
	UseCaseTransition getRootTransition(Step step) {
		Assert.isTrue(step.eContainer() instanceof UseCaseTransition);
		UseCaseTransition root = (UseCaseTransition) step.eContainer();
		
		while (root.eContainer() instanceof Step) {
			Step parent = (Step) root.eContainer();
			Assert.isTrue(parent.eContainer() instanceof UseCaseTransition);
			root = (UseCaseTransition) parent.eContainer();
		}
		Assert.isTrue(root.eContainer() instanceof CounterExample);
		
		return root;
	}
	
	public void handleEditorUCStepSelected(List<Step> selection) {
		GraphItem[] items = new GraphItem[selection.size()];
		int i = 0;
		for (Step step: selection) {
			UseCaseTransition t = getRootTransition(step);
			GraphConnection con = connectionTracker.get(t).get(step.getUcStep());
			items[i++] = con;
		}
		try {
			viewer.getGraphControl().setSelection(items);
		} catch (org.eclipse.swt.SWTException e) {
			// Just ignore it...
		}
	}
	
	private void generateGraphNodes(StateMachine m) {
		figureProvider.setMachine(m);
		for (State s: m.getTransitionalStates()) {
			GraphNode node = new CGraphNode(viewer.getGraphControl(), SWT.NONE,
				figureProvider.getFigure(s));
			node.setData(s);
			state2Node.put(s, node);
		}
		
		State s = m.getInitialState();
		GraphNode node = new CGraphNode(viewer.getGraphControl(), SWT.NONE,
			figureProvider.getFigure(s));
		node.setData(s);
		state2Node.put(s, node);
		
		s = m.getFinalState();
		node = new CGraphNode(viewer.getGraphControl(), SWT.NONE,
			figureProvider.getFigure(s));
		node.setData(s);
		state2Node.put(s, node);
	}
	
	private void processTransition(Transition transition, AbortState abort, UseCaseTransition trans) {
		if (
				(transition.getRelatedStep() != null) &&
				(transition.getRelatedStep().getAction() instanceof UseCaseInclude)
		) {
			UseCaseInclude inc = (UseCaseInclude) transition.getRelatedStep().getAction();
			UseCase uc = inc.getIncludeTarget();
			if (uc != null) {
				StateMachine mach = useCase2Machine.get(uc);
				Assert.isNotNull(mach);
				State st1 = mach.getInitialState();
				State st2 = mach.getFinalState();
				Assert.isNotNull(st1);
				Assert.isNotNull(st2);
				Assert.isNotNull(state2Node.get(st1));
				Assert.isNotNull(state2Node.get(st2));
				GraphConnection c1 =
					new GraphConnection(viewer.getGraphControl(),
						ZestStyles.CONNECTIONS_DIRECTED, state2Node.get(transition.getSourceState()), state2Node.get(st1));
				GraphConnection c2 =
					new GraphConnection(viewer.getGraphControl(),
						ZestStyles.CONNECTIONS_DIRECTED, state2Node.get(st2), state2Node.get(transition.getTargetState()));
				
				Shape shape = (Shape) c1.getConnectionFigure();
				shape.setAntialias(SWT.ON);
				shape.setLineStyle(SWT.LINE_CUSTOM);
				shape.setLineDash(new float[] {7.0f, 5.0f});
				
				shape = (Shape) c2.getConnectionFigure();
				shape.setAntialias(SWT.ON);
				shape.setLineStyle(SWT.LINE_CUSTOM);
				shape.setLineDash(new float[] {7.0f, 5.0f});
			}
		}
		
		GraphConnection con = null;
		
		if (transition.getTargetState() == abort) {
			GraphNode node = new CGraphNode(viewer.getGraphControl(), SWT.NONE,
					figureProvider.getFigure(abort));
			node.setData(abort);
			trans2Node.put(transition, node);
			con = new GraphConnection(viewer.getGraphControl(), ZestStyles.CONNECTIONS_DIRECTED,
					state2Node.get(transition.getSourceState()), node);
			trans2Edge.put(transition, con);
		} else {
			con = new GraphConnection(viewer.getGraphControl(), ZestStyles.CONNECTIONS_DIRECTED,
						state2Node.get(transition.getSourceState()), state2Node.get(transition.getTargetState()));
			trans2Edge.put(transition, con);
			if (gotoTransitions.contains(transition)) {
				con.setCurveDepth(16);
			}
		}
		
		if (transition.getRelatedStep() != null) {
			IFigure toolTip = new Label();
			
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("Label: " + transition.getRelatedStep().getLabel());
			stringBuffer.append("\n");
			stringBuffer.append("Text: " + WordUtils.wrap( transition.getRelatedStep().getContent(), 40) );
			
			List<StepAnnotation> annots = transition.getRelatedStep().getAnnotations();
			if (!annots.isEmpty()) {
				stringBuffer.append("\n");
				stringBuffer.append("Annots: ");
				int c = 0;
				for (StepAnnotation a : annots) {
					c++;
					stringBuffer.append(a.getAnnotationType().getName() + "_" + a.getId());
					if (c < annots.size()) {
						stringBuffer.append(", ");
					}
				}
			}
			
			Scenario scenario = (Scenario) transition.getRelatedStep().eContainer();
			if (scenario != null) {
				EList<Condition> preconditions = scenario.getPreconditions();
				if (!preconditions.isEmpty()) {
					stringBuffer.append("\n");
					stringBuffer.append("Cond: " + preconditions.get(0).getContent());
				}
			} else {
				Assert.isTrue(!strayConnections.containsKey(con.getSource()));
				strayConnections.put(con.getSource(), con);
			}
			((Label) toolTip).setText(stringBuffer.toString());
			con.setTooltip(toolTip);
			
			connectionTracker.get(trans).put(transition.getRelatedStep(), con);
		}
	}
	
	private void generateGraphEdges(StateMachine m, UseCaseTransition trans) {
		figureProvider.setMachine(m);
		for (Transition t: m.getInitialState().getTransitions()) {
			processTransition(t, m.getAbortState(), trans);
		}
		
		for (TransitionalState tState: m.getTransitionalStates()) {
			for (Transition t: tState.getTransitions()) {
				processTransition(t, m.getAbortState(), trans);
			}
		}
	}
	
	private void selectUCTransition(UseCaseTransition trans, UseCaseTransition ref) {
		if (ref == null) {
			ref = trans;
		}
		GraphNode prevNode = null;
		for (Step step: trans.getSteps()) {
			UseCaseStep ucStep = step.getUcStep();
			GraphConnection con = connectionTracker.get(ref).get(ucStep);
			if (con == null) {
				continue;
			}
			if ((prevNode != null) && (con.getSource() != prevNode)) {
				GraphConnection c = strayConnections.get(prevNode);
				c.setLineColor(ColorConstants.red);
				while (c.getDestination() != con.getSource()) {
					c = strayConnections.get(c);
					c.setLineColor(ColorConstants.red);
					c.setHighlightColor(ColorConstants.red);
				}
			}
			con.setLineColor(ColorConstants.red);
			con.setHighlightColor(ColorConstants.red);
			
			List<StepAnnotation> annots = ucStep.getAnnotations();
			if (!annots.isEmpty()) {
				StringBuffer annotationLabel = new StringBuffer();
				int c = 0;
				for (StepAnnotation a : annots) {
					c++;
					annotationLabel.append(a.getAnnotationType().getName() + "_" + a.getId());
					if (c < annots.size()) {
						annotationLabel.append(", ");
					}
				}
				con.setText(annotationLabel.toString());
			}
			
			if (step.getUseCaseTransition() != null) {
				selectUCTransition(step.getUseCaseTransition(), ref);
			}
			prevNode = con.getDestination();
		}
	}
	
	private void selectCounterExamplePath() {
		for (UseCaseTransition trans: transitions) {
			selectUCTransition(trans, null);
		}
	}
	
	/**
	 * @param graphParent
	 *            Where to paint the graph
	 * @param machine
	 *            Which StateMachine to show
	 */
	private void createLtsGraph(final Composite graphParent) {
		List<LTSGraphBox> ltsParams = new ArrayList<LTSGraphBox>();		
		for (UseCaseTransition trans: transitions) {
			LTSGraphBox box = new LTSGraphBox();
			
			regenerateLTS(trans);
			
			// Firstly we need to generate all nodes
			generateGraphNodes(machine);
			for (StateMachine m: includedMachines) {
				generateGraphNodes(m);
			}
			
			// Only after nodes are ready we can create edges.
			connectionTracker.put(trans, new HashMap<UseCaseStep, GraphConnection>());
			generateGraphEdges(machine, trans);
			for (StateMachine m: includedMachines) {
				generateGraphEdges(m, trans);
			}
			
			box.setTrans2Node(trans2Node);
			box.setUcStep2TransLayout(ucStep2TransLayout);
			box.setUcStep2TransOriginal(ucStep2TransOriginal);
			box.setUseCase2Machine(useCase2Machine);
			box.setMachine2UseCase(machine2UseCase);
			box.setIncludedMachines(includedMachines);
			box.getIncludedMachines().add(0, machine);
			
			ltsParams.add(box);
			
			trans2Node.clear();
			ucStep2TransLayout.clear();
			ucStep2TransOriginal.clear();
			useCase2Machine.clear();
			machine2UseCase.clear();
			includedMachines.clear();			
		}
		selectCounterExamplePath();
				
		// layout algorithm
		LayoutAlgorithm ltsLayout = new LTSLayoutAlgorithm(ltsParams);
		
		viewer.setLayoutAlgorithm(ltsLayout, false);
		viewer.getGraphControl().setNodeStyle(ZestStyles.NODES_NO_ANIMATION);
		viewer.applyLayout();
	
		viewer.getGraphControl().addMouseListener(new MouseAdapter() {
			
			public void mouseDown(MouseEvent e) {
				if (e.button == 1) {
					// viewer.getGraphControl().setSelection(null);
				}
			}
			
		});
	}
	
	@Override
	public void createControl(Composite parent) {
		graphParent = parent;
		viewer = new GraphViewer(graphParent, SWT.NONE);
		
		viewer.getGraphControl().getLightweightSystem().setEventDispatcher(
				new SWTEventDispatcher() {
					@Override
					public void dispatchMouseMoved(org.eclipse.swt.events.MouseEvent me) {
						// Do nothing. This disables nodes replacing with mouse.
					}
				}
		);
		
		createLtsGraph(parent);
	}

	@Override
	public void dispose() {
	}

	@Override
	public Control getControl() {
		return viewer.getControl();
	}

	@Override
	public void setActionBars(IActionBars actionBars) {
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
	}

	@Override
	public ISelection getSelection() {
		return null;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
	}

	@Override
	public void setSelection(ISelection selection) {
	}
}