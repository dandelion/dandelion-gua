package com.github.dandelion.gua.core.bundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dandelion.core.Context;
import com.github.dandelion.core.bundle.loader.AbstractBundleLoader;

/**
 * <p>
 * Bundle loader in charge of loading bundles of the gua component.
 * </p>
 *
 * @author Romain Lespinasse
 * @since 0.11.0
 */
public class GoogleAnalyticsBundleLoader extends AbstractBundleLoader {

   private static final Logger LOG = LoggerFactory.getLogger(GoogleAnalyticsBundleLoader.class);

   public static final String LOADER_NAME = "dandelion-gua";
   public static final String SCANNING_PATH = "dandelion/gua";

   public GoogleAnalyticsBundleLoader(Context context, boolean usedStandalone) {
      super(context, usedStandalone);
   }

   @Override
   public String getName() {
      return LOADER_NAME;
   }

   @Override
   public String getScanningPath() {
      return SCANNING_PATH;
   }

   @Override
   protected Logger getLogger() {
      return LOG;
   }
}
