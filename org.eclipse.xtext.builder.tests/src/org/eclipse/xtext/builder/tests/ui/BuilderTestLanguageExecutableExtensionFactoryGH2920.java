/*******************************************************************************
 * Copyright (c) 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.tests.ui;

import org.eclipse.xtext.builder.tests.internal.TestsActivator;
import org.eclipse.xtext.builder.tests.internal.TestsActivatorCustom;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.google.inject.Injector;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class BuilderTestLanguageExecutableExtensionFactoryGH2920 extends BuilderTestLanguageExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(TestsActivatorCustom.class);
	}

	@Override
	protected Injector getInjector() {
		TestsActivator activator = TestsActivator.getInstance();
		return activator != null ? activator.getInjector(TestsActivatorCustom.ORG_ECLIPSE_XTEXT_BUILDER_TESTS_BUILDERTESTLANGUAGE_GH2920) : null;
	}
}
