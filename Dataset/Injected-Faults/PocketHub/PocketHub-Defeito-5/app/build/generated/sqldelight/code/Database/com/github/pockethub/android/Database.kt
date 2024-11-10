package com.github.pockethub.android

import com.github.pockethub.android.app.newInstance
import com.github.pockethub.android.app.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

interface Database : Transacter {
  val issue_filterQueries: Issue_filterQueries

  val organizationsQueries: OrganizationsQueries

  val repositoriesQueries: RepositoriesQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = Database::class.schema

    operator fun invoke(driver: SqlDriver, milestonesAdapter: Milestones.Adapter): Database =
        Database::class.newInstance(driver, milestonesAdapter)}
}
