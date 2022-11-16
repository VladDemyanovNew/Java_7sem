<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "stafftag.tld" prefix ="staff" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;
 charset=ISO-8859-1" />
    <title>Dossier</title>
</head>
<body>
<staff:dossier action="Ttt" >
    <br />
    <staff:custom-input
        value="Ruslanovich"
        label="Surname"
        type="text"
        name="surname"
        styles="width=\"150\";" />
    <br />
    <br />
    <staff:custom-input
        value="Demyanov"
        label="Lastname"
        type="text"
        name="lastname"
        styles="width=\"150\"" />
    <br />
    <br />
    <fieldset id="sex" style="border: none; margin: 0">
        <staff:custom-input
                value="Male"
                label="Male"
                type="radio"
                name="sex"
                styles="width=\"150\""/>
        <staff:custom-input
                value="Female"
                label="Female"
                type="radio"
                name="sex"
                styles="width=\"150\""/>
    </fieldset>
    <staff:custom-input
            value="OK"
            type="submit"
            styles="width=\"150\"" />
    <staff:custom-input
            value="Cancel"
            type="reset"
            styles="width=\"150\"" />
</staff:dossier>
</body>
</html>