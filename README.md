<img src="https://travis-ci.org/ignl/happyfacescrud.svg?branch=master" />

<b>Quick startup</b><br/>
<code>
mvn archetype:generate -DarchetypeGroupId=com.github.happyfaces -DarchetypeArtifactId=happyfaces-archetype -DarchetypeVersion=1.0.2 -DgroupId=com.test -DartifactId=MyTestApp
</code>

<b>Tutorial</b><br/>
http://intelligentjava.wordpress.com/2014/07/11/happyfaces-archetype-tutorial-easy-jsf-with-custom-components/

<b>More info</b><br/>
https://github.com/ignl/happyfacescrud/wiki

<b>Features</b><br/>

  * Setup Spring and JSF project with all configuration. Saves a lot of work for the project startup.
  * Quite some Jsf composite components which works well with standard backing beans and allows to create crud pages in very fast and easy way without loosing flexibility and ability to customize to your requirements (which is often a trade off with code generation tools).
  * Jsf composite components are easily accessible and can be modified themselves (add new parameter or change logic) or can be used as example to start new components without any additional work on configuration etc.
  * Spring data and Query DSL setup.
  * Dynamic query for search which means no need to write your own query for each different set of search fields.
  * Spring security setup and simple JPA login/logout implementation.
  * Change password functionality.
  * Unit/integration tests setup and some tests created.
  * Lazy Primefaces datatable with pagination and sorting setup with solved issues about working with columns that are in <a href=http://code.google.com/p/primefaces/issues/detail?id=2930>taglibs</a>.
  * Spring REST WS setup.
  * File upload setup.
  * Generic converters for all enums and all entities so no need to create a new ones.
  * Maven site and plugins configuration. Reports like findbugs, checkstyle, pmd. Code has 0 issues for findbugs/checkstyle/pmd.
  * Other small things like clear button component with <a href=http://balusc.blogspot.com/2012/03/reset-non-processed-input-components-on.html>Omnifaces</a>, easy JPA entity properties fetching from db etc..
