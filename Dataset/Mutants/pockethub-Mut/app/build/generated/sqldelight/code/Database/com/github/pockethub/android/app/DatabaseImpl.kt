package com.github.pockethub.android.app

import com.github.pockethub.android.Database
import com.github.pockethub.android.GetFilters
import com.github.pockethub.android.Issue_filterQueries
import com.github.pockethub.android.Milestones
import com.github.pockethub.android.OrganizationsQueries
import com.github.pockethub.android.RepositoriesQueries
import com.github.pockethub.android.SelectUserAndOrgs
import com.github.pockethub.android.Users
import com.meisolsson.githubsdk.model.IssueState
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

internal val KClass<Database>.schema: SqlDriver.Schema
  get() = DatabaseImpl.Schema

internal fun KClass<Database>.newInstance(driver: SqlDriver, milestonesAdapter: Milestones.Adapter):
    Database = DatabaseImpl(driver, milestonesAdapter)

private class DatabaseImpl(
  driver: SqlDriver,
  internal val milestonesAdapter: Milestones.Adapter
) : TransacterImpl(driver), Database {
  override val issue_filterQueries: Issue_filterQueriesImpl = Issue_filterQueriesImpl(this, driver)

  override val organizationsQueries: OrganizationsQueriesImpl = OrganizationsQueriesImpl(this,
      driver)

  override val repositoriesQueries: RepositoriesQueriesImpl = RepositoriesQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 13

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE labels (
          |    repo_id INTEGER,
          |    name TEXT,
          |    color TEXT,
          |    PRIMARY KEY (repo_id, name)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE milestones (
          |    repo_id INTEGER,
          |    title TEXT,
          |    state TEXT,
          |    milestone_id INTEGER,
          |    number INTEGER,
          |    PRIMARY KEY (repo_id, milestone_id)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE issue_filters (
          |    id TEXT PRIMARY KEY,
          |    repo_id INTEGER,
          |    milestone_id INTEGER,
          |    assignee_id INTEGER,
          |    open INTEGER,
          |    direction TEXT,
          |    sort_type TEXT,
          |
          |    FOREIGN KEY (repo_id) REFERENCES repositories(repoId),
          |    FOREIGN KEY (repo_id, milestone_id) REFERENCES milestones(repo_id, milestone_id),
          |    FOREIGN KEY (assignee_id) REFERENCES users(id)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE issue_filter_labels (
          |    issue_filter_id TEXT,
          |    repo_id INTEGER,
          |    name TEXT,
          |
          |    FOREIGN KEY (issue_filter_id) REFERENCES issue_filters(id),
          |    FOREIGN KEY (repo_id, name) REFERENCES labels(repo_id, name)
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE organizations (
          |    id INTEGER PRIMARY KEY
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE users (
          |    id INTEGER PRIMARY KEY,
          |    login TEXT,
          |    name TEXT,
          |    avatarurl TEXT
          |)
          """.trimMargin(), 0)
      driver.execute(null, """
          |CREATE TABLE repositories (
          |    id INTEGER PRIMARY KEY,
          |    repoId INTEGER UNIQUE,
          |    orgId INTEGER,
          |    name TEXT,
          |    ownerId INTEGER,
          |    private INTEGER,
          |    fork INTEGER,
          |    description TEXT,
          |    forks INTEGER,
          |    watchers INTEGER,
          |    language TEXT,
          |    hasIssues INTEGER,
          |    mirrorUrl TEXT,
          |    permissions_admin INTEGER,
          |    permissions_pull INTEGER,
          |    permissions_push INTEGER
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
      if (oldVersion <= 9 && newVersion > 9) {
        driver.execute(null, "ALTER TABLE orgs RENAME TO organizations;", 0)
        driver.execute(null, "ALTER TABLE users ADD COLUMN login TEXT;", 0)
        driver.execute(null, "UPDATE users SET login = name;", 0)
        driver.execute(null, "UPDATE users SET name = NULL;", 0)
      }
      if (oldVersion <= 10 && newVersion > 10) {
        driver.execute(null, "ALTER TABLE repos RENAME TO repositories;", 0)
      }
      if (oldVersion <= 11 && newVersion > 11) {
        driver.execute(null, "PRAGMA foreign_keys=off;", 0)
        driver.execute(null, "BEGIN TRANSACTION;", 0)
        driver.execute(null, "DROP TABLE IF EXISTS repositories;", 0)
        driver.execute(null, """
            |CREATE TABLE repositories
            |(
            |    id INTEGER PRIMARY KEY,
            |    repoId INTEGER UNIQUE,
            |    orgId INTEGER,
            |    name TEXT,
            |    ownerId INTEGER,
            |    private INTEGER,
            |    fork INTEGER,
            |    description TEXT,
            |    forks INTEGER,
            |    watchers INTEGER,
            |    language TEXT,
            |    hasIssues INTEGER,
            |    mirrorUrl TEXT,
            |    permissions_admin INTEGER,
            |    permissions_pull INTEGER,
            |    permissions_push INTEGER
            |);
            """.trimMargin(), 0)
        driver.execute(null, "COMMIT;", 0)
        driver.execute(null, "PRAGMA foreign_keys=on;", 0)
      }
      if (oldVersion <= 12 && newVersion > 12) {
        driver.execute(null, """
            |CREATE TABLE labels (
            |    repo_id INTEGER,
            |    name TEXT,
            |    color TEXT,
            |    PRIMARY KEY (repo_id, name)
            |);
            """.trimMargin(), 0)
        driver.execute(null, """
            |CREATE TABLE milestones (
            |    repo_id INTEGER,
            |    title TEXT,
            |    state TEXT,
            |    milestone_id INTEGER,
            |    number INTEGER,
            |    PRIMARY KEY (repo_id, milestone_id)
            |);
            """.trimMargin(), 0)
        driver.execute(null, """
            |CREATE TABLE issue_filters (
            |    id TEXT PRIMARY KEY,
            |    repo_id INTEGER,
            |    milestone_id INTEGER,
            |    assignee_id INTEGER,
            |    open INTEGER,
            |    direction TEXT,
            |    sort_type TEXT,
            |
            |    FOREIGN KEY (repo_id) REFERENCES repositories(repoId),
            |    FOREIGN KEY (repo_id, milestone_id) REFERENCES milestones(repo_id, milestone_id),
            |    FOREIGN KEY (assignee_id) REFERENCES users(id)
            |);
            """.trimMargin(), 0)
        driver.execute(null, """
            |CREATE TABLE issue_filter_labels (
            |    issue_filter_id TEXT,
            |    repo_id INTEGER,
            |    name TEXT,
            |
            |    FOREIGN KEY (issue_filter_id) REFERENCES issue_filters(id),
            |    FOREIGN KEY (repo_id, name) REFERENCES labels(repo_id, name)
            |);
            """.trimMargin(), 0)
      }
    }
  }
}

private class Issue_filterQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), Issue_filterQueries {
  internal val getFilters: MutableList<Query<*>> = copyOnWriteList()

  internal val getFilterLabels: MutableList<Query<*>> = copyOnWriteList()

  override fun getFilters(): Query<GetFilters> = Query(239341289, getFilters, driver,
      "issue_filter.sq", "getFilters", """
  |SELECT *
  |FROM issue_filters
  |LEFT JOIN milestones
  |USING (repo_id, milestone_id)
  |LEFT JOIN repositories
  |ON repositories.repoId = issue_filters.repo_id
  |LEFT JOIN users
  |ON users.id = issue_filters.assignee_id
  """.trimMargin()) { cursor ->
    GetFilters.Impl(
      cursor.getString(0)!!,
      cursor.getLong(1),
      cursor.getLong(2),
      cursor.getLong(3),
      cursor.getLong(4)?.let { it == 1L },
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)?.let(database.milestonesAdapter.stateAdapter::decode),
      cursor.getLong(9),
      cursor.getLong(10),
      cursor.getLong(11),
      cursor.getLong(12),
      cursor.getString(13),
      cursor.getLong(14),
      cursor.getLong(15)?.let { it == 1L },
      cursor.getLong(16)?.let { it == 1L },
      cursor.getString(17),
      cursor.getLong(18)?.toInt(),
      cursor.getLong(19)?.toInt(),
      cursor.getString(20),
      cursor.getLong(21)?.let { it == 1L },
      cursor.getString(22),
      cursor.getLong(23)?.let { it == 1L },
      cursor.getLong(24)?.let { it == 1L },
      cursor.getLong(25)?.let { it == 1L },
      cursor.getLong(26),
      cursor.getString(27),
      cursor.getString(28),
      cursor.getString(29)
    )
  }

  override fun <T : Any> getFilterLabels(issue_filter_id: String?, mapper: (
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?,
    repo_id_: Long?,
    name_: String?,
    color: String?
  ) -> T): Query<T> = GetFilterLabels(issue_filter_id) { cursor ->
    mapper(
      cursor.getString(0),
      cursor.getLong(1),
      cursor.getString(2),
      cursor.getLong(3),
      cursor.getString(4),
      cursor.getString(5)
    )
  }

  override fun getFilterLabels(issue_filter_id: String?):
      Query<com.github.pockethub.android.GetFilterLabels> = getFilterLabels(issue_filter_id,
      com.github.pockethub.android.GetFilterLabels::Impl)

  override fun insertOrReplaceIssueFilter(
    id: String,
    repo_id: Long?,
    milestone_id: Long?,
    assignee_id: Long?,
    open: Boolean?,
    direction: String?,
    sort_type: String?
  ) {
    driver.execute(-1779657443, """
    |INSERT OR REPLACE
    |INTO issue_filters(id, repo_id, milestone_id, assignee_id, open, direction, sort_type)
    |VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)
    """.trimMargin(), 7) {
      bindString(1, id)
      bindLong(2, repo_id)
      bindLong(3, milestone_id)
      bindLong(4, assignee_id)
      bindLong(5, if (open == null) null else if (open) 1L else 0L)
      bindString(6, direction)
      bindString(7, sort_type)
    }
    notifyQueries(-1779657443, {database.issue_filterQueries.getFilters})
  }

  override fun insertOrReplaceLabel(
    repo_id: Long?,
    name: String?,
    color: String?
  ) {
    driver.execute(576879456, """
    |INSERT OR REPLACE
    |INTO labels(repo_id, name, color)
    |VALUES (?1, ?2, ?3)
    """.trimMargin(), 3) {
      bindLong(1, repo_id)
      bindString(2, name)
      bindString(3, color)
    }
    notifyQueries(576879456, {database.issue_filterQueries.getFilterLabels})
  }

  override fun insertOrReplaceIssueFilterLabel(
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?
  ) {
    driver.execute(-1249795113, """
    |INSERT OR REPLACE
    |INTO issue_filter_labels(issue_filter_id, repo_id, name)
    |VALUES (?1, ?2, ?3)
    """.trimMargin(), 3) {
      bindString(1, issue_filter_id)
      bindLong(2, repo_id)
      bindString(3, name)
    }
    notifyQueries(-1249795113, {database.issue_filterQueries.getFilterLabels})
  }

  override fun insertOrReplaceMilestone(
    repo_id: Long?,
    title: String?,
    state: IssueState?,
    milestone_id: Long?,
    number: Long?
  ) {
    driver.execute(-790954020, """
    |INSERT OR REPLACE
    |INTO milestones(repo_id, title, state, milestone_id, number)
    |VALUES (?1, ?2, ?3, ?4, ?5)
    """.trimMargin(), 5) {
      bindLong(1, repo_id)
      bindString(2, title)
      bindString(3, if (state == null) null else
          database.milestonesAdapter.stateAdapter.encode(state))
      bindLong(4, milestone_id)
      bindLong(5, number)
    }
    notifyQueries(-790954020, {database.issue_filterQueries.getFilters})
  }

  override fun removeIssueFilter(id: String) {
    driver.execute(690212585, """DELETE FROM issue_filters WHERE id = ?1""", 1) {
      bindString(1, id)
    }
    notifyQueries(690212585, {database.issue_filterQueries.getFilters})
  }

  override fun removeIssueFilterLabel(
    issue_filter_id: String?,
    repo_id: Long?,
    name: String?
  ) {
    driver.execute(null,
        """DELETE FROM issue_filter_labels WHERE issue_filter_id ${ if (issue_filter_id == null) "IS" else "=" } ?1 AND repo_id ${ if (repo_id == null) "IS" else "=" } ?2 AND name ${ if (name == null) "IS" else "=" } ?3""",
        3) {
      bindString(1, issue_filter_id)
      bindLong(2, repo_id)
      bindString(3, name)
    }
    notifyQueries(755149195, {database.issue_filterQueries.getFilterLabels})
  }

  override fun clearIssueFilters() {
    driver.execute(-877142997, """DELETE FROM issue_filter_labels""", 0)
    notifyQueries(-877142997, {database.issue_filterQueries.getFilterLabels})
  }

  override fun clearMilestones() {
    driver.execute(-1079864692, """DELETE FROM issue_filters""", 0)
    notifyQueries(-1079864692, {database.issue_filterQueries.getFilters})
  }

  override fun clearLabels() {
    driver.execute(-36042232, """DELETE FROM labels""", 0)
    notifyQueries(-36042232, {database.issue_filterQueries.getFilterLabels})
  }

  override fun clearIssueFilterLabels() {
    driver.execute(1697252199, """DELETE FROM issue_filter_labels""", 0)
    notifyQueries(1697252199, {database.issue_filterQueries.getFilterLabels})
  }

  private inner class GetFilterLabels<out T : Any>(
    @JvmField
    val issue_filter_id: String?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getFilterLabels, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT *
    |FROM issue_filter_labels
    |JOIN labels
    |ON issue_filter_labels.repo_id = labels.repo_id AND issue_filter_labels.name = labels.name
    |WHERE issue_filter_id ${ if (issue_filter_id == null) "IS" else "=" } ?1
    """.trimMargin(), 1) {
      bindString(1, issue_filter_id)
    }

    override fun toString(): String = "issue_filter.sq:getFilterLabels"
  }
}

private class OrganizationsQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), OrganizationsQueries {
  internal val selectUserAndOrgs: MutableList<Query<*>> = copyOnWriteList()

  internal val selectUser: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectUserAndOrgs(mapper: (
    id: Long,
    login: String?,
    name: String?,
    avatarurl: String?
  ) -> T): Query<T> = Query(-927956503, selectUserAndOrgs, driver, "organizations.sq",
      "selectUserAndOrgs", """
  |SELECT users.id, login, name, avatarurl
  |FROM organizations
  |JOIN users
  |USING (id)
  """.trimMargin()) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3)
    )
  }

  override fun selectUserAndOrgs(): Query<SelectUserAndOrgs> =
      selectUserAndOrgs(SelectUserAndOrgs::Impl)

  override fun <T : Any> selectUser(id: Long, mapper: (
    id: Long,
    login: String?,
    name: String?,
    avatarurl: String?
  ) -> T): Query<T> = SelectUser(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3)
    )
  }

  override fun selectUser(id: Long): Query<Users> = selectUser(id, Users::Impl)

  override fun clearOrgs() {
    driver.execute(-1732321722, """DELETE FROM organizations""", 0)
    notifyQueries(-1732321722, {database.organizationsQueries.selectUserAndOrgs})
  }

  override fun clearUsers() {
    driver.execute(2138170545, """DELETE FROM users""", 0)
    notifyQueries(2138170545, {database.issue_filterQueries.getFilters +
        database.organizationsQueries.selectUserAndOrgs + database.organizationsQueries.selectUser +
        database.repositoriesQueries.selectUserRepositories})
  }

  override fun replaceUser(
    id: Long?,
    login: String?,
    name: String?,
    avatarurl: String?
  ) {
    driver.execute(-476437911, """
    |REPLACE INTO users(id, login, name, avatarurl)
    |VALUES (?1, ?2, ?3, ?4)
    """.trimMargin(), 4) {
      bindLong(1, id)
      bindString(2, login)
      bindString(3, name)
      bindString(4, avatarurl)
    }
    notifyQueries(-476437911, {database.issue_filterQueries.getFilters +
        database.organizationsQueries.selectUserAndOrgs + database.organizationsQueries.selectUser +
        database.repositoriesQueries.selectUserRepositories})
  }

  override fun insertOrg(id: Long?) {
    driver.execute(-845758027, """
    |INSERT INTO organizations(id)
    |VALUES (?1)
    """.trimMargin(), 1) {
      bindLong(1, id)
    }
    notifyQueries(-845758027, {database.organizationsQueries.selectUserAndOrgs})
  }

  private inner class SelectUser<out T : Any>(
    @JvmField
    val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectUser, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(1224330205, """
    |SELECT *
    |FROM users
    |WHERE id = ?1
    """.trimMargin(), 1) {
      bindLong(1, id)
    }

    override fun toString(): String = "organizations.sq:selectUser"
  }
}

private class RepositoriesQueriesImpl(
  private val database: DatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), RepositoriesQueries {
  internal val selectUserRepositories: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectUserRepositories(orgId: Long?, mapper: (
    repoId: Long?,
    name: String?,
    id: Long?,
    login: String?,
    name_: String?,
    avatarurl: String?,
    private: Boolean?,
    fork: Boolean?,
    description: String?,
    forks: Int?,
    watchers: Int?,
    language: String?,
    hasIssues: Boolean?,
    mirrorUrl: String?,
    permissions_admin: Boolean?,
    permissions_pull: Boolean?,
    permissions_push: Boolean?
  ) -> T): Query<T> = SelectUserRepositories(orgId) { cursor ->
    mapper(
      cursor.getLong(0),
      cursor.getString(1),
      cursor.getLong(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getLong(6)?.let { it == 1L },
      cursor.getLong(7)?.let { it == 1L },
      cursor.getString(8),
      cursor.getLong(9)?.toInt(),
      cursor.getLong(10)?.toInt(),
      cursor.getString(11),
      cursor.getLong(12)?.let { it == 1L },
      cursor.getString(13),
      cursor.getLong(14)?.let { it == 1L },
      cursor.getLong(15)?.let { it == 1L },
      cursor.getLong(16)?.let { it == 1L }
    )
  }

  override fun selectUserRepositories(orgId: Long?):
      Query<com.github.pockethub.android.SelectUserRepositories> = selectUserRepositories(orgId,
      com.github.pockethub.android.SelectUserRepositories::Impl)

  override fun clearUserRepositories(orgId: Long?) {
    driver.execute(null,
        """DELETE FROM repositories WHERE orgId ${ if (orgId == null) "IS" else "=" } ?1""", 1) {
      bindLong(1, orgId)
    }
    notifyQueries(-1564476654, {database.issue_filterQueries.getFilters +
        database.repositoriesQueries.selectUserRepositories})
  }

  override fun clearRepositories() {
    driver.execute(-309785817, """DELETE FROM repositories""", 0)
    notifyQueries(-309785817, {database.issue_filterQueries.getFilters +
        database.repositoriesQueries.selectUserRepositories})
  }

  override fun replaceRepo(
    repoId: Long?,
    name: String?,
    orgId: Long?,
    ownerId: Long?,
    private: Boolean?,
    fork: Boolean?,
    description: String?,
    forks: Int?,
    watchers: Int?,
    language: String?,
    hasIssues: Boolean?,
    mirrorUrl: String?,
    permissions_admin: Boolean?,
    permissions_pull: Boolean?,
    permissions_push: Boolean?
  ) {
    driver.execute(-1480386408, """
    |REPLACE
    |INTO repositories(repoId, name, orgId, ownerId, private, fork, description, forks, watchers, language, hasIssues, mirrorUrl, permissions_admin, permissions_pull, permissions_push)
    |VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15)
    """.trimMargin(), 15) {
      bindLong(1, repoId)
      bindString(2, name)
      bindLong(3, orgId)
      bindLong(4, ownerId)
      bindLong(5, if (private == null) null else if (private) 1L else 0L)
      bindLong(6, if (fork == null) null else if (fork) 1L else 0L)
      bindString(7, description)
      bindLong(8, if (forks == null) null else forks.toLong())
      bindLong(9, if (watchers == null) null else watchers.toLong())
      bindString(10, language)
      bindLong(11, if (hasIssues == null) null else if (hasIssues) 1L else 0L)
      bindString(12, mirrorUrl)
      bindLong(13, if (permissions_admin == null) null else if (permissions_admin) 1L else 0L)
      bindLong(14, if (permissions_pull == null) null else if (permissions_pull) 1L else 0L)
      bindLong(15, if (permissions_push == null) null else if (permissions_push) 1L else 0L)
    }
    notifyQueries(-1480386408, {database.issue_filterQueries.getFilters +
        database.repositoriesQueries.selectUserRepositories})
  }

  private inner class SelectUserRepositories<out T : Any>(
    @JvmField
    val orgId: Long?,
    mapper: (SqlCursor) -> T
  ) : Query<T>(selectUserRepositories, mapper) {
    override fun execute(): SqlCursor = driver.executeQuery(null, """
    |SELECT repoId, repositories.name, users.id, users.login, users.name, users.avatarurl, repositories.private, repositories.fork, repositories.description, repositories.forks, repositories.watchers, repositories.language, repositories.hasIssues, repositories.mirrorUrl, repositories.permissions_admin, repositories.permissions_pull, repositories.permissions_push
    |FROM repositories
    |LEFT JOIN users
    |ON repositories.ownerId = users.id
    |WHERE repositories.orgId ${ if (orgId == null) "IS" else "=" } ?1
    """.trimMargin(), 1) {
      bindLong(1, orgId)
    }

    override fun toString(): String = "repositories.sq:selectUserRepositories"
  }
}
