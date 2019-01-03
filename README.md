# Android-GmailAppClone

_My attempt to recreate the Android Gmail App, together with some of my personal touch._

## Screenshot

<img src ="https://user-images.githubusercontent.com/39665412/50645624-f7585c00-0fae-11e9-8c5a-e6c90c7d8520.png" width = "250" />

## Features

+ RecyclerView
    1. Swipe-to-archive functionality for individual recyclerview items
    2. Recyclerview scroll-to-top
    3. Swipe-to-refresh data shown
+ Navigation drawer with menu toggle
+ Toggle to show more edittext fields
+ Single Activity,Multiple Fragment
    1. Single toolbar with different menu items for different fragments
    2. Lock navigation drawer and replace hamburger icon for specific fragments

## Libraries/Dependencies

```java
    //Design support library
    implementation 'com.android.support:design:28.0.0'
    //RecyclerView
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    //Volley
    implementation 'com.android.volley:volley:1.1.1'
    //Glide
    implementation 'com.github.bumptech.glide:glide:4.8.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.8.0'
    //CircularImageView
    implementation 'de.hdodenhof:circleimageview:2.2.0'
```

## License

```tex
MIT License

Copyright (c) 2018 Nicholas Gan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```