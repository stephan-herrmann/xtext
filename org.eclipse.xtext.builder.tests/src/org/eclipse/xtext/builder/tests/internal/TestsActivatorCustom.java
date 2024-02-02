/*******************************************************************************
 * Copyright (c) 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.builder.tests.internal;

import org.apache.log4j.Logger;
import org.eclipse.xtext.Constants;
import org.eclipse.xtext.builder.tests.BuilderTestLanguageRuntimeModule;
import org.eclipse.xtext.ui.shared.JdtHelper;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.ui.util.IJdtHelper;
import org.eclipse.xtext.util.Modules2;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.name.Names;

/**
 * @author Lorenzo Bettini - Initial contribution and API
 */
public class TestsActivatorCustom extends TestsActivator {

	public static final String ORG_ECLIPSE_XTEXT_BUILDER_TESTS_BUILDERTESTLANGUAGE_GH2920 = ORG_ECLIPSE_XTEXT_BUILDER_TESTS_BUILDERTESTLANGUAGE + "GH2920";

	private static final Logger logger = Logger.getLogger(TestsActivatorCustom.class);

	@Override
	protected Injector createInjector(String language) {
		if (!ORG_ECLIPSE_XTEXT_BUILDER_TESTS_BUILDERTESTLANGUAGE_GH2920.equals(language))
			return super.createInjector(language);
		try {
			com.google.inject.Module runtimeModule = new BuilderTestLanguageRuntimeModule() {
				@Override
				public void configureFileExtensions(Binder binder) {
					if (properties == null || properties.getProperty(Constants.FILE_EXTENSIONS) == null)
						binder.bind(String.class).annotatedWith(Names.named(Constants.FILE_EXTENSIONS)).toInstance("buildertestlanguageGH2920");
				}
			};
			com.google.inject.Module sharedStateModule = new SharedStateModule() {
				@Override
				public Provider<IJdtHelper> provideJdtHelper() {
					return new Provider<>() {
						@Override
						public IJdtHelper get() {
							return new JdtHelper() {
								@Override
								protected boolean computeJavaCoreAvailable() {
									return false;
								}
							};
						}
					};
				}
			};
			com.google.inject.Module uiModule = getUiModule(ORG_ECLIPSE_XTEXT_BUILDER_TESTS_BUILDERTESTLANGUAGE);
			com.google.inject.Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}

}
