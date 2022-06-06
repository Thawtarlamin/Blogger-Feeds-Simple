# Blogger-Feeds-Simple

# Usage Library
>settings.gradle ထည့်‌ပေးပါ

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
>build.gradle ထည့်‌ပေးပါ
```
dependencies {
	        implementation 'com.github.Thawtarlamin:Blogger-Feeds-Simple:1.0.0'
	}
```

>MainActivity.java
```
new BloggerFeeds(this, "http://sofiafoodparadise.blogspot.com/", new BloggerFeeds.onComplete() {
            @Override
            public void onComplete(Models models) {
                Log.d("my-test", "Title is: "+models.getTitle());
                Log.d("my-test", "Image is: "+models.getImage());
                Log.d("my-test", "Content is: "+models.getContent());
            }
        }, new BloggerFeeds.onError() {
            @Override
            public void onError(Exception e) {
                Log.d("my-test", "onError: "+e.getMessage());
            }
        });
```

> အသုံးပြုသူများအဆ‌င်ပြေစေရန်ရည်ရွယ်၍ ထုတ်ပေးထားခြင်းဖြစ်ပါသည်။

# Creator By Thaw Tar La Minn


# 😘😘😘😘
# Donate Paypal
```
nyeineikhin.nek@gmail.com
```

>Thank you for  All User
