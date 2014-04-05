Secure Document Repository
90.308 Final Group Project
Group member : Jean-Robert Mimy

Objectives: - A repository to save Enterprise documents
            	
		Two modules:
			- Document Search tool:Documents Management
	    		- Document Access control: Security Administration
	
Database scripts

Create table logstbl (logcode varchar(8), errmessage varchar(100), user varchar(10), createdate timestamp);

Create table securedoctbl(doccode varchar(10), createdate date, modifieddate date, subject varchar(25), author varchar(10), information varchar(100), accesscontrol tinyint, size int);

Create table userdocrel(username varchar(10), doccode varchar(10), rights varchar(1));

Create table users(username varchar(10), password varchar(8), name varchar(50), homepath varchar(75), active tinyint, createby varchar(10), type varchar(6));

insert into securedoctbl(doccode, createdate, modifieddate, subject, author, information, accesscontrol, size) values('1002', '2014-03-20', '2014-03-20', 'The crocodiles', 'jmimy', 'The animals', 1, 24);

insert into userdocrel(username, doccode, rights) values('jmimy', '1002', 'x');

insert into users (username, password, active) values('jmimy','jmimy',1);


All Classes
Class name           	Type            	Test class
DocumentDAO		DAO Java class		
DocumentFactory		Java class		DocumentFactoryTest
SecureDocument		Java class		SecureDocumentTest
Users			Java class		UsersTest
UserFactory		Java class		UserFactoryTest
UserDocRel		Java class		UserDocRelTest

Documents		Interface		N/A
Profile			Interface		N/A

MessageFrame		Java Swing class	N/A
SecureDocumentFrame	Java Swing class	N/A
SignOnMainFrame		Java Swing class	N/A

Sign on info
Use those credentials to get access to the application
jmimy/jmimy
