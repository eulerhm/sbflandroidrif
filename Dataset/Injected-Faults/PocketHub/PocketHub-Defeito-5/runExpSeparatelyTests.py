from subprocess import check_output
import timing 
from time import perf_counter

print(check_output(f"mkdir testReports-SeparatelyTests", shell="True").decode('cp850'))

execTimeFile = open("executionTime-SeparatelyTests.txt","a")


testMethods = ['com.github.pockethub.android.tests.commit.CommitUriMatcherTest#testEmptyUri',
                'com.github.pockethub.android.tests.commit.CommitUriMatcherTest#testHttpUri',
                'com.github.pockethub.android.tests.commit.CommitUriMatcherTest#testHttpsUri',
                'com.github.pockethub.android.tests.commit.CommitUriMatcherTest#testCommentUri',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testAbbreviate',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testGetName',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testIsValidCommit',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testGetAuthor',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testGetCommitter',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testGetAuthorDate',
                'com.github.pockethub.android.tests.commit.CommitUtilsTest#testGetCommitterDate',
                'com.github.pockethub.android.tests.commit.CreateCommentActivityTest#testEmptyCommentIsProhibited',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testEmptyFiles',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testEmptyPatch',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testOnlyNewline',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testEmptyPatchLineWithOtherValidLines',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testTrailingEmptyLine',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testOnlyNewlines',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testNoTrailingNewlineAfterSecondLine',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testNoTrailingNewline',
                'com.github.pockethub.android.tests.commit.DiffStylerTest#testFormattedPatch',
                'com.github.pockethub.android.tests.commit.FullCommitTest#testSingleLineCommentSingleFile',
                'com.github.pockethub.android.tests.commit.FullCommitTest#testSingleCommentSingleFile',
                'com.github.pockethub.android.tests.commit.FullCommitTest#testSingleCommentNoFiles',
                'com.github.pockethub.android.tests.commit.FullCommitTest#testNoCommentsSingleFile',
                'com.github.pockethub.android.tests.commit.FullCommitTest#testBothTypesOfComments',
                'com.github.pockethub.android.tests.gist.CreateCommentActivityTest#testEmptyCommentIsProhibited',
                'com.github.pockethub.android.tests.gist.CreateGistActivityTest#testCreateWithInitialText',
                'com.github.pockethub.android.tests.gist.CreateGistActivityTest#testCreateWithNoInitialText',
                'com.github.pockethub.android.tests.gist.GistFilesViewActivityTest#testChangingPages',
                'com.github.pockethub.android.tests.gist.GistStoreTest#testReuseIssue',
                'com.github.pockethub.android.tests.gist.GistUriMatcherTest#testEmptyUri',
                'com.github.pockethub.android.tests.gist.GistUriMatcherTest#testNonGistId',
                'com.github.pockethub.android.tests.gist.GistUriMatcherTest#testPublicGist',
                'com.github.pockethub.android.tests.gist.GistUriMatcherTest#testPrivateGist',
                'com.github.pockethub.android.tests.issue.CreateCommentActivityTest#testEmptyCommentIsProhibited',
                'com.github.pockethub.android.tests.issue.EditIssueActivityTest#testSaveMenuEnabled',
                'com.github.pockethub.android.tests.issue.IssueFilterTest#testEqualFilter',
                'com.github.pockethub.android.tests.issue.IssueStoreTest#testReuseIssue',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testEmptyUri',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testNonNumericIssueNumber',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testHttpUri',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testPullUri',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testHttpsUri',
                'com.github.pockethub.android.tests.issue.IssueUriMatcherTest#testCommentUri',
                'com.github.pockethub.android.tests.ref.RefUtilsTest#testIsBranch',
                'com.github.pockethub.android.tests.ref.RefUtilsTest#testIsTag',
                'com.github.pockethub.android.tests.ref.RefUtilsTest#testIsValid',
                'com.github.pockethub.android.tests.ref.RefUtilsTest#testGetName',
                'com.github.pockethub.android.tests.ref.RefUtilsTest#testGetPath',
                'com.github.pockethub.android.tests.repo.RecentRepositoriesTest#testBadInput',
                'com.github.pockethub.android.tests.repo.RecentRepositoriesTest#testMaxReached',
                'com.github.pockethub.android.tests.repo.RecentRepositoriesTest#testIO',
                'com.github.pockethub.android.tests.repo.RecentRepositoriesTest#testScopedStorage',
                'com.github.pockethub.android.tests.repo.RepositoryEventMatcherTest#testIncompleteRepositoryFork',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testEmptyUri',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testUriWithNoOnwer',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testUriWithNoName',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testHttpUriWithOwnerAndName',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testHttpsUriWithOwnerAndName',
                'com.github.pockethub.android.tests.repo.RepositoryUriMatcherTest#testInvalidOwner',
                'com.github.pockethub.android.tests.user.UserComparatorTest#testLoginMatch',
                'com.github.pockethub.android.tests.user.UserComparatorTest#testNoLoginMatch',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testEmptyUri',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testUriWithNoName',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testHttpUriWithName',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testHttpsUriWithName',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testUriWithTrailingSlash',
                'com.github.pockethub.android.tests.user.UserUriMatcherTest#testUriWithTrailingSlashes',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testToggleRemoved',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testTogglesRemoved',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testEmailQuoted',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testEmailFragment',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testEmailFragments',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testTrailingEmailFragment',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testLeadingBreak',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testTrailingBreak',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testWrappedBreaks',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testWrappedParagraphs',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testParagraphReplacedWithBreak',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testEmReplacedWithI',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testStrongReplacedWithB',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testLeadingWhitespace',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testTrailingWhitespace',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testWrappedWhitetspace',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testPreWithNoWhitespace',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testPreWithSpaces',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testPreWithTabs',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testPreWithNewline',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testPreWithAllWhitepsace',
                'com.github.pockethub.android.tests.util.HtmlUtilsTest#testMultiplePresEscaped',
                'com.github.pockethub.android.tests.NewsEventTextTest#testCommitCommentEvent',
                'com.github.pockethub.android.tests.NewsEventTextTest#testCreateRepositoryEvent',
                'com.github.pockethub.android.tests.NewsEventTextTest#testCreateBranchEvent',
                'com.github.pockethub.android.tests.NewsEventTextTest#testDelete',
                'com.github.pockethub.android.tests.NewsEventTextTest#testFollow',
                'com.github.pockethub.android.tests.NewsEventTextTest#testGist',
                'com.github.pockethub.android.tests.NewsEventTextTest#testWiki',
                'com.github.pockethub.android.tests.NewsEventTextTest#testIssueComment',
                'com.github.pockethub.android.tests.NewsEventTextTest#testIssue',
                'com.github.pockethub.android.tests.NewsEventTextTest#testAddMember',
                'com.github.pockethub.android.tests.NewsEventTextTest#testOpenSourced',
                'com.github.pockethub.android.tests.NewsEventTextTest#testWatch',
                'com.github.pockethub.android.tests.NewsEventTextTest#testPullRequest',
                'com.github.pockethub.android.tests.NewsEventTextTest#testPush',
                'com.github.pockethub.android.tests.NewsEventTextTest#testTeamAdd']

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
execTimeFile.write(f"Execution time : {timeTaken}")
execTimeFile.write("\n")
execTimeFile.flush()

execTimeFile.close()

