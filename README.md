# "The DoToo" - MobDev2022 FinalProject 
#### Authors: Eduard Žurin, Maksimilian Tsenkman, Aleksander Zahharov

## The main idea
We thought that adding a calendar API into your ToDo list will increase a likelyhood of a user completing those goals.

Our app urges you to be proactive and helps you to reach your goals more sequentially.

Creating tasks user can add title, steps and set an alarm time (in case he forgets to do the task). 

The app includes an option to make a contribution to the progress of the mankind and donate a choosen sum to the developers via Google Pay in only two clicks.

## How to run
There is two project that we wanted to merge together (The ToDo and Google Pay Api), but did not succeed.
Googe pay API can be viewed separately.

The ToDo list project is compiled and built simply as any other project.

In order to test GPay project(package name "kotlin") you need to enter your Google account on emulator(or you physical devise) in "Settings" -> "Passwords & Accounts".
And you should have at least one real card attached to your account. The GPay app is in the test mode, so no real money will be withdrawn.

## Difficulties' analysis
We have encountered many difficulies along the way.
Most significant were Graddle compatibility. This is the reason we were not able to merge projects together.

Aditionally, connecting checkboxes to the delete button. We used fragments, therefore we had to take every view within the recyclerView into account.
There were also difficulties making an app open with a click on a notification (in future we want to finaly add this option).

Implementing the GPay API was also a chellenge because Google's video gides and trial repository contains slightly different information than it is written in the oficial documentation. Some things now seem obvious, but it is hard for a newcomer to understand.

The initial idea was to create a WiFi Heatmap and Eduard spent a lot of time trying to implement it, but realised that this is too complicated.

## Who did what
Eduard Žurin: Room DB implementation

Maksimilian Tsenkman: The todo list implementation, design

Aleksander Zahharov: Google Pay implimentation, documentation

## Resourses
Oficial Google Pay try-yourself app repo: https://github.com/google-pay/android-quickstart/tree/475b218072b4568bcc07db478f3084fe1e84ad49
