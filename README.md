[![](https://jitpack.io/v/lucas-asu/carousel-picker-android.svg)](https://jitpack.io/#lucas-asu/carousel-picker-android)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
# Carousel Picker Library

This is a library to help any Android developer to create a Carousel with a list of images and descriptions.

<img src="https://raw.githubusercontent.com/lucas-asu/carousel-picker-android/master/img/sample.png" height="300">
<img src="https://raw.githubusercontent.com/lucas-asu/carousel-picker-android/master/gif/picker01.gif" height="300">
<img src="https://raw.githubusercontent.com/lucas-asu/carousel-picker-android/master/gif/picker02.gif" height="300">
<img src="https://raw.githubusercontent.com/lucas-asu/carousel-picker-android/master/gif/picker03.gif" height="300">
<img src="https://raw.githubusercontent.com/lucas-asu/carousel-picker-android/master/gif/picker04.gif" height="300">

## Gradle Dependency

Add this in your root build.gradle file at the end of repositories:
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency : 
```java
dependencies {
	       compile 'com.github.lucas-asu:carousel-picker-android:v1.0'
	}
```
Sync the gradle and that's it! :+1:

## Features : 
* Supports icons and text as items of the picker.
* Gives a nice carousel view of the items.
* Listener to monitor the current selected item.

## Usage

### XML : 

```xml
<com.ldealmei.libs.carousel.CarouselPicker
        android:id="@+id/carousel_picker"
        android:layout_width="match_parent"
        android:layout_height="150dp"
        android:background="@color/background2"
        app:customDescriptionColor="@color/description_color"
        app:displayIndicator="true"
        app:customIndicator="@drawable/ic_indicator"
        app:customIndicatorColor="@color/indicator_color"
        app:customIndicatorSize="@dimen/indicator_size"/>
```

```items_visible```  represents the number of pages visible on the screen. Three, five and seven are the available options.


### Java :
Carousel picker supports both images and text or a mixture of both !
```java
final CarouselPicker carouselPicker = (CarouselPicker) findViewById(R.id.carousel_picker);

//Creating a list of itens
List<ItemPicker> itens = new ArrayList<>();
itens.add(new ItemPicker(R.drawable.ic_banana,"Banana"));
itens.add(new ItemPicker(R.drawable.ic_grapes,"Grapes"));
itens.add(new ItemPicker(R.drawable.ic_orange,"Orange"));

//adding a list and building the carousel
carouselPicker.addList(itens).build(this);
 
```

## Listeners :

You can add many listener you want for item selection:

```java
 carouselPicker.addListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
                
    }
});
```
