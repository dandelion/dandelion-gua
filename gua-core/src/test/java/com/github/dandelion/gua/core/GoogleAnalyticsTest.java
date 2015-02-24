package com.github.dandelion.gua.core;

import com.github.dandelion.core.Context;
import com.github.dandelion.core.asset.Asset;
import com.github.dandelion.core.asset.AssetDomPosition;
import com.github.dandelion.core.asset.AssetMapper;
import com.github.dandelion.core.asset.AssetType;
import com.github.dandelion.core.asset.locator.impl.ApiLocator;
import com.github.dandelion.core.storage.AssetStorageUnit;
import com.github.dandelion.core.web.WebConstants;
import com.github.dandelion.gua.core.field.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.*;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

public class GoogleAnalyticsTest {

    private MockHttpServletRequest request;
    private Context context;
    private ApiLocator locator = new ApiLocator();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        request = new MockHttpServletRequest();
        request.setContextPath("/context");
        request.setRequestURI("/context/page.html");
        context = new Context(new MockFilterConfig());
        // TODO new version of "context.getConfiguration().setToolAssetPrettyPrintingEnabled(true);"
        request.setAttribute(WebConstants.DANDELION_CONTEXT_ATTRIBUTE, context);
    }

    @Test
    public void generateAnalyticsAsset() {
        GoogleAnalytics.on(request).defaultTracker().goBack()
            // Get (or Create) Default Tracker
            .defaultTracker("UA-XXXX-Y")
                .set(GeneralField.anonymizeIp, "true")
                .set(SessionField.sessionControl, "sessionControl").goBack()
            // Get (or Create) Another Tracker
            .tracker("UA-XXXX-Z", "name-Z")
                // Access to "create" section
                .create()
                    .set(new HashMap<AnalyticsCreateField, String>() {{
                        put(CreateOnlyField.clientId, "clientId");
                        put(CreateOnlyField.sampleRate, "1");
                        put(CreateOnlyField.siteSpeedSampleRate, "1");
                    }})
                    // Create field set
                    .set(CreateOnlyField.clientId, "clientId")
                    .set(CreateOnlyField.sampleRate, "1")
                    .set(CreateOnlyField.siteSpeedSampleRate, "1")
                    .set(CreateOnlyField.alwaysSendReferrer, "true").goBack()
                // Global field set
                .set(GeneralField.anonymizeIp, "true")
                .set(SessionField.sessionControl, "sessionControl")
                // Access to "send" section with name
                .send("nameSend")
                    // Define a "pageview" send command
                    .pageview()
                        .overrideLocation("location")
                        .overridePage("page")
                        // Define a "callback" on this command
                        .callback("callbackFunction", true).goBack().goBack()
                // Access to a previous "send" section from her name
                .send("nameSend")
                    // Define a function "timing" send command [function wrapValueFunction(value) ]
                    .timing("Category", "Var", "wrapValueFunction")
                        .setLabel("Label")
                        .set(HitField.nonInteraction, "true").goBack()
                    .pageview()
                        .overrideTitle("title")
                        // In adding, we can define "any" field
                        .set(ContentInformationField.hostname, "localhost").goBack().goBack()
                .send()
                    // Define a "event" send command
                    .event("Category", "Action")
                        .setLabel("Label")
                        .setValue(4)
                        .set(HitField.nonInteraction, "true")
                        // Ask for a link between the "event" send command and a Html Element Id
                        .onElementId("html_element_id")
                        // Define a another action for the link
                        .onAction("AnotherAction")
                        // Define a "critical callback" on this command
                        .callback("callbackFunction").goBack().goBack()
                // We can define multiple type in one "send" command section
                .send()
                    // Define a "social" send command
                    .social("network", "action", "target")
                        .set(ExceptionsField.exDescription, "error").goBack()
                    // Define a simple "timing" send command
                    .timing("Category", "Var", 4)
                        .setLabel("Label")
                        .set(HitField.nonInteraction, "true").goBack()
                    // We can add some information in a previous send command (because we are in the same "send" section)
                    .social("network", "action", "target")
                        .wrapIntoFunction("functionName").goBack().goBack()
                    .send()
                        // Define a "screenview" send command
                        .screenview()
                            .setScreenName("ScreenName")
                            .setAppName("AppName")
                            .setAppId("AppId")
                            .setAppVersion("AppVersion")
                            .setAppInstallerId("AppInstallerId")
                            .set(HitField.nonInteraction, "true").goBack().goBack()
                    .send()
                        // Define a "screenview" send command with a screen name (API says "Optional" but "Recommended"
                        .screenview("ScreenName").goBack().goBack()
                    .send()
                        // Define a "exception" send command
                        .exception()
                            .setDescription("Description")
                            .setFatal(true)
                            .set(HitField.nonInteraction, "true");

        GoogleAnalytics.on(request).tracker("UA-XXXX-A").get(HitField.nonInteraction);
        GoogleAnalytics.on(request).tracker("UA-XXXX-A").create().get(HitField.nonInteraction);
        GoogleAnalytics.on(request).tracker("UA-XXXX-A").send("previousSendName").pageviewGet(HitField.nonInteraction);
        GoogleAnalytics.on(request).tracker("UA-XXXX-A").send("previousSendName").eventGet(EventTrackingField.eventLabel);

        AssetStorageUnit asset = new AssetStorageUnit();
        asset.setName("dandelion-gua");
        asset.setVersion("latest");
        asset.setType(AssetType.js);
        asset.setDom(AssetDomPosition.head);
        asset.setLocations(singletonMap(locator.getLocationKey(), "dandelion-gua.js"));
        String content = locator.getContent(new AssetMapper(context, request).mapToAsset(asset), request);
        assertThat(content).contains("ga('create', 'UA-XXXX-Y', 'auto');",
                "ga('name-Z.send', 'screenview', {\n    'screenName': 'ScreenName'\n});");
    }

}