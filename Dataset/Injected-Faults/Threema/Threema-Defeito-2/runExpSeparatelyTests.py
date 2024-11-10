from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")

testMethods = ['ch.threema.app.emojis.MarkupParserTest#parseBold',
			'ch.threema.app.emojis.MarkupParserTest#parseItalic',
			'ch.threema.app.emojis.MarkupParserTest#parseStrikethrough',
			'ch.threema.app.emojis.MarkupParserTest#parseTwoBold',
			'ch.threema.app.emojis.MarkupParserTest#parseTwoItalic',
			'ch.threema.app.emojis.MarkupParserTest#parseTwoStrikethrough',
			'ch.threema.app.emojis.MarkupParserTest#parseMixedMarkup',
			'ch.threema.app.emojis.MarkupParserTest#parseMixedMarkupNested',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries1',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries2',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries3',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries4',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries5',
			'ch.threema.app.emojis.MarkupParserTest#atWordBoundaries6',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries1',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries2',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries3',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries4',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries5',
			'ch.threema.app.emojis.MarkupParserTest#onlyWordBoundaries6',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls1',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls2',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls3',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls4',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls5',
			'ch.threema.app.emojis.MarkupParserTest#avoidBreakingUrls6',
			'ch.threema.app.emojis.MarkupParserTest#ignoreInvalidMarkup1',
			'ch.threema.app.emojis.MarkupParserTest#ignoreInvalidMarkup2',
			'ch.threema.app.emojis.MarkupParserTest#notAcrossNewlines1',
			'ch.threema.app.emojis.MarkupParserTest#notAcrossNewlines2',
			'ch.threema.app.emojis.MarkupParserTest#notAcrossNewlines3',
			'ch.threema.app.emojis.MarkupParserTest#notAcrossNewlines4',
			'ch.threema.app.utils.BackgroundErrorNotificationTest#testNotificationWithoutAction',
			'ch.threema.app.utils.TextUtilTest#testCheckBadPasswordNumericOnly',
			'ch.threema.app.utils.TextUtilTest#testCheckBadPasswordSameCharacter',
			'ch.threema.app.utils.TextUtilTest#testCheckBadPasswordBlacklisted',
			'ch.threema.app.voip.SdpTest#testOfferAudioOnly',
			'ch.threema.app.voip.SdpTest#testOfferVideo',
			'ch.threema.app.voip.SdpTest#testAnswerAudioOnly',
			'ch.threema.app.voip.SdpTest#testAnswerVideo',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingMissed',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingRejectedUnknown',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingRejectedBusy',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingRejectedTimeout',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingRejectedRejected',
			'ch.threema.app.voip.VoipStatusMessageTest#testIncomingRejectedDisabled',
			'ch.threema.app.voip.VoipStatusMessageTest#testOutgoingRejectedUnknown',
			'ch.threema.app.voip.VoipStatusMessageTest#testOutgoingRejectedBusy',
			'ch.threema.app.voip.VoipStatusMessageTest#testOutgoingRejectedTimeout',
			'ch.threema.app.voip.VoipStatusMessageTest#testOutgoingRejectedRejected',
			'ch.threema.app.voip.VoipStatusMessageTest#testOutgoingRejectedDisabled',
			'ch.threema.app.webclient.converter.MessageTest#testFixFileName',
			'ch.threema.app.webclient.converter.MessageTest#testMaybePutFile',
			'ch.threema.app.webclient.converter.MsgpackTest#testPutString']

startTime = perf_counter()

for tm in testMethods:
		command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
				createNoneDebugCoverageReport"

		#print(command)
		print(check_output(command, shell=True).decode('cp850'))

		print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
		print(check_output(f"cp -Rf app/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"Time taken: {timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

