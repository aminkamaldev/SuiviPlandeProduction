# SuiviPlanProduction
This application was generated using JHipster 4.0.3, you can find documentation and help at [https://jhipster.github.io/documentation-archive/v4.0.3](https://jhipster.github.io/documentation-archive/v4.0.3).

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:

1. [Node.js][]: We use Node to run a development web server and build the project.

2. [Yarn][]: We use Yarn to manage Node dependencies.


After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in `package.json`.

    yarn install

We use yarn scripts and [Webpack][] as our build system.


Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    yarn start

[Yarn][] is also used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in `package.json`. You can also run `yarn update` and `yarn install` to manage dependencies.
Add the `help` flag on any command to see how you can use it. For example, `yarn help update`.

The `yarn run` command will list all of the scripts available to run for this project.

### Managing dependencies

For example, to add [Leaflet][] library as a runtime dependency of your application, you would run following command:

    yarn add --exact leaflet

To benefit from TypeScript type definitions from [DefinitelyTyped][] repository in development, you would run following command:

    yarn add --dev --exact @types/leaflet

Then you would import the JS and CSS files specified in library's installation instructions so that [Webpack][] knows about them:

Edit `src/main/webapp/app/vendor.ts`file:
~~~
import 'leaflet/dist/leaflet.js';
~~~

Edit `src/main/webapp/content/css/vendor.css` file:
~~~
@import '~leaflet/dist/leaflet.css';
~~~

Note: there are still few other things remaining to do for Leaflet that we won't detail here.


### Using angular-cli

You can also use [Angular CLI][] to generate some custom client code.

For example, the following command:

    ng generate component my-component

will generate few files:

    create src/main/webapp/app/my-component/my-component.component.html
    create src/main/webapp/app/my-component/my-component.component.ts
    update src/main/webapp/app/app.module.ts

## Building for production

To optimize the SuiviPlanProduction application for production, run:

    ./mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.



## Testing

To launch your application's tests, run:

    ./mvnw clean test

### Client tests

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript/` and can be run with:

    yarn test


### Other tests

Performance tests are run by [Gatling][] and written in Scala. They're located in `src/test/gatling` and can be run with:

    ./mvnw gatling:execute

For more information, refer to the [Running tests page][].



[JHipster Homepage and latest documentation]: https://jhipster.github.io
[JHipster 4.0.3 archive]: https://jhipster.github.io/documentation-archive/v4.0.3

[Using JHipster in development]: https://jhipster.github.io/documentation-archive/v4.0.3/development/
[Using Docker and Docker-Compose]: https://jhipster.github.io/documentation-archive/v4.0.3/docker-compose
[Using JHipster in production]: https://jhipster.github.io/documentation-archive/v4.0.3/production/
[Running tests page]: https://jhipster.github.io/documentation-archive/v4.0.3/running-tests/
[Setting up Continuous Integration]: https://jhipster.github.io/documentation-archive/v4.0.3/setting-up-ci/

[Gatling]: http://gatling.io/
[Node.js]: https://nodejs.org/
[Yarn]: https://yarnpkg.org/
[Webpack]: https://webpack.github.io/
[Angular CLI]: https://cli.angular.io/
[BrowserSync]: http://www.browsersync.io/
[Karma]: http://karma-runner.github.io/
[Jasmine]: http://jasmine.github.io/2.0/introduction.html
[Protractor]: https://angular.github.io/protractor/
[Leaflet]: http://leafletjs.com/
[DefinitelyTyped]: http://definitelytyped.org/
