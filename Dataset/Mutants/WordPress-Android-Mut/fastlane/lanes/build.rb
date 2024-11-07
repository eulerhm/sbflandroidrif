platform :android do
  #####################################################################################
  # build_and_upload_release
  # -----------------------------------------------------------------------------------
  # This lane builds the final release of the app and uploads it
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_and_upload_release app:<wordpress|jetpack> [skip_confirm:<skip confirm>] [skip_prechecks:<skip prechecks>] [create_release:<Create release on GH> ]
  #
  # Example:
  # bundle exec fastlane build_and_upload_release app:wordpress
  # bundle exec fastlane build_and_upload_release app:wordpress skip_confirm:true
  # bundle exec fastlane build_and_upload_release app:jetpack skip_prechecks:true
  # bundle exec fastlane build_and_upload_release app:wordpress create_release:true
  #####################################################################################
  desc 'Builds and updates for distribution'
  lane :build_and_upload_release do |options|
    android_build_prechecks(
      skip_confirm: options[:skip_confirm],
      alpha: false,
      beta: false,
      final: true
    )
    android_build_preflight() unless options[:skip_prechecks]

    # Create the file names
    app = get_app_name_option!(options)
    version = android_get_release_version()
    build_bundle(app: app, version: version, flavor: 'Vanilla', buildType: 'Release')

    upload_build_to_play_store(app: app, version: version, track: 'production')

    create_gh_release(app: app, version: version) if options[:create_release]
  end

  #####################################################################################
  # build_and_upload_pre_releases
  # -----------------------------------------------------------------------------------
  # This lane builds the app for both internal and external distribution and uploads them
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_and_upload_pre_releases app:<wordpress|jetpack> [skip_confirm:<true|false>] [skip_prechecks:<true|false>] <[create_release:<true|false>]
  #
  # Example:
  # bundle exec fastlane build_and_upload_pre_releases
  # bundle exec fastlane build_and_upload_pre_releases skip_confirm:true
  # bundle exec fastlane build_and_upload_beta create_release:true
  #####################################################################################
  desc 'Builds and updates for distribution'
  lane :build_and_upload_pre_releases do |options|
    android_build_prechecks(
      skip_confirm: options[:skip_confirm],
      alpha: true,
      beta: true,
      final: false
    )
    android_build_preflight() unless options[:skip_prechecks]
    app = get_app_name_option!(options)
    build_alpha(app: app, skip_prechecks: true, skip_confirm: options[:skip_confirm], upload_to_play_store: true, create_release: options[:create_release])
    build_beta(app: app, skip_prechecks: true, skip_confirm: options[:skip_confirm], upload_to_play_store: true, create_release: options[:create_release])
  end

  #####################################################################################
  # build_alpha
  # -----------------------------------------------------------------------------------
  # This lane builds the app for internal testing and optionally uploads it
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_alpha app:<wordpress|jetpack> [skip_confirm:<true|false>] [upload_to_play_store:<true|false>] [create_release:<true|false>]
  #
  # Example:
  # bundle exec fastlane build_alpha app:wordpress create_release:true
  # bundle exec fastlane build_alpha app:wordpress skip_confirm:true upload_to_play_store:true
  # bundle exec fastlane build_alpha app:jetpack
  #####################################################################################
  desc 'Builds and updates for distribution'
  lane :build_alpha do |options|
    android_build_prechecks(skip_confirm: options[:skip_confirm], alpha: true) unless options[:skip_prechecks]
    android_build_preflight() unless options[:skip_prechecks]

    # Create the file names
    app = get_app_name_option!(options)
    version = android_get_alpha_version()
    build_bundle(app: app, version: version, flavor: 'Zalpha', buildType: 'Release')

    upload_build_to_play_store(app: app, version: version, track: 'alpha') if options[:upload_to_play_store]

    create_gh_release(app: app, version: version, prerelease: true) if options[:create_release]
  end

  #####################################################################################
  # build_beta
  # -----------------------------------------------------------------------------------
  # This lane builds the app for internal testing and optionally uploads it
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_beta app:<wordpress|jetpack> [skip_confirm:<true|false>] [upload_to_play_store:<true|false>] [create_release:<true|false>]
  #
  # Example:
  # bundle exec fastlane build_beta app:wordpress create_release:true
  # bundle exec fastlane build_beta app:wordpress skip_confirm:true upload_to_play_store:true
  # bundle exec fastlane build_beta app:jetpack create_release:true
  #####################################################################################
  desc 'Builds and updates for distribution'
  lane :build_beta do |options|
    android_build_prechecks(skip_confirm: options[:skip_confirm], beta: true) unless options[:skip_prechecks]
    android_build_preflight() unless options[:skip_prechecks]

    # Create the file names
    app = get_app_name_option!(options)
    version = android_get_release_version()
    build_bundle(app: app, version: version, flavor: 'Vanilla', buildType: 'Release')

    upload_build_to_play_store(app: app, version: version, track: 'beta') if options[:upload_to_play_store]

    create_gh_release(app: app, version: version, prerelease: true) if options[:create_release]
  end

  #####################################################################################
  # build_internal
  # -----------------------------------------------------------------------------------
  # This lane builds the app for restricted internal testing, and optionally uploads it to PlayStore's Internal track
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_internal app:<wordpress|jetpack> [skip_confirm:<true|false>] [upload_to_play_store:<true|false>] [create_release:<true|false>]
  #
  # Example:
  # bundle exec fastlane build_internal app:wordpress
  # bundle exec fastlane build_internal app:wordpress skip_confirm:true upload_to_play_store:true
  # bundle exec fastlane build_internal app:jetpack create_release:true
  #####################################################################################
  desc 'Builds and updates for internal testing'
  lane :build_internal do |options|
    android_build_prechecks(skip_confirm: options[:skip_confirm]) unless options[:skip_prechecks]
    android_build_preflight() unless options[:skip_prechecks]

    # Create the file names
    app = get_app_name_option!(options)
    version = android_get_release_version()
    build_bundle(app: app, version: version, flavor: 'Zalpha', buildType: 'Debug')

    upload_build_to_play_store(app: app, version: version, track: 'internal') if options[:upload_to_play_store]

    create_gh_release(app: app, version: version, prerelease: true) if options[:create_release]
  end

  #####################################################################################
  # upload_build_to_play_store
  # -----------------------------------------------------------------------------------
  # This lane uploads the build to Play Store for the given version to the given track
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane upload_build_to_play_store app:<wordpress|jetpack> version:<version> track:<track>
  #
  # Example:
  # bundle exec fastlane upload_build_to_play_store app:wordpress version:15.0 track:production
  # bundle exec fastlane upload_build_to_play_store app:wordpress version:alpha-228 track:alpha
  # bundle exec fastlane upload_build_to_play_store app:jetpack version:15.0-rc-1 track:beta
  #####################################################################################
  desc 'Upload Build to Play Store'
  lane :upload_build_to_play_store do |options|
    app = get_app_name_option!(options)
    package_name = APP_SPECIFIC_VALUES[app.to_sym][:package_name]
    metadata_dir = File.join('fastlane', APP_SPECIFIC_VALUES[app.to_sym][:metadata_dir], 'android')

    version = options[:version]

    if version.nil?
      UI.message("No version available for #{options[:track]} track for #{app}")
      next
    end

    aab_file_path = bundle_file_path(app, version)

    if File.exist? aab_file_path
      retry_count = 2
      begin
        upload_to_play_store(
          package_name: package_name,
          aab: aab_file_path,
          track: options[:track],
          release_status: 'draft',
          metadata_path: metadata_dir,
          skip_upload_metadata: (options[:track] != 'production'), # Only update app title/description/etc. if uploading for Production, skip for alpha/beta tracks
          skip_upload_changelogs: false,
          skip_upload_images: true,
          skip_upload_screenshots: true,
          json_key: UPLOAD_TO_PLAY_STORE_JSON_KEY
        )
      rescue FastlaneCore::Interface::FastlaneError => e
        # Sometimes the upload fails randomly with a "Google Api Error: Invalid request - This Edit has been deleted.".
        # It seems one reason might be a race condition when we do multiple edits at the exact same time (WP alpha, WP beta, JP beta). Retrying usually fixes it
        if e.message.start_with?('Google Api Error') && (retry_count -= 1) > 0
          UI.error 'Upload failed with Google API error. Retrying in 2mn...'
          sleep(120)
          retry
        end
        raise
      end
    else
      UI.error("Unable to find a build artifact at #{aab_file_path}")
    end
  end

  #####################################################################################
  # build_and_upload_installable_build
  # -----------------------------------------------------------------------------------
  # Build a WordPress Installable Build and make it available for download
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_and_upload_installable_build
  #####################################################################################
  desc 'Build an Installable Build and make it available for download'
  lane :build_and_upload_wordpress_installable_build do
    UI.user_error!("'BUILDKITE_ARTIFACTS_S3_BUCKET' must be defined as an environment variable.") unless ENV['BUILDKITE_ARTIFACTS_S3_BUCKET']

    gradle(
      task: 'assemble',
      flavor: 'WordPressJalapeno',
      build_type: 'Debug'
    )

    upload_installable_build(product: 'WordPress')
  end

  #####################################################################################
  # build_and_upload_jetpack_installable_build
  # -----------------------------------------------------------------------------------
  # Build a Jetpack Installable Build and make it available for download
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_and_upload_installable_build
  #####################################################################################
  desc 'Build an Installable Build and make it available for download'
  lane :build_and_upload_jetpack_installable_build do
    UI.user_error!("'BUILDKITE_ARTIFACTS_S3_BUCKET' must be defined as an environment variable.") unless ENV['BUILDKITE_ARTIFACTS_S3_BUCKET']

    gradle(
      task: 'assemble',
      flavor: 'JetpackJalapeno',
      build_type: 'Debug'
    )

    upload_installable_build(product: 'Jetpack')
  end

  #####################################################################################
  # build_bundle
  # -----------------------------------------------------------------------------------
  # This lane builds an app bundle
  # -----------------------------------------------------------------------------------
  # Usage:
  # bundle exec fastlane build_bundle app:<wordpress|jetpack> version:<version> flavor:<flavor> buildType:<debug|release> [skip_lint:<true|false>]
  #####################################################################################
  desc 'Builds an app bundle'
  lane :build_bundle do |options|
    # Create the file names
    version = options[:version]
    app = get_app_name_option!(options)

    if version.nil?
      UI.message("Version specified for #{app} bundle is nil. Skipping ahead")
      next
    end

    prefix = APP_SPECIFIC_VALUES[app.to_sym][:bundle_name_prefix]
    name = "#{prefix}-#{version['name']}.aab"

    aab_file = "org.wordpress.android-#{app}-#{options[:flavor]}-#{options[:buildType]}.aab".downcase
    output_dir = 'WordPress/build/outputs/bundle/'
    build_dir = 'build/'
    logfile_path = "#{build_dir}build.log"

    # Intermediate Variables
    bundle_path = "#{output_dir}#{app}#{options[:flavor].capitalize}#{options[:buildType].capitalize}/#{aab_file}"

    # Build
    Dir.chdir('..') do
      sh("mkdir -p #{build_dir}")

      UI.message('Cleaning branch...')
      sh("echo \"Cleaning branch\" >> #{logfile_path}")
      sh("./gradlew clean >> #{logfile_path} 2>&1")

      sh("mkdir -p #{build_dir}")
      if options[:skip_lint].nil?
        UI.message('Running lint...')
        sh("echo \"Running lint...\" >> #{logfile_path}")
        sh("./gradlew lint#{app}#{options[:flavor]}#{options[:buildType]} >> #{logfile_path} 2>&1") unless is_ci
      else
        UI.message('Skipping lint...')
      end

      UI.message("Building #{version['name']} / #{version['code']} - #{aab_file}...")
      sh("echo \"Building #{version['name']} / #{version['code']} - #{aab_file}...\" >> #{logfile_path}")
      sh("./gradlew bundle#{app}#{options[:flavor]}#{options[:buildType]} >> #{logfile_path} 2>&1")

      UI.crash!("Unable to find a bundle at #{bundle_path}") unless File.file?(bundle_path)

      sh("cp -v #{bundle_path} #{build_dir}#{name} | tee -a #{logfile_path}")
      UI.message("Bundle ready: #{name}")
      sh("echo \"Bundle ready: #{name}\" >> #{logfile_path}")
    end
    "#{build_dir}#{name}"
  end

  # Uploads the apk built by the `gradle` (i.e. `SharedValues::GRADLE_APK_OUTPUT_PATH`) to S3 then comment on the PR to provide the download link
  #
  # @param [String] product the display name of the app to upload to S3. 'WordPress' or 'Jetpack'
  #
  def upload_installable_build(product:)
    filename = "#{product.downcase}-installable-build-#{generate_installable_build_number}.apk"

    upload_path = upload_to_s3(
      bucket: 'a8c-apps-public-artifacts',
      key: filename,
      file: lane_context[SharedValues::GRADLE_APK_OUTPUT_PATH]
    )

    return if ENV['BUILDKITE_PULL_REQUEST'].nil?

    install_url = "#{INSTALLABLE_BUILD_DOMAIN}/#{upload_path}"
    qr_code_url = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=#{CGI.escape(install_url)}&choe=UTF-8"
    comment_body = "You can test the #{product} changes on this Pull Request by <a href='#{install_url}'>downloading an installable build (#{filename})</a>, or scanning this QR code:<br><a href='#{install_url}'><img src='#{qr_code_url}' width='250' height='250' /></a>"

    comment_on_pr(
      project: GHHELPER_REPO,
      pr_number: Integer(ENV['BUILDKITE_PULL_REQUEST']),
      reuse_identifier: "#{product.downcase}-installable-build-link",
      body: comment_body
    )
  end

  # This function is Buildkite-specific
  def generate_installable_build_number
    if ENV['BUILDKITE']
      commit = ENV['BUILDKITE_COMMIT'][0, 7]
      branch = ENV['BUILDKITE_BRANCH'].parameterize
      pr_num = ENV['BUILDKITE_PULL_REQUEST']

      pr_num == 'false' ? "#{branch}-#{commit}" : "pr#{pr_num}-#{commit}"
    else
      repo = Git.open(PROJECT_ROOT_FOLDER)
      commit = repo.current_branch.parameterize
      branch = repo.revparse('HEAD')[0, 7]

      "#{branch}-#{commit}"
    end
  end
end
