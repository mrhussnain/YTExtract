# YTExtract

Signup https://rapidapi.com/mrhussnain3/api/ytextract to get API_KEY in order to use this api.

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
		maven { url 'https://raw.github.com/zeeshanejaz/unirest-android/mvn-repo' }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file:

```gradle
dependencies {
	...
	implementation 'com.github.mrhussnain:YTExtract:v3.3'
}
```

## Sample Code

_Check Sample app project for complete implementation of this api. [API DEMO](https://github.com/mrhussnain/YTExtract/raw/master/sample.apk)_

Add permission to your `AndroidManifest.xml` file:

``` xml
<uses-permission android:name="android.permission.INTERNET"/>
```

Place this code in any `activity` or `java` class.

``` java
 String ytUrl = "demo-yt-url";
 new YTExtract(API_KEY){

     @Override
     public void onExtractionComplete(YTModel model) {

         // Use the model data as you like for eg...
         // I'm storing first mp3 and video download Url in string...
	 
         String mp3Url = model.getMP3().get(0).getDownloadUrl();
         String videoUrl = model.getVideo().get(0).getDownloadUrl();
     }
 }.extract(ytUrl);
```


## License

* [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

```
Copyright 2019 CreativeApps Inc

This program is free software: you can redistribute it and/or modify
it under the terms of the Apache License version 2 as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the Apache License version 2 License
along with this program.  If not, see <https://www.apache.org/licenses/LICENSE-2.0>.
```

