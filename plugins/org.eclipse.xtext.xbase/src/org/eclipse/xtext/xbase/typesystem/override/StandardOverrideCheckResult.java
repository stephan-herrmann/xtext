/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.typesystem.override;

import java.util.EnumSet;

import org.eclipse.xtext.common.types.JvmOperation;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class StandardOverrideCheckResult extends AbstractOverrideCheckResult {
	
	private EnumSet<OverrideCheckDetails> details;
	private EnumSet<OverrideCheckDetails> problems;

	public StandardOverrideCheckResult(IResolvedOperation thisOperation, JvmOperation givenOperation, OverrideCheckDetails detail, OverrideCheckDetails... details) {
		this(thisOperation, givenOperation, EnumSet.of(detail, details));
	}
	
	public StandardOverrideCheckResult(IResolvedOperation thisOperation, JvmOperation givenOperation, EnumSet<OverrideCheckDetails> details) {
		super(thisOperation, givenOperation);
		this.details = EnumSet.copyOf(details);
		this.problems = EnumSet.copyOf(details);
		this.problems.removeAll(overridingIfAnyOf);
		this.problems.remove(OverrideCheckDetails.COVARIANT_RETURN);
	}
	
	public boolean isOverridingOrImplementing() {
		return problems.isEmpty();
	}

	public boolean hasProblems() {
		return !problems.isEmpty();
	}

	public EnumSet<OverrideCheckDetails> getDetails() {
		return details;
	}

}
