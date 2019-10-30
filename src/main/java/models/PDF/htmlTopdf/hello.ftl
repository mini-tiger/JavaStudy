<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>
    <style>
        @page{ size: 11.69in 8.27in;}
        body{
            font-family:SimHei;
        }
        .blue{
            color: blue;
        }
    </style>
</head>

<body>
${key}
<table >
    <#if hello?exists>
                <#list hello?keys as key>
                   <tr>
                       <td>${key}</td>
                       <td>${hello[key]} </td>
                   </tr>
                </#list>
    </#if>

</table>
</body>

</html>


