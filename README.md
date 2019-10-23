# YTExtract

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	implementation 'com.github.mrhussnain:YTExtract:v2.0'
}
```

## Sample Code

Place this code in any activity or java class.

```
 String ytUrl = "demo-yt-url";
 new AsyncTask<String, Void, Void>() {

        YTModel model;

        @Override
        protected Void doInBackground(String... strings) {

            // This will extract all media and save to YTModel... might take some time :)

            YTExtract ytExtract = new YTExtract(API_KEY);
            model = ytExtract.Extract(strings[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            if (model!=null) {

                // Use the model data as you like for eg...
                String mp3url = model.getMP3().get(0).getDownloadUrl();
            }
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }.execute(ytUrl);
```

