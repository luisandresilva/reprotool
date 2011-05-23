package reprotool.ide.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import reprotool.ide.editors.UseCaseEditor;
import reprotool.model.usecase.Scenario;
import reprotool.model.usecase.UseCaseStep;

public class MoveStep extends AbstractHandler {
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		UseCaseEditor editor = UseCaseEditor.getActiveUseCaseEditor();
		if (editor == null)
			return null;
		UseCaseStep step = editor.getFirstSelectedStep();
		if (step == null)
			return null;
		
		Scenario parent = (Scenario)step.eContainer();
		
		int pos = parent.getSteps().indexOf(step);
		if (event.getCommand().getId().equals("commands.stepUp") && pos > 0)
			parent.getSteps().move(pos-1, pos);
		else if (event.getCommand().getId().equals("commands.stepDown") && pos < parent.getSteps().size()-1)
			parent.getSteps().move(pos+1, pos);
		
		editor.refresh();
		editor.setSelection(step);
		return null;
	}

}
