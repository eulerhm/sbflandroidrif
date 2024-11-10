import glob
from pyquery import PyQuery

testMethods = ['net.programmierecke.radiodroid2.tests.UIFavouritesFragmentTest',
				'net.programmierecke.radiodroid2.tests.UIHistoryFragmentTest',
 				'net.programmierecke.radiodroid2.tests.UINotificationTests#playback_ShouldStart_OnResumeFromNotification',
 				'net.programmierecke.radiodroid2.tests.UINotificationTests#playback_ShouldPause_OnPauseFromNotification',
 				'net.programmierecke.radiodroid2.tests.UIPlaybackTest#error_ShouldAppear_OnStreamPayWall',
 				'net.programmierecke.radiodroid2.tests.UIRotationTest#stationsFragment_ShouldNotCrash_WhenScreenRotated',
 				'net.programmierecke.radiodroid2.tests.UIRotationTest#historyFragment_ShouldNotCrash_WhenScreenRotated',
 				'net.programmierecke.radiodroid2.tests.UIRotationTest#favouritesFragment_ShouldNotCrash_WhenScreenRotated',
 				'net.programmierecke.radiodroid2.tests.UIRotationTest#settingsFragment_ShouldNotCrash_WhenScreenRotated',
 				'net.programmierecke.radiodroid2.tests.UIRotationTest#playback_ShouldNotStop_WhenScreenRotated',
 				'net.programmierecke.radiodroid2.tests.UISmallPlayerTest#stationListItem_ShouldStartPlayBack_WhenClicked',
 				'net.programmierecke.radiodroid2.tests.UISmallPlayerTest#playBackState_ShouldBeCorrect_AfterRapidToggling',
 				'net.programmierecke.radiodroid2.tests.UIStationListsTest',
 				'net.programmierecke.radiodroid2.tests.UIStationTagsTest#stationTag_ShouldSearchStationsByTag_WhenClicked']


for tm in testMethods:
    reportFile = glob.glob(f"/media/euler/SSD_2/workspace-SBES-ExtendedPaper/INJECTED FAULT ANALYSIS/RadioDroid/RadioDroid-Defeito-4/testReports-SeparatelyTests/report-{tm}/reports/androidTests/connected/flavors/freeDebugAndroidTest/index.html")
    
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
