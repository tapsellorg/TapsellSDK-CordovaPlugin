<div dir="rtl">
<h1>مستندات راه‌اندازی تبلیغات تپسل در Cordova</h1>
&nbsp; (نسخه پلاگین: ۳.۰.۴۵ – نسخه اندروید: ۳.۰.۳۶ – نسخه iOS:
<span style="color: #ffffff;">ـ</span>۳.۰.۵ ) توجه پلاگین تپسل، در نسخه‌های
۳.۰.۰ و بالاتر cordova قابل استفاده است. اگر نسخه cordova شما خارج از این محدوده باشد، امکان استفاده از این پلاگین را نخواهید
داشت.نسخه کتابخانه اندروید مورد نیاز
جهت استفاده از SDK تپسل می‌بایست از build tools نسخه 23 و بالاتر استفاده کنید. فهرست مطالب
<ul>
	<li>
		<a href="#rewarded">پیاده‌سازی تبلیغات ویدئویی (Interstitial/Rewarded Video) و بنری تمام صفحه (Interstitial Banner) در پروژه Cordova</a>
	</li>
	<li>
		<a href="#standard-banner">پیاده‌سازی تبلیغات بنری استاندارد در پروژه Cordova </a>
	</li>
	<li>
		<a href="#advanced-options">موارد پیشرفته‌تر در SDK</a>
	</li>
	<li>
		<a href="https://github.com/tapselladnet/TapsellSDK_v3_Cordova_Sample">پروژه نمونه</a>
	</li>
</ul>

<!-- <div id="android-init"></div> -->

<!-- <div id="ios-init"></div> -->

<div id="rewarded">
<h2>پیاده‌سازی تبلیغات ویدئویی (Interstitial/Rewarded Video) و بنری تمام صفحه (Interstitial Banner) در پروژه Cordova</h2>
&nbsp;
<h3>گام ۱: افزودن پلاگین تپسل به پروژه cordova</h3>
ابتدا command prompt را باز کرده و به پوشه مربوط به پروژه خود بروید. سپس دستور زیر را در آن اجرا کنید.
<pre dir="ltr"> cordova plugin add tapsell-v3-cordova-plugin</pre> با انجام این دستور، پلاگین تپسل در پروژه شما افزوده می‌گردد. &nbsp;
<h3>گام ۲: دریافت کلید تپسل</h3>
وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.
<p style="text-align: center;">
	<a href="https://dashboard.tapsell.ir">
		<button>ورود به داشبورد تپسل</button>
	</a>
