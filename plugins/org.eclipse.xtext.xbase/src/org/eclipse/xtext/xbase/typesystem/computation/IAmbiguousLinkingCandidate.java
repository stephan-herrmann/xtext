/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.typesystem.computation;

import java.util.List;

/**
 * A linking candidate may be ambiguous if the passed arguments do
 * match multiple candidates equally well. In those cases, the applied
 * candidate implements this interface.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public interface IAmbiguousLinkingCandidate {

	/**
	 * A readonly representation of all equally good candidates.
	 * The list is never null or empty.
	 * 
	 * @return all the candidates.
	 */
	List<? extends ILinkingCandidate> getAlternatives();
	
}
