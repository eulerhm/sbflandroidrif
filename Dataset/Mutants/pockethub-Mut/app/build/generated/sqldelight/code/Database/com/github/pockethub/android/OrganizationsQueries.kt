package com.github.pockethub.android

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

interface OrganizationsQueries : Transacter {
  fun <T : Any> selectUserAndOrgs(mapper: (
    id: Long,
    login: String?,
    name: String?,
    avatarurl: String?
  ) -> T): Query<T>

  fun selectUserAndOrgs(): Query<SelectUserAndOrgs>

  fun <T : Any> selectUser(id: Long, mapper: (
    id: Long,
    login: String?,
    name: String?,
    avatarurl: String?
  ) -> T): Query<T>

  fun selectUser(id: Long): Query<Users>

  fun clearOrgs()

  fun clearUsers()

  fun replaceUser(
    id: Long?,
    login: String?,
    name: String?,
    avatarurl: String?
  )

  fun insertOrg(id: Long?)
}