</p>
&nbsp;
<h3>گام ۳: شروع کار با SDK تپسل</h3>
در گام دوم با ثبت برنامه خود در پنل تپسل، یک عبارت با عنوان کلید تپسل در اختیار شما قرار می‌گیر. برای استفاده از تپسل ابتدا
باید این کلید را به برنامه خود معرفی نمایید. لذا در بخش onDeviceReady فایل javascript خود، تابع زیر را با کلید تپسل برنامه
خود فراخوانی نمایید.
<pre dir="ltr">tapsell.initialize(appKey);</pre> ورودی
<code>appKey</code> کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید. &nbsp;
<h3>گام ۴: دریافت تبلیغ</h3>
نمایش یک تبلیغ ویدئویی در اپلیکیشن به دو صورت ممکن است صورت پذیرد. یک روش، نمایش تبلیغ بصورت استریم می باشد. در این حالت،
همزمان که کاربر درحال مشاهده بخشی از تبلیغ است، ادامه آن از اینترنت لود می گردد. ممکن است به دلیل کندی سرعت اینترنت، در این
حالت کاربر با مکث های متعددی در هنگام دریافت و مشاهده تبلیغ مواجه شود. برای اینکه کاربر در هنگام نمایش تبلیغ منتظر نماند
و تجربه کاربر در استفاده از اپلیکیشن بهبود یابد،روش دیگری نیز در SDK تپسل تعبیه شده است که در آن ابتدا فایل ویدئوی تبلیغاتی
بطور کامل بارگذاری شده و سپس تبلیغ نمایش داده می شود. همچنین در تپسل، تبلیغ می تواند در ناحیه‌های مختلفی از برنامه شما (مانند
فروشگاه، انتهای هر مرحله، ابتدای مرحله جهت دریافت امتیاز دوبرابر، دریافت بنزین/لایف و ...) پخش شود. در تپسل به این ناحیه‌ها
<code>zone</code> گفته می شود. ناحیه‌های هر اپلیکیشن در پنل تپسل تعریف می شوند. با اجرای تابع زیر، می توانید یک درخواست تبلیغ به تپسل ارسال
کرده و یک تبلیغ دریافت نمایید:
<pre dir="ltr"><span style="color: #000000;">tapsell.requestAd(zoneId, cached, </span><span style="color: #003366;">function</span><span style="color: #000000;">(result){
    <span style="color: #003366;">if</span>(result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'onAdAvailable'</span>)
    { 
        zoneId = result[<span style="color: #999999;">'zoneId'</span>]; 
        adId = result[<span style="color: #999999;">'adId'</span>]; <span style="color: #008000;">// store this to show the ad later</span>
    }
    <span style="color: #003366;">else if</span>( result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'onNoAdAvailable'</span> )
    {
        zoneId = result[<span style="color: #999999;">'zoneId'</span>];
    }
    <span style="color: #003366;">else if</span>( result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'onNoNetwork'</span> )
    {
        zoneId = result[<span style="color: #999999;">'zoneId'</span>];
    }
    <span style="color: #003366;">else if</span>( result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'onError'</span> )
    {
        zoneId = result[<span style="color: #999999;">'zoneId'</span>];
        error = result[<span style="color: #999999;">'error'</span>]; <span style="color: #008000;">// description of error</span>
    }
    <span style="color: #003366;">else if</span>(result['action']==<span style="color: #999999;">'onExpiring'</span>)
    { 
        zoneId = result[<span style="color: #999999;">'zoneId'</span>];
        adId = result[<span style="color: #999999;">'adId'</span>];
        <span style="color: #008000;">// indicates that this ad cannot be shown anymore, you should </span>
<span style="color: #008000;">        // request a new add</span>
    } 
});
</span></pre> هر درخواست شامل یک ورودی
<code>zoneId</code> است که برای استفاده از
<code>zone</code> پیش فرض می توانید از مقدار
<code>null</code> و یا یک رشته خالی استفاده نمایید. اطلاعات بیشتر درباره zone را می توانید از تیم فنی تپسل دریافت کنید. ورودی cached یک متغیر
bool (با مقدار True/False) می باشد که نشان می دهد که آیا تبلیغ باید ابتدا دانلود شده و سپس به کاربر نشان داده شود یا خیر.
کش کردن ویدئو تنها در ناحیه‌هایی که کاربر با
احتمال زیادی پس از باز کردن اپلیکیشن تبلیغ آن را مشاهده می‌کند، از تبلیغ Cached استفاده کنید. جهت توضیحات بیشتر درباره روش
انتخاب متد دریافت مناسب،
<a href="https://answers.tapsell.ir/?ht_kb=cached-vs-streamed">اینجا</a> را مطالعه کنید. تابع داده شده به عنوان
<code>callback</code> نتیجه درخواست تبلیغ را در
<code>result</code> به شما بر‌می‌گرداند. نتیجه درخواست در این متغیر و با کلید
<code>action</code> آمده است. همانطور که در کد فوق ملاحظه می‌کنید، در صورتی‌که
<code>action</code> برگردانده شده برابر با
<code>onAdAvailable</code> باشد، می‌بایست شناسه تبلیغ (
<code>adId</code>) را برای نمایش آن ذخیره کنید. در صورتی که
<code>action</code> بازگردانده شده
<code>onExpiring</code> باشد، بدین معناست که تبلیغ دریافت شده دیگر معتبر نیست و زمان زیادی از دریافت آن گذشته است و می‌بایست یک تبلیغ جدید از تپسل
دریافت نمایید. توضیحات مقادیر مختلف متناظر با کلید
<code>action</code> و شرایط اجرا شدن آن‌ها در ادامه در جدول ۱ آمده است. &nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%">
	<caption>جدول ۱ اکشن های دریافت نتیجه درخواست تبلیغ</caption>
	<tbody>
		<tr style="border-bottom: 1px solid #ddd;">
			<th width="40%">آرگومان action (سایر پارامترها)</th>
			<th width="60%">توضیحات (زمان اجرا)</th>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="40%">onError (zoneId, error)</td>
			<td>هنگامی که هر نوع خطایی در پروسه‌ی دریافت تبلیغ بوجود بیاید</td>
		</tr>
		<tr style="background-color: #f2f2f2;">
			<td dir="ltr" width="40%">onAdAvailable (zoneId, adId)</td>
			<td width="60%">زمانی که تبلیغ دریافت شده و آماده‌ی نمایش باشد.</td>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="40%">onNoAdAvailable (zoneId)</td>
			<td width="60%">در صورتی که تبلیغی برای نمایش وجود نداشته باشد.</td>
		</tr>
		<tr style="background-color: #f2f2f2;">
			<td dir="ltr" width="40%">onNoNetwork (zoneId)</td>
			<td width="60%">زمانی که دسترسی به شبکه موجود نباشد.</td>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="40%">onExpiring (zoneId, adId)</td>
			<td width="60%">زمانی که تبلیغ منقضی شود. هر تبلیغ مدت زمان مشخصی معتبر است و در صورتی که تا قبل از آن نمایش داده نشود منقضی شده و دیگر
				قابل نمایش نخواهد بود.</td>
		</tr>
	</tbody>
