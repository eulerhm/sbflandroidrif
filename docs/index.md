## Artifacts of the paper "Evaluating testing strategies for resource related failures in mobile applications"

The dataset includes 10 mobile applications. These applications are from different categories named according to the Play Store categories, with a large variation of size and test code size. For instance, applications vary from 14,499 lines of code (OwnTracks) to more than 347,000 lines of code (WordPress-Android). Similarly, test code size varies from 525 lines of code (Ground) to 5,564 (OneBusAway). Moreover, the number of test cases vary from 4 (Ground) to 164 (AnkiDroid).

|Name 	        |Category  | LOC|	Test LOC	|Test Cases|Resources|
|-------        |-------|---------|---------|-------|--------|-------                   |
|AnkiDroid            |Education |158,607       |2,770   |164  |	Camera, Mobile Data, Wi-Fi      |
|CommonsApp             |Education |40,171      |1,341  |28  |	Location, Mobile Data, Wi-Fi      |
|Ground             |Productivity |19,906      |525   |4  |	Camera, Location, Mobile Data, Wi-Fi      |
|OneBusAway             |Maps, Navigation |35,217       |5,564   |126  |	Location, Mobile Data, Wi-Fi     |
|Openscale             |Health, Fitness |27,781       |1,451   |14  |	Bluetooth, Location     |
|OwnTracks             |Travel, Local |14,499       |889   |27  |	Location, Mobile Data, Wi-Fi     |
|PocketHub             |Productivity |29,001       |1,663   |107  |	Mobile Data, Wi-Fi     |
|RadioDroid             |Music, Audio |22,815       |1,735   |23  |	Bluetooth, Mobile Data, Wi-Fi    |
|Threema             |Communication |238,045       |1,931   |54  | Bluetooth, Camera, Location, Mobile Data, Wi-Fi    |
|WordPress-Android             |Productivity |347,897       |3,674   |115  | Camera, Mobile Data, Wi-Fi     |



### Study phases

Our study consists of five phases as explained bellow:


1 - Application selection: 

The dataset includes 10 applications. The gradle build scripts and the AndroidManifest files were adapted to our instrumentation, for instance, to include suitable API dependencies and to allow control permissions (such as for accessing Wi-Fi state). Each application has the test classes of androidTest folder extended with our instrumentation.

2 - Resource control: 

Our instrumentation was implemented for Java and Kotlin. There are particularities according to the screen device, since we use the [UI Automator testing framework](https://developer.android.com/training/testing/other-components/ui-automator).

3 - Fault seeding:

We generated mutants and manually injected faults using common bug-fix patterns.

4 - Test suite execution: 

Requirements: A device running Android 10. This device must be rooted in order to install [Sensor Disabler app](https://play.google.com/store/apps/details?id=com.mrchandler.disableprox&hl=en&gl=US) to manage the sensors. Also, the [Lens Cap app](https://play.google.com/store/apps/details?id=com.ownzordage.chrx.lenscap&hl=en_IN&gl=US) must be installed to allow camera control.
The host machine must have Python 3 installed and the Android SDK and the Java SDK environment system variables properly set.


5 - SBFL analysis: 

All test reports are available in TestResults. The frequent item set mining analysis was performed using the Apriori implementation of the [mlxtend library](https://pypi.org/project/mlxtend/).


### Contact

- [Euler Marinho](http://labsoft.dcc.ufmg.br/doku.php?id=people:students:euler_marinho): eulerhm at dcc dot ufmg dot br 
- [Fischer Ferreira](http://labsoft.dcc.ufmg.br/doku.php?id=people:students:fischer_ferreira): fischer dot ferreira at unifei dot edu dot br
- [Eduardo Figueiredo](http://www.dcc.ufmg.br/~figueiredo)
