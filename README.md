## Android Gravatar

A simple drop-in class for requesting Gravatar profile photos in Android.

### Installation
Drop the `src/Gravatar.java` file into your project. Update the package 
as needed.

### Usage
```
Gravatar.getInstance().getAvatar("user@domain.com", new Gravatar.Listener() {
	public void onGravatarReady(String emailAddress, Bitmap avatar) {
		if ( avatar != null ) {
			// yourImageView.setImageBitmap(avatar);
		}
	}
});
```

### Examples

An example project can be found in the `sample/` directory.

![Android Gravatar Sample Project](screenshot.png)

### License

The MIT License (MIT)

Copyright (c) 2014-2015, Andrey Butov. All Rights Reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