</table>
&nbsp;
<h3>گام ۵: نمایش تبلیغ</h3>
جهت نمایش تبلیغ، نیاز به شناسه یک تبلیغ دارید که پس از فراخوانی
<code>tapsell.requestAd</code> دریافت می‌کنید. جهت نمایش تبلیغ، از تابع زیر استفاده نمایید. (این تابع حداکثر یک بار برای هر شناسه تبلیغ قابل اجراست):
<pre dir="ltr">tapsell.showAd(adId, disable_back, immersive_mode, rotation_mode , show_dialog, function(result){
        if(result['action']=='onOpened')
        {
            zoneId = result['zoneId']; 
            adId = result['adId']; // id of the found ad
            console.log('tapsell onOpened');
        }
        else if(result['action']=='onClosed')
        {
            zoneId = result['zoneId']; 
            adId = result['adId']; // id of the found ad
            console.log('tapsell onClosed');
        }
    }
);</pre> ورودی
<code>adId</code> شناسه تبلیغ است که پس از درخواست تبلیغ آن را دریافت نمودید. ورودی‌های
<code>disable_back</code> و
<code>immersive_mode</code> از نوع
<code>boolean</code> هستند که جهت قفل کردن کلید back گوشی در هنگام نمایش تبلیغ rewarded و همینطور نمایش تبلیغ در حالت Immersive Mode (عدم نمایش
دکمه‌های روی اسکرین و نمایش ویدئو بصورت تمام صفحه در اندروید 4.4 و بالاتر) بکار می‌روند. ورودی
<code>show_dialog</code>، از نوع
<code>boolean</code> است و تعیین کننده این موضوع است که آیا در تبلیغات جایزه‌دار، باید در زمان استفاده کاربر از دکمه back پیغام اخطار به وی نشان
داده شود یا خیر. ورودی
<code>rotation_mode</code> برای تعیین جهت‌ نمایش ویدئو در دستگاه ( Orientation ) بکار می‌رود و مقادیر مختلف قابل استفاده برای آن در جدول ۲ آمده است.
&nbsp;
<table style="text-align: center; border-collapse: collapse;" width="100%" cellpadding="6">
	<caption>جدول ۲ مقادیر قابل استفاده برای rotation_mode</caption>
	<tbody>
		<tr style="border-bottom: 1px solid #ddd;">
			<th width="50%">مقدار</th>
			<th width="50%">توضیحات</th>
		</tr>
		<tr style="background-color: #fefefe;">
			<td style="text-align: center;" dir="ltr" width="50%">tapsell_rotation_locked_portrait</td>
			<td width="50%">
				<div style="text-align: center;" align="right">نمایش ویدئو در حالت Portrait</div>
			</td>
		</tr>
		<tr style="background-color: #f2f2f2;">
			<td dir="ltr" width="50%">tapsell_rotation_locked_landscape</td>
			<td width="50%">
				<div style="text-align: center;" align="right">نمایش ویدئو در حالت Landscape</div>
			</td>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="50%">tapsell_rotation_unlocked</td>
			<td width="50%">
				<div style="text-align: center;" align="right">
					<span style="font-family: inherit; font-size: inherit; text-align: center;">عدم قفل کردن چرخش گوشی</span>
				</div>
			</td>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="50%">tapsell_rotation_locked_reversed_portrait</td>
			<td width="50%">
				<div style="text-align: center;" align="right">
					<span style="font-family: inherit; font-size: inherit; text-align: center;">نمایش ویدئو در حالت reversed portrait</span>
				</div>
			</td>
		</tr>
		<tr style="background-color: #fefefe;">
			<td dir="ltr" width="50%">tapsell_rotation_locked_reversed_landscape</td>
			<td width="50%">
				<div style="text-align: center;" align="right">
					<span style="font-family: inherit; font-size: inherit; text-align: center;">نمایش ویدئو در حالت reversed landscape</span>
				</div>
			</td>
		</tr>
	</tbody>
