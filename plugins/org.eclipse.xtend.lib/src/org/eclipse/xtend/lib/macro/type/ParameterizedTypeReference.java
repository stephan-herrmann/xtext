/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.lib.macro.type;

import java.util.List;

import org.eclipse.xtend.lib.macro.declaration.Type;

public interface ParameterizedTypeReference extends TypeReference {
	
	Type getType();

	List<TypeReference> getActualTypeArguments();
}