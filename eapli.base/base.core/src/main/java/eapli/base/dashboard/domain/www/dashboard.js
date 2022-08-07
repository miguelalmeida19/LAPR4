function refreshPersonalInfo() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("personalInformation");

    request.onload = function () {
        vBoard.innerHTML = this.responseText;
        setTimeout(refreshPersonalInfo, 100);
    };

    request.ontimeout = function () {
        vBoard.innerHTML = "Server timeout, still trying ...";
        setTimeout(refreshPersonalInfo, 100);
    };

    request.onerror = function () {
        vBoard.innerHTML = "No server reply, still trying ...";
        setTimeout(refreshPersonalInfo, 5000);
    };

    request.open("GET", "/personalInformation", true);
    request.timeout = 5000;
    request.send();
}

function paint(element) {
    element.id = 'columnn';
}

function unpaint(element) {
    element.id = 'aisle';
}

const colorShade = (col, amt) => {
    col = col.replace(/^#/, '')
    if (col.length === 3) col = col[0] + col[0] + col[1] + col[1] + col[2] + col[2]

    let [r, g, b] = col.match(/.{2}/g);
    ([r, g, b] = [parseInt(r, 16) + amt, parseInt(g, 16) + amt, parseInt(b, 16) + amt])

    r = Math.max(Math.min(255, r), 0).toString(16)
    g = Math.max(Math.min(255, g), 0).toString(16)
    b = Math.max(Math.min(255, b), 0).toString(16)

    const rr = (r.length < 2 ? '0' : '') + r
    const gg = (g.length < 2 ? '0' : '') + g
    const bb = (b.length < 2 ? '0' : '') + b

    return `#${rr}${gg}${bb}`
}

function showRows(num){
    var vBoard = document.getElementById("dashboard");
    const text = 'A' + num;
    const array = vBoard.getElementsByClassName(text);

    let amt = 0;
    let color = colorShade('#ff0000', amt);

    if (array.length > 0) {
        for (let j = 0; j < array.length; j++) {
            const text1 = 'R' + num + "_" + (j+1);
            const array1 = vBoard.getElementsByClassName(text1);
            for (const element of array1) {
                element.style.background = color;
                element.innerHTML = 'R' + (j+1);
                element.style.cursor = "pointer";
                element.onclick = function() { refreshDashboard(); };
            }
            amt = amt+70;
            color = colorShade('#ff0000', amt)
        }
    }
}


function refreshDashboard() {
    var request = new XMLHttpRequest();
    var vBoard = document.getElementById("dashboard");

    request.onload = function () {
        vBoard.innerHTML = this.responseText;
        let amt = 0;
        let color = colorShade('#A5A5A5FF', amt);

        for (let i = 0; i < vBoard.children.length; i++) {
            const text = 'A' + (i + 1);
            const array = vBoard.getElementsByClassName(text);
            if (array.length > 0) {
                for (const element of array) {
                    element.style.background = color;
                    element.style.cursor = "pointer";
                    element.onclick = function() { showRows((i + 1)); };
                }
            }
            amt = amt+20;
            color = colorShade('#A5A5A5FF', amt)
        }

        setTimeout(refreshDashboard, 100);
    };

    request.ontimeout = function () {
        vBoard.innerHTML = "Server timeout, still trying ...";
        setTimeout(refreshDashboard, 500);
    };

    request.onerror = function () {
        vBoard.innerHTML = "No server reply, still trying ...";
        setTimeout(refreshDashboard, 5000);
    };

    request.open("GET", "/refreshDashboard", true);
    request.timeout = 500;
    request.send();
}