</table>
&nbsp;
<h3>گام ۶: دریافت نتیجه نمایش تبلیغ</h3>
جهت دریافت نتیجه نمایش تبلیغات، باید یک تابع Callback برای ارسال نتایج دریافت تبلیغ به SDK تپسل ارسال کنید. برای این کار،
باید از تابع
<code>tapsell.setRewardCallback</code> استفاده نمایید.
<pre dir="ltr">tapsell.setRewardCallback(<span style="color: #003366;">function</span> (result){
    if(result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'onAdShowFinished'</span>)
    {
        console.log(<span style="color: #999999;">'showing ad was finished'</span>);
        zoneId = result[<span style="color: #999999;">'zoneId'</span>];
        adId = result[<span style="color: #999999;">'adId'</span>];
        completed = result[<span style="color: #999999;">'completed'</span>];
        rewarded = result[<span style="color: #999999;">'rewarded'</span>];
    }
});</pre> نتیجه نمایش تبلیغ در
<code>callback</code> داده شده بصورت یک تابع برگردانده می‌شود. در نتیجه دریافت شده،
<code>adId</code> و
<code>zoneId</code> شناسه مربوط به تبلیغ و محل نمایش آن در اپلیکیشن است. دو متغیر
<code>completed</code> و
<code>rewarded</code> از نوع
<code>boolean</code> بوده و نشان دهنده‌ی این است که کاربر ویدئو را تا انتها مشاهده کرده است یا خیر و تبلیغ نمایش داده شده از نوع جایزه‌دار بوده
است یا خیر. در صورتی که کاربر تبلیغی از نوع جایزه دار را تا انتها مشاهده کند و هردو مقدار
<code>completed</code> و
<code>rewarded</code> برابر با
<code>true</code> باشند، ، باید جایزه درون برنامه (سکه، اعتبار، بنزین یا ...) را به کاربر بدهید.
<h3>نمونه پیاده‌سازی</h3>
یک نمونه پیاده‌سازی تپسل در Cordova را می‌توانید در repository زیر مشاهده کنید.
<p style="text-align: center;">
	<a href="https://github.com/tapselladnet/TapsellSDK_v3_Cordova_Sample">
		<button>مشاهده پروژه نمونه</button>
	</a>
