from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests-Mut", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")

neededMutantId = [6070, 6090, 5599, 5602, 5308, 5315, 4702, 4712, 3701, 3718, \
                    1566, 1575, 1570, 1574, 1580, 9658, 9661, 9662, 9664, 9666]


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

for mut in neededMutantId:
    print(check_output(f"mkdir testReports-SeparatelyTests-Mut/mut_{mut}", shell="True").decode('cp850'))
    for tm in testMethods:
        command1 = f"adb shell dumpsys battery unplug"
        print(check_output(command1, shell=True).decode('cp850'))

        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                -Pandroid.testInstrumentationRunnerArguments.neededMutantId={mut} createProdDebugCoverageReport"

        #print(command)
        print(check_output(command, shell=True).decode('cp850'))

        command2 = f"adb shell dumpsys battery reset"
        print(check_output(command2, shell=True).decode('cp850'))


        print(check_output(f"mkdir testReports-SeparatelyTests-Mut/mut_{mut}/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests-Mut/mut_{mut}/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

