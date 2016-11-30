# bike-point

Demo web-page showing 5 nearest BikePoints:

* around Leyton
* having available bicycles
* with basic authentication

showing a minimalistic table with BikePoint
locations, distances from Leyton and number of available bikes

## Usage

`git clone https://github.com/icslabspace/bike-point`

To start the web server call `lein run` from the project's root

To stop it just hit Ctrl-C

After the server starts visit your favorite browser and enter [localhost:3000/Leyton](http://localhost:3000/Leyton)

If all goes well you should be asked for a username and password (use the ones bellow):

`username=icslab`

`password=www.icslab.eu`

## Technologies involved

[Leiningen](http://leiningen.org/) your standard Clojure project automation tool

[Hiccup](https://github.com/weavejester/hiccup) fast Clojure library for HTML rendering

[Yada](https://github.com/juxt/yada) a Clojure web API

[Bidi](https://github.com/juxt/bidi) a bidirectional URI routing Clojure lib

[Aleph](https://github.com/ztellman/aleph) asynch communication

## License

Copyright © 2016

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
