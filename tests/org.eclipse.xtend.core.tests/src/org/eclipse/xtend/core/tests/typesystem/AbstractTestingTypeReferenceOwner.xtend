/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.typesystem

import org.eclipse.xtext.xbase.typesystem.references.TypeReferenceOwner
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtext.xbase.typesystem.references.LightweightBoundTypeArgument
import org.eclipse.xtext.xbase.typesystem.util.CommonTypeComputationServices
import com.google.inject.Inject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.xbase.typesystem.references.OwnedConverter
import org.junit.After
import org.eclipse.xtext.common.types.JvmTypeReference

/**
 * @author Sebastian Zarnekow
 */
class AbstractTestingTypeReferenceOwner extends AbstractXtendTestCase implements TypeReferenceOwner {
	
	@Inject
	CommonTypeComputationServices services
	
	ResourceSet contextResourceSet
	
	OwnedConverter owner = new OwnedConverter(this)
	
	def toLightweightReference(JvmTypeReference reference) {
		return owner.toLightweightReference(reference)
	}

	@After
	def void tearDown() {
		contextResourceSet = null
	}
	
	override protected function(String string) throws Exception {
		val result = super.function(string)
		contextResourceSet = result.eResource.resourceSet
		return result
	}

	override acceptHint(Object handle, LightweightBoundTypeArgument boundTypeArgument) {
		throw new UnsupportedOperationException("Auto-generated function stub")
	}
	
	override getAllHints(Object handle) {
		throw new UnsupportedOperationException("Auto-generated function stub")
	}
	
	override getContextResourceSet() {
		contextResourceSet
	}
	
	override getServices() {
		services
	}
	
}