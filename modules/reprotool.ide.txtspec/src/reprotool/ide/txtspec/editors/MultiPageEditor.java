package reprotool.ide.txtspec.editors;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.osgi.framework.Bundle;

/**
 * An example showing how to create a multi-page editor. This example has 3
 * pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor extends MultiPageEditorPart implements
		IResourceChangeListener {

	private TxtSpecEditor TSEditor;
	
	/** The text editor used in page 0. */
	private DOMParsedEditor editor;

	/** The font chosen in no page */
	private Font font;

	/** The text widget used in no page */
	private StyledText text;

	/** the xml editor used in page 1 */
	private XMLEditor xmlEditor;

	/**
	 * Creates a multi-page editor example.
	 */
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Creates page 0 of the multi-page editor, which contains a text editor with DOM.
	 */
	void createPage0() {
		try {
			editor = new DOMParsedEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle() + " (Enriched-Text View)");
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor", null, e.getStatus());
		}
	}

	void createPage1() {
		try {
			xmlEditor = new XMLEditor();
			int index = addPage(xmlEditor, getEditorInput());
			setPageText(index, xmlEditor.getTitle() + " (Xml View)");
			IDocument inputDocument = xmlEditor.getDocumentProvider()
					.getDocument(xmlEditor.getEditorInput());
			String documentContent = inputDocument.get();
			String newDocumentContent = "";
			if (documentContent.isEmpty()) {
				documentContent = "";
				Bundle bundle = Platform.getBundle("reprotool.ide.txtspec");
				URL url = bundle.getResource("schema/default.txtspec.xml");
				try {
					InputStream ir = url.openStream();
					InputStreamReader isr = new InputStreamReader(ir);
					BufferedReader br = new BufferedReader(isr);
					String append;
					if ((documentContent = (br.readLine())) != null)
						;
					while ((append = (br.readLine())) != null)
						documentContent += ("\n" + append);
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage() + " " + e.getCause().toString());
				}
				documentContent = documentContent.trim();
				xmlEditor.setDocument(documentContent);
			}
			newDocumentContent += (new ReadXMLFile()).VPO(documentContent);
			editor.setDocument(newDocumentContent);
			
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating xml text editor", null, e.getStatus());
		}
	}

