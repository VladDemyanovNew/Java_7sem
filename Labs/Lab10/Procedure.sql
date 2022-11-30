USE DVR_UNIVER;
GO

CREATE PROCEDURE GET_TEACHER_NAME @teacher CHAR(10), @name VARCHAR(100) OUTPUT AS
BEGIN
	SELECT @name = TEACHER_NAME FROM TEACHER
	WHERE TEACHER = @teacher;
END;
GO

DECLARE @result VARCHAR(100);

EXEC GET_TEACHER_NAME '', @result OUTPUT;

PRINT @result;