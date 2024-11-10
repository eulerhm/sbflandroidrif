import glob
from pyquery import PyQuery

testMethods = ['org.owntracks.android.e2e.ContactActivityTests#testClickingOnContactLoadsContactOnMap',
               'org.owntracks.android.ui.map.MapActivityTests#statusActivityCanBeLaunchedFromMapActivityDrawer',
               'org.owntracks.android.ui.map.MapActivityTests#preferencesActivityCanBeLaunchedFromMapActivityDrawer',
               'org.owntracks.android.ui.map.MapActivityTests#modeButtonOnMapActivityCyclesThroughModes',
               'org.owntracks.android.ui.map.MapActivityTests#welcomeActivityShouldNotRunWhenFirstStartPreferencesSet',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityCanLoadConfigFromOwntracksInlineConfigURL',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityShowsErrorWhenLoadingFromInlineConfigURLContaninigInvalidJSON',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityShowsErrorWhenLoadingFromInlineConfigURLContaninigInvalidBase64',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityCanLoadConfigFromOwntracksRemoteURL',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityShowsErrorTryingToLoadNotFoundRemoteUrl',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityCanLoadConfigFromFileURL',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityCanLoadConfigFromContentURL',
               'org.owntracks.android.ui.LoadActivityTests#loadActivityErrorsCorrectlyFromInvalidContentURL',
               'org.owntracks.android.ui.LogViewerActivityTests#logViewerActivityShowsTitle',
               'org.owntracks.android.ui.LogViewerActivityTests#logViewerActivityExportFiresIntent',
               'org.owntracks.android.ui.PreferencesActivityTests#initialViewShowsTopLevelMenu',
               'org.owntracks.android.ui.PreferencesActivityTests#documentationLinkOpensSite',
               'org.owntracks.android.ui.PreferencesActivityTests#twitterLinkOpensSite',
               'org.owntracks.android.ui.PreferencesActivityTests#sourceLinkOpensSite',
               'org.owntracks.android.ui.PreferencesActivityTests#librariesLinkListsLibraries',
               'org.owntracks.android.ui.PreferencesActivityTests#configurationManagementCanEditASetType',
               'org.owntracks.android.ui.PreferencesActivityTests#configurationManagementCanEditAStringType',
               'org.owntracks.android.ui.PreferencesActivityTests#configurationManagementCanEditABooleanType',
               'org.owntracks.android.ui.PreferencesActivityTests#settingSimpleHTTPConfigSettingsCanBeExported',
               'org.owntracks.android.ui.StatusActivityTests#statusActivityShowsEndpointState',
               'org.owntracks.android.ui.StatusActivityTests#statusActivityShowsLogsLauncher']

for tm in testMethods:
    reportFile = glob.glob(f"/media/euler/SSD_2/workspace-SBES-ExtendedPaper/INJECTED FAULT ANALYSIS/OwnTracks/OwnTracks-Defeito-2/project/testReports-SeparatelyTests/report-{tm}/reports/androidTests/connected/flavors/debugAndroidTest/index.html")
    
    #reportFile = glob.glob('**/index.html',recursive=True)
    if reportFile:
        with open(reportFile[0]) as file_object:
            html = file_object.read()
            #print(html)
            doc = PyQuery(html)
            tagtext = doc("div").text()
            #chunks = tagtext.split('\n')
            chunks = tagtext.split()
            #print(chunks)
            if (int(chunks[4]) > 0):
                print("TOTAL FAILURES IN TEST REPORT " + tm + ": " + chunks[4])
            else:
                print("NO FAILURE!")
