 function formatJSON(jsonObj) {
                let htmlOutput = "<ul>";

                for (let key in jsonObj) {
                    if (jsonObj.hasOwnProperty(key)) {
                        htmlOutput += "<li><strong>" + key + ":</strong> ";

                         if (key === "Poster" && typeof jsonObj[key] === "string") {
                            htmlOutput += "<img src='" + jsonObj[key] + "' alt='Poster'>";
                         } else if (typeof jsonObj[key] === "object") {
                            htmlOutput += formatJSON(jsonObj[key]);
                         } else {
                            htmlOutput += jsonObj[key];
                         }

                    htmlOutput += "</li>";
                    }
                }

                htmlOutput += "</ul>";
                return htmlOutput;
            }
 function loadPostMsg() {
    let message = document.getElementById("msg").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        try {
            response = JSON.parse(this.responseText);
            const formattedOutput = formatJSON(response);
            document.getElementById("getrespmsg").innerHTML = "<pre>" + formattedOutput + "</pre>";
        } catch (error) {
            document.getElementById("getrespmsg").innerHTML =
            this.responseText;
        }
    };
    xhttp.open("POST", "/log");
    xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    xhttp.send(message);
 }