</p>
</div>

<!-- <div id="native-banner"></div> -->

<!-- <div id="native-video"></div> -->

<div id="standard-banner">
<h2>پیاده‌سازی تبلیغات بنری استاندارد در پروژه Cordova (اندروید)</h2>
جهت راه اندازی تبلیغات تپسل در اپلیکیشن شما، باید مراحل زیر را در پروژه خود انجام دهید. &nbsp;
<h3>گام ۱: افزودن پلاگین تپسل به پروژه cordova</h3>
ابتدا command prompt را باز کرده و به پوشه مربوط به پروژه خود بروید. سپس دستور زیر را در آن اجرا کنید.
<pre dir="ltr"> cordova plugin add tapsell-v3-cordova-plugin</pre> با انجام این دستور، پلاگین تپسل در پروژه شما افزوده می‌گردد. &nbsp;
<h3>گام ۲: دریافت کلید تپسل</h3>
وارد پنل مدیریت تپسل شده و با تعریف یک اپلیکیشن جدید با عنوان پکیج اپلیکیشن اندرویدی خود، یک کلید تپسل دریافت کنید.
<p style="text-align: center;">
	<a href="https://dashboard.tapsell.ir">
		<button>ورود به داشبورد تپسل</button>
	</a>
</p>

<h3></h3>
<h3>گام ۳: شروع کار با SDK تپسل</h3>
در گام دوم با ثبت برنامه خود در پنل تپسل، یک عبارت با عنوان کلید تپسل در اختیار شما قرار می‌گیر. برای استفاده از تپسل ابتدا
باید این کلید را به برنامه خود معرفی نمایید. لذا در بخش onDeviceReady فایل javascript خود، تابع زیر را با کلید تپسل برنامه
خود فراخوانی نمایید.
<pre dir="ltr">tapsell.initialize(appKey);</pre> ورودی
<code>appKey</code> کلید تپسلی است که در گام قبل از پنل تپسل دریافت کردید. &nbsp;
<h3>گام ۴: دریافت تبلیغ</h3>
جهت نمایش بنر استاندارد، باید محلی برای نمایش آن در صفحه در نظر بگیرید. بنر استاندارد، دارای سایزهای استانداردی است که در
SDK تپسل مشخص شده اند. جهت نمایش بنر، از تابع زیر استفاده کنید:
<pre dir="ltr">tapsell.requestBannerAd(zoneId, BannerType, horizontalGravity, verticalGravity);</pre> مقدار zoneId کلیدی ست که بعد از ساخت اپلیکیشن در پنل و ثبت یک zone از نوع بنری استاندارد دریافت میکنید.ورودی BannerType
اندازه های مختلف را بیان میکند و شامل مقادیر tapsell_banner_320x100 ,tapsell_banner_320x50 , tapsell_banner_300x250 , tapsell_banner_250x250
میباشد. ورودی horizontalGravity نشان میدهد که آیا تبلیغ، بالا یا پایین صفحه باشد و شامل tapsell_top, tapsell_bottom می باشد،
همچنین verticalGravity بیان میکند که تبلیغ از جهت عرضی در کجای صفحه باشد و میتواند شامل مقادیر tapsell_left , tapsell_right
, tapsell_center باشد. یک نمونه پیاده سازی کد به شکل زیر است:
<pre dir="ltr">tapsell.requestBannerAd("5a0041c8e995ee0001937574",tapsell_banner_320x50,tapsell_top,tapsell_center);</pre>
<div id="s3gt_translate_tooltip_mini" class="s3gt_translate_tooltip_mini_box" style="background: initial !important; border: initial !important; border-radius: initial !important; border-spacing: initial !important; border-collapse: initial !important; direction: ltr !important; flex-direction: initial !important; font-weight: initial !important; height: initial !important; letter-spacing: initial !important; min-width: initial !important; max-width: initial !important; min-height: initial !important; max-height: initial !important; margin: auto !important; outline: initial !important; padding: initial !important; position: absolute; table-layout: initial !important; text-align: initial !important; text-shadow: initial !important; width: initial !important; word-break: initial !important; word-spacing: initial !important; overflow-wrap: initial !important; box-sizing: initial !important; display: initial !important; color: inherit !important; font-size: 13px !important; font-family: X-LocaleSpecific,sans-serif,Tahoma,Helvetica !important; line-height: 13px !important; vertical-align: top !important; white-space: inherit !important; left: 752px; top: 1336px; opacity: 0.8;"></div>
</div>

