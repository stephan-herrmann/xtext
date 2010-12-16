/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import static org.eclipse.xtext.ui.junit.util.IResourcesSetupUtil.*;
import static org.eclipse.xtext.ui.junit.util.JavaProjectSetupUtil.*;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.util.StringInputStream;

import com.google.common.collect.Lists;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class BuilderParticipantTest extends AbstractParticipatingBuilderTest {

	private Collection<IBuildContext> contexts;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		contexts = Lists.newArrayList();
	}

	@Override
	public void reset() {
		contexts.clear();
		super.reset();
	}
	
	@Override
	public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
		super.build(context, monitor);
		if (isLogging()) {
			contexts.add(context);
		}
	}

	protected void validateContext(IBuildContext context) {
		IProject project = context.getBuiltProject();
		String prefix = "platform:/resource/" + project.getName();
		for(IResourceDescription.Delta delta: context.getDeltas()) {
			assertTrue("Exptected uri '" + delta.getUri() + "' starts with '" + prefix + "'", 
					delta.getUri().toString().startsWith(prefix));
		}
	}
	
	public void testParticipantInvoked() throws Exception {
		startLogging();
		IJavaProject project = createJavaProject("foo");
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		IFolder folder = project.getProject().getFolder("src");
		IFile file = folder.getFile("Foo" + F_EXT);
		file.create(new StringInputStream("object Foo"), true, monitor());
		waitForAutoBuild();
		assertTrue(0 < getInvocationCount());
		validateContexts();
		reset();
		
		file.delete(true, monitor());
		waitForAutoBuild();
		assertEquals(1, getInvocationCount());
		assertSame(BuildType.INCREMENTAL, getContext().getBuildType());
		validateContexts();
		reset();
		
		project.getProject().build(IncrementalProjectBuilder.CLEAN_BUILD, monitor());
		assertSame(BuildType.CLEAN, getContext().getBuildType());
		waitForAutoBuild();
		assertEquals(2, getInvocationCount());
		assertSame(BuildType.FULL, getContext().getBuildType());
		validateContexts();
		reset();
		
		project.getProject().build(IncrementalProjectBuilder.FULL_BUILD, monitor());
		assertSame(BuildType.FULL, getContext().getBuildType());
		validateContexts();
		reset();
	}
	
	private void validateContexts() {
		for(IBuildContext context: contexts) {
			validateContext(context);
		}
	}

	public void testTwoFilesInTwoReferencedProjects() throws Exception {
		createTwoReferencedProjects();
		IFile firstFile = createFile("first/src/first"+F_EXT, "object First ");
		createFile("second/src/second"+F_EXT, "object Second references First");
		waitForAutoBuild();
		startLogging();
		firstFile.setContents(new StringInputStream("object Modified "), true, true, monitor());
		waitForAutoBuild();
		validateContexts();
		assertEquals(2, getInvocationCount());
	}

	protected void createTwoReferencedProjects() throws CoreException {
		IJavaProject firstProject = createJavaProjectWithRootSrc("first");
		IJavaProject secondProject = createJavaProjectWithRootSrc("second");
		addProjectReference(secondProject, firstProject);
	}
	
	protected IJavaProject createJavaProjectWithRootSrc(String string) throws CoreException {
		IJavaProject project = createJavaProject(string);
		addNature(project.getProject(), XtextProjectHelper.NATURE_ID);
		return project;
	}
	
}
