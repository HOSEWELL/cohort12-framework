<!DOCTYPE html>
<html lang="en">
<jsp:useBean id="contactUs" class="app.model.ContactUs" />
<jsp:setProperty name="contactUs" property="*" />
<head>
    <meta charset="UTF-8">
    <title>Message Sent</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .card {
            background: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            text-align: center;
            max-width: 500px;
        }

        .icon {
            font-size: 50px;
            color: #28a745;
            margin-bottom: 20px;
        }

        h1 {
            color: #333;
            margin-bottom: 10px;
        }

        p {
            color: #555;
            margin-bottom: 20px;
        }

        a.button {
            display: inline-block;
            padding: 12px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 6px;
        }

        a.button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<div class="card">
    <h1>Thank You!</h1>
        Name: <jsp:getProperty name="contactUs" property="name" /><br/>
        Email: <jsp:getProperty name="contactUs" property="email" /><br/>
        Subject: <jsp:getProperty name="contactUs" property="subject" /><br/>
        Message: <jsp:getProperty name="contactUs" property="message" /><br/>
    <p>Your message has been successfully submitted. We will get back to you shortly.</p>

    <a href="index.html" class="button">Back to Home</a>
</div>

<jsp:include page="footer.jsp" />
</body>
</html>