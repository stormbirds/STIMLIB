# STIMLIB
stormbirds im demo for android by netty with spring boot

# How to use
## Step 1
## Gradle
###  1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
###  2. Add the dependency
```
dependencies {
	        implementation 'com.github.stormbirds:stimlib:v0.0.1'
}
```
## Maven
###  1. Add the JitPack repository to your build file
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
###  2. Add the dependency
```
	<dependency>
	    <groupId>com.github.stormbirds</groupId>
	    <artifactId>stimlib</artifactId>
	    <version>Tag</version>
	</dependency>
```
## Step 2
Add BaseApplication file
```
package cn.stormbirds.stimdemo.base;

import cn.stormbirds.stimlib.STIMApp;

public class BaseApplication extends STIMApp {

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
```
 Modify AndroidManifest.xml file and add this line
```
    <application
        android:name=".base.BaseApplication"
        ......
    </application>
```
in MainActivity or any where you want use im implements IM_EventListener
example /app/src/main/java/cn/stormbirds/stimdemo/MainActivity.java
