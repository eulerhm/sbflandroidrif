## Artifacts of the paper "Fault Localization in Mobile Applications: An Empirical Study on Resource Interaction Failures"

The dataset includes 10 mobile applications. These applications are from different categories named according to the Play Store categories, with a large variation of size and test code size. For instance, applications vary from 14,499 lines of code (OwnTracks) to more than 347,000 lines of code (WordPress-Android). Similarly, test code size vary between 525 lines of code (Ground) and 5,564 (OneBusAway). Moreover, the number of test cases vary from 4 (Ground) to 164 (AnkiDroid). The number of commits vary between 21 (Threema) to 68,148 (WordPress-Android).

|Name 	        |Category  | LOC|	Test LOC	|Test Cases|Resources|	Commits	  |
|-------        |-------|---------|---------|-------|--------|-------                   |
|[AnkiDroid](pages/ankidroid.md)             |Education |158,607       |2,770   |164  |	Camera, Mobile Data, Wi-Fi      |13,643      |
|[CommonsApp](pages/commonsapp.md)             |Education |40,171      |1,341   |28  |	Camera, Location, Mobile Data, Wi-Fi      |6,437     |
|[Ground](pages/ground.md)             |Productivity |19,906      |525   |4  |	Camera, Location, Mobile Data, Wi-Fi      |4,936     |
|[OneBusAway](pages/onebusaway.md)             |Maps, Navigation |35,217       |5,564   |126  |	Location, Mobile Data, Wi-Fi     |2,652      |
|[Openscale](pages/openscale.md)             |Health, Fitness |27,781       |1,451   |14  |	Bluetooth, Location     |2,027      |
|[OwnTracks](pages/owntracks.md)             |Travel, Local |14,499       |889   |27  |	Location, Mobile Data, Wi-Fi     |1,995      |
|[PocketHub](pages/pockethub.md)             |Productivity |29,001       |1,663   |107  |	Mobile Data, Wi-Fi     |3,512      |
|[RadioDroid](pages/radiodroid.md)             |Music, Audio |22,815       |1,735   |23  |	Bluetooth, Mobile Data, Wi-Fi    |1,186      |
|[Threema](pages/threema.md)             |Communication |238,045       |1,931   |54  | Bluetooth, Camera, Location, Mobile Data, Wi-Fi    |21      |
|[WordPress-Android](pages/wordpress.md)             |Productivity |347,897       |3,674   |115  | Camera, Mobile Data, Wi-Fi     |68,148      |



### Study phases

Our study consists of three phases:


1 - Application Selection and Resource Control: 

The dataset includes 10 applications. The gradle build scripts and the AndroidManifest files were adapted to our instrumentation, for instance, to include suitable API dependencies and to allow control permissions (such as for accessing Wi-Fi state). Each application has the test classes of androidTest folder extended with our instrumentation.

For RQ1 and RQ2, we use Pairwise as a sampling strategy. 

2 - Fault Seeding: 

Mutant Generation: We decided to use a prototype tool that implements four of the five operators of the Mothra mutation system. This tool is able to generate mutants for Java code using a set of three mutation operators: AOR, ROR, and LCR.

Bug-fix Patterns: We requested two developers for generating four faulty versions for each application. Each developer focuses on five applications and used five common bug-fix patterns: AS-CE, IF-APC, IF-CC, MC-DAP, and MC-DNP. 

3 - Test Suite Execution and Analysis: 

Requirements: A device or emulator running Android 10. For RQ1 and RQ2, we require a rooted device in order to install [Sensor Disabler app](https://play.google.com/store/apps/details?id=com.mrchandler.disableprox&hl=en&gl=US) to manage the sensors. Also, the [Lens Cap app](https://play.google.com/store/apps/details?id=com.ownzordage.chrx.lenscap&hl=en_IN&gl=US) must be installed to allow camera control.
The host machine must have Python 3 installed and the Android SDK and the Java SDK environment system variables properly set.


We analyzed test reports to identify failed test cases and the coverage reports to get the coverage information. In order to decrease the complexity of the analysis, we use the method coverage data for calculating the Ochiai coefficient of each method in target applications. We generated code coverage information using [JaCoCo](https://www.eclemma.org/jacoco/) that outputs coverage files in HTML and XML format detailing classes, methods, blocks and lines. We also parsed each XML coverage report for filtering the covered methods. We parsed HTML test reports to determine if a test case failed.


There are [detailed instructions for replicability](pages/replicationInstructions.md).


Due to some Github storage policies, some raw content of this repository is hosted on an [external link](https://1drv.ms/u/s!Al0oCdVFAH7thoZo-OAiDqpKjeFIhg?e=lMLFD8).

### Contact

- Euler Marinho: eulerhm at gmail dot com
- Fischer Ferreira: fischer.ferreira at unifei dot edu dot br
- [Eduardo Figueiredo](http://www.dcc.ufmg.br/~figueiredo)
