/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package reprotool.txtspec.ui.syntaxcoloring;

import java.util.Iterator;

import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import reprotool.txtspec.txtSpec.ModelElementBlock;

public class SemanticHighlightingCalculator implements ISemanticHighlightingCalculator {
	
	public void provideHighlightingFor(final XtextResource resource, IHighlightedPositionAcceptor acceptor) {
		if (resource == null)
			return;
		
		Iterator<INode> allNodes = resource.getParseResult().getRootNode().getAsTreeIterable().iterator();
		while(allNodes.hasNext()) {
			INode node = allNodes.next();
			if (node.getGrammarElement() instanceof ModelElementBlock) {
				highlightNode(node, SemanticHighlightingConfiguration.CROSS_REF, acceptor);
			}
		}
	}
	
	private void highlightNode(INode node, String id, IHighlightedPositionAcceptor acceptor) {
		if (node == null)
			return;
		if (node instanceof ILeafNode) {
			acceptor.addPosition(node.getOffset(), node.getLength(), id);
		} else {
			for(ILeafNode leaf: node.getLeafNodes()) {
				if (!leaf.isHidden()) {
					acceptor.addPosition(leaf.getOffset(), leaf.getLength(), id);
				}
			}
		}
	}

}