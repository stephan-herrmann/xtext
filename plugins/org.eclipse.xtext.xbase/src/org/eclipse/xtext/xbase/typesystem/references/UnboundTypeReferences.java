/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.xbase.typesystem.references;

import java.util.Collection;
import java.util.Map;

import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeParameter;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * TODO JavaDocs
 */
public final class UnboundTypeReferences {
	
	private UnboundTypeReferences() {
		throw new UnsupportedOperationException();
	}
	
	public static JvmTypeParameter findMappedParameter(JvmTypeParameter parameter,
			Map<JvmTypeParameter, LightweightMergedBoundTypeArgument> mapping, Collection<JvmTypeParameter> visited) {
		for(Map.Entry<JvmTypeParameter, LightweightMergedBoundTypeArgument> entry: mapping.entrySet()) {
			LightweightTypeReference reference = entry.getValue().getTypeReference();
			JvmType type = null;
			if (reference instanceof UnboundTypeReference) {
				type = ((UnboundTypeReference) reference).getTypeParameter();
			} else {
				type = reference.getType();
			}
			if (parameter == type) {
				if (visited.add(entry.getKey()))
					return entry.getKey();
				return null;
			}
		}
		return null;
	}
	
//	public static boolean isUnboundAndEqual(UnboundTypeReference unboundReference, LightweightTypeReference reference) {
//		if (reference instanceof UnboundTypeReference) {
//			UnboundTypeReference other = (UnboundTypeReference) reference;
//			if (!other.isResolved()) {
//				if (unboundReference.getHandle().equals(other.getHandle())) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
//	public static boolean isUnboundTypeParameter(XComputedTypeReference reference) {
//		if (reference.eIsSet(TypesPackage.Literals.JVM_SPECIALIZED_TYPE_REFERENCE__EQUIVALENT))
//			return false;
//		IJvmTypeReferenceProvider typeProvider = reference.getTypeProvider();
//		if (typeProvider instanceof UnboundTypeParameter) {
//			boolean result = !((UnboundTypeParameter) typeProvider).isComputed();
//			return result;
//		}
//		return false;
//	}
	
//	public static  JvmTypeReference asWrapperType(JvmTypeReference potentialPrimitive, Primitives primitives) {
//		if (potentialPrimitive instanceof XComputedTypeReference) {
//			if (isUnboundTypeParameter((XComputedTypeReference) potentialPrimitive)){
//				return potentialPrimitive;
//			}
//		}
//		JvmTypeReference result = primitives.asWrapperTypeIfPrimitive(potentialPrimitive);
//		return result;
//	}

}
