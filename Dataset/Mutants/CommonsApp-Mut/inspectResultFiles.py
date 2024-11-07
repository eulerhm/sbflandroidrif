import glob
from pyquery import PyQuery

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


for tm in testMethods:
    reportFile = glob.glob(f"/media/euler/SSD_2_Linux/workspace-SBES-ExtendedPaper/CommonsApp-Mut/testReports-SeparatelyTests-Mut/mut_6090/report-{tm}/reports/androidTests/connected/flavors/prod/index.html")
    
    #reportFile = glob.glob('**/index.html',recursive=True)
    if reportFile:
        with open(reportFile[0]) as file_object:
            html = file_object.read()
            #print(html)
            doc = PyQuery(html)
            tagtext = doc("div").text()
            #chunks = tagtext.split('\n')
            chunks = tagtext.split()
            #print(chunks)/media/euler/SSD_2_Linux/workspace-SBES-ExtendedPaper/CommonsApp-Mut/testReports-SeparatelyTests-Mut/mut_6070
            if (int(chunks[4]) > 0):
                print("TOTAL FAILURES IN TEST REPORT " + tm + ": " + chunks[4])
            else:
                print("NO FAILURE!")