//	private String changeFormat(String documentContent) 
//	{
//		int i;char ch;
//		String output="";
//		for(ch=documentContent.charAt(i=0);ch!='\0';ch=documentContent.charAt(++i))
//		{
//			if(ch=='<') output+="<\n";
//			else if(ch=='>') output+="\n>";
//			else output+=ch;
//		}
//		System.out.println(output);
//		return output;
//	}

	void createPage2() 
	{
		try 
		{
			TSEditor = new TxtSpecEditor(xmlEditor.getDocumentProvider());
			int index = addPage(TSEditor, getEditorInput());
			setPageText(index, TSEditor.getTitle() + " (TextSpec View)");
			IDocument inputDocument = xmlEditor.getDocumentProvider().getDocument(xmlEditor.getEditorInput());
			String documentContent = inputDocument.get();
			if (documentContent.isEmpty()) 
			{
				documentContent = "";
				Bundle bundle = Platform.getBundle("reprotool.ide.txtspec");
				URL url = bundle.getResource("schema/default.txtspec.xml");
				try 
				{
					InputStream ir = url.openStream();
					InputStreamReader isr = new InputStreamReader(ir);
					BufferedReader br = new BufferedReader(isr);
					String append;
					if ((documentContent = (br.readLine())) != null);
						while ((append = (br.readLine())) != null)
							documentContent += ("\n" + append);
						br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage() + " " + e.getCause().toString());
				}
				documentContent = documentContent.trim();
				TSEditor.setDocument(documentContent);
			}
		} 
		catch (PartInitException e) 
		{
			ErrorDialog.openError(getSite().getShell(),"Error creating TextSpec Editor", null, e.getStatus());
		}
	}
	
	/**
	 * Creates page 3 of the multi-page editor, which allows you to change the
	 * font used in page 2.
	 */
	void createPage3() {

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");

		fontButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}

	/**
	 * Creates page 4 of the multi-page editor, which shows the sorted text.
	 */
	void createPage4() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		text = new StyledText(composite, SWT.H_SCROLL | SWT.V_SCROLL);
		text.setEditable(false);

		int index = addPage(composite);
		setPageText(index, "Preview");
	}

	/**
	 * Creates the pages of the multi-page editor.
	 */
	protected void createPages() {
		createPage0();
		createPage1();
		createPage2();
		//createPage3();
		//createPage4();
	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}

	/**
	 * Saves the multi-page editor's document.
	 */
	public void doSave(IProgressMonitor monitor) {
		getEditor(1).doSave(monitor);
	}

	/**
	 * Saves the multi-page editor's document as another file. Also updates the
	 * text for page 0's tab, and updates this multi-page editor's input to
	 * correspond to the nested editor's.
	 */
	public void doSaveAs() {
		IEditorPart editor = getEditor(1);
		editor.doSaveAs();
		setPageText(1, editor.getTitle());
		setInput(editor.getEditorInput());
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}

	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException(
					"Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}

	/*
	 * (non-Javadoc) Method declared on IEditorPart.
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			IDocument inputDocument = xmlEditor.getDocumentProvider()
					.getDocument(xmlEditor.getEditorInput());
			String documentContent = inputDocument.get();
			if (documentContent.isEmpty()) {
				documentContent = "";
				Bundle bundle = Platform.getBundle("reprotool.ide.txtspec");
				URL url = bundle.getResource("schema/default.txtspec.xml");
				try {
					InputStream ir = url.openStream();
					InputStreamReader isr = new InputStreamReader(ir);
					BufferedReader br = new BufferedReader(isr);
					String append;
					if ((documentContent = (br.readLine())) != null);
					while ((append = (br.readLine())) != null)
						documentContent += ("\n" + append);
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage() + " "+ e.getCause().toString());
				}
				documentContent = documentContent.trim();
			}
			TSEditor.setDocument(documentContent);
		}else 
			if (newPageIndex == 1) {
				IDocument inputDocument = xmlEditor.getDocumentProvider()
					.getDocument(xmlEditor.getEditorInput());
				String documentContent = inputDocument.get();
				TSEditor.getMasterDocument();
				if (documentContent.isEmpty()) {
				documentContent = "";
				Bundle bundle = Platform.getBundle("reprotool.ide.txtspec");
				URL url = bundle.getResource("schema/default.txtspec.xml");
				try {
					InputStream ir = url.openStream();
					InputStreamReader isr = new InputStreamReader(ir);
					BufferedReader br = new BufferedReader(isr);
					String append;
					if ((documentContent = (br.readLine())) != null);
					while ((append = (br.readLine())) != null)
						documentContent += ("\n" + append);
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage() + " "+ e.getCause().toString());
				}
				documentContent = documentContent.trim();
			}
			xmlEditor.setDocument(documentContent);			
		} else if (newPageIndex == 0) {
			IDocument inputDocument = xmlEditor.getDocumentProvider()
					.getDocument(xmlEditor.getEditorInput());
			String documentContent = inputDocument.get();
			String newDocumentContent = "";
			if (documentContent.isEmpty()) {
				documentContent = "";
				Bundle bundle = Platform.getBundle("reprotool.ide.txtspec");
				URL url = bundle.getResource("schema/default.txtspec.xml");
				try {
					InputStream ir = url.openStream();
					InputStreamReader isr = new InputStreamReader(ir);
					BufferedReader br = new BufferedReader(isr);
					String append;
					if ((documentContent = (br.readLine())) != null)
						;
					while ((append = (br.readLine())) != null)
						documentContent += ("\n" + append);
					br.close();
				} catch (Exception e) {
					System.err.println(e.getMessage() + " " + e.getCause().toString());
				}
				documentContent = documentContent.trim();
			}
			newDocumentContent = (new ReadXMLFile()).VPO(documentContent);
			editor.setDocument(newDocumentContent);
		}
	}

	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow()
							.getPages();
					for (int i = 0; i < pages.length; i++) {
						if (((FileEditorInput) editor.getEditorInput())
								.getFile().getProject()
								.equals(event.getResource())) {
							IEditorPart editorPart = pages[i].findEditor(editor
									.getEditorInput());
							pages[i].closeEditor(editorPart, true);
						}
					}
				}
			});
		}
	}

	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			font = new Font(text.getDisplay(), fontData);
			text.setFont(font);
		}
	}

	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText = editor.getDocumentProvider()
				.getDocument(editor.getEditorInput()).get();

		StringTokenizer tokenizer = new StringTokenizer(editorText,
				" \t\n\r\f!@#\u0024%^&*()-_=+`~[]{};:'\",.<>/?|\\");
		ArrayList<String> editorWords = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			editorWords.add(tokenizer.nextToken());
		}

		Collections.sort(editorWords, Collator.getInstance());
		StringWriter displayText = new StringWriter();
		for (int i = 0; i < editorWords.size(); i++) {
			displayText.write(((String) editorWords.get(i)));
			displayText.write(System.getProperty("line.separator"));
		}
		text.setText(displayText.toString());
	}
}
