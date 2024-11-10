from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


testMethods = ['com.ichi2.anki.reviewer.PeripheralKeymapTest#testNumpadAction',
                'com.ichi2.anki.tests.libanki.DBTest#testDBCorruption',
                'com.ichi2.anki.tests.libanki.HttpTest#testLogin',
                'com.ichi2.anki.tests.libanki.ImportTest#testAnki2Mediadupes',
                'com.ichi2.anki.tests.libanki.ImportTest#testApkg',
                'com.ichi2.anki.tests.libanki.ImportTest#testAnki2DiffmodelTemplates',
                'com.ichi2.anki.tests.libanki.ImportTest#testAnki2Updates',
                'com.ichi2.anki.tests.libanki.ImportTest#testCsv',
                'com.ichi2.anki.tests.libanki.ImportTest#testCsv2',
                'com.ichi2.anki.tests.libanki.ImportTest#testCsvWithByteOrderMark',
                'com.ichi2.anki.tests.libanki.ImportTest#csvManualBasicExample',
                'com.ichi2.anki.tests.libanki.ImportTest#csvManualLineBreakExample',
                'com.ichi2.anki.tests.libanki.ImportTest#testDupeIgnore',
                'com.ichi2.anki.tests.libanki.MediaTest#testAdd',
                'com.ichi2.anki.tests.libanki.MediaTest#testAddEmptyFails',
                'com.ichi2.anki.tests.libanki.MediaTest#testStrings',
                'com.ichi2.anki.tests.libanki.MediaTest#testDeckIntegration',
                'com.ichi2.anki.tests.libanki.MediaTest#testChanges',
                'com.ichi2.anki.tests.libanki.MediaTest#testIllegal',
                'com.ichi2.anki.tests.libanki.ModelTest#bigQuery',
                'com.ichi2.anki.tests.ACRATest#testDebugConfiguration',
                'com.ichi2.anki.tests.ACRATest#testProductionConfigurationUserDisabled',
                'com.ichi2.anki.tests.ACRATest#testProductionConfigurationUserAsk',
                'com.ichi2.anki.tests.ACRATest#testCrashReportLimit',
                'com.ichi2.anki.tests.ACRATest#testProductionConfigurationUserAlways',
                'com.ichi2.anki.tests.ACRATest#testDialogEnabledWhenMovingFromAlwaysToAsk',
                'com.ichi2.anki.tests.ACRATest#testToastTextWhenMovingFromAskToAlways',
                'com.ichi2.anki.tests.CollectionTest#testOpenCollection',
                'com.ichi2.anki.tests.ContentProviderTest#testInsertAndRemoveNote',
                'com.ichi2.anki.tests.ContentProviderTest#testInsertTemplate',
                'com.ichi2.anki.tests.ContentProviderTest#testInsertField',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryDirectSqlQuery',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryNoteIds',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryNotesProjection',
                'com.ichi2.anki.tests.ContentProviderTest#testUpdateNoteFields',
                'com.ichi2.anki.tests.ContentProviderTest#testInsertAndUpdateModel',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryAllModels',
                'com.ichi2.anki.tests.ContentProviderTest#testMoveCardsToOtherDeck',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryCurrentModel',
                'com.ichi2.anki.tests.ContentProviderTest#testUnsupportedOperations',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryAllDecks',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryCertainDeck',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryNextCard',
                'com.ichi2.anki.tests.ContentProviderTest#testQueryCardFromCertainDeck',
                'com.ichi2.anki.tests.ContentProviderTest#testSetSelectedDeck',
                'com.ichi2.anki.tests.ContentProviderTest#testAnswerCard',
                'com.ichi2.anki.tests.ContentProviderTest#testBuryCard',
                'com.ichi2.anki.tests.ContentProviderTest#testSuspendCard',
                'com.ichi2.anki.tests.ContentProviderTest#testUpdateTags',
                'com.ichi2.anki.tests.ContentProviderTest#testProviderProvidesDefaultForEmptyModelDeck',
                'com.ichi2.anki.tests.LayoutValidationTest',
                'com.ichi2.anki.tests.NotificationChannelTest#testChannelCreation',
                'com.ichi2.anki.utils.DiffEngineTest#testSimpleDiff',
                'com.ichi2.anki.FieldEditLineTest#testSetters',
                'com.ichi2.anki.FieldEditLineTest#testSaveRestore',
                'com.ichi2.anki.NoteEditorTabOrderTest#testTabOrder',
                'com.ichi2.libanki.DBTest#writeAheadLoggingIsDisabled',
                'com.ichi2.ui.ActionBarOverflowTest#hasValidActionBarReflectionMethod',
                'com.ichi2.ui.ActionBarOverflowTest#errorsAreBeingThrownCanary',
                'com.ichi2.ui.ActionBarOverflowTest#testAndroidXMenuItem',
                'com.ichi2.ui.ActionBarOverflowTest#testAndroidMenuItem']

startTime = perf_counter()


for tm in testMethods:
    command = f"./gradlew -Pandroid.testInstrumentationRunnerArguments.class={tm} \
            createDebugCoverageReport"

    #print(command)
    print(check_output(command, shell=True).decode('cp850'))


    print(check_output(f"mkdir testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))
    print(check_output(f"cp -Rf AnkiDroid/build/reports testReports-SeparatelyTests/report-{tm}", shell="True").decode('cp850'))


timeTaken = perf_counter()-startTime
print(f"Time taken: {timeTaken}") 
execTimeFile.write(f"c{exec},{timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

