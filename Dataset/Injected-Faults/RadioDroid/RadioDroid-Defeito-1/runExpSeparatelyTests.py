from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


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

startTime = perf_counter()

for tm in testMethods:

		command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
				createFreeDebugCoverageReport"

		#print(command)
		print(check_output(command, shell=True).decode('cp850'))


		print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
		print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

