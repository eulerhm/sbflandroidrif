from subprocess import check_output
import timing 
from time import perf_counter

#print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")

testMethods = ['org.owntracks.android.ui.map.MapActivityTests#welcomeActivityShouldNotRunWhenFirstStartPreferencesSet',
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

startTime = perf_counter()


for tm in testMethods:
        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                 createDebugCoverageReport"

        #print(command)
        print(check_output(command, shell=True).decode('cp850'))

        print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

