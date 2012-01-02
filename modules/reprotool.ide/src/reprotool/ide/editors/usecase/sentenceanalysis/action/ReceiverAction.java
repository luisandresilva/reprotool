package reprotool.ide.editors.usecase.sentenceanalysis.action;

import org.eclipse.jface.action.IAction;

import reprotool.ide.Activator;
import reprotool.ide.editors.usecase.MarkingService;

public class ReceiverAction extends AbstractMarkingAction {

	public ReceiverAction(String text, IAction refreshEditorAction) {
		super(text, refreshEditorAction);
		imageDescriptor = Activator.getImageDescriptor("icons/receiver-sq-16x16.png");
	}

	@Override
	public void run() {
		MarkingService.getInstance().markReceiver();
		refreshEditorAction.run();
	}
}
