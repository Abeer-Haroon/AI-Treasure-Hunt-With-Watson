# AI Treasure Hunt Game With Watson

## Learning Objectives
Android Game using IBM Watson Visual Recognition service, Text-to-Speech Service and App ID Service. This is a Treasure Hunt Game, in which the player will be given the hints to find something and take a photo of it to pass a level.  To give the hints, Text-to-Speech Service is used. To determine if the player passes the level, custom visual recognition models are built in Watson Studio with datasets of images. This is integrated within the application. The models are trained with Watson Studio. Furthermore, App ID service is used to add authentication to the game, to track players of the game.

## Demo

#### Login


![Demo](images/login.gif)


#### Authentication


![Demo1](images/loggingin.gif)


#### Level 1: Visual Recognition

 
![Demo3](images/level1.gif)


#### Level Passed

 
![Demo4](images/PassedLevel.gif)

## Prerequisites

1. Sign up for an [IBM Cloud account](https://cloud.ibm.com/registration/).
2. Install [Android Studio](https://developer.android.com/studio/)
3. A dataset for the visual recognition model. Use these Sample [Datasets](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/datasets) or any dataset of your choice.

## Estimated time

This tutorial takes about 20 minutes to complete if you already have an IBM cloud account set up.

 ## Steps
 
First you set up the services on IBM Cloud. Then you set up the client application.

1. Create an instance of the [Watson Visual Recognition](https://www.ibm.com/watson/services/visual-recognition/) service and get your credentials:
   - Go to the [Watson Visual Recognition](https://cloud.ibm.com/catalog/services/visual-recognition) page in the IBM Cloud Catalog.
   - Log in to your IBM Cloud account.
   - Click **Create**.
   - Click **Service Credentials** > New Credentials > Add
   
![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath3.png)

   - Copy the `apikey` value, or copy the `username` and `password` values if your service instance doesn’t provide an `apikey`.
   - Copy the `url` value.

![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath4.png)

2. Create an instance of the App ID service and get your credentials:
   - Go to the [App ID](https://cloud.ibm.com/catalog/services/app-id) page in the IBM Cloud Catalog.
   - Click **Create**.
   - Click **Service Credentials** > New Credentials > Add
   - Copy the `apikey` value, or copy the `username` and `password` values if your service instance doesn’t provide an `apikey`.
   - Copy the `url` value.
   
3. Create an instance of the App ID service and get your credentials:
   - Go to the [Text-to-Speech](https://cloud.ibm.com/catalog/services/text-to-speech) page in the IBM Cloud Catalog.
   - Click **Create**.
   - Click **Service Credentials** > New Credentials > Add
   - Copy the `apikey` value, or copy the `username` and `password` values if your service instance doesn’t provide an `apikey`.
   - Copy the `url` value.
   
4. Create an instance of the Watson Studio
   - Go to the [Watson Studio](https://cloud.ibm.com/catalog/services/watson-studio?bss_account=e366b6e4fb004c5eaccfbe7042b670a4) page in the IBM Cloud Catalog.
   - Click **Create**.

![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath12.png)

   - Click **Get Started**.
 
 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath13.png)
 
    
   - Click **Create a Project** > Visual Recognition
 
 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath14.png)
 

   - Name the project. Select **Storage** > cloud object storage. **Create**
 
4. Creating Visual Recognition Models 

   - Click **Create a Class**. Give it a name. Create at least 2 classes. In this tutorial, 5 classes: "Trees", "BurjKhalifa",   "BurjArab", "MiracleGarden", and "GlowGarden".
   - Upload the zip files for the dataset. **Upload to Project** > Browse > Choose folder.
   - Open the class you want to add the dataset to. Click on the uploaded zip folder on the right > **Add to Model**

 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath19.png) 
 
   - Once this is added, Click **Train Model**. This will take some time.
   
![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath22.png)   
    
 To change the name of the model, edit the following field:
 
 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath23.png) 
 
 **Updated name of the Model**
 
 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath24.png) 

5. Go back to Projects 

![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath25.png) 

  - Click on the Project Name.
  
![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ath26.png) 

  - Copy the Model ID provided. 
 
**Set up the client application: Android App**

* Clone this repo.
* Start **Android Studio** and **open project**.
* In Android Studio under **Gradle Scripts/build.gradle (Module:app)**. Add 

For App ID 

``` 
    implementation 'com.github.ibm-cloud-security:appid-clientsdk-android:5.0.0'   
``` 
Android SDK for IBM Watson

``` 
    implementation 'com.ibm.watson.developer_cloud:android-sdk:0.5.0'  
```
Java SDK for IBM Watson

``` 
    implementation 'com.ibm.watson.developer_cloud:java-sdk:6.13.1' 
``` 
   
in the **dependencies block**.

 **App ID**
 
 
* Add this in defaultConfig in the same gradle file
 
 ``` 
    manifestPlaceholders = ['appIdRedirectScheme': android.defaultConfig.applicationId]
   ``` 
 
 * Click on **Sync Now**
 
**Manifest File**

Add the following permissions to the manifest file:

```
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.CAMERA" />
```

**Credentials**

1. Add the tenant ID from App ID credentials in the following line of code in Activity_Login:

```
AppID.getInstance().initialize(getApplicationContext(), "tenant id", AppID.REGION_US_SOUTH); 

```

2. Add the Credentials for Visual Recognition.

   Edit the api key in strings.xml file 

``` <string name="api_key">api key</string> ```

3. Add the Credentials for Text-to-Speech.

   Edit the api key in strings.xml file 

``` <string name="api_keyTTS">api key for text to speech</string> ```

   Add the url in the code below from service credentials (for all Level Activities under **speakhint** ):
   
```
     public void speakhint() {
        IamOptions options = new IamOptions.Builder()
                .apiKey(getString(R.string.api_keyTTS))
                .build();

        textToSpeech = new TextToSpeech(options);


        //Add the url from service credentials
        textToSpeech.setEndPoint("add url here");

        new SynthesisTask().execute(hint);
```



4. Add the Model ID in the following line of code in all Level Activities(Level1, Level2, Level3, Level4 and Level5).
                        
```
ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                        .imagesFile(imagesStream)
                        .imagesFilename(photoFile.getName())
                        .threshold((float) 0.6)
                        .classifierIds(Arrays.asList("Model ID"))
                        .build();
```

Once you’ve followed the instructions above to add credentials (Model ID, api keys and tenant ID), You're Done! Run the app. Play the Game!

## Code

**App ID Login**

The following code was added in the Activity_Login to implement App ID for user authentication. In this game, we're using this service to track the users of the game since it adds authentication method to the app.

```
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppID.getInstance().initialize(getApplicationContext(), "tenant id", AppID.REGION_US_SOUTH);
        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_login.setVisibility(View.GONE);
             
                LoginWidget loginWidget = AppID.getInstance().getLoginWidget();
                loginWidget.launch(Activity_Login.this, new AuthorizationListener() {
                    @Override
                    public void onAuthorizationFailure (AuthorizationException exception) {
                        //Exception occurred
                    }

                    @Override
                    public void onAuthorizationCanceled () {
                        //Authentication canceled by the user
                    }

                    @Override
                    public void onAuthorizationSuccess (AccessToken accessToken, IdentityToken identityToken, RefreshToken refreshToken) {
                        //User authenticated

                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
```


**Visual Recognition**

* In level activities, under **onActivityResult** method, The following code was added to implement visual recognition service:

```
@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            final Bitmap photo = mCameraHelper.getBitmap(resultCode);
            photoFile = mCameraHelper.getFile(resultCode);
            //  mImageView.setImageBitmap(photo);

            backgroundThread();

        }
    }
```

* In **backgroundThread** method, the following code was added for making the network call, parsing the result from visual recognition service to determine whether to start next activity (Level passed) or not (Level Failed). 

```
private void backgroundThread(){

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                InputStream imagesStream = null;
                try {
                    imagesStream = new FileInputStream(photoFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                        .imagesFile(imagesStream)
                        .imagesFilename(photoFile.getName())
                        .threshold((float) 0.6)
                        .classifierIds(Arrays.asList("Model Number"))
                        .build();
                ClassifiedImages result = mVisualRecognition.classify(classifyOptions).execute();
                Gson gson = new Gson();
                String json = gson.toJson(result);
                Log.d("json", json);
                String name = null;
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("images");
                    JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                    JSONArray jsonArray1 = jsonObject1.getJSONArray("classifiers");
                    JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
                    JSONArray jsonArray2 = jsonObject2.getJSONArray("classes");
                    JSONObject jsonObject3 = jsonArray2.getJSONObject(0);
                    name = jsonObject3.getString("class");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final String finalName = name;




                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("Detected Image: " + finalName);

                        Log.d(TAG, "Ans: " + finalName);

                        if(finalName.equals("Trees")){
                            Intent mass = new Intent(Main2Activity.this, Main3Activity.class);
                            startActivity(mass);
                        }
                        else {

                            Toast toast = Toast.makeText(getApplicationContext(), "Sorry. Try Again!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.show();

                        }


                    }
                });


            }
        });

    }
```

In the above code, the name of the class should be edited in the following condition in the place of Trees, depending on the names of classes you added in the visual recognition model.

```
 if(finalName.equals("Trees")){
                            Intent mass = new Intent(Main2Activity.this, Main3Activity.class);
                            startActivity(mass);
                        }
                        
```
**Text-to-Speech**

* In level activities, the following code was added to implement Text-to-Speech service:

```
 public void speakhint() {
        IamOptions options = new IamOptions.Builder()
                .apiKey(getString(R.string.api_keyTTS))
                .build();

        textToSpeech = new TextToSpeech(options);


        //Add the url from service credentials
        textToSpeech.setEndPoint("add url here");

        new SynthesisTask().execute(hint);

    }
    
 private class SynthesisTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text(params[0])
                    .voice(speakLanguage)
                    .accept(SynthesizeOptions.Accept.AUDIO_WAV)
                    .build();
            player.playStream(textToSpeech.synthesize(synthesizeOptions).execute());
            return "Did synthesize";
        }
    }

```

#### Architecture

 ![](https://github.com/Abeer-Haroon/AI-Treasure-Hunt-With-Watson/blob/master/images/ArchitectureDiagram.png) 

## Summary
This tutorial introduced visual recognition models by showing how to build a fun treasure hunt game. You started by creating services on the IBM Cloud platform. Then you built the application on the client side and there you have it!

## References

You can use Android SDK to use other IBM Watson services in your android app at: https://github.com/watson-developer-cloud/android-sdk

Do more with App ID: https://github.com/ibm-cloud-security/appid-clientsdk-android

Developing an image classifier: https://github.com/sudoalgorithm/Developing-A-Image-Classifier-Using-Watson-Visual-Recognition-On-Watson-Studio


## Contributors

[Kunal Malhotra](https://github.com/sudoalgorithm) 

[Ayush Maan](https://github.com/ayushmaan6) 

[Anchal Bhalla](https://github.com/anchalbhalla/) 


