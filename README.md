This is a Cash app that displays list of Stock names and its corresponding prices.
Build and run on an emulator. The data is displayed in a list.

User Interface of the app is done using Jetpack Compose. Recomposition happens only for the UI portion whose data gets updated. Hence performance is better.
The list of the data is displayed using a lazy column. Handles displaying large lists of data effectively
The JSON data is fetched using Retrofit library which makes it easy to fetch remote data.
Two data classes have been defined for Stocks and Stock data structure.
Malformed json data is handled using try catch block.
A retrofit service is defined which has suspend functions to get json data from the url.
A customized view model factory is created which passes the retrofit service object to a view model class.
A view model class is created to store that so that it can persist through configurations changes. 
Function in view model scope in view model class is used to fetch the data from retrofit service suspend functions
and the response is emitted to a mutable live data using a coroutine live data builder.
The live data is then observed in Jetpack Compose and the list is updated based on the value.

Unit tests are written to test the retrofit service using Mock Web Server
