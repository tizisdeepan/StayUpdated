# StayUpdated

This is an Android App that uses the free version of newsapi.org news API to show the current headlines along with the details of any headline one click away.

## Building the App

First, Extract download and extract StayUpdated.zip

### Android Studio (Recommended)

(These instructions were tested with Android Studio version 3.4.1)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the extracted repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the Sample App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

### Gradle

* Install the debug APK on your device `./gradlew installDebug`
* Start the APK: `<path to Android SDK>/platform-tools/adb -d shell am start com.deepan.stayupdated.list.view/com.deepan.stayupdated.list.view.HeadlinesActivity`
