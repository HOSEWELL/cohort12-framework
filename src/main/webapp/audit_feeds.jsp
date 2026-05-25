<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Activity Feed</title>

    <style>
        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family: Arial, sans-serif;
        }

        body{
            background:#f4f6f9;
            padding:40px;
        }

        .feed-container{
            max-width:700px;
            margin:auto;
        }

        .feed-title{
            font-size:28px;
            font-weight:bold;
            margin-bottom:25px;
            color:#1f2937;
        }

        .activity-card{
            background:#fff;
            border-radius:14px;
            padding:18px;
            margin-bottom:18px;
            display:flex;
            gap:15px;
            align-items:flex-start;
            box-shadow:0 2px 8px rgba(0,0,0,0.06);
            transition:0.3s;
        }

        .activity-card:hover{
            transform:translateY(-2px);
            box-shadow:0 6px 16px rgba(0,0,0,0.08);
        }

        .activity-icon{
            width:50px;
            height:50px;
            border-radius:50%;
            display:flex;
            align-items:center;
            justify-content:center;
            font-size:22px;
            color:#fff;
            flex-shrink:0;
        }

        .bg-blue{
            background:#2563eb;
        }

        .bg-green{
            background:#16a34a;
        }

        .bg-orange{
            background:#ea580c;
        }

        .bg-purple{
            background:#7c3aed;
        }

        .activity-content{
            flex:1;
        }

        .activity-header{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:8px;
        }

        .activity-user{
            font-weight:bold;
            color:#111827;
        }

        .activity-time{
            font-size:13px;
            color:#6b7280;
        }

        .activity-text{
            color:#374151;
            line-height:1.6;
            margin-bottom:10px;
        }

        .activity-tag{
            display:inline-block;
            padding:5px 10px;
            border-radius:20px;
            font-size:12px;
            background:#eef2ff;
            color:#4338ca;
            font-weight:bold;
        }

        @media(max-width:600px){
            body{
                padding:20px;
            }

            .activity-header{
                flex-direction:column;
                align-items:flex-start;
                gap:4px;
            }
        }
    </style>
</head>
<body>
    <div class="feed-container" id ="feed-container">
        <div class="feed-title">Recent Activities</div>
    </div>
    <script>
        const socket = new WebSocket("ws://localhost:8080/cohort12/audit_feeds");

        socket.onopen = function() {
            console.log("Connected!!!!!");
        }

        socket.onmessage = function(event){

            const feedContainer = document.getElementById("feed-container");
            feedContainer.innerHTML += `<div class="activity-card">
                    <div class="activity-content">
                        <div class="activity-text">
                            ${event.data}
                        </div>
                    </div>
                </div>`;

        }

        socket.onclose = function() {
            console.log("Connection closed!!!!!");
        }

    </script>

</body>
</html>