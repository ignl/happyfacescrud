#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
Install mysql and create database for your project.
Edit filter-dev.properties and add details of your database.
Add to tomcat, start it and go to http://localhost:8080/{projectName}/login.jsf
User/password: admin/admin.

Import.sql on startup is used for sample data population.

To create maven site with javadocs, source code, findbugs, checkstyle etc run mvn site:run (url: http://localhost:port/)

Currently archetype creates project with sample application. Reason for that is that I have no time to write documentation, and example
is always good learning tool.
To remove current sample application:
Delete Customer.java, CustomerPerk.java, Account.java, Contacts.java, Operation.java, OperationType.java, City.java from domain package
Delete CustomerBean.java, AccountBean.java, OperationBean.java, CityBean.java from beans package
Delete AccountService.java, CustomerService.java, OperationService.java, CityService.java, 
       IAccountService.java, ICustomerService.java, IOperationService.java, ICityService.java from services package
Delete AccountRepository.java, CustomerRepository.java, OperationRepository.java, CityRepository.java from repositories package
Delete accountEdit.xhtml, accounts.xhtml, operationEdit.xhtml, operations.xhtml, customerEdit.xhtml, customers.xhtml from src/main/webapp/pages/
Delete entries from demoDatabase.xml(if u use it) and import.sql
Change menu.xhtml
Remove messages from messages.properties
Remove faces-config.xml navigation rules.

After those steps you are good to go further with your own application without any sight of demo files.