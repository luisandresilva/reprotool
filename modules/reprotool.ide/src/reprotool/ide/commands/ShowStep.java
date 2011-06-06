package reprotool.ide.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import reprotool.ide.editors.UseCaseEditor;
import reprotool.ide.views.ParsedTreeView;
import reprotool.ide.views.UcStepView;
import reprotool.ling.LingTools;
import reprotool.model.usecase.UseCaseStep;

public class ShowStep extends AbstractHandler
{
	/*
	 * LingTools is just a temporary stupid implementation of a sentence parsing
	 * and tree building.
	 * When the real linguistic tools are added to this project, they will be used. 
	 */
	private static final LingTools lingTools = new LingTools();
	
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		UcStepView view = (UcStepView) page.findView(UcStepView.ID);
		
		UseCaseEditor editor = UseCaseEditor.getActiveUseCaseEditor();
		if (view != null && editor != null)
			view.setStep(editor.getFirstSelectedStep());
		
		ParsedTreeView treeView = (ParsedTreeView) page.findView(ParsedTreeView.ID);
		if (treeView != null && editor != null && editor.getFirstSelectedStep() != null) {
			UseCaseStep ucstep = editor.getFirstSelectedStep();
			if (ucstep.getParsedSentence() == null) {
				ucstep.setParsedSentence(lingTools.parseSentence(ucstep.getSentence()));
			}
			treeView.showTree(editor.getFirstSelectedStep().getParsedSentence());
		}
		
		return null;
	}
}