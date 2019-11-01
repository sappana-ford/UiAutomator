# UiAutomator


Que: How to open UiAutomator view?

Ans: cd {android-sdk-location}/tools/bin/ and run command {./uiautomatorviewer} in terminal.

This will open up a window with current state of the application on the device. This window will have the details of the entire layout of the page

Note: <br/>
BaseTest is configured for both Ford and Lincoln.<br/>
TARGET_APPLICATION_ID should be modified to open FordPass or LincolnWay app.<br/>
LincolnWay needs more timeout time. Please provide accordingly.<br/>
Most of the operations can be performed using functions exposed in BaseTest.<br/>
It is preferable(performance wise) if flow tests are written using UiAutomator.<br/>


*** Imp Note: Please free to modify/optimize.
