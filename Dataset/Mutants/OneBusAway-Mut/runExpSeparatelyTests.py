from subprocess import check_output
import timing 
from time import perf_counter

# print(check_output(f"mkdir testReports-SeparatelyTests-Mut", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")

neededMutantId = [8381, 8382, 8136, 8147, 6347, 6351, 8499, 8502, 7691, 7701, \
                    8281, 8284, 8288, 8357, 8304, 8465, 8474, 8467, 8472, 8469]

testMethods = ['org.onebusaway.android.io.test.AgenciesWithCoverageTest#testRequest',
               'org.onebusaway.android.io.test.AgenciesWithCoverageTest#testBuilder',
               'org.onebusaway.android.io.test.AgencyRequestTest#testKCMAgency',
               'org.onebusaway.android.io.test.AgencyRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testKCMStopRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testKCMStopRequestUsingRegion',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testHARTStopRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testHARTStopRequestUsingRegion',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testKCMStopResponseUsingCustomUrl',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testKCMStopResponseUsingRegion',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testHARTStopResponseUsingCustomUrl',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testHARTStopResponseUsingRegion',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testTotalStopsInTrip',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testNewRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testNewRequestUsingRegion',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testStopSituationPsta',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testStopSituationDart',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testRouteSituationSdmts',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testSituationNoEndTimeSdmts',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testOccupancy',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testCanceledTrips',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testBadArrivalData',
               'org.onebusaway.android.io.test.ArrivalInfoRequestTest#testScheduledArrival',
               'org.onebusaway.android.io.test.CurrentTimeRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.FailTest#test404_1',
               'org.onebusaway.android.io.test.FailTest#test404_2',
               'org.onebusaway.android.io.test.FailTest#testBadJson',
               'org.onebusaway.android.io.test.JacksonTest#testPrimitive',
               'org.onebusaway.android.io.test.JacksonTest#testError',
               'org.onebusaway.android.io.test.JacksonTest#testSerialization',
               'org.onebusaway.android.io.test.JacksonTest#testStopsForLocation',
               'org.onebusaway.android.io.test.RegionsTest#testRequest',
               'org.onebusaway.android.io.test.ReportProblemOpen311Test#testHillsboroughCounty',
               'org.onebusaway.android.io.test.ReportProblemWithStopRequestTest#testPugetSoundReportProblemRequestUsingRegion',
               'org.onebusaway.android.io.test.ReportProblemWithStopRequestTest#testHARTReportProblemRequestUsingRegion',
               'org.onebusaway.android.io.test.ReportProblemWithStopRequestTest#testStagingReportProblemRequestUsingRegionCustomUrl',
               'org.onebusaway.android.io.test.ReportProblemWithTripRequestTest#testPugetSoundReportProblemRequestUsingRegion',
               'org.onebusaway.android.io.test.ReportProblemWithTripRequestTest#testHARTReportProblemRequestUsingRegion',
               'org.onebusaway.android.io.test.ReportProblemWithTripRequestTest#testStagingReportProblemRequestUsingRegionCustomUrl',
               'org.onebusaway.android.io.test.RouteIdsForAgencyRequestTest#testSoundTransit',
               'org.onebusaway.android.io.test.RouteIdsForAgencyRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.RoutesForLocationTest#testDowntownSeattle',
               'org.onebusaway.android.io.test.RoutesForLocationTest#testQuery',
               'org.onebusaway.android.io.test.RoutesForLocationTest#testQueryFail',
               'org.onebusaway.android.io.test.RoutesForLocationTest#testOutOfRange',
               'org.onebusaway.android.io.test.ScheduleForStopTest#testKCMStopRequest',
               'org.onebusaway.android.io.test.ScheduleForStopTest#testKCMStop',
               'org.onebusaway.android.io.test.ScheduleForStopTest#testKCMStopRequestWithDate',
               'org.onebusaway.android.io.test.ScheduleForStopTest#testKCMStopResponseWithDate',
               'org.onebusaway.android.io.test.ShapeRequestTest#testShape',
               'org.onebusaway.android.io.test.ShapeTest#testDecodeLines',
               'org.onebusaway.android.io.test.ShapeTest#testDecodeLevels',
               'org.onebusaway.android.io.test.StopIdsForAgencyRequestTest#testSoundTransit',
               'org.onebusaway.android.io.test.StopIdsForAgencyRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.StopRequestTest#testKCMStop',
               'org.onebusaway.android.io.test.StopRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.StopsForLocationTest#testDowntownSeattle1',
               'org.onebusaway.android.io.test.StopsForLocationTest#testQuery',
               'org.onebusaway.android.io.test.StopsForLocationTest#testQueryFail',
               'org.onebusaway.android.io.test.StopsForLocationTest#testOutOfRange',
               'org.onebusaway.android.io.test.StopsForRouteRequestTest#testKCMRoute',
               'org.onebusaway.android.io.test.StopsForRouteRequestTest#testNoShapes',
               'org.onebusaway.android.io.test.TripDetailsRequest#testKCMTripRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testKCMTripRequestUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testKCMTripResponseUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testKCMTripResponseUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoTripsRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoTripsRequestUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoTripsResponse',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoScheduleRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoScheduleRequestUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoScheduleResponse',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoStatusRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoStatusRequestUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNoStatus',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNewRequestUsingCustomUrl',
               'org.onebusaway.android.io.test.TripDetailsRequest#testNewRequestUsingRegion',
               'org.onebusaway.android.io.test.TripDetailsRequest#testTripResponseOccupancy',
               'org.onebusaway.android.io.test.TripRequestTest#testKCMTripRequest',
               'org.onebusaway.android.io.test.TripRequestTest#testKCMTripResponse',
               'org.onebusaway.android.io.test.TripRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.TripsForLocationTest#test1',
               'org.onebusaway.android.io.test.TripsForLocationTest#testOutOfRange',
               'org.onebusaway.android.io.test.TripsForRouteRequestTest#testHARTTripsForRouteRequest',
               'org.onebusaway.android.io.test.TripsForRouteRequestTest#testHARTTripsForRouteResponse',
               'org.onebusaway.android.io.test.TripsForRouteRequestTest#testNewRequest',
               'org.onebusaway.android.io.test.TripsForRouteRequestTest#testTripsForRouteOccupancyResponse',
               'org.onebusaway.android.io.test.UrlFormatTest#testBasicUrlUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testBasicUrlUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithSpacesUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithSpacesUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPathAndSeparatorUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPathAndSeparatorUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPathNoSeparatorUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPathNoSeparatorUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlNoSeparatorUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlNoSeparatorUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPortUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlWithPortUsingRegion',
               'org.onebusaway.android.io.test.UrlFormatTest#testUrlNoSchemeUsingCustomUrl',
               'org.onebusaway.android.io.test.UrlFormatTest#testHttps',
               'org.onebusaway.android.io.test.UrlFormatTest#testHttpsAndPort',
               'org.onebusaway.android.io.test.UrlFormatTest#testRegionBaseUrls',
               'org.onebusaway.android.io.test.UrlFormatTest#testRegionTwitterUrls',
               'org.onebusaway.android.provider.test.ProviderTest#testStops',
               'org.onebusaway.android.provider.test.ProviderTest#testLimit',
               'org.onebusaway.android.provider.test.RegionsLoaderTest#testLoader',
               'org.onebusaway.android.util.test.LocationUtilsTest#testLocationComparisonByTime',
               'org.onebusaway.android.util.test.LocationUtilsTest#testLocationComparison',
               'org.onebusaway.android.util.test.LocationUtilsTest#testLocationApiV1',
               'org.onebusaway.android.util.test.LocationUtilsTest#testLocationServices',
               'org.onebusaway.android.util.test.LocationUtilsTest#testIsDuplicate',
               'org.onebusaway.android.util.test.MathUtilTest#testOrientationToDirection',
               'org.onebusaway.android.util.test.MyTextUtilsTest#testIsAllCaps',
               'org.onebusaway.android.util.test.ReportUtilTest#testServiceKeywordMatching',
               'org.onebusaway.android.util.test.UIUtilTest#testSerializeRouteDisplayNames',
               'org.onebusaway.android.util.test.UIUtilTest#testDeserializeRouteDisplayNames',
               'org.onebusaway.android.util.test.UIUtilTest#testFormatRouteDisplayNames',
               'org.onebusaway.android.util.test.UIUtilTest#testFormatDisplayText',
               'org.onebusaway.android.util.test.UIUtilTest#testBuildTripOptions',
               'org.onebusaway.android.util.test.UIUtilTest#testCreateStopDetailsDialogText',
               'org.onebusaway.android.util.test.UIUtilTest#testArrivalTimeIndexSearch',
               'org.onebusaway.android.util.test.UIUtilTest#testArrivalInfoLabels',
               'org.onebusaway.android.util.test.UIUtilTest#testMaybeShrinkRouteName',
               'org.onebusaway.android.util.test.UIUtilTest#testGetTransparentColor',
               'org.onebusaway.android.util.test.UIUtilTest#testGetAllSituations']


startTime = perf_counter()

for mut in neededMutantId:
    print(check_output(f"mkdir testReports-SeparatelyTests-Mut/mut_{mut}", shell="True").decode('cp850'))
    for tm in testMethods:
        command1 = f"adb shell dumpsys battery unplug"
        print(check_output(command1, shell=True).decode('cp850'))

        command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
                -Pandroid.testInstrumentationRunnerArguments.neededMutantId={mut} createAgencyXGoogleDebugCoverageReport"

        #print(command)
        print(check_output(command, shell=True).decode('cp850'))

        command2 = f"adb shell dumpsys battery reset"
        print(check_output(command2, shell=True).decode('cp850'))


        print(check_output(f"mkdir testReports-SeparatelyTests-Mut/mut_{mut}/report-{tm}", shell="True").decode('cp850'))
        print(check_output(f"cp -Rf onebusaway-android/build/reports testReports-SeparatelyTests-Mut/mut_{mut}/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

