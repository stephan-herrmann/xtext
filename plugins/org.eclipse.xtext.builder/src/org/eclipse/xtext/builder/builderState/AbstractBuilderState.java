/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.builderState;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.builder.impl.BuildData;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.AbstractResourceDescriptionChangeEventSource;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionDelta;
import org.eclipse.xtext.resource.impl.ResourceDescriptionChangeEvent;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Knut Wannheden
 */
public abstract class AbstractBuilderState extends AbstractResourceDescriptionChangeEventSource implements
		IBuilderState {

	private volatile Map<URI, IResourceDescription> resourceDescriptionMap = Collections.emptyMap();

	@Inject
	private IMarkerUpdater markerUpdater;

	@Inject
	private PersistedStateProvider persister;

	private boolean isLoaded = false;

	public synchronized void load() {
		resourceDescriptionMap = Maps.uniqueIndex(persister.load(), new Function<IResourceDescription, URI>() {
			public URI apply(IResourceDescription from) {
				return from.getURI();
			}
		});
		isLoaded = true;
	}

	protected void ensureLoaded() {
		if (!isLoaded)
			load();
	}

	protected Set<URI> ensureNotNull(Set<URI> uris) {
		return uris != null ? uris : Collections.<URI> emptySet();
	}

	protected void setResourceDescriptionsMap(Map<URI, IResourceDescription> newMap) {
		resourceDescriptionMap = Collections.unmodifiableMap(newMap);
	}

	protected void updateMarkers(ResourceSet resourceSet, ImmutableList<IResourceDescription.Delta> deltas,
			IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 1);
		markerUpdater.updateMarker(resourceSet, deltas, progress.newChild(1));
	}

	protected Map<URI, IResourceDescription> getCopiedResourceDescriptionsMap() {
		return Maps.newHashMap(resourceDescriptionMap);
	}

	public Iterable<IResourceDescription> getAllResourceDescriptions() {
		ensureLoaded();
		return resourceDescriptionMap.values();
	}

	public IResourceDescription getResourceDescription(URI uri) {
		ensureLoaded();
		return resourceDescriptionMap.get(uri);
	}

	public void setPersister(PersistedStateProvider persister) {
		this.persister = persister;
	}

	public synchronized ImmutableList<IResourceDescription.Delta> update(BuildData buildData, IProgressMonitor monitor) {
		ensureLoaded();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.AbstractBuilderState_0, 1);
		subMonitor.subTask(Messages.AbstractBuilderState_0);
		if (buildData.isEmpty())
			return ImmutableList.of();
		if (monitor.isCanceled())
			throw new OperationCanceledException();

		final Map<URI, IResourceDescription> newMap = getCopiedResourceDescriptionsMap();
		final Collection<IResourceDescription.Delta> result = doUpdate(buildData, newMap, subMonitor.newChild(1));

		if (monitor.isCanceled())
			throw new OperationCanceledException();
		final ResourceDescriptionChangeEvent event = new ResourceDescriptionChangeEvent(result, this);
		// update the reference
		setResourceDescriptionsMap(newMap);
		notifyListeners(event);
		return event.getDeltas();
	}

	protected abstract Collection<IResourceDescription.Delta> doUpdate(BuildData buildData, Map<URI, IResourceDescription> newMap, IProgressMonitor monitor);

	public synchronized ImmutableList<IResourceDescription.Delta> clean(Set<URI> toBeRemoved, IProgressMonitor monitor) {
		ensureLoaded();
		toBeRemoved = ensureNotNull(toBeRemoved);

		SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.AbstractBuilderState_0, 2);
		subMonitor.subTask(Messages.AbstractBuilderState_0);
		if (toBeRemoved.isEmpty())
			return ImmutableList.of();
		if (monitor.isCanceled())
			throw new OperationCanceledException();
		Collection<IResourceDescription.Delta> deltas = doClean(toBeRemoved, subMonitor.newChild(1));

		final Map<URI, IResourceDescription> newMap = getCopiedResourceDescriptionsMap();
		if (monitor.isCanceled())
			throw new OperationCanceledException();
		for (IResourceDescription.Delta delta : deltas) {
			newMap.remove(delta.getOld().getURI());
		}
		ResourceDescriptionChangeEvent event = new ResourceDescriptionChangeEvent(deltas, this);
		if (monitor.isCanceled())
			throw new OperationCanceledException();
		updateMarkers(null, event.getDeltas(), subMonitor.newChild(1));
		// update the reference
		setResourceDescriptionsMap(newMap);
		notifyListeners(event);
		return event.getDeltas();
	}

	protected Collection<IResourceDescription.Delta> doClean(Set<URI> toBeRemoved, IProgressMonitor monitor) {
        SubMonitor subMonitor = SubMonitor.convert(monitor, Messages.AbstractBuilderState_2, toBeRemoved.size());
        subMonitor.subTask(Messages.AbstractBuilderState_2);
        Set<URI> toBeDeletedAsSet = Sets.newHashSet(toBeRemoved);
        Map<URI, IResourceDescription.Delta> result = Maps.newHashMap();
        for (URI toDelete : toBeDeletedAsSet) {
            IResourceDescription resourceDescription = getResourceDescription(toDelete);
            if (resourceDescription != null) {
                result.put(toDelete, new DefaultResourceDescriptionDelta(resourceDescription, null));
            }
            subMonitor.worked(1);
        }
        return result.values();
	}

}
