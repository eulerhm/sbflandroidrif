from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


testMethods = ['fr.free.nrw.commons.ui.PasteSensitiveTextInputEditTextTest#extractFormattingAttributeSet',
               'fr.free.nrw.commons.ui.PasteSensitiveTextInputEditTextTest#setFormattingAllowed',
               'fr.free.nrw.commons.AboutActivityTest#testBuildNumber',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchWebsite',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchFacebook',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchGithub',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchAboutPrivacyPolicy',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchTranslate',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchAboutCredits',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchUserGuide',
               'fr.free.nrw.commons.AboutActivityTest#testLaunchAboutFaq',
               'fr.free.nrw.commons.MainActivityTest#testNearby',
               'fr.free.nrw.commons.MainActivityTest#testExplore',
               'fr.free.nrw.commons.MainActivityTest#testBookmarks',
               'fr.free.nrw.commons.MainActivityTest#testNotifications',
               'fr.free.nrw.commons.ProfileActivityTest#testProfile',
               'fr.free.nrw.commons.ReviewActivityTest#orientationChange',
               'fr.free.nrw.commons.ReviewDaoTest#insert',
               'fr.free.nrw.commons.ReviewDaoTest#isReviewedAlready',
               'fr.free.nrw.commons.SearchActivityTest#exploreActivityTest',
               'fr.free.nrw.commons.SettingsActivityLoggedInTest#testSettings',
               'fr.free.nrw.commons.SettingsActivityTest#useAuthorNameTogglesOn',
               'fr.free.nrw.commons.SettingsActivityTest#orientationChange',
               'fr.free.nrw.commons.UploadActivityTest#orientationChange',
               'fr.free.nrw.commons.WelcomeActivityTest#ifBetaShowsSkipButton',
               'fr.free.nrw.commons.WelcomeActivityTest#testBetaSkipButton',
               'fr.free.nrw.commons.WelcomeActivityTest#swipeBeyondBounds',
               'fr.free.nrw.commons.WelcomeActivityTest#swipeTillLastAndFinish',
               'fr.free.nrw.commons.WelcomeActivityTest#orientationChange']

startTime = perf_counter()

for tm in testMethods:
        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                createProdDebugCoverageReport"
        print(check_output(command, shell=True).decode('cp850'))

        print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

