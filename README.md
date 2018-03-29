# Smart Amravati


It is an application to provide different utilities to the citizen.
The services include Bill payments, Tax payments, Generation of different certificates. Also include feature like push notification and complaint registration.
This project is developed using Android Studio, Java.
For backend Google Firebase, Google Cloud, Git, etc are used.

## SYSTEM DEVELOPMENT TOOL

### ANDROID STUDIO
### FIREBASE

## SYSTEM FEATURES:

###•	Better personalization:
Mobile apps can let users set up their preferences at the start, based on which users can         be served with customized content. Apps can also track and observe user engagement, and use it to offer custom recommendations and updates to the users. Furthermore, they can also identify location of the users in real-time to provide geography-specific content.

###•	Ease of sending notification:
The ability to send instant, non-intrusive notifications to users is so desired that it is one of the major reasons why many businesses want to have a mobile app in the first place. The notifications are of two types: push and in-app notifications. They both are exciting alternatives for communicating with app users in a less intrusive manner.

###•	Making use of mobile device feature:
Mobile apps have the advantage of utilizing features of a mobile device like camera,      contact list, GPS, phone calls, accelerometer, compass, etc. For instance, users completing a form on a banking app might need to submit their photograph for completion of the process. The app can let users take help of the camera of their mobile device to capture and submit a photograph.

###•	Availability to work offline:
It is probably the most fundamental difference between a mobile website and an app.
Although apps too might require internet connectivity to perform most of their tasks, they can still offer basic content and functionality to users in offline mode. The app can provide features like tax calculation, installment calculation, and determination of loan limit. These features can work even without the help of an internet connection.
	
###•	Works faster than websites:
A well-designed mobile app can perform actions much quicker than a mobile website. Apps usually store their data locally on mobile devices, in contrast to websites that generally use web servers. For this reason, data retrieval happens swiftly in mobile apps. Apps can further save users’ time by storing their preferences, and using them to take proactive actions on users’ behalf.

###•	Freedom in Designing:
Even with all the technological advancements in web designing, mobile websites have to rely a lot on browsers to perform even the most elementary functions. Mobile websites depend on browser features like ‘back button,’ ‘refresh button,’ and ‘address bar’ to work. Mobile Apps don’t have any of these restrictions. A mobile app can be designed with a lot of elaborate functions, based on advanced gestures like ‘tap,’ ‘swipe,’ ‘drag,’ ‘pinch,’ ‘hold,’ and more.

###•	New Stream of Conversions:
Mobile apps can be used to acquire both top-of-the-funnel (ToFu) and bottom-of-the-funnel (BoFu) users. For instance, utility apps can bring-in ToFu users, which can be later nurtured into BoFu leads. On the other hand, apps like eCommerce already have BOTF users, who have a higher possibility of converting.

###•	Brand Presence:
Users spend a substantial amount of their time on mobile devices. It’s safe to say that many of the users encounter the apps they’ve installed on their devices, almost every day. This regular encounter can be viewed as a branding opportunity for the apps. Even when users are not actively using a mobile app, they are still reminded of the brand associated with the app. The icon of the app acts like a mini-advertisement for the brand.

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
