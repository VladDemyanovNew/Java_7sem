USE master;
GO

CREATE DATABASE UWSR;
GO

USE UWSR;
GO

CREATE TABLE [References]
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[Url] NVARCHAR(4000) NOT NULL,
	[Description] NVARCHAR(4000),
	Minus int,
	Plus int
);

CREATE TABLE Comments 
(
	Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	ReferenceId INT NOT NULL FOREIGN KEY REFERENCES [References](Id) ON DELETE CASCADE,
	SessionId INT,
	Stamp Date,
	[Text] NVARCHAR(4000)
);

INSERT INTO [References] ([Url], [Description], Minus, Plus)
VALUES('test', 'test', 1, 1);