<div id="advanced-options">
<h2>موارد پیشرفته‌تر در SDK تپسل (Cordova)</h2>
&nbsp;
<h3>بررسی وجود تبلیغ دریافت شده</h3>
در صورتیکه در SDK برای یک ناحیه درخواست دریافت تبلیغ از سرور انجام داده باشید، علاوه بر
<code>action</code> داده شده در تابع
<code>requestAd</code>، می‌توانید از تابع زیر نیز برای چک کردن وجود تبلیغ دریافت شده استفاده کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #000000;">tapsell</span>.isAdReadyToShow(zoneId, <em><span style="color: #008080;">function</span></em>(result){
    if(result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'isAdReadyToShow'</span>)
    {
        ready = result[<span style="color: #808080;">'isAdReady'</span>];
    }
});</pre> ورودی zoneId در این تابع، شناسه ناحیه نمایش تبلیغ است که از پنل تپسل دریافت نموده‌اید. &nbsp;
<h3>دریافت نسخه SDK تپسل</h3>
درصورتی که نیازمند به دانستن نسخه تپسل پیاده‌سازی شده در اپلیکیشن خود هستید، می‌توانید با فراخوانی تابع زیر عنوان نسخه را
دریافت نمایید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #000000;">tapsell</span>.getVersion( <em><span style="color: #008080;">function</span></em>(result){
    if(result[<span style="color: #999999;">'action'</span>]==<span style="color: #999999;">'getVersion'</span>)
    {
        console.log(<span style="color: #999999;">'tapsell version: '</span>+result[<span style="color: #999999;">'version'</span>]);
    }
});</pre> &nbsp;
<h3>تنظیمات کشینگ</h3>
همانطور که در بخش
<a href="#rewarded">پیاده‌سازی SDK تپسل در اپلیکیشن</a> گفته شد، از نسخه ۳ به بعد تپسل قابلیت نمایش ویدئو بصورت استریم و همینطور نمایش ویدئو
بعد از دانلود فایل (کشینگ) را دارد. با این قابلیت، قبل از نمایش تبلیغ و در هنگامی که کاربر مشغول استفاده از اپلیکیشن است،
ویدئو بطور کامل دریافت می‌شود و کاربر بدون هیچگونه مکثی می‌تواند ویدئو را تماشا کند. از طرف دیگر، در اپلیکیشن‌ها و بازی‌های
آنلاین، دریافت ویدئو در پس زمینه ممکن است در روند اصلی برنامه خلل ایجاد کند و آن را کند نماید. جهت جلوگیری از اشغال پهنای
باند زیاد توسط تپسل، شما می‌توانید درصد مشخصی از کل پهنای باند موجود را به دانلود ویدئو اختصاص دهید. جهت انجام این عمل، تابع
زیر را در آغاز برنامه و تابع
<code>initialize</code> (قبل از درخواست تبلیغ) فراخوانی کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #333333;">tapsell</span>.setMaxAllowedBandwidthUsagePercentage(maxPercentage);</pre> در این تابع، ورودی
<code>maxPercentage</code> حداکثر درصدی از پهنای باند در دسترس اپلیکیشن است که SDK تپسل از آن برای دریافت ویدئو استفاده می‌کند. این پارامتر باید عددی
بین 0 تا 100 باشد. همچنین درصورتی که از سرعت دانلود واقعی کاربر در اپلیکیشن خود اطلاع دارید می‌توانید به کمک تابع زیر، مقدار
حداکثر پهنای باند قابل استفاده برای دانلود ویدئو را به کمک تابع زیر تنظیم کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #333333;">tapsell</span>.setMaxAllowedBandwidthUsage(maxBpsSpeed)</pre> ورودی دوم این تابع، میزان حداکثر سرعت دانلود ویدئو است که باید به واحد بایت بر ثانیه داده شود. در صورتی که در بخشی از اپلیکیشن
خود می‌خواهید تنظیمات مربوط به محدودیت سرعت دانلود را غیرفعال نمایید، از تابع زیر استفاده کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #333333;">tapsell</span>.clearBandwidthUsageConstrains()</pre> &nbsp; توضیحات بیشتر درباره کشینگ و استریمینگ در SDK تپسل را
<a href="https://answers.tapsell.ir/?ht_kb=cached-vs-streamed">اینجا</a> بخوانید. &nbsp;
<h3>تنظیمات دسترسی‌های زمان اجرا (Run Time Permissions)</h3>
از نسخه اندروید 6 و بالاتر، برخی دسترسی‌ها در اندروید در زمان اجرا باید از کاربر درخواست شوند. یکی از این دسترسی‌ها، دسترسی
<code>READ_PHONE_STATE</code> است که توسط تپسل استفاده می‌شود و بدون این دسترسی، SDK تپسل قابل استفاده نیست. برای سهولت پیاده‌سازی، SDK تپسل بصورت اتوماتیک
دسترسی‌ها را از کاربر درخواست می‌کند و هربار درخواست تبلیغی ارسال شود، درصورتی که دسترسی مورد نیاز موجود نباشد، این دسترسی
از کاربر خواسته می شود. در صورتی که شما می‌خواهید درخواست دسترسی‌ها از کاربر را به نحو دیگری در اپلیکیشن خود پیاده‌سازی نمایید،
می‌توانید این ویژگی پیش‌فرض را در تپسل غیر فعال کنید. جهت انجام این عمل، کافیست از تابع زیر در زمان آغاز برنامه و بعد از
تابع
<code>initialize</code> استفاده کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #000000;">tapsell</span>.setAutoHandlePermissions (<span style="color: #33cccc;">false</span>);</pre> با این دستور، درخواست دسترسی توسط SDK تپسل به کاربر نشان داده نمی‌شود و می‌توانید بصورت مطلوب خود آن را پیاده‌سازی نمایید.
&nbsp;
<h3>حالت دیباگ (Debug Mode)</h3>
در هنگام پیاده‌سازی SDK، ممکن است بدلیل عدم رعایت نکات گفته شده و یا خطاهای دیگر، تبلیغات قابل دریافت و نمایش نباشند. حالت
دیباگ جهت تسهیل فرآیند عیب‌یابی در هنگام پیاده‌سازی تعبیه شده است. با فعالسازی این حالت، می‌توانید گزارش‌های لاگ نمایش داده
شده توسط SDK را در logcat مشاهده کنید. برای فعالسازی حالت دیباگ کافیست از تابع زیر در آغاز برنامه استفاده کنید.
<pre dir="ltr" style="margin: 0; line-height: 125%;"><span style="color: #000000;">tapsell</span>.setDebugMode (<span style="color: #33cccc;">true</span>);</pre> سپس با استفاده از نرم‌افزار Android Studio، از بخش Android Monitor، قسمت logcat را باز کرده و لاگ‌های نوشته شده را بررسی
کنید.
<p style="text-align: center;">
	<img src="https://answers.tapsell.ir/wp-content/uploads/2017/02/logs-cordova.png" />
</p>
</div>

</div>
