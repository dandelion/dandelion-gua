package com.github.dandelion.gua.core.bundle;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.github.dandelion.core.bundle.loader.AbstractBundleLoader;
import com.github.dandelion.core.storage.BundleStorageUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Bundle loader used to load user-defined bundles inside the
 * {@code dandelion/gua} folder (and all subfolders) of the classpath.
 *
 *
 * @author Romain Lespinasse
 * @since 0.11.0
 */
public class GoogleAnalyticsBundleLoader extends AbstractBundleLoader {

	private static final Logger LOG = LoggerFactory.getLogger(GoogleAnalyticsBundleLoader.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Logger getLogger() {
		return LOG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "dandelion-gua";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPath() {
		return "dandelion/gua";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<String> getExcludedPaths() {
		return Collections.emptySet();
	}

    @Override
    protected void doCustomBundlePostProcessing(List<BundleStorageUnit> bundles) {
        // Do nothing
    }
}
