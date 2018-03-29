# Smart Amravati


It is an application to provide different utilities to the citizen.
The services include Bill payments, Tax payments, Generation of different certificates. Also include feature like push notification and complaint registration.
This project is developed using Android Studio, Java.
For backend Google Firebase, Google Cloud, Git, etc are used.

## SYSTEM DEVELOPMENT TOOL

### ANDROID STUDIO
### FIREBASE

## SYSTEM FEATURES:

### Ease of sending notification:
The ability to send instant, non-intrusive notifications to users is so desired that it is one of the major reasons why many businesses want to have a mobile app in the first place. The notifications are of two types: push and in-app notifications. They both are exciting alternatives for communicating with app users in a less intrusive manner.

### Making use of mobile device feature:
Mobile apps have the advantage of utilizing features of a mobile device like camera,      contact list, GPS, phone calls, accelerometer, compass, etc. For instance, users completing a form on a banking app might need to submit their photograph for completion of the process. The app can let users take help of the camera of their mobile device to capture and submit a photograph.

### Availability to work offline:
It is probably the most fundamental difference between a mobile website and an app.
Although apps too might require internet connectivity to perform most of their tasks, they can still offer basic content and functionality to users in offline mode. The app can provide features like tax calculation, installment calculation, and determination of loan limit. These features can work even without the help of an internet connection.
	
### Works faster than websites:

### Brand Presence:

## Screenshots

<img  style='margin:300px;' src="https://user-images.githubusercontent.com/33481219/35508070-08f88192-0515-11e8-8322-099fb5915676.png" width="500" >

## Code Example

package com.example.ash.smartamravati.activity.other;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class SmartAmravati extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}


## Motivation


In the era of digitalization every information must be available to our fingertips to access it from anywhere around the globe. As of now the digitalization of everything is becoming an essential part of modern culture. Until now we were using the traditional method of data-warehousing which was too time consuming and complex. Thus in next step the data was globalized by various service providers like Google, Wikipedia which have the accessible database but not accessible to simplest extent, because using an interface is an easy task rather than searching in the vast data-flow. 
That's why the innovative way of personalization by using the interface provided through application. Thus we have decided to develop such environment that will come under the "Digital India" initiative proposed by Honourable P.M. Mr Narendra Modi. So we are developing an application to miniaturise the administrative load of Municipal Corporation of Amravati, this will serve various needs of local residents. 


## Contributors

Abhishek Bande
