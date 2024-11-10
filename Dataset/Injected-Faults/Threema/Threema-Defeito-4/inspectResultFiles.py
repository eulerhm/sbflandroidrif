import glob
from pyquery import PyQuery

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


for tm in testMethods:
    reportFile = glob.glob(f"/media/euler/SSD_2/workspace-SBES-ExtendedPaper/INJECTED_FAULT_ANALYSIS/Threema/Threema-Defeito-4/testReports-SeparatelyTests/report-{tm}/reports/androidTests/connected/flavors/noneDebugAndroidTest/index.html")
    